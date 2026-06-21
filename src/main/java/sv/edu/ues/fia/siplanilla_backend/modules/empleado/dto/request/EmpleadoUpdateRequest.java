package sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto.request;

import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO para actualizaciones parciales (PATCH) de empleados
 * Todos los campos son opcionales
 */
public class EmpleadoUpdateRequest {

    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @JsonProperty("emp_nombre")
    private String empNombre;

    @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
    @JsonProperty("emp_apellido")
    private String empApellido;

    @PastOrPresent(message = "La fecha de ingreso no puede ser en el futuro")
    @JsonProperty("emp_fecha_ingreso")
    private LocalDate empFechaIngreso;

    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    @JsonProperty("emp_fecha_nacimiento")
    private LocalDate empFechaNacimiento;

    @Pattern(regexp = "^\\d{8}-\\d$", message = "El DUI debe estar en formato: 12345678-9")
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

    @DecimalMin(value = "0.0", inclusive = false, message = "El salario base debe ser mayor a 0")
    @JsonProperty("emp_salario_base")
    private BigDecimal empSalarioBase;

    @Email(message = "El correo personal debe ser válido")
    @JsonProperty("emp_correo_personal")
    private String empCorreoPersonal;

    @Email(message = "El correo institucional debe ser válido")
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

    // Getters y Setters
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
}
