package sv.edu.ues.fia.siplanilla_backend.modules.seguridad.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.dto.CambiarRolRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.dto.CrearUsuarioRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.dto.UsuarioResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.service.UsuarioService;
import sv.edu.ues.fia.siplanilla_backend.util.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
@RequiredArgsConstructor
@PreAuthorize("hasRole('Administrador')")
@Tag(name = "Administración - Usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UsuarioResponse>>> findAll() {
        return ResponseEntity.ok(ApiResponse.ok(usuarioService.findAll()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioResponse>> crear(@Valid @RequestBody CrearUsuarioRequest request) {
        UsuarioResponse response = usuarioService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Usuario creado exitosamente", response));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<ApiResponse<UsuarioResponse>> cambiarEstado(
            @PathVariable Long id,
            @RequestParam Boolean estado) {
        UsuarioResponse response = usuarioService.cambiarEstado(id, estado);
        return ResponseEntity.ok(ApiResponse.ok("Estado actualizado", response));
    }

    @PutMapping("/{id}/rol")
    public ResponseEntity<ApiResponse<UsuarioResponse>> cambiarRol(
            @PathVariable Long id,
            @Valid @RequestBody CambiarRolRequest request) {
        UsuarioResponse response = usuarioService.cambiarRol(id, request.getIdRol());
        return ResponseEntity.ok(ApiResponse.ok("Rol actualizado", response));
    }
}
