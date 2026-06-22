package sv.edu.ues.fia.siplanilla_backend.modules.seguridad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
}
