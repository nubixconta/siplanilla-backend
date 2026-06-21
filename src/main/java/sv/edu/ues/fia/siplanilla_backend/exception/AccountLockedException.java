package sv.edu.ues.fia.siplanilla_backend.exception;

/**
 * Excepción lanzada cuando una cuenta de usuario está bloqueada.
 * Permite al frontend distinguir este error del de credenciales inválidas.
 */
public class AccountLockedException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    private final String username;

    public AccountLockedException(String message) {
        super(message);
        this.username = null;
    }

    public AccountLockedException(String message, String username) {
        super(message);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
