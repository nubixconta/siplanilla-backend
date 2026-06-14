package sv.edu.ues.fia.siplanilla_backend.modules.empleado.entity;

import jakarta.persistence.*;
import lombok.*;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.entity.*;
import sv.edu.ues.fia.siplanilla_backend.modules.empresa.entity.Empresa;
import sv.edu.ues.fia.siplanilla_backend.modules.organizacion.entity.UnidadOrganizativa;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Empleado")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Long idEmpleado;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_emp_supeior")
    private Empleado superior;

    @Column(name = "emp_nombre")
    private String empNombre;

    @Column(name = "emp_apellido")
    private String empApellido;

    @Column(name = "emp_fecha_ingreso")
    private LocalDate empFechaIngreso;

    @Column(name = "emp_fecha_nacimiento")
    private LocalDate empFechaNacimiento;

    @Column(name = "emp_DUI")
    private String empDui;

    @Column(name = "emp_estado")
    private Boolean empEstado;

    @Column(name = "emp_NIT")
    private String empNit;

    @Column(name = "emp_NUP")
    private String empNup;

    @Column(name = "emp_ISSS")
    private String empIsss;

    @Column(name = "emp_salario_base")
    private BigDecimal empSalarioBase;

    @Column(name = "emp_correo_personal")
    private String empCorreoPersonal;

    @Column(name = "emp_correo_institucional")
    private String empCorreoInstitucional;

    @Column(name = "emp_representante_legal")
    private Boolean empRepresentanteLegal;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sexo")
    private Sexo sexo;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_civil")
    private EstadoCivil estadoCivil;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesion")
    private ProfesionOficio profesionOficio;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_puesto")
    private PuestoTrabajo puestoTrabajo;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad")
    private UnidadOrganizativa unidad;
}
