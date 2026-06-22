package sv.edu.ues.fia.siplanilla_backend.modules.planilla.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sv.edu.ues.fia.siplanilla_backend.exception.BusinessException;
import sv.edu.ues.fia.siplanilla_backend.exception.ResourceNotFoundException;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.response.DetallePlanillaResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.entity.DetallePlanilla;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.mapper.DetallePlanillaMapper;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.repository.DetallePlanillaRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity.Usuario;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.repository.UsuarioRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetallePlanillaServiceImpl implements DetallePlanillaService {

    private final DetallePlanillaRepository repository;
    private final DetallePlanillaMapper mapper;
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<DetallePlanillaResponse> findAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public DetallePlanillaResponse findById(Long id) {
        DetallePlanilla entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("DetallePlanilla", id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetallePlanillaResponse> findByUsernameEmpleado(String username) {
        Usuario usuario = usuarioRepository.findByUsuUsername(username)
                .orElseThrow(() -> new BusinessException("Usuario no encontrado: " + username));

        if (usuario.getEmpleado() == null) {
            return List.of();
        }

        Long idEmpleado = usuario.getEmpleado().getIdEmpleado();
        return mapper.toResponseList(repository.findByEmpleadoIdEmpleado(idEmpleado));
    }
}
