package sv.edu.ues.fia.siplanilla_backend.modules.planilla.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.request.DescuentoRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.response.DescuentoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.service.DescuentoService;
import sv.edu.ues.fia.siplanilla_backend.util.ApiResponse;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/planilla/descuentos")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('Administrador','RRHH')")
@Tag(name = "Planilla - Descuentos")
public class DescuentoController {

    private final DescuentoService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<DescuentoResponse>>> findAll() {
        return ResponseEntity.ok(ApiResponse.ok(service.findAll()));
    }

    @GetMapping("/detalle/{idDetalle}")
    public ResponseEntity<ApiResponse<List<DescuentoResponse>>> findByDetalle(
            @PathVariable Long idDetalle) {
        return ResponseEntity.ok(ApiResponse.ok(service.findByDetallePlanilla(idDetalle)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DescuentoResponse>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.findById(id)));
    }

    @GetMapping("/total/{idDetalle}")
    public ResponseEntity<ApiResponse<BigDecimal>> calcularTotal(@PathVariable Long idDetalle) {
        return ResponseEntity.ok(ApiResponse.ok(service.calcularTotalDescuentos(idDetalle)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DescuentoResponse>> create(
            @Valid @RequestBody DescuentoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.ok("Descuento creado", service.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DescuentoResponse>> update(
            @PathVariable Long id, @Valid @RequestBody DescuentoRequest request) {
        return ResponseEntity.ok(ApiResponse.ok("Descuento actualizado", service.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Descuento eliminado", null));
    }
}