package sv.edu.ues.fia.siplanilla_backend.modules.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sv.edu.ues.fia.siplanilla_backend.modules.auth.entity.DesbloqueoToken;
import java.util.Optional;

@Repository
public interface DesbloqueoTokenRepository extends JpaRepository<DesbloqueoToken, Long> {

    Optional<DesbloqueoToken> findByTokValor(String tokValor);

    @Query(value = "SELECT * FROM TokenUsuario WHERE id_usuario = :idUsuario AND tok_usado = 0 " +
           "AND tok_tipo = 'DESBLOQUEO' AND tok_fecha_exp > SYSDATE ORDER BY id_token DESC FETCH FIRST 1 ROWS ONLY",
           nativeQuery = true)
    Optional<DesbloqueoToken> findActiveTokenByUsuario(@Param("idUsuario") Long idUsuario);
}
