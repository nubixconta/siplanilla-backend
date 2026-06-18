package sv.edu.ues.fia.siplanilla_backend.modules.planilla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.entity.Ingreso;
import java.math.BigDecimal;
import java.util.List;

public interface IngresoRepository extends JpaRepository<Ingreso, Long> {

    List<Ingreso> findByDetallePlanillaIdDetallePlanilla(Long idDetallePlanilla);

    @Query("SELECT COALESCE(SUM(i.ingMonto), 0) FROM Ingreso i WHERE i.detallePlanilla.idDetallePlanilla = :idDetalle")
    BigDecimal sumMontoByDetallePlanilla(@Param("idDetalle") Long idDetallePlanilla);
}
