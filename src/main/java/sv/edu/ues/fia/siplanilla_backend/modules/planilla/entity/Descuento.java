package sv.edu.ues.fia.siplanilla_backend.modules.planilla.entity;

import jakarta.persistence.*;
import lombok.*;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.entity.TipoDescuento;
import java.math.BigDecimal;

@Entity
@Table(name = "Descuento")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Descuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_descuento")
    private Long idDescuento;

    @Column(name = "des_monto")
    private BigDecimal desMonto;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_descuento")
    private TipoDescuento tipoDescuento;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detalle_planilla")
    private DetallePlanilla detallePlanilla;
}
