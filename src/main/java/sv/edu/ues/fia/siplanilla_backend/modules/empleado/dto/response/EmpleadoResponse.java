package sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDate;

public class EmpleadoResponse {

    @JsonProperty("id_empleado")
    private Long idEmpleado;

    @JsonProperty("emp_nombre")
    private String empNombre;

    @JsonProperty("emp_apellido")
    private String empApellido;

    @JsonProperty("emp_fecha_ingreso")
    private LocalDate empFechaIngreso;

    @JsonProperty("emp_fecha_nacimiento")
    private LocalDate empFechaNacimiento;

    @JsonProperty("emp_dui")
    private String empDui;

    @JsonProperty("emp_estado")
    private Boolean empEstado;

    @JsonProperty("emp_nit")
    private String empNit;

    @JsonProperty("emp_nup")
    private String empNup;

    @JsonProperty("emp_isss")
    private String empIsss;

    @JsonProperty("emp_salario_base")
    private BigDecimal empSalarioBase;

    @JsonProperty("emp_correo_personal")
    private String empCorreoPersonal;

    @JsonProperty("emp_correo_institucional")
    private String empCorreoInstitucional;

    @JsonProperty("emp_representante_legal")
    private Boolean empRepresentanteLegal;

    @JsonProperty("id_sexo")
    private Long idSexo;

    @JsonProperty("id_estado_civil")
    private Long idEstadoCivil;

    @JsonProperty("id_profesion")
    private Long idProfesion;

    @JsonProperty("id_puesto")
    private Long idPuesto;

    @JsonProperty("id_empresa")
    private Long idEmpresa;

    @JsonProperty("id_unidad")
    private Long idUnidad;

    @JsonProperty("id_superior")
    private Long idSuperior;

    @JsonProperty("direccion")
    private DireccionEmpleadoResponse direccion;

    // Constructor
    public EmpleadoResponse() {}

    public EmpleadoResponse(Long idEmpleado, String empNombre, String empApellido, LocalDate empFechaIngreso,
            LocalDate empFechaNacimiento, String empDui, Boolean empEstado, String empNit, String empNup,
            String empIsss, BigDecimal empSalarioBase, String empCorreoPersonal, String empCorreoInstitucional,
            Boolean empRepresentanteLegal, Long idSexo, Long idEstadoCivil, Long idProfesion, Long idPuesto,
            Long idEmpresa, Long idUnidad, Long idSuperior) {
        this.idEmpleado = idEmpleado;
        this.empNombre = empNombre;
        this.empApellido = empApellido;
        this.empFechaIngreso = empFechaIngreso;
        this.empFechaNacimiento = empFechaNacimiento;
        this.empDui = empDui;
        this.empEstado = empEstado;
        this.empNit = empNit;
        this.empNup = empNup;
        this.empIsss = empIsss;
        this.empSalarioBase = empSalarioBase;
        this.empCorreoPersonal = empCorreoPersonal;
        this.empCorreoInstitucional = empCorreoInstitucional;
        this.empRepresentanteLegal = empRepresentanteLegal;
        this.idSexo = idSexo;
        this.idEstadoCivil = idEstadoCivil;
        this.idProfesion = idProfesion;
        this.idPuesto = idPuesto;
        this.idEmpresa = idEmpresa;
        this.idUnidad = idUnidad;
        this.idSuperior = idSuperior;
    }

    // Getters y Setters
    public Long getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(Long idEmpleado) { this.idEmpleado = idEmpleado; }

    public String getEmpNombre() { return empNombre; }
    public void setEmpNombre(String empNombre) { this.empNombre = empNombre; }

    public String getEmpApellido() { return empApellido; }
    public void setEmpApellido(String empApellido) { this.empApellido = empApellido; }

    public LocalDate getEmpFechaIngreso() { return empFechaIngreso; }
    public void setEmpFechaIngreso(LocalDate empFechaIngreso) { this.empFechaIngreso = empFechaIngreso; }

    public LocalDate getEmpFechaNacimiento() { return empFechaNacimiento; }
    public void setEmpFechaNacimiento(LocalDate empFechaNacimiento) { this.empFechaNacimiento = empFechaNacimiento; }

    public String getEmpDui() { return empDui; }
    public void setEmpDui(String empDui) { this.empDui = empDui; }

    public Boolean getEmpEstado() { return empEstado; }
    public void setEmpEstado(Boolean empEstado) { this.empEstado = empEstado; }

    public String getEmpNit() { return empNit; }
    public void setEmpNit(String empNit) { this.empNit = empNit; }

    public String getEmpNup() { return empNup; }
    public void setEmpNup(String empNup) { this.empNup = empNup; }

    public String getEmpIsss() { return empIsss; }
    public void setEmpIsss(String empIsss) { this.empIsss = empIsss; }

    public BigDecimal getEmpSalarioBase() { return empSalarioBase; }
    public void setEmpSalarioBase(BigDecimal empSalarioBase) { this.empSalarioBase = empSalarioBase; }

    public String getEmpCorreoPersonal() { return empCorreoPersonal; }
    public void setEmpCorreoPersonal(String empCorreoPersonal) { this.empCorreoPersonal = empCorreoPersonal; }

    public String getEmpCorreoInstitucional() { return empCorreoInstitucional; }
    public void setEmpCorreoInstitucional(String empCorreoInstitucional) { this.empCorreoInstitucional = empCorreoInstitucional; }

    public Boolean getEmpRepresentanteLegal() { return empRepresentanteLegal; }
    public void setEmpRepresentanteLegal(Boolean empRepresentanteLegal) { this.empRepresentanteLegal = empRepresentanteLegal; }

    public Long getIdSexo() { return idSexo; }
    public void setIdSexo(Long idSexo) { this.idSexo = idSexo; }

    public Long getIdEstadoCivil() { return idEstadoCivil; }
    public void setIdEstadoCivil(Long idEstadoCivil) { this.idEstadoCivil = idEstadoCivil; }

    public Long getIdProfesion() { return idProfesion; }
    public void setIdProfesion(Long idProfesion) { this.idProfesion = idProfesion; }

    public Long getIdPuesto() { return idPuesto; }
    public void setIdPuesto(Long idPuesto) { this.idPuesto = idPuesto; }

    public Long getIdEmpresa() { return idEmpresa; }
    public void setIdEmpresa(Long idEmpresa) { this.idEmpresa = idEmpresa; }

    public Long getIdUnidad() { return idUnidad; }
    public void setIdUnidad(Long idUnidad) { this.idUnidad = idUnidad; }

    public Long getIdSuperior() { return idSuperior; }
    public void setIdSuperior(Long idSuperior) { this.idSuperior = idSuperior; }

    public DireccionEmpleadoResponse getDireccion() { return direccion; }
    public void setDireccion(DireccionEmpleadoResponse direccion) { this.direccion = direccion; }
}
