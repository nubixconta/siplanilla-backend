package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.service.impl;

import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.CatalogoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.repository.EstadoCivilRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.service.EstadoCivilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstadoCivilServiceImpl implements EstadoCivilService {

    private final EstadoCivilRepository estadoCivilRepository;

    @Override
    public List<CatalogoResponse> obtenerTodos() {
        return estadoCivilRepository.findAll()
                .stream()
                .map(ec -> new CatalogoResponse(ec.getIdEstadoCivil(), ec.getEscNombre()))
                .collect(Collectors.toList());
    }
}
