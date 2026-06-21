package sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Clase que representa la clave primaria compuesta de RolPermiso.
 * Combina el idRol e idPermiso como identificador único.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolPermisoId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idRol;
    private Long idPermiso;
}
