package sv.edu.ues.fia.siplanilla_backend.modules.organizacion.entity;

import jakarta.persistence.*;
import lombok.*;
import sv.edu.ues.fia.siplanilla_backend.modules.empresa.entity.Empresa;

@Entity
@Table(name = "UnidadOrganizativa")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnidadOrganizativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unidad")
    private Long idUnidad;

    @Column(name = "uni_nombre")
    private String uniNombre;

    @Column(name = "uni_tipo")
    private String uniTipo;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad_padre")
    private UnidadOrganizativa padre;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;
}
