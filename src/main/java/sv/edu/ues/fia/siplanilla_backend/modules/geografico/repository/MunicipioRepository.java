package sv.edu.ues.fia.siplanilla_backend.modules.geografico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.geografico.entity.Municipio;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
}
