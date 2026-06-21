package sv.edu.ues.fia.siplanilla_backend.modules.empleado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.entity.Empleado;
import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    @Modifying
    @Transactional
    @Query(value = "{ call cambiar_estado_empleado(:p_id_empleado, :p_estado) }", nativeQuery = true)
    void cambiarEstadoEmpleado(
        @Param("p_id_empleado") Long idEmpleado,
        @Param("p_estado") Integer estado
    );

    @Query("SELECT e FROM Empleado e WHERE UPPER(e.empNombre) LIKE UPPER(CONCAT('%', :nombre, '%')) " +
           "OR UPPER(e.empApellido) LIKE UPPER(CONCAT('%', :nombre, '%'))")
    List<Empleado> buscarPorNombre(@Param("nombre") String nombre);
}
