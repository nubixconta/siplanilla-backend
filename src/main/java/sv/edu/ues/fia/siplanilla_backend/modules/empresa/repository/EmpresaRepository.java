package sv.edu.ues.fia.siplanilla_backend.modules.empresa.repository;

import sv.edu.ues.fia.siplanilla_backend.modules.empresa.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
