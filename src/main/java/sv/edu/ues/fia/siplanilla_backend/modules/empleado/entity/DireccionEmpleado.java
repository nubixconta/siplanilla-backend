package sv.edu.ues.fia.siplanilla_backend.modules.empleado.entity;

import jakarta.persistence.*;
import lombok.*;
import sv.edu.ues.fia.siplanilla_backend.modules.geografico.entity.Municipio;

@Entity
@Table(name = "DireccionEmpleado")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DireccionEmpleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion")
    private Long idDireccion;

    @Column(name = "dir_calle")
    private String dirCalle;

    @Column(name = "dir_colonia")
    private String dirColonia;

    @Column(name = "dir_referencia")
    private String dirReferencia;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_municipio")
    private Municipio municipio;
}
