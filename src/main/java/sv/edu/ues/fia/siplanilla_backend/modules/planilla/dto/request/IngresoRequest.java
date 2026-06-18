package sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class IngresoRequest {

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a cero")
    private BigDecimal ingMonto;

    @NotNull(message = "El tipo de ingreso es obligatorio")
    private Long idTipoIngreso;

    private Long idDetallePlanilla;
}
