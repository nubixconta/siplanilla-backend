package sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class IngresoResponse {
    private Long idIngreso;
    private BigDecimal ingMonto;
    private Long idTipoIngreso;
    private String tigNombre;
    private Long idDetallePlanilla;
}