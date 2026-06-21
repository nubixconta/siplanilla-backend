package sv.edu.ues.fia.siplanilla_backend.modules.organizacion.controller;

import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.CatalogoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.organizacion.service.UnidadOrganizativaService;
import sv.edu.ues.fia.siplanilla_backend.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/catalogo/unidades")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UnidadOrganizativaController {

    private final UnidadOrganizativaService unidadOrganizativaService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CatalogoResponse>>> obtenerTodos() {
        List<CatalogoResponse> unidades = unidadOrganizativaService.obtenerTodos();
        return ResponseEntity.ok(
                ApiResponse.ok("Unidades organizativas obtenidas exitosamente", unidades)
        );
    }
}
