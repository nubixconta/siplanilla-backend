package sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CambiarEstadoRequest {

    @NotNull(message = "El estado es requerido")
    @Min(value = 0, message = "El estado debe ser 0 (inactivo) o 1 (activo)")
    @Max(value = 1, message = "El estado debe ser 0 (inactivo) o 1 (activo)")
    @JsonProperty("estado")
    private Integer estado;
}
