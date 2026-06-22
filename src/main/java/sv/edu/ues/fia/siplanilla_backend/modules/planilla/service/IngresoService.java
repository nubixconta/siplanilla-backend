package sv.edu.ues.fia.siplanilla_backend.modules.planilla.service;

import sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.request.IngresoRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.response.IngresoResponse;
import java.math.BigDecimal;
import java.util.List;

public interface IngresoService {
    List<IngresoResponse> findAll();
    List<IngresoResponse> findByDetallePlanilla(Long idDetallePlanilla);
    IngresoResponse findById(Long id);
    IngresoResponse create(IngresoRequest request);
    IngresoResponse update(Long id, IngresoRequest request);
    void delete(Long id);
    BigDecimal calcularTotalIngresos(Long idDetallePlanilla);
}