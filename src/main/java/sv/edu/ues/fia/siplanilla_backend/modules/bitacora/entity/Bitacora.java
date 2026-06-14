package sv.edu.ues.fia.siplanilla_backend.modules.bitacora.entity;

import jakarta.persistence.*;
import lombok.*;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity.Usuario;
import java.time.LocalDateTime;

@Entity
@Table(name = "Bitacora")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bitacora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bitacora")
    private Long idBitacora;

    @Column(name = "bit_fecha")
    private LocalDateTime bitFecha;

    @Column(name = "bit_accion")
    private String bitAccion;

    @Column(name = "bit_tabla")
    private String bitTabla;

    @Column(name = "bit_descripcion")
    private String bitDescripcion;

    @Column(name = "bit_ip")
    private String bitIp;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
