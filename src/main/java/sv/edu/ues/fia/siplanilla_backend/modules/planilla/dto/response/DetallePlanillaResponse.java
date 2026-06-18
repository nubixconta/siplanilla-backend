package sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class DetallePlanillaResponse {
    private Long idDetallePlanilla;
    private BigDecimal detSalarioBase;
    private BigDecimal detTotalIngresos;
    private BigDecimal detTotalDescuentos;
    private BigDecimal detSalarioNeto;
    private Long idEmpleado;
    private String nombreEmpleado;
    private Long idPlanilla;
    private String periodo;
}
