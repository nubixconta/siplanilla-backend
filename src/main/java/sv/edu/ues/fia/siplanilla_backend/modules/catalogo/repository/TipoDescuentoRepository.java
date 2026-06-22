package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.entity.TipoDescuento;

import java.util.List;

public interface TipoDescuentoRepository extends JpaRepository<TipoDescuento, Long> {

    List<TipoDescuento> findByTidNombreContainingIgnoreCase(String nombre);
}