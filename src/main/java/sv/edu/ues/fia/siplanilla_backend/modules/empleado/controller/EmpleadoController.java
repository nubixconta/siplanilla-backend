package sv.edu.ues.fia.siplanilla_backend.modules.empleado.controller;

import sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto.request.CambiarEstadoRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto.request.EmpleadoRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto.request.EmpleadoUpdateRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto.response.EmpleadoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.service.EmpleadoService;
import sv.edu.ues.fia.siplanilla_backend.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/empleados")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@PreAuthorize("hasAnyRole('Administrador','RRHH')")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    /**
     * Crear un nuevo empleado
     */
    @PostMapping
    public ResponseEntity<ApiResponse<EmpleadoResponse>> crear(@Valid @RequestBody EmpleadoRequest request) {
        EmpleadoResponse response = empleadoService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Empleado creado exitosamente", response));
    }

    /**
     * Obtener todos los empleados
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<EmpleadoResponse>>> obtenerTodos() {
        List<EmpleadoResponse> empleados = empleadoService.obtenerTodos();
        return ResponseEntity.ok(
                ApiResponse.ok("Empleados obtenidos exitosamente", empleados)
        );
    }

    /**
     * Obtener un empleado por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmpleadoResponse>> obtenerPorId(@PathVariable Long id) {
        EmpleadoResponse response = empleadoService.obtenerPorId(id);
        return ResponseEntity.ok(
                ApiResponse.ok("Empleado obtenido exitosamente", response)
        );
    }

    /**
     * Actualizar un empleado (completo)
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmpleadoResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody EmpleadoRequest request) {
        EmpleadoResponse response = empleadoService.actualizar(id, request);
        return ResponseEntity.ok(
                ApiResponse.ok("Empleado actualizado exitosamente", response)
        );
    }

    /**
     * Actualizar parcialmente un empleado (solo los campos proporcionados)
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<EmpleadoResponse>> actualizarParcial(
            @PathVariable Long id,
            @Valid @RequestBody EmpleadoUpdateRequest request) {
        EmpleadoResponse response = empleadoService.actualizarParcial(id, request);
        return ResponseEntity.ok(
                ApiResponse.ok("Empleado actualizado exitosamente", response)
        );
    }

    /**
     * Cambiar estado de un empleado (activar/desactivar) usando procedimiento Oracle
     * estado: 0 = inactivo, 1 = activo
     */
    @PatchMapping("/{id}/estado")
    public ResponseEntity<ApiResponse<EmpleadoResponse>> cambiarEstado(
            @PathVariable Long id,
            @Valid @RequestBody CambiarEstadoRequest request) {
        EmpleadoResponse response = empleadoService.cambiarEstado(id, request.getEstado());
        return ResponseEntity.ok(
                ApiResponse.ok("Estado del empleado actualizado exitosamente", response)
        );
    }

    /**
     * Buscar empleados por nombre (busca en nombre y apellido)
     */
    @GetMapping("/buscar")
    public ResponseEntity<ApiResponse<List<EmpleadoResponse>>> buscarPorNombre(
            @RequestParam String nombre) {
        List<EmpleadoResponse> empleados = empleadoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(
                ApiResponse.ok("Empleados encontrados", empleados)
        );
    }
}
