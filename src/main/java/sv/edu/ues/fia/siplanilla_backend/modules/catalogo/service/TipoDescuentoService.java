package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.service;

import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.request.TipoDescuentoRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.response.TipoDescuentoResponse;

import java.util.List;

public interface TipoDescuentoService {

    List<TipoDescuentoResponse> findAll(String search);
    TipoDescuentoResponse findById(Long id);
    TipoDescuentoResponse create(TipoDescuentoRequest request);
    TipoDescuentoResponse update(Long id, TipoDescuentoRequest request);
    void delete(Long id);
}
