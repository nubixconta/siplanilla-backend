package sv.edu.ues.fia.siplanilla_backend.modules.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitarDesbloqueoRequest {

    @NotBlank(message = "El nombre de usuario es requerido")
    @JsonProperty("username")
    private String username;
}
