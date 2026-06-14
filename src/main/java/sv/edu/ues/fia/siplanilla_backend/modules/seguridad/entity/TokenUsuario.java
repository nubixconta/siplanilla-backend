package sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TokenUsuario")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_token")
    private Long idToken;

    @Column(name = "tok_valor")
    private String tokValor;

    @Column(name = "tok_tipo")
    private String tokTipo;

    @Column(name = "tok_fecha_exp")
    private LocalDateTime tokFechaExp;

    @Column(name = "tok_usado")
    private Boolean tokUsado;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
