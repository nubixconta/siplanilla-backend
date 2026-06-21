package sv.edu.ues.fia.siplanilla_backend.modules.empresa.controller;

import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.CatalogoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.empresa.service.EmpresaService;
import sv.edu.ues.fia.siplanilla_backend.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/catalogo/empresas")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CatalogoResponse>>> obtenerTodos() {
        List<CatalogoResponse> empresas = empresaService.obtenerTodos();
        return ResponseEntity.ok(
                ApiResponse.ok("Empresas obtenidas exitosamente", empresas)
        );
    }
}
