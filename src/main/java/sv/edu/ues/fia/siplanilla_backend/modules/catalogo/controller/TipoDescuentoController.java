package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.request.TipoDescuentoRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.response.TipoDescuentoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.service.TipoDescuentoService;
import sv.edu.ues.fia.siplanilla_backend.util.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/catalogos/tipos-descuento")
@RequiredArgsConstructor
@PreAuthorize("hasRole('Administrador')")
@Tag(name = "Catálogos")
public class TipoDescuentoController {

    private final TipoDescuentoService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TipoDescuentoResponse>>> findAll(
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(ApiResponse.ok(service.findAll(search)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TipoDescuentoResponse>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TipoDescuentoResponse>> create(
            @Valid @RequestBody TipoDescuentoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("TipoDescuento creado", service.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TipoDescuentoResponse>> update(
            @PathVariable Long id, @Valid @RequestBody TipoDescuentoRequest request) {
        return ResponseEntity.ok(ApiResponse.ok("TipoDescuento actualizado", service.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("TipoDescuento eliminado", null));
    }
}
