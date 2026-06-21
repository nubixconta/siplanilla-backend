package sv.edu.ues.fia.siplanilla_backend.modules.auth.service;

import sv.edu.ues.fia.siplanilla_backend.exception.BusinessException;
import sv.edu.ues.fia.siplanilla_backend.exception.AccountLockedException;
import sv.edu.ues.fia.siplanilla_backend.modules.auth.entity.DesbloqueoToken;
import sv.edu.ues.fia.siplanilla_backend.modules.auth.repository.DesbloqueoTokenRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity.Usuario;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DesbloqueoService {

    private final DesbloqueoTokenRepository desbloqueoTokenRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;
    private final EntityManager entityManager;

    @Value("${app.desbloqueo.max-intentos:3}")
    private Integer maxIntentos;

    @Value("${app.desbloqueo.token-expiration-minutes:15}")
    private Integer tokenExpirationMinutes;

    /**
     * Verificar intentos fallidos y bloquear si es necesario
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void registrarIntentoFallido(String username) {
        Usuario usuario = usuarioRepository.findByUsuUsername(username)
                .orElseThrow(() -> new BusinessException("Usuario no encontrado"));

        Integer intentoActual = usuario.getUsuIntentosFallidos() != null ? usuario.getUsuIntentosFallidos() : 0;
        intentoActual++;

        usuario.setUsuIntentosFallidos(intentoActual);

        if (intentoActual >= maxIntentos) {
            usuario.setUsuBloqueado(true);
            usuario.setUsuFechaBloqueo(LocalDateTime.now());
            usuarioRepository.save(usuario);
            entityManager.flush();

            // Generar token y enviar correo de desbloqueo
            generarTokenDesbloqueo(usuario);
            log.warn("Usuario {} bloqueado después de {} intentos fallidos", username, intentoActual);
        } else {
            usuarioRepository.save(usuario);
            entityManager.flush();
            log.info("Usuario {} tiene {} intentos fallidos", username, intentoActual);
        }
    }

    /**
     * Generar token de desbloqueo y enviar correo
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void generarTokenDesbloqueo(Usuario usuario) {
        // Generar token único
        String token = UUID.randomUUID().toString();
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime expiracion = ahora.plusMinutes(tokenExpirationMinutes);

        // Guardar token en BD
        DesbloqueoToken desbloqueo = DesbloqueoToken.builder()
                .tokValor(token)
                .idUsuario(usuario.getIdUsuario())
                .tokTipo("DESBLOQUEO")
                .tokFechaExp(expiracion)
                .tokUsado(0)
                .build();

        desbloqueoTokenRepository.save(desbloqueo);
        entityManager.flush();

        // Enviar correo
        String nombreUsuario = usuario.getEmpleado() != null ?
                usuario.getEmpleado().getEmpNombre() + " " + usuario.getEmpleado().getEmpApellido() :
                usuario.getUsuUsername();

        emailService.enviarCorreoDesbloqueo(usuario.getUsuCorreo(), nombreUsuario, token);
    }

    /**
     * Solicitar desbloqueo (endpoint público)
     */
    @Transactional
    public void solicitarDesbloqueo(String username) {
        Usuario usuario = usuarioRepository.findByUsuUsername(username)
                .orElseThrow(() -> new BusinessException("Usuario no encontrado"));

        if (!usuario.getUsuBloqueado()) {
            throw new RuntimeException("La cuenta no está bloqueada");
        }

        // Verificar si ya hay un token válido
        var tokenActivo = desbloqueoTokenRepository.findActiveTokenByUsuario(usuario.getIdUsuario());
        if (tokenActivo.isPresent()) {
            throw new RuntimeException("Ya existe un token de desbloqueo activo. Revisa tu correo.");
        }

        // Generar nuevo token
        generarTokenDesbloqueo(usuario);
    }

    /**
     * Confirmar desbloqueo usando el token
     */
    @Transactional
    public String confirmarDesbloqueo(String token) {
        DesbloqueoToken desbloqueo = desbloqueoTokenRepository.findByTokValor(token)
                .orElseThrow(() -> new BusinessException("Token inválido o expirado"));

        // Validar que no esté ya utilizado
        if (desbloqueo.getTokUsado() == 1) {
            throw new BusinessException("Este token ya fue utilizado");
        }

        // Validar que no esté expirado
        if (LocalDateTime.now().isAfter(desbloqueo.getTokFechaExp())) {
            throw new BusinessException("Token expirado");
        }

        // Desbloquear usuario
        Usuario usuario = usuarioRepository.findById(desbloqueo.getIdUsuario())
                .orElseThrow(() -> new BusinessException("Usuario no encontrado"));

        usuario.setUsuBloqueado(false);
        usuario.setUsuIntentosFallidos(0);
        usuario.setUsuFechaBloqueo(null);
        usuarioRepository.save(usuario);
        entityManager.flush(); // Asegurar que se guarden los cambios en la BD

        // Marcar token como utilizado
        desbloqueo.setTokUsado(1);
        desbloqueoTokenRepository.save(desbloqueo);
        entityManager.flush();

        // Enviar correo de confirmación
        String nombreUsuario = usuario.getEmpleado() != null ?
                usuario.getEmpleado().getEmpNombre() + " " + usuario.getEmpleado().getEmpApellido() :
                usuario.getUsuUsername();
        emailService.enviarCorreoDesbloqueoExitoso(usuario.getUsuCorreo(), nombreUsuario);

        log.info("Usuario {} desbloqueado exitosamente", usuario.getUsuUsername());
        return "Cuenta desbloqueada exitosamente. Ya puedes iniciar sesión.";
    }

    /**
     * Resetear intentos fallidos en login exitoso
     */
    @Transactional
    public void resetearIntentosFallidos(String username) {
        Usuario usuario = usuarioRepository.findByUsuUsername(username)
                .orElseThrow(() -> new BusinessException("Usuario no encontrado"));

        usuario.setUsuIntentosFallidos(0);
        usuarioRepository.save(usuario);
        entityManager.flush();
    }

    /**
     * Validar si usuario está bloqueado
     */
    public void validarBloqueado(String username) {
        Usuario usuario = usuarioRepository.findByUsuUsername(username)
                .orElseThrow(() -> new BusinessException("Usuario no encontrado"));

        if (usuario.getUsuBloqueado() != null && usuario.getUsuBloqueado()) {
            throw new AccountLockedException(
                "Cuenta bloqueada. Revisa tu correo para desbloquearla con el link que te enviamos.",
                username
            );
        }
    }
}
