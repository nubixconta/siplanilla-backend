package sv.edu.ues.fia.siplanilla_backend.modules.seguridad.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponse {
    private Long id;
    private String username;
    private String email;
    private Boolean estado;
    private Boolean bloqueado;
    private List<String> roles;
    @JsonProperty("id_empleado")
    private Long idEmpleado;
    @JsonProperty("nombre_empleado")
    private String nombreEmpleado;
}
