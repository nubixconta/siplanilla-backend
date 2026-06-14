package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Sexo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sexo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sexo")
    private Long idSexo;

    @Column(name = "sex_nombre")
    private String sexNombre;
}
