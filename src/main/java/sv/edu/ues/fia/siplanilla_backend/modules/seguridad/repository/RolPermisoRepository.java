package sv.edu.ues.fia.siplanilla_backend.modules.seguridad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity.RolPermiso;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity.RolPermisoId;
import java.util.List;

@Repository
public interface RolPermisoRepository extends JpaRepository<RolPermiso, RolPermisoId> {
    
    /**
     * Encuentra todos los permisos asociados a un rol
     */
    List<RolPermiso> findByIdRol(Long idRol);
    
    /**
     * Encuentra todos los roles que tienen un permiso específico
     */
    List<RolPermiso> findByIdPermiso(Long idPermiso);
    
    /**
     * Verifica si un rol tiene un permiso específico
     */
    boolean existsByIdRolAndIdPermiso(Long idRol, Long idPermiso);
}
