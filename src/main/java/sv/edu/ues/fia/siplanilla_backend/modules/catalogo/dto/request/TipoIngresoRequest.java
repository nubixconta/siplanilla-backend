package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

// PATRÓN DE REFERENCIA
@Data
public class TipoIngresoRequest {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre no puede superar 50 caracteres")
    private String tigNombre;
}
