package sv.edu.ues.fia.siplanilla_backend.modules.seguridad.repository;

import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsuUsername(String usuUsername);
    Optional<Usuario> findByUsuCorreo(String usuCorreo);
    boolean existsByUsuUsername(String usuUsername);
    boolean existsByUsuCorreo(String usuCorreo);
}
