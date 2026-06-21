package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.service;

import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.CatalogoResponse;
import java.util.List;

public interface PuestoTrabajoService {
    List<CatalogoResponse> obtenerTodos();
}
