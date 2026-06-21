package sv.edu.ues.fia.siplanilla_backend.modules.empleado.controller;

import sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto.request.DireccionEmpleadoRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto.request.DireccionEmpleadoUpdateRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto.response.DireccionEmpleadoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.service.DireccionEmpleadoService;
import sv.edu.ues.fia.siplanilla_backend.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/direcciones")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class DireccionEmpleadoController {

    private final DireccionEmpleadoService direccionEmpleadoService;

    @PostMapping
    public ResponseEntity<ApiResponse<DireccionEmpleadoResponse>> crear(
            @Valid @RequestBody DireccionEmpleadoRequest request) {
        DireccionEmpleadoResponse direccion = direccionEmpleadoService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.ok("Dirección creada exitosamente", direccion)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DireccionEmpleadoResponse>> obtenerPorId(
            @PathVariable Long id) {
        DireccionEmpleadoResponse direccion = direccionEmpleadoService.obtenerPorId(id);
        return ResponseEntity.ok(
                ApiResponse.ok("Dirección obtenida exitosamente", direccion)
        );
    }

    @GetMapping("/empleado/{idEmpleado}")
    public ResponseEntity<ApiResponse<List<DireccionEmpleadoResponse>>> obtenerPorEmpleado(
            @PathVariable Long idEmpleado) {
        List<DireccionEmpleadoResponse> direcciones = direccionEmpleadoService.obtenerPorEmpleado(idEmpleado);
        return ResponseEntity.ok(
                ApiResponse.ok("Direcciones del empleado obtenidas exitosamente", direcciones)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DireccionEmpleadoResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody DireccionEmpleadoUpdateRequest request) {
        DireccionEmpleadoResponse direccion = direccionEmpleadoService.actualizar(id, request);
        return ResponseEntity.ok(
                ApiResponse.ok("Dirección actualizada exitosamente", direccion)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<DireccionEmpleadoResponse>> actualizarParcial(
            @PathVariable Long id,
            @RequestBody DireccionEmpleadoUpdateRequest request) {
        DireccionEmpleadoResponse direccion = direccionEmpleadoService.actualizarParcial(id, request);
        return ResponseEntity.ok(
                ApiResponse.ok("Dirección actualizada parcialmente", direccion)
        );
    }
}
