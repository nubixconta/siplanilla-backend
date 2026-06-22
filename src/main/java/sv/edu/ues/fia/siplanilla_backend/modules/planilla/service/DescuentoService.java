package sv.edu.ues.fia.siplanilla_backend.modules.planilla.service;

import sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.request.DescuentoRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.response.DescuentoResponse;
import java.math.BigDecimal;
import java.util.List;

public interface DescuentoService {
    List<DescuentoResponse> findAll();
    List<DescuentoResponse> findByDetallePlanilla(Long idDetallePlanilla);
    DescuentoResponse findById(Long id);
    DescuentoResponse create(DescuentoRequest request);
    DescuentoResponse update(Long id, DescuentoRequest request);
    void delete(Long id);
    BigDecimal calcularTotalDescuentos(Long idDetallePlanilla);
}