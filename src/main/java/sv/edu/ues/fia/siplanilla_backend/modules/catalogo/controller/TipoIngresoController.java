package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.request.TipoIngresoRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.response.TipoIngresoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.service.TipoIngresoService;
import sv.edu.ues.fia.siplanilla_backend.util.ApiResponse;

import java.util.List;

// PATRÓN DE REFERENCIA
@RestController
@RequestMapping("/catalogos/tipos-ingreso")
@RequiredArgsConstructor
@Tag(name = "Catálogos")
public class TipoIngresoController {

    private final TipoIngresoService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TipoIngresoResponse>>> findAll(
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(ApiResponse.ok(service.findAll(search)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TipoIngresoResponse>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TipoIngresoResponse>> create(
            @Valid @RequestBody TipoIngresoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("TipoIngreso creado", service.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TipoIngresoResponse>> update(
            @PathVariable Long id, @Valid @RequestBody TipoIngresoRequest request) {
        return ResponseEntity.ok(ApiResponse.ok("TipoIngreso actualizado", service.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("TipoIngreso eliminado", null));
    }
}
