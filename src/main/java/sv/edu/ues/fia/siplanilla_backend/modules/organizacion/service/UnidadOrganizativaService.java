package sv.edu.ues.fia.siplanilla_backend.modules.organizacion.service;

import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.CatalogoResponse;
import java.util.List;

public interface UnidadOrganizativaService {
    List<CatalogoResponse> obtenerTodos();
}
