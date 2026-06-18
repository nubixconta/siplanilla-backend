package sv.edu.ues.fia.siplanilla_backend.modules.planilla.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.edu.ues.fia.siplanilla_backend.exception.ResourceNotFoundException;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.entity.TipoIngreso;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.repository.TipoIngresoRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.request.IngresoRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.response.IngresoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.entity.Ingreso;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.entity.DetallePlanilla;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.mapper.IngresoMapper;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.repository.IngresoRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.repository.DetallePlanillaRepository;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngresoServiceImpl implements IngresoService {

    private final IngresoRepository repository;
    private final IngresoMapper mapper;
    private final TipoIngresoRepository tipoIngresoRepository;
    private final DetallePlanillaRepository detallePlanillaRepository;

    @Override
    public List<IngresoResponse> findAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public List<IngresoResponse> findByDetallePlanilla(Long idDetallePlanilla) {
        return mapper.toResponseList(
            repository.findByDetallePlanillaIdDetallePlanilla(idDetallePlanilla)
        );
    }

    @Override
    public IngresoResponse findById(Long id) {
        Ingreso entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Ingreso", id));
        return mapper.toResponse(entity);
    }

    @Override
    public IngresoResponse create(IngresoRequest request) {
        if (request.getIdDetallePlanilla() == null) {
            throw new IllegalArgumentException("El detalle de planilla es obligatorio");
        }

        TipoIngreso tipo = tipoIngresoRepository.findById(request.getIdTipoIngreso())
            .orElseThrow(() -> new ResourceNotFoundException("TipoIngreso", request.getIdTipoIngreso()));

        DetallePlanilla detalle = detallePlanillaRepository.findById(request.getIdDetallePlanilla())
            .orElseThrow(() -> new ResourceNotFoundException("DetallePlanilla", request.getIdDetallePlanilla()));

        Ingreso entity = mapper.toEntity(request);
        entity.setTipoIngreso(tipo);
        entity.setDetallePlanilla(detalle);

        Ingreso saved = repository.save(entity);

        BigDecimal total = repository.sumMontoByDetallePlanilla(detalle.getIdDetallePlanilla());
        detalle.setDetTotalIngresos(total);
        detallePlanillaRepository.save(detalle);

        return mapper.toResponse(saved);
    }

    @Override
    public IngresoResponse update(Long id, IngresoRequest request) {
        Ingreso entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Ingreso", id));

        TipoIngreso tipo = tipoIngresoRepository.findById(request.getIdTipoIngreso())
            .orElseThrow(() -> new ResourceNotFoundException("TipoIngreso", request.getIdTipoIngreso()));

        entity.setIngMonto(request.getIngMonto());
        entity.setTipoIngreso(tipo);
        Ingreso saved = repository.save(entity);

        BigDecimal total = repository.sumMontoByDetallePlanilla(entity.getDetallePlanilla().getIdDetallePlanilla());
        DetallePlanilla detalle = entity.getDetallePlanilla();
        detalle.setDetTotalIngresos(total);
        detallePlanillaRepository.save(detalle);

        return mapper.toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        Ingreso entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Ingreso", id));
        DetallePlanilla detalle = entity.getDetallePlanilla();
        repository.deleteById(id);

        BigDecimal total = repository.sumMontoByDetallePlanilla(detalle.getIdDetallePlanilla());
        detalle.setDetTotalIngresos(total);
        detallePlanillaRepository.save(detalle);
    }

    @Override
    public BigDecimal calcularTotalIngresos(Long idDetallePlanilla) {
        return repository.sumMontoByDetallePlanilla(idDetallePlanilla);
    }
}