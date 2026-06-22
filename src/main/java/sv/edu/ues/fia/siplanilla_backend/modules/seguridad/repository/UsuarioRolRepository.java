package sv.edu.ues.fia.siplanilla_backend.modules.seguridad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity.Usuario;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity.UsuarioRol;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity.UsuarioRolId;

import java.util.List;

@Repository
public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, UsuarioRolId> {

    @Query("SELECT ur FROM UsuarioRol ur JOIN FETCH ur.rol WHERE ur.usuario = :usuario")
    List<UsuarioRol> findByUsuarioWithRol(@Param("usuario") Usuario usuario);

    @Modifying
    @Query("DELETE FROM UsuarioRol ur WHERE ur.usuario.idUsuario = :idUsuario")
    void deleteByUsuarioId(@Param("idUsuario") Long idUsuario);
}
