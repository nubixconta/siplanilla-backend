package sv.edu.ues.fia.siplanilla_backend.modules.geografico.controller;

import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.CatalogoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.geografico.service.MunicipioService;
import sv.edu.ues.fia.siplanilla_backend.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/catalogo/municipios")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class MunicipioController {

    private final MunicipioService municipioService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CatalogoResponse>>> obtenerTodos() {
        List<CatalogoResponse> municipios = municipioService.obtenerTodos();
        return ResponseEntity.ok(
                ApiResponse.ok("Municipios obtenidos exitosamente", municipios)
        );
    }
}
