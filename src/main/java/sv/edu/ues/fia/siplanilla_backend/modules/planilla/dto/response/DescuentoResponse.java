package sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class DescuentoResponse {
    private Long idDescuento;
    private BigDecimal desMonto;
    private Long idTipoDescuento;
    private String tidNombre;
    private Long idDetallePlanilla;
}
