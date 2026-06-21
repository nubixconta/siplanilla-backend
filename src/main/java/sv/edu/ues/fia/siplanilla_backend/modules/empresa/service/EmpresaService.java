package sv.edu.ues.fia.siplanilla_backend.modules.empresa.service;

import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.CatalogoResponse;
import java.util.List;

public interface EmpresaService {
    List<CatalogoResponse> obtenerTodos();
}
