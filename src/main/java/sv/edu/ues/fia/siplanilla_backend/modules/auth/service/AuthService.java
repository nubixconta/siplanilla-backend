package sv.edu.ues.fia.siplanilla_backend.modules.auth.service;

import sv.edu.ues.fia.siplanilla_backend.modules.auth.dto.request.LoginRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.auth.dto.request.RegisterRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.auth.dto.response.AuthResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.entity.Empleado;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.repository.EmpleadoRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity.Usuario;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.repository.UsuarioRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.repository.UsuarioRolRepository;
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

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final EmpleadoRepository empleadoRepository;
    private final UsuarioRolRepository usuarioRolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final DesbloqueoService desbloqueoService;

    @Transactional
    public AuthResponse login(LoginRequest loginRequest) {
        try {
            desbloqueoService.validarBloqueado(loginRequest.getUsername());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            Usuario usuario = usuarioRepository.findByUsuUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new BusinessException("Usuario no encontrado"));

            desbloqueoService.resetearIntentosFallidos(loginRequest.getUsername());

            List<String> roles = usuarioRolRepository.findByUsuarioWithRol(usuario)
                    .stream()
                    .map(ur -> ur.getRol().getRolNombre())
                    .collect(Collectors.toList());

            Long idEmpleado = usuario.getEmpleado() != null
                    ? usuario.getEmpleado().getIdEmpleado()
                    : null;

            String token = jwtProvider.generateToken(usuario, roles);
            Long expiresIn = jwtProvider.getExpirationTime();

            AuthResponse.UsuarioDto usuarioDto = AuthResponse.UsuarioDto.builder()
                    .id(usuario.getIdUsuario())
                    .username(usuario.getUsuUsername())
                    .email(usuario.getUsuCorreo())
                    .roles(roles)
                    .idEmpleado(idEmpleado)
                    .build();

            return AuthResponse.builder()
                    .accessToken(token)
                    .tokenType("Bearer")
                    .expiresIn(expiresIn)
                    .user(usuarioDto)
                    .build();

        } catch (Exception e) {
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

        Empleado empleado = empleadoRepository.findById(registerRequest.getIdEmpleado())
                .orElseThrow(() -> new BusinessException("Empleado no encontrado con id: "
                        + registerRequest.getIdEmpleado()));

        Usuario usuario = Usuario.builder()
                .usuUsername(registerRequest.getUsername())
                .usuCorreo(registerRequest.getEmail())
                .usuPassword(passwordEncoder.encode(registerRequest.getPassword()))
                .usuEstado(true)
                .empleado(empleado)
                .build();

        usuario = usuarioRepository.save(usuario);

        // Al registrar no hay roles asignados aún — el admin los asigna después
        List<String> roles = usuarioRolRepository.findByUsuarioWithRol(usuario)
                .stream()
                .map(ur -> ur.getRol().getRolNombre())
                .collect(Collectors.toList());

        Long idEmpleado = usuario.getEmpleado() != null
                ? usuario.getEmpleado().getIdEmpleado()
                : null;

        String token = jwtProvider.generateToken(usuario, roles);
        Long expiresIn = jwtProvider.getExpirationTime();

        AuthResponse.UsuarioDto usuarioDto = AuthResponse.UsuarioDto.builder()
                .id(usuario.getIdUsuario())
                .username(usuario.getUsuUsername())
                .email(usuario.getUsuCorreo())
                .roles(roles)
                .idEmpleado(idEmpleado)
                .build();

        return AuthResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .expiresIn(expiresIn)
                .user(usuarioDto)
                .build();
    }
}
