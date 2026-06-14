package sv.edu.ues.fia.siplanilla_backend.modules.geografico.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Municipio")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_municipio")
    private Long idMunicipio;

    @Column(name = "mun_nombre")
    private String munNombre;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;
}
