package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.controller;

import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.CatalogoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.service.PuestoTrabajoService;
import sv.edu.ues.fia.siplanilla_backend.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/catalogo/puestos")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('Administrador','RRHH')")
public class PuestoTrabajoController {

    private final PuestoTrabajoService puestoTrabajoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CatalogoResponse>>> obtenerTodos() {
        List<CatalogoResponse> puestos = puestoTrabajoService.obtenerTodos();
        return ResponseEntity.ok(
                ApiResponse.ok("Puestos obtenidos exitosamente", puestos)
        );
    }
}
