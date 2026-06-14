package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.edu.ues.fia.siplanilla_backend.exception.ResourceNotFoundException;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.request.TipoIngresoRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.response.TipoIngresoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.entity.TipoIngreso;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.mapper.TipoIngresoMapper;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.repository.TipoIngresoRepository;

import java.util.List;

// PATRÓN DE REFERENCIA
@Service
@RequiredArgsConstructor
public class TipoIngresoServiceImpl implements TipoIngresoService {

    private final TipoIngresoRepository repository;
    private final TipoIngresoMapper mapper;

    @Override
    public List<TipoIngresoResponse> findAll(String search) {
        List<TipoIngreso> list = (search == null || search.isBlank())
                ? repository.findAll()
                : repository.findByTigNombreContainingIgnoreCase(search);
        return mapper.toResponseList(list);
    }

    @Override
    public TipoIngresoResponse findById(Long id) {
        TipoIngreso entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoIngreso", id));
        return mapper.toResponse(entity);
    }

    @Override
    public TipoIngresoResponse create(TipoIngresoRequest request) {
        TipoIngreso entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public TipoIngresoResponse update(Long id, TipoIngresoRequest request) {
        TipoIngreso entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoIngreso", id));
        entity.setTigNombre(request.getTigNombre());
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("TipoIngreso", id);
        }
        repository.deleteById(id);
    }
}
