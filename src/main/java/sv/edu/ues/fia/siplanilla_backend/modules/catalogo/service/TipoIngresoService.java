package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.service;

import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.request.TipoIngresoRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.response.TipoIngresoResponse;

import java.util.List;

// PATRÓN DE REFERENCIA
public interface TipoIngresoService {

    List<TipoIngresoResponse> findAll(String search);
    TipoIngresoResponse findById(Long id);
    TipoIngresoResponse create(TipoIngresoRequest request);
    TipoIngresoResponse update(Long id, TipoIngresoRequest request);
    void delete(Long id);
}
