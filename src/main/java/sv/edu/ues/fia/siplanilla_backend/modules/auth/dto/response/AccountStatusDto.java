package sv.edu.ues.fia.siplanilla_backend.modules.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que retorna el estado de la cuenta de un usuario.
 * Utilizado por el frontend para determinar si mostrar formulario de desbloqueo.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountStatusDto {

    @JsonProperty("username")
    private String username;

    @JsonProperty("existe")
    private Boolean existe;

    @JsonProperty("bloqueado")
    private Boolean bloqueado;

    @JsonProperty("activo")
    private Boolean activo;

    @JsonProperty("mensaje")
    private String mensaje;
}
