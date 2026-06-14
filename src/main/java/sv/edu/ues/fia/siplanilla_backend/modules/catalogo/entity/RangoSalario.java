package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "RangoSalario")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RangoSalario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rango")
    private Long idRango;

    @Column(name = "rsa_salario_min")
    private BigDecimal rsaSalarioMin;

    @Column(name = "rsa_salario_max")
    private BigDecimal rsaSalarioMax;
}
