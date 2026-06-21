package sv.edu.ues.fia.siplanilla_backend.modules.auth.controller;

import sv.edu.ues.fia.siplanilla_backend.modules.auth.dto.request.LoginRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.auth.dto.request.RegisterRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.auth.dto.response.AuthResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.auth.service.AuthService;
import sv.edu.ues.fia.siplanilla_backend.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = authService.login(loginRequest);
        return ResponseEntity.ok(
                ApiResponse.<AuthResponse>builder()
                        .success(true)
                        .message("Login exitoso")
                        .data(authResponse)
                        .build()
        );
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody RegisterRequest registerRequest) {
        AuthResponse authResponse = authService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<AuthResponse>builder()
                        .success(true)
                        .message("Registro exitoso")
                        .data(authResponse)
                        .build()
        );
    }
}
