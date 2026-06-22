package sv.edu.ues.fia.siplanilla_backend.modules.planilla.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.request.IngresoRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.response.IngresoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.service.IngresoService;
import sv.edu.ues.fia.siplanilla_backend.util.ApiResponse;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/planilla/ingresos")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('Administrador','RRHH')")
@Tag(name = "Planilla - Ingresos")
public class IngresoController {

    private final IngresoService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<IngresoResponse>>> findAll() {
        return ResponseEntity.ok(ApiResponse.ok(service.findAll()));
    }

    @GetMapping("/detalle/{idDetalle}")
    public ResponseEntity<ApiResponse<List<IngresoResponse>>> findByDetalle(
            @PathVariable Long idDetalle) {
        return ResponseEntity.ok(ApiResponse.ok(service.findByDetallePlanilla(idDetalle)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<IngresoResponse>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.findById(id)));
    }

    @GetMapping("/total/{idDetalle}")
    public ResponseEntity<ApiResponse<BigDecimal>> calcularTotal(@PathVariable Long idDetalle) {
        return ResponseEntity.ok(ApiResponse.ok(service.calcularTotalIngresos(idDetalle)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<IngresoResponse>> create(
            @Valid @RequestBody IngresoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.ok("Ingreso creado", service.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<IngresoResponse>> update(
            @PathVariable Long id, @Valid @RequestBody IngresoRequest request) {
        return ResponseEntity.ok(ApiResponse.ok("Ingreso actualizado", service.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Ingreso eliminado", null));
    }
}
