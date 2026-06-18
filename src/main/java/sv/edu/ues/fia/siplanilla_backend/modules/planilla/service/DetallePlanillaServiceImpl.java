package sv.edu.ues.fia.siplanilla_backend.modules.planilla.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.edu.ues.fia.siplanilla_backend.exception.ResourceNotFoundException;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.response.DetallePlanillaResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.entity.DetallePlanilla;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.mapper.DetallePlanillaMapper;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.repository.DetallePlanillaRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DetallePlanillaServiceImpl implements DetallePlanillaService {

    private final DetallePlanillaRepository repository;
    private final DetallePlanillaMapper mapper;

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
}