package sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa la relación entre Rol y Permiso.
 * Define qué permisos tiene cada rol en el sistema.
 */
@Entity
@Table(name = "RolPermiso")
@IdClass(RolPermisoId.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolPermiso {

    @Id
    @Column(name = "id_rol")
    private Long idRol;

    @Id
    @Column(name = "id_permiso")
    private Long idPermiso;

    @ManyToOne
    @JoinColumn(name = "id_rol", insertable = false, updatable = false)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "id_permiso", insertable = false, updatable = false)
    private Permiso permiso;
}
