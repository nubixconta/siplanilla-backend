package sv.edu.ues.fia.siplanilla_backend.modules.empresa.service.impl;

import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.CatalogoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.empresa.repository.EmpresaRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.empresa.service.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;

    @Override
    public List<CatalogoResponse> obtenerTodos() {
        return empresaRepository.findAll()
                .stream()
                .map(empresa -> new CatalogoResponse(empresa.getIdEmpresa(), empresa.getEprNombre()))
                .collect(Collectors.toList());
    }
}
