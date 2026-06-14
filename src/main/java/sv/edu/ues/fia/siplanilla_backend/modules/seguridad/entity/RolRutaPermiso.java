package sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "RolRutaPermiso")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolRutaPermiso {

    @EmbeddedId
    private RolRutaPermisoId id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idRol")
    @JoinColumn(name = "id_rol")
    private Rol rol;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idRuta")
    @JoinColumn(name = "id_ruta")
    private Ruta ruta;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idPermiso")
    @JoinColumn(name = "id_permiso")
    private Permiso permiso;
}
