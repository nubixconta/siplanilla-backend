package sv.edu.ues.fia.siplanilla_backend.modules.auth.service;

import sv.edu.ues.fia.siplanilla_backend.modules.auth.dto.request.LoginRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.auth.dto.request.RegisterRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.auth.dto.response.AuthResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.auth.dto.response.AccountStatusDto;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.entity.Empleado;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.repository.EmpleadoRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity.Usuario;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.repository.UsuarioRepository;
import sv.edu.ues.fia.siplanilla_backend.exception.BusinessException;
import sv.edu.ues.fia.siplanilla_backend.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final EmpleadoRepository empleadoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final DesbloqueoService desbloqueoService;

    @Transactional
    public AuthResponse login(LoginRequest loginRequest) {
        try {
            // 1. Validar que el usuario no está bloqueado
            desbloqueoService.validarBloqueado(loginRequest.getUsername());

            // 2. Intentar autenticar
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            Usuario usuario = usuarioRepository.findByUsuUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new BusinessException("Usuario no encontrado"));

            // 3. Resetear intentos fallidos en login exitoso
            desbloqueoService.resetearIntentosFallidos(loginRequest.getUsername());

            String token = jwtProvider.generateToken(usuario);
            Long expiresIn = jwtProvider.getExpirationTime();

            AuthResponse.UsuarioDto usuarioDto = AuthResponse.UsuarioDto.builder()
                    .id(usuario.getIdUsuario())
                    .username(usuario.getUsuUsername())
                    .email(usuario.getUsuCorreo())
                    .build();

            return AuthResponse.builder()
                    .accessToken(token)
                    .tokenType("Bearer")
                    .expiresIn(expiresIn)
                    .user(usuarioDto)
                    .build();

        } catch (Exception e) {
            // 4. Registrar intento fallido
            try {
                desbloqueoService.registrarIntentoFallido(loginRequest.getUsername());
            } catch (Exception ex) {
                log.warn("No se pudo registrar intento fallido: {}", ex.getMessage());
            }
            log.error("Error al autenticar usuario: {}", e.getMessage());
            throw new BusinessException("Credenciales inválidas");
        }
    }

    @Transactional
    public AuthResponse register(RegisterRequest registerRequest) {
        if (usuarioRepository.existsByUsuUsername(registerRequest.getUsername())) {
            throw new BusinessException("El usuario ya existe");
        }

        if (usuarioRepository.existsByUsuCorreo(registerRequest.getEmail())) {
            throw new BusinessException("El email ya está registrado");
        }


        Empleado empleado = null;
        if (registerRequest.getIdEmpleado() != null) {
            empleado = empleadoRepository.findById(registerRequest.getIdEmpleado())
                    .orElseThrow(() -> new BusinessException("Empleado no encontrado con id: "
                            + registerRequest.getIdEmpleado()));
        }

        Usuario usuario = Usuario.builder()
                .usuUsername(registerRequest.getUsername())
                .usuCorreo(registerRequest.getEmail())
                .usuPassword(passwordEncoder.encode(registerRequest.getPassword()))
                .usuEstado(true)
                .empleado(empleado) // ← NUEVO
                .build();

        usuario = usuarioRepository.save(usuario);

        String token = jwtProvider.generateToken(usuario);
        Long expiresIn = jwtProvider.getExpirationTime();

        AuthResponse.UsuarioDto usuarioDto = AuthResponse.UsuarioDto.builder()
                .id(usuario.getIdUsuario())
                .username(usuario.getUsuUsername())
                .email(usuario.getUsuCorreo())
                .build();

        return AuthResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .expiresIn(expiresIn)
                .user(usuarioDto)
                .build();
    }

    /**
     * Verificar el estado de una cuenta sin requerir contraseña.
     * Útil para que el frontend sepa si debe mostrar formulario de desbloqueo.
     * 
     * Mapeo de valores:
     * usu_bloqueado = 0 (BD) → bloqueado: false (JSON)
     * usu_bloqueado = 1 (BD) → bloqueado: true (JSON)
     */
    public AccountStatusDto verificarEstadoCuenta(String username) {
        var usuario = usuarioRepository.findByUsuUsername(username);

        if (usuario.isEmpty()) {
            return AccountStatusDto.builder()
                    .username(username)
                    .existe(false)
                    .bloqueado(false)
                    .activo(false)
                    .mensaje("Usuario no encontrado")
                    .build();
        }

        Usuario u = usuario.get();
        boolean estaActivo = u.getUsuEstado() != null && u.getUsuEstado();
        boolean estaBloqueado = u.getUsuBloqueado() != null && u.getUsuBloqueado();

        log.debug("Usuario {} - BD bloqueado: {}, Convertido a: {}", 
                username, u.getUsuBloqueado(), estaBloqueado);

        return AccountStatusDto.builder()
                .username(username)
                .existe(true)
                .bloqueado(estaBloqueado)
                .activo(estaActivo)
                .mensaje(estaBloqueado ? "Cuenta bloqueada. Solicita desbloqueo." 
                        : (estaActivo ? "Cuenta activa. Puedes iniciar sesión." 
                           : "Cuenta inactiva."))
                .build();
    }
}

