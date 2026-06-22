package sv.edu.ues.fia.siplanilla_backend.modules.seguridad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sv.edu.ues.fia.siplanilla_backend.exception.BusinessException;
import sv.edu.ues.fia.siplanilla_backend.exception.ResourceNotFoundException;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.entity.Empleado;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.repository.EmpleadoRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.dto.CrearUsuarioRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.dto.UsuarioResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity.Rol;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity.Usuario;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity.UsuarioRol;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity.UsuarioRolId;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.repository.RolRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.repository.UsuarioRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.repository.UsuarioRolRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioRolRepository usuarioRolRepository;
    private final RolRepository rolRepository;
    private final EmpleadoRepository empleadoRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponse> findAll() {
        return usuarioRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UsuarioResponse crear(CrearUsuarioRequest request) {
        if (usuarioRepository.existsByUsuUsername(request.getUsername())) {
            throw new BusinessException("El username ya está en uso");
        }
        if (usuarioRepository.existsByUsuCorreo(request.getEmail())) {
            throw new BusinessException("El email ya está registrado");
        }

        Empleado empleado = empleadoRepository.findById(request.getIdEmpleado())
                .orElseThrow(() -> new ResourceNotFoundException("Empleado", request.getIdEmpleado()));

        Rol rol = rolRepository.findById(request.getIdRol())
                .orElseThrow(() -> new ResourceNotFoundException("Rol", request.getIdRol()));

        Usuario usuario = Usuario.builder()
                .usuUsername(request.getUsername())
                .usuCorreo(request.getEmail())
                .usuPassword(passwordEncoder.encode(request.getPassword()))
                .usuEstado(true)
                .usuBloqueado(false)
                .usuIntentosFallidos(0)
                .empleado(empleado)
                .build();

        usuario = usuarioRepository.save(usuario);

        UsuarioRol usuarioRol = UsuarioRol.builder()
                .id(new UsuarioRolId(usuario.getIdUsuario(), rol.getIdRol()))
                .usuario(usuario)
                .rol(rol)
                .build();

        usuarioRolRepository.save(usuarioRol);

        return toResponse(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponse cambiarEstado(Long id, Boolean estado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", id));

        usuario.setUsuEstado(estado);
        usuario = usuarioRepository.save(usuario);
        return toResponse(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponse cambiarRol(Long id, Long idRol) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", id));

        Rol rol = rolRepository.findById(idRol)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", idRol));

        usuarioRolRepository.deleteByUsuarioId(id);

        UsuarioRol nuevoRol = UsuarioRol.builder()
                .id(new UsuarioRolId(usuario.getIdUsuario(), rol.getIdRol()))
                .usuario(usuario)
                .rol(rol)
                .build();

        usuarioRolRepository.save(nuevoRol);

        return toResponse(usuario);
    }

    private UsuarioResponse toResponse(Usuario usuario) {
        List<String> roles = usuarioRolRepository.findByUsuarioWithRol(usuario)
                .stream()
                .map(ur -> ur.getRol().getRolNombre())
                .collect(Collectors.toList());

        String nombreEmpleado = null;
        Long idEmpleado = null;
        if (usuario.getEmpleado() != null) {
            idEmpleado = usuario.getEmpleado().getIdEmpleado();
            nombreEmpleado = usuario.getEmpleado().getEmpNombre()
                    + " " + usuario.getEmpleado().getEmpApellido();
        }

        return UsuarioResponse.builder()
                .id(usuario.getIdUsuario())
                .username(usuario.getUsuUsername())
                .email(usuario.getUsuCorreo())
                .estado(usuario.getUsuEstado())
                .bloqueado(usuario.getUsuBloqueado() != null && usuario.getUsuBloqueado())
                .roles(roles)
                .idEmpleado(idEmpleado)
                .nombreEmpleado(nombreEmpleado)
                .build();
    }
}
