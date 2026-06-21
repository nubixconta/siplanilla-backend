package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.controller;

import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.CatalogoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.service.EstadoCivilService;
import sv.edu.ues.fia.siplanilla_backend.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/catalogo/estados-civiles")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class EstadoCivilController {

    private final EstadoCivilService estadoCivilService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CatalogoResponse>>> obtenerTodos() {
        List<CatalogoResponse> estadosCiviles = estadoCivilService.obtenerTodos();
        return ResponseEntity.ok(
                ApiResponse.ok("Estados civiles obtenidos exitosamente", estadosCiviles)
        );
    }
}
