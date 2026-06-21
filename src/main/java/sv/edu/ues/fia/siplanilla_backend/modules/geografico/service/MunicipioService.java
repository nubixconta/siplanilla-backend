package sv.edu.ues.fia.siplanilla_backend.modules.geografico.service;

import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.CatalogoResponse;
import java.util.List;

public interface MunicipioService {
    List<CatalogoResponse> obtenerTodos();
}
