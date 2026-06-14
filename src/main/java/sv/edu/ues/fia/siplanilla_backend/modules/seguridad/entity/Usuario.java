package sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity;

import jakarta.persistence.*;
import lombok.*;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.entity.Empleado;

@Entity
@Table(name = "Usuario")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "usu_username")
    private String usuUsername;

    @Column(name = "usu_password")
    private String usuPassword;

    @Column(name = "usu_estado")
    private Boolean usuEstado;

    @Column(name = "usu_correo")
    private String usuCorreo;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;
}
