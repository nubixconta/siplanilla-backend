package sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DireccionEmpleadoResponse {

    @JsonProperty("id_direccion")
    private Long idDireccion;

    @JsonProperty("dir_calle")
    private String dirCalle;

    @JsonProperty("dir_colonia")
    private String dirColonia;

    @JsonProperty("dir_referencia")
    private String dirReferencia;

    @JsonProperty("id_municipio")
    private Long idMunicipio;

    @JsonProperty("municipio_nombre")
    private String municipioNombre;

    @JsonProperty("id_empleado")
    private Long idEmpleado;
}
