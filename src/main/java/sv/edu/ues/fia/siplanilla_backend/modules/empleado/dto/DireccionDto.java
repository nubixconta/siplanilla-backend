package sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DireccionDto {

    @NotBlank(message = "La calle es requerida")
    @JsonProperty("dir_calle")
    private String dirCalle;

    @NotBlank(message = "La colonia es requerida")
    @JsonProperty("dir_colonia")
    private String dirColonia;

    @NotBlank(message = "La referencia es requerida")
    @JsonProperty("dir_referencia")
    private String dirReferencia;

    @NotNull(message = "El municipio es requerido")
    @JsonProperty("id_municipio")
    private Long idMunicipio;
}
