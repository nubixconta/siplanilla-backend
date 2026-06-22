package sv.edu.ues.fia.siplanilla_backend.modules.seguridad.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CrearUsuarioRequest {

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotNull
    @JsonProperty("id_empleado")
    private Long idEmpleado;

    @NotNull
    @JsonProperty("id_rol")
    private Long idRol;
}
