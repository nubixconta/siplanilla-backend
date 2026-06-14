package sv.edu.ues.fia.siplanilla_backend.modules.geografico.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Departamento")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departamento")
    private Long idDepartamento;

    @Column(name = "dep_nombre")
    private String depNombre;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pais")
    private Pais pais;
}
