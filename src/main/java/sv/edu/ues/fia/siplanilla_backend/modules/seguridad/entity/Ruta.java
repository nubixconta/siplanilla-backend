package sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Ruta")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ruta")
    private Long idRuta;

    @Column(name = "rut_path")
    private String rutPath;

    @Column(name = "rut_nombre")
    private String rutNombre;

    @Column(name = "rut_modulo")
    private String rutModulo;

    @Column(name = "rut_activo")
    private Boolean rutActivo;
}
