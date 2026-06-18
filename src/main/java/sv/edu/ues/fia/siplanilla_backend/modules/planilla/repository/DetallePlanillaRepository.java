package sv.edu.ues.fia.siplanilla_backend.modules.planilla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.entity.DetallePlanilla;

public interface DetallePlanillaRepository extends JpaRepository<DetallePlanilla, Long> {
}
