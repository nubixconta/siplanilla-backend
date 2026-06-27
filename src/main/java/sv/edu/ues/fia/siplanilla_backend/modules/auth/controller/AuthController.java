package sv.edu.ues.fia.siplanilla_backend.modules.auth.controller;

import sv.edu.ues.fia.siplanilla_backend.modules.auth.dto.request.ConfirmarDesbloqueoRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.auth.dto.request.LoginRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.auth.dto.request.RegisterRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.auth.dto.request.SolicitarDesbloqueoRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.auth.dto.response.AuthResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.auth.dto.response.AccountStatusDto;
import sv.edu.ues.fia.siplanilla_backend.modules.auth.service.AuthService;
import sv.edu.ues.fia.siplanilla_backend.modules.auth.service.DesbloqueoService;
import sv.edu.ues.fia.siplanilla_backend.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final DesbloqueoService desbloqueoService;

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

    /**
     * Solicitar desbloqueo de cuenta (envía correo con enlace)
     */
    @PostMapping("/solicitar-desbloqueo")
    public ResponseEntity<ApiResponse<String>> solicitarDesbloqueo(
            @Valid @RequestBody SolicitarDesbloqueoRequest request) {
        desbloqueoService.solicitarDesbloqueo(request.getUsername());
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Se ha enviado un correo con el enlace de desbloqueo")
                        .data("Revisa tu correo electrónico para desbloquear tu cuenta")
                        .build()
        );
    }

    /**
     * Confirmar desbloqueo con token
     */
    @PostMapping("/confirmar-desbloqueo")
    public ResponseEntity<ApiResponse<String>> confirmarDesbloqueo(
            @Valid @RequestBody ConfirmarDesbloqueoRequest request) {
        String mensaje = desbloqueoService.confirmarDesbloqueo(request.getToken());
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Desbloqueo exitoso")
                        .data(mensaje)
                        .build()
        );
    }

    /**
     * Verificar estado de la cuenta (bloqueada o activa)
     * Endpoint PÚBLICO para que el frontend sepa si debe mostrar el formulario de desbloqueo
     */
    @GetMapping("/verificar-estado/{username}")
    public ResponseEntity<ApiResponse<AccountStatusDto>> verificarEstado(
            @PathVariable String username) {
        AccountStatusDto status = authService.verificarEstadoCuenta(username);
        return ResponseEntity.ok(
                ApiResponse.<AccountStatusDto>builder()
                        .success(true)
                        .message("Estado de cuenta verificado")
                        .data(status)
                        .build()
        );
    }
}
