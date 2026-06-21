package sv.edu.ues.fia.siplanilla_backend.modules.geografico.service.impl;

import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.CatalogoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.geografico.repository.MunicipioRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.geografico.service.MunicipioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MunicipioServiceImpl implements MunicipioService {

    private final MunicipioRepository municipioRepository;

    @Override
    public List<CatalogoResponse> obtenerTodos() {
        return municipioRepository.findAll()
                .stream()
                .map(m -> new CatalogoResponse(m.getIdMunicipio(), m.getMunNombre()))
                .collect(Collectors.toList());
    }
}
