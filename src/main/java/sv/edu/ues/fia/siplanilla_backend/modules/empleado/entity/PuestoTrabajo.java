package sv.edu.ues.fia.siplanilla_backend.modules.empleado.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "PuestoTrabajo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PuestoTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_puesto")
    private Long idPuesto;

    @Column(name = "pst_nombre")
    private String pstNombre;

    @Column(name = "pst_fecha")
    private LocalDate pstFecha;
}
