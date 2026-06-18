package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.edu.ues.fia.siplanilla_backend.exception.ResourceNotFoundException;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.request.TipoDescuentoRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.response.TipoDescuentoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.entity.TipoDescuento;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.mapper.TipoDescuentoMapper;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.repository.TipoDescuentoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoDescuentoServiceImpl implements TipoDescuentoService {

    private final TipoDescuentoRepository repository;
    private final TipoDescuentoMapper mapper;

    @Override
    public List<TipoDescuentoResponse> findAll(String search) {
        List<TipoDescuento> list = (search == null || search.isBlank())
                ? repository.findAll()
                : repository.findByTidNombreContainingIgnoreCase(search);
        return mapper.toResponseList(list);
    }

    @Override
    public TipoDescuentoResponse findById(Long id) {
        TipoDescuento entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoDescuento", id));
        return mapper.toResponse(entity);
    }

    @Override
    public TipoDescuentoResponse create(TipoDescuentoRequest request) {
        TipoDescuento entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public TipoDescuentoResponse update(Long id, TipoDescuentoRequest request) {
        TipoDescuento entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoDescuento", id));
        entity.setTidNombre(request.getTidNombre());
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("TipoDescuento", id);
        }
        repository.deleteById(id);
    }
}
