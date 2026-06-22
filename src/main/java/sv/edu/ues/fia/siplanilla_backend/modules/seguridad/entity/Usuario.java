package sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.entity.Empleado;
import sv.edu.ues.fia.siplanilla_backend.config.BooleanToNumberConverter;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Convert(converter = BooleanToNumberConverter.class)
    private Boolean usuEstado;

    @Column(name = "usu_correo")
    private String usuCorreo;

    @Column(name = "usu_intentos_fallidos")
    private Integer usuIntentosFallidos = 0;

    @Column(name = "usu_bloqueado")
    @Convert(converter = BooleanToNumberConverter.class)
    private Boolean usuBloqueado = false;

    @Column(name = "usu_fecha_bloqueo")
    private LocalDateTime usuFechaBloqueo;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_empleado", nullable = true)
    private Empleado empleado;

    @Transient
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities != null ? this.authorities : List.of();
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
        return this.usuBloqueado == null || !this.usuBloqueado;
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
