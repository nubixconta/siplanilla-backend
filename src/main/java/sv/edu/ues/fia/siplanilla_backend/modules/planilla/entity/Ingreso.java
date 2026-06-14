package sv.edu.ues.fia.siplanilla_backend.modules.planilla.entity;

import jakarta.persistence.*;
import lombok.*;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.entity.TipoIngreso;
import java.math.BigDecimal;

@Entity
@Table(name = "Ingreso")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ingreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingreso")
    private Long idIngreso;

    @Column(name = "ing_monto")
    private BigDecimal ingMonto;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_ingreso")
    private TipoIngreso tipoIngreso;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detalle_planilla")
    private DetallePlanilla detallePlanilla;
}
