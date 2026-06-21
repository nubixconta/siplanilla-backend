package sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.entity.Empleado;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Usuario")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {

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
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.usuPassword;
    }

    @Override
    public String getUsername() {
        return this.usuUsername;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.usuEstado != null && this.usuEstado;
    }
}
