package sv.edu.ues.fia.siplanilla_backend.modules.geografico.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Pais")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pais")
    private Long idPais;

    @Column(name = "pai_nombre")
    private String paiNombre;
}
