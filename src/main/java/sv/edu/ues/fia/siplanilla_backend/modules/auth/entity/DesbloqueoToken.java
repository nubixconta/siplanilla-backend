package sv.edu.ues.fia.siplanilla_backend.modules.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "TokenUsuario")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DesbloqueoToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_token")
    private Long idToken;

    @Column(name = "tok_valor", unique = true, nullable = false, length = 255)
    private String tokValor;

    @Column(name = "tok_tipo", nullable = false, length = 20)
    private String tokTipo;

    @Column(name = "tok_fecha_exp", nullable = false)
    private LocalDateTime tokFechaExp;

    @Column(name = "tok_usado")
    @Builder.Default
    private Integer tokUsado = 0;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;
}
