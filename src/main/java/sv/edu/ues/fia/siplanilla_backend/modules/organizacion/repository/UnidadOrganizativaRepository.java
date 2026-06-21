package sv.edu.ues.fia.siplanilla_backend.modules.organizacion.repository;

import sv.edu.ues.fia.siplanilla_backend.modules.organizacion.entity.UnidadOrganizativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadOrganizativaRepository extends JpaRepository<UnidadOrganizativa, Long> {
}
