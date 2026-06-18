package sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class DescuentoRequest {

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a cero")
    private BigDecimal desMonto;

    @NotNull(message = "El tipo de descuento es obligatorio")
    private Long idTipoDescuento;

    private Long idDetallePlanilla;
}