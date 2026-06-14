package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "EstadoCivil")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoCivil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_civil")
    private Long idEstadoCivil;

    @Column(name = "esc_nombre")
    private String escNombre;

    @Column(name = "esc_activo")
    private Boolean escActivo;
}
