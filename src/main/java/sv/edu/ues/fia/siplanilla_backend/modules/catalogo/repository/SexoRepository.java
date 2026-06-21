package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.repository;

import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.entity.Sexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SexoRepository extends JpaRepository<Sexo, Long> {
}
