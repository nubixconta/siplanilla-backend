package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.entity.PuestoTrabajo;

@Repository
public interface PuestoTrabajoRepository extends JpaRepository<PuestoTrabajo, Long> {
}
