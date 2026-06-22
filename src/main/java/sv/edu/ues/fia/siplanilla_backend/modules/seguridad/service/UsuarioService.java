package sv.edu.ues.fia.siplanilla_backend.modules.seguridad.service;

import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.dto.CrearUsuarioRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.dto.UsuarioResponse;

import java.util.List;

public interface UsuarioService {
    List<UsuarioResponse> findAll();
    UsuarioResponse crear(CrearUsuarioRequest request);
    UsuarioResponse cambiarEstado(Long id, Boolean estado);
    UsuarioResponse cambiarRol(Long id, Long idRol);
}
