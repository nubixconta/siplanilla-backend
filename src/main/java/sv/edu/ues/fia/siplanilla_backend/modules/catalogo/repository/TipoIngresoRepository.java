package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.entity.TipoIngreso;

import java.util.List;

// PATRÓN DE REFERENCIA
public interface TipoIngresoRepository extends JpaRepository<TipoIngreso, Long> {

    List<TipoIngreso> findByTigNombreContainingIgnoreCase(String nombre);
}
