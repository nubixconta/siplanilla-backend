package sv.edu.ues.fia.siplanilla_backend.modules.empresa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Empresa")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Long idEmpresa;

    @Column(name = "epr_nombre")
    private String eprNombre;

    @Column(name = "epr_direccion")
    private String eprDireccion;

    @Column(name = "epr_nit")
    private String eprNit;

    @Column(name = "epr_nic")
    private String eprNic;

    @Column(name = "epr_telefono")
    private String eprTelefono;

    @Column(name = "epr_correo")
    private String eprCorreo;
}
