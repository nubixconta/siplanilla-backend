package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.service.impl;

import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.CatalogoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.repository.ProfesionOficioRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.service.ProfesionOficioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfesionOficioServiceImpl implements ProfesionOficioService {

    private final ProfesionOficioRepository profesionOficioRepository;

    @Override
    public List<CatalogoResponse> obtenerTodos() {
        return profesionOficioRepository.findAll()
                .stream()
                .map(po -> new CatalogoResponse(po.getIdProfesion(), po.getProNombre()))
                .collect(Collectors.toList());
    }
}
