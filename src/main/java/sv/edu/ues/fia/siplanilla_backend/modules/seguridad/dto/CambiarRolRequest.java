package sv.edu.ues.fia.siplanilla_backend.modules.seguridad.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CambiarRolRequest {

    @NotNull
    @JsonProperty("id_rol")
    private Long idRol;
}
