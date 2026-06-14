package sv.edu.ues.fia.siplanilla_backend.modules.organizacion.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "CentroCosto")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CentroCosto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_centro_costo")
    private Long idCentroCosto;

    @Column(name = "ctc_anio")
    private LocalDate ctcAnio;

    @Column(name = "ctc_monto_asignado")
    private BigDecimal ctcMontoAsignado;

    @Column(name = "ctc_monto_disponible")
    private BigDecimal ctcMontoDisponible;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad")
    private UnidadOrganizativa unidad;
}
