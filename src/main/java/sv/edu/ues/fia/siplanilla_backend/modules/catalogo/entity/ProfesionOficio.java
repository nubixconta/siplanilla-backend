package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ProfesionOficio")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfesionOficio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesion")
    private Long idProfesion;

    @Column(name = "pro_nombre")
    private String proNombre;
}
