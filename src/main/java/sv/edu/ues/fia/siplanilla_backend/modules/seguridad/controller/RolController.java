package sv.edu.ues.fia.siplanilla_backend.modules.seguridad.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity.Rol;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.repository.RolRepository;
import sv.edu.ues.fia.siplanilla_backend.util.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/v1/roles")
@RequiredArgsConstructor
@PreAuthorize("hasRole('Administrador')")
@Tag(name = "Administración - Roles")
public class RolController {

    private final RolRepository rolRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Rol>>> findAll() {
        return ResponseEntity.ok(ApiResponse.ok(rolRepository.findAll()));
    }
}
