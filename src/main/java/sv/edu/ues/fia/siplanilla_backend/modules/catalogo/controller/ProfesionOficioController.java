package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.controller;

import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.CatalogoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.service.ProfesionOficioService;
import sv.edu.ues.fia.siplanilla_backend.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/catalogo/profesiones")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ProfesionOficioController {

    private final ProfesionOficioService profesionOficioService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CatalogoResponse>>> obtenerTodos() {
        List<CatalogoResponse> profesiones = profesionOficioService.obtenerTodos();
        return ResponseEntity.ok(
                ApiResponse.ok("Profesiones obtenidas exitosamente", profesiones)
        );
    }
}
