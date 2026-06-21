package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.service.impl;

import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.CatalogoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.repository.SexoRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.service.SexoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SexoServiceImpl implements SexoService {

    private final SexoRepository sexoRepository;

    @Override
    public List<CatalogoResponse> obtenerTodos() {
        return sexoRepository.findAll()
                .stream()
                .map(sexo -> new CatalogoResponse(sexo.getIdSexo(), sexo.getSexNombre()))
                .collect(Collectors.toList());
    }
}
