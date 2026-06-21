package sv.edu.ues.fia.siplanilla_backend.modules.organizacion.service.impl;

import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.CatalogoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.organizacion.repository.UnidadOrganizativaRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.organizacion.service.UnidadOrganizativaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnidadOrganizativaServiceImpl implements UnidadOrganizativaService {

    private final UnidadOrganizativaRepository unidadOrganizativaRepository;

    @Override
    public List<CatalogoResponse> obtenerTodos() {
        return unidadOrganizativaRepository.findAll()
                .stream()
                .map(uo -> new CatalogoResponse(uo.getIdUnidad(), uo.getUniNombre()))
                .collect(Collectors.toList());
    }
}
