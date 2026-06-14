package sv.edu.ues.fia.siplanilla_backend.modules.planilla.entity;

import jakarta.persistence.*;
import lombok.*;
import sv.edu.ues.fia.siplanilla_backend.modules.empresa.entity.Empresa;
import java.time.LocalDate;

@Entity
@Table(name = "Planilla")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Planilla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_planilla")
    private Long idPlanilla;

    @Column(name = "pla_fecha")
    private LocalDate plaFecha;

    @Column(name = "periodo")
    private String periodo;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;
}
