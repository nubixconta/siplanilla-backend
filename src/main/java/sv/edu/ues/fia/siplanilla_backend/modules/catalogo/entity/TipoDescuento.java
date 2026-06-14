package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TipoDescuento")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoDescuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_descuento")
    private Long idTipoDescuento;

    @Column(name = "tid_nombre")
    private String tidNombre;
}
