package sv.edu.ues.fia.siplanilla_backend.modules.planilla.entity;

import jakarta.persistence.*;
import lombok.*;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.entity.Empleado;
import java.math.BigDecimal;

@Entity
@Table(name = "DetallePlanilla")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetallePlanilla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_planilla")
    private Long idDetallePlanilla;

    @Column(name = "det_salario_base")
    private BigDecimal detSalarioBase;

    @Column(name = "det_total_ingresos")
    private BigDecimal detTotalIngresos;

    @Column(name = "det_total_descuentos")
    private BigDecimal detTotalDescuentos;

    @Column(name = "det_salario_neto")
    private BigDecimal detSalarioNeto;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_planilla")
    private Planilla planilla;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;
}
