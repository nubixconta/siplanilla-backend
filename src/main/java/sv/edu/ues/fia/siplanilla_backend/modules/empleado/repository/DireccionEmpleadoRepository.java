package sv.edu.ues.fia.siplanilla_backend.modules.empleado.repository;

import sv.edu.ues.fia.siplanilla_backend.modules.empleado.entity.DireccionEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DireccionEmpleadoRepository extends JpaRepository<DireccionEmpleado, Long> {
    List<DireccionEmpleado> findByEmpleado_IdEmpleado(Long idEmpleado);
}
