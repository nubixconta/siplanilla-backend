package sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto.request;

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
public class DireccionEmpleadoUpdateRequest {

    @JsonProperty("dir_calle")
    private String dirCalle;

    @JsonProperty("dir_colonia")
    private String dirColonia;

    @JsonProperty("dir_referencia")
    private String dirReferencia;

    @JsonProperty("id_municipio")
    private Long idMunicipio;
}
