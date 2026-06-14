package sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolRutaPermisoId implements Serializable {

    @Column(name = "id_rol")
    private Long idRol;

    @Column(name = "id_ruta")
    private Long idRuta;

    @Column(name = "id_permiso")
    private Long idPermiso;
}
