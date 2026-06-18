package sv.edu.ues.fia.siplanilla_backend.modules.planilla.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.edu.ues.fia.siplanilla_backend.exception.ResourceNotFoundException;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.entity.TipoDescuento;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.repository.TipoDescuentoRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.request.DescuentoRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.response.DescuentoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.entity.Descuento;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.entity.DetallePlanilla;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.mapper.DescuentoMapper;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.repository.DescuentoRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.repository.DetallePlanillaRepository;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DescuentoServiceImpl implements DescuentoService {

    private final DescuentoRepository repository;
    private final DescuentoMapper mapper;
    private final TipoDescuentoRepository tipoDescuentoRepository;
    private final DetallePlanillaRepository detallePlanillaRepository;

    @Override
    public List<DescuentoResponse> findAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public List<DescuentoResponse> findByDetallePlanilla(Long idDetallePlanilla) {
        return mapper.toResponseList(
            repository.findByDetallePlanillaIdDetallePlanilla(idDetallePlanilla)
        );
    }

    @Override
    public DescuentoResponse findById(Long id) {
        Descuento entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Descuento", id));
        return mapper.toResponse(entity);
    }

    @Override
    public DescuentoResponse create(DescuentoRequest request) {
        if (request.getIdDetallePlanilla() == null) {
            throw new IllegalArgumentException("El detalle de planilla es obligatorio");
        }

        TipoDescuento tipo = tipoDescuentoRepository.findById(request.getIdTipoDescuento())
            .orElseThrow(() -> new ResourceNotFoundException("TipoDescuento", request.getIdTipoDescuento()));

        DetallePlanilla detalle = detallePlanillaRepository.findById(request.getIdDetallePlanilla())
            .orElseThrow(() -> new ResourceNotFoundException("DetallePlanilla", request.getIdDetallePlanilla()));

        Descuento entity = mapper.toEntity(request);
        entity.setTipoDescuento(tipo);
        entity.setDetallePlanilla(detalle);

        Descuento saved = repository.save(entity);

        // Recalcular y actualizar el total de descuentos en DetallePlanilla
        BigDecimal total = repository.sumMontoByDetallePlanilla(detalle.getIdDetallePlanilla());
        detalle.setDetTotalDescuentos(total);
        detallePlanillaRepository.save(detalle);

        return mapper.toResponse(saved);
    }

    @Override
    public DescuentoResponse update(Long id, DescuentoRequest request) {
        Descuento entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Descuento", id));

        TipoDescuento tipo = tipoDescuentoRepository.findById(request.getIdTipoDescuento())
            .orElseThrow(() -> new ResourceNotFoundException("TipoDescuento", request.getIdTipoDescuento()));

        entity.setDesMonto(request.getDesMonto());
        entity.setTipoDescuento(tipo);
        Descuento saved = repository.save(entity);

        // Recalcular total
        BigDecimal total = repository.sumMontoByDetallePlanilla(entity.getDetallePlanilla().getIdDetallePlanilla());
        DetallePlanilla detalle = entity.getDetallePlanilla();
        detalle.setDetTotalDescuentos(total);
        detallePlanillaRepository.save(detalle);

        return mapper.toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        Descuento entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Descuento", id));
        DetallePlanilla detalle = entity.getDetallePlanilla();
        repository.deleteById(id);

        // Recalcular total tras eliminar
        BigDecimal total = repository.sumMontoByDetallePlanilla(detalle.getIdDetallePlanilla());
        detalle.setDetTotalDescuentos(total);
        detallePlanillaRepository.save(detalle);
    }

    @Override
    public BigDecimal calcularTotalDescuentos(Long idDetallePlanilla) {
        return repository.sumMontoByDetallePlanilla(idDetallePlanilla);
    }
}