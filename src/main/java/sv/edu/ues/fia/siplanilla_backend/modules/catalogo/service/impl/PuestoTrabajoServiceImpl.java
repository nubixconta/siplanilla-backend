package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.service.impl;

import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.CatalogoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.repository.PuestoTrabajoRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.service.PuestoTrabajoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PuestoTrabajoServiceImpl implements PuestoTrabajoService {

    private final PuestoTrabajoRepository puestoTrabajoRepository;

    @Override
    public List<CatalogoResponse> obtenerTodos() {
        return puestoTrabajoRepository.findAll()
                .stream()
                .map(pt -> new CatalogoResponse(pt.getIdPuesto(), pt.getPstNombre()))
                .collect(Collectors.toList());
    }
}
