package sv.edu.ues.fia.siplanilla_backend.modules.planilla.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.response.DetallePlanillaResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.service.DetallePlanillaService;
import sv.edu.ues.fia.siplanilla_backend.util.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/planilla/detalle")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('Administrador','RRHH')")
@Tag(name = "Planilla - Detalle")
public class DetallePlanillaController {

    private final DetallePlanillaService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<DetallePlanillaResponse>>> findAll() {
        return ResponseEntity.ok(ApiResponse.ok(service.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DetallePlanillaResponse>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.findById(id)));
    }

    @GetMapping("/mis-boletas")
    @PreAuthorize("hasRole('Empleado')")
    public ResponseEntity<ApiResponse<List<DetallePlanillaResponse>>> misBoletas(Authentication authentication) {
        List<DetallePlanillaResponse> boletas = service.findByUsernameEmpleado(authentication.getName());
        return ResponseEntity.ok(ApiResponse.ok(boletas));
    }
}
