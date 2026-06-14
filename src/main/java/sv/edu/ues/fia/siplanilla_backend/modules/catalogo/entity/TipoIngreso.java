package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TipoIngreso")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoIngreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_ingreso")
    private Long idTipoIngreso;

    @Column(name = "tig_nombre")
    private String tigNombre;
}
