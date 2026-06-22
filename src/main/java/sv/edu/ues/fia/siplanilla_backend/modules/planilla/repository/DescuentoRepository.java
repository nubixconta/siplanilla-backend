package sv.edu.ues.fia.siplanilla_backend.modules.planilla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.entity.Descuento;
import java.math.BigDecimal;
import java.util.List;

public interface DescuentoRepository extends JpaRepository<Descuento, Long> {

    List<Descuento> findByDetallePlanillaIdDetallePlanilla(Long idDetallePlanilla);

    @Query("SELECT COALESCE(SUM(d.desMonto), 0) FROM Descuento d WHERE d.detallePlanilla.idDetallePlanilla = :idDetalle")
    BigDecimal sumMontoByDetallePlanilla(@Param("idDetalle") Long idDetallePlanilla);
}