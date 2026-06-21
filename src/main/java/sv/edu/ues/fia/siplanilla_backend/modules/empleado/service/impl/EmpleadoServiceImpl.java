package sv.edu.ues.fia.siplanilla_backend.modules.empleado.service.impl;

import sv.edu.ues.fia.siplanilla_backend.exception.ResourceNotFoundException;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.entity.*;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.repository.*;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto.request.EmpleadoRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto.request.EmpleadoUpdateRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto.response.DireccionEmpleadoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto.response.EmpleadoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.entity.DireccionEmpleado;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.entity.Empleado;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.entity.PuestoTrabajo;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.repository.DireccionEmpleadoRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.repository.EmpleadoRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.service.EmpleadoService;
import sv.edu.ues.fia.siplanilla_backend.modules.empresa.entity.Empresa;
import sv.edu.ues.fia.siplanilla_backend.modules.empresa.repository.EmpresaRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.geografico.entity.Municipio;
import sv.edu.ues.fia.siplanilla_backend.modules.geografico.repository.MunicipioRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.organizacion.entity.UnidadOrganizativa;
import sv.edu.ues.fia.siplanilla_backend.modules.organizacion.repository.UnidadOrganizativaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final DireccionEmpleadoRepository direccionEmpleadoRepository;
    private final SexoRepository sexoRepository;
    private final EstadoCivilRepository estadoCivilRepository;
    private final ProfesionOficioRepository profesionOficioRepository;
    private final PuestoTrabajoRepository puestoTrabajoRepository;
    private final EmpresaRepository empresaRepository;
    private final UnidadOrganizativaRepository unidadOrganizativaRepository;
    private final MunicipioRepository municipioRepository;

    @Override
    @Transactional
    public EmpleadoResponse crear(EmpleadoRequest request) {
        // Validar y obtener referencias de catálogos
        Sexo sexo = sexoRepository.findById(request.getIdSexo())
                .orElseThrow(() -> new ResourceNotFoundException("Sexo", request.getIdSexo()));

        EstadoCivil estadoCivil = estadoCivilRepository.findById(request.getIdEstadoCivil())
                .orElseThrow(() -> new ResourceNotFoundException("Estado civil", request.getIdEstadoCivil()));

        ProfesionOficio profesion = profesionOficioRepository.findById(request.getIdProfesion())
                .orElseThrow(() -> new ResourceNotFoundException("Profesión", request.getIdProfesion()));

        PuestoTrabajo puesto = puestoTrabajoRepository.findById(request.getIdPuesto())
                .orElseThrow(() -> new ResourceNotFoundException("Puesto", request.getIdPuesto()));

        Empresa empresa = empresaRepository.findById(request.getIdEmpresa())
                .orElseThrow(() -> new ResourceNotFoundException("Empresa", request.getIdEmpresa()));

        UnidadOrganizativa unidad = unidadOrganizativaRepository.findById(request.getIdUnidad())
                .orElseThrow(() -> new ResourceNotFoundException("Unidad", request.getIdUnidad()));

        // Obtener empleado superior si existe
        Empleado superior = null;
        if (request.getIdSuperior() != null) {
            superior = empleadoRepository.findById(request.getIdSuperior())
                    .orElseThrow(() -> new ResourceNotFoundException("Empleado", request.getIdSuperior()));
        }

        // Crear nuevo empleado
        Empleado empleado = Empleado.builder()
                .empNombre(request.getEmpNombre())
                .empApellido(request.getEmpApellido())
                .empFechaIngreso(request.getEmpFechaIngreso())
                .empFechaNacimiento(request.getEmpFechaNacimiento())
                .empDui(request.getEmpDui())
                .empEstado(request.getEmpEstado())
                .empNit(request.getEmpNit())
                .empNup(request.getEmpNup())
                .empIsss(request.getEmpIsss())
                .empSalarioBase(request.getEmpSalarioBase())
                .empCorreoPersonal(request.getEmpCorreoPersonal())
                .empCorreoInstitucional(request.getEmpCorreoInstitucional())
                .empRepresentanteLegal(request.getEmpRepresentanteLegal())
                .sexo(sexo)
                .estadoCivil(estadoCivil)
                .profesionOficio(profesion)
                .puestoTrabajo(puesto)
                .empresa(empresa)
                .unidad(unidad)
                .superior(superior)
                .build();

        Empleado empleadoGuardado = empleadoRepository.save(empleado);

        // Crear dirección si se proporciona
        if (request.getDireccion() != null) {
            Municipio municipio = municipioRepository.findById(request.getDireccion().getIdMunicipio())
                    .orElseThrow(() -> new ResourceNotFoundException("Municipio", request.getDireccion().getIdMunicipio()));

            DireccionEmpleado direccion = DireccionEmpleado.builder()
                    .dirCalle(request.getDireccion().getDirCalle())
                    .dirColonia(request.getDireccion().getDirColonia())
                    .dirReferencia(request.getDireccion().getDirReferencia())
                    .empleado(empleadoGuardado)
                    .municipio(municipio)
                    .build();

            direccionEmpleadoRepository.save(direccion);
        }

        return mapearAResponse(empleadoGuardado);
    }

    @Override
    public EmpleadoResponse obtenerPorId(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado", id));
        return mapearAResponse(empleado);
    }

    @Override
    public List<EmpleadoResponse> obtenerTodos() {
        return empleadoRepository.findAll()
                .stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EmpleadoResponse actualizar(Long id, EmpleadoRequest request) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado", id));

        // Validar y obtener referencias de catálogos
        Sexo sexo = sexoRepository.findById(request.getIdSexo())
                .orElseThrow(() -> new ResourceNotFoundException("Sexo", request.getIdSexo()));

        EstadoCivil estadoCivil = estadoCivilRepository.findById(request.getIdEstadoCivil())
                .orElseThrow(() -> new ResourceNotFoundException("Estado civil", request.getIdEstadoCivil()));

        ProfesionOficio profesion = profesionOficioRepository.findById(request.getIdProfesion())
                .orElseThrow(() -> new ResourceNotFoundException("Profesión", request.getIdProfesion()));

        PuestoTrabajo puesto = puestoTrabajoRepository.findById(request.getIdPuesto())
                .orElseThrow(() -> new ResourceNotFoundException("Puesto", request.getIdPuesto()));

        Empresa empresa = empresaRepository.findById(request.getIdEmpresa())
                .orElseThrow(() -> new ResourceNotFoundException("Empresa", request.getIdEmpresa()));

        UnidadOrganizativa unidad = unidadOrganizativaRepository.findById(request.getIdUnidad())
                .orElseThrow(() -> new ResourceNotFoundException("Unidad", request.getIdUnidad()));

        // Obtener empleado superior si existe
        Empleado superior = null;
        if (request.getIdSuperior() != null) {
            superior = empleadoRepository.findById(request.getIdSuperior())
                    .orElseThrow(() -> new ResourceNotFoundException("Empleado", request.getIdSuperior()));
        }

        // Actualizar empleado
        empleado.setEmpNombre(request.getEmpNombre());
        empleado.setEmpApellido(request.getEmpApellido());
        empleado.setEmpFechaIngreso(request.getEmpFechaIngreso());
        empleado.setEmpFechaNacimiento(request.getEmpFechaNacimiento());
        empleado.setEmpDui(request.getEmpDui());
        empleado.setEmpEstado(request.getEmpEstado());
        empleado.setEmpNit(request.getEmpNit());
        empleado.setEmpNup(request.getEmpNup());
        empleado.setEmpIsss(request.getEmpIsss());
        empleado.setEmpSalarioBase(request.getEmpSalarioBase());
        empleado.setEmpCorreoPersonal(request.getEmpCorreoPersonal());
        empleado.setEmpCorreoInstitucional(request.getEmpCorreoInstitucional());
        empleado.setEmpRepresentanteLegal(request.getEmpRepresentanteLegal());
        empleado.setSexo(sexo);
        empleado.setEstadoCivil(estadoCivil);
        empleado.setProfesionOficio(profesion);
        empleado.setPuestoTrabajo(puesto);
        empleado.setEmpresa(empresa);
        empleado.setUnidad(unidad);
        empleado.setSuperior(superior);

        Empleado empleadoActualizado = empleadoRepository.save(empleado);
        return mapearAResponse(empleadoActualizado);
    }

    @Override
    public EmpleadoResponse actualizarParcial(Long id, EmpleadoUpdateRequest request) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado", id));

        // Actualizar solo los campos que no son nulos
        if (request.getEmpNombre() != null) {
            empleado.setEmpNombre(request.getEmpNombre());
        }
        if (request.getEmpApellido() != null) {
            empleado.setEmpApellido(request.getEmpApellido());
        }
        if (request.getEmpFechaIngreso() != null) {
            empleado.setEmpFechaIngreso(request.getEmpFechaIngreso());
        }
        if (request.getEmpFechaNacimiento() != null) {
            empleado.setEmpFechaNacimiento(request.getEmpFechaNacimiento());
        }
        if (request.getEmpDui() != null) {
            empleado.setEmpDui(request.getEmpDui());
        }
        if (request.getEmpEstado() != null) {
            empleado.setEmpEstado(request.getEmpEstado());
        }
        if (request.getEmpNit() != null) {
            empleado.setEmpNit(request.getEmpNit());
        }
        if (request.getEmpNup() != null) {
            empleado.setEmpNup(request.getEmpNup());
        }
        if (request.getEmpIsss() != null) {
            empleado.setEmpIsss(request.getEmpIsss());
        }
        if (request.getEmpSalarioBase() != null) {
            empleado.setEmpSalarioBase(request.getEmpSalarioBase());
        }
        if (request.getEmpCorreoPersonal() != null) {
            empleado.setEmpCorreoPersonal(request.getEmpCorreoPersonal());
        }
        if (request.getEmpCorreoInstitucional() != null) {
            empleado.setEmpCorreoInstitucional(request.getEmpCorreoInstitucional());
        }
        if (request.getEmpRepresentanteLegal() != null) {
            empleado.setEmpRepresentanteLegal(request.getEmpRepresentanteLegal());
        }
        
        // Actualizar referencias solo si se proporcionan
        if (request.getIdSexo() != null) {
            Sexo sexo = sexoRepository.findById(request.getIdSexo())
                    .orElseThrow(() -> new ResourceNotFoundException("Sexo", request.getIdSexo()));
            empleado.setSexo(sexo);
        }
        if (request.getIdEstadoCivil() != null) {
            EstadoCivil estadoCivil = estadoCivilRepository.findById(request.getIdEstadoCivil())
                    .orElseThrow(() -> new ResourceNotFoundException("Estado civil", request.getIdEstadoCivil()));
            empleado.setEstadoCivil(estadoCivil);
        }
        if (request.getIdProfesion() != null) {
            ProfesionOficio profesion = profesionOficioRepository.findById(request.getIdProfesion())
                    .orElseThrow(() -> new ResourceNotFoundException("Profesión", request.getIdProfesion()));
            empleado.setProfesionOficio(profesion);
        }
        if (request.getIdPuesto() != null) {
            PuestoTrabajo puesto = puestoTrabajoRepository.findById(request.getIdPuesto())
                    .orElseThrow(() -> new ResourceNotFoundException("Puesto", request.getIdPuesto()));
            empleado.setPuestoTrabajo(puesto);
        }
        if (request.getIdEmpresa() != null) {
            Empresa empresa = empresaRepository.findById(request.getIdEmpresa())
                    .orElseThrow(() -> new ResourceNotFoundException("Empresa", request.getIdEmpresa()));
            empleado.setEmpresa(empresa);
        }
        if (request.getIdUnidad() != null) {
            UnidadOrganizativa unidad = unidadOrganizativaRepository.findById(request.getIdUnidad())
                    .orElseThrow(() -> new ResourceNotFoundException("Unidad", request.getIdUnidad()));
            empleado.setUnidad(unidad);
        }
        if (request.getIdSuperior() != null) {
            Empleado superior = empleadoRepository.findById(request.getIdSuperior())
                    .orElseThrow(() -> new ResourceNotFoundException("Empleado", request.getIdSuperior()));
            empleado.setSuperior(superior);
        }

        Empleado empleadoActualizado = empleadoRepository.save(empleado);
        return mapearAResponse(empleadoActualizado);
    }

    @Override
    @Transactional
    public EmpleadoResponse cambiarEstado(Long id, Integer estado) {
        // Validar que el empleado existe
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado", id));

        // Validar estado
        if (estado == null || (estado != 0 && estado != 1)) {
            throw new RuntimeException("Estado inválido. Use 1=Activo o 0=Inactivo.");
        }

        // Llamar al procedimiento Oracle para cambiar estado
        try {
            empleadoRepository.cambiarEstadoEmpleado(id, estado);
        } catch (Exception e) {
            throw new RuntimeException("Error al cambiar estado del empleado: " + e.getMessage());
        }

        // Obtener el empleado actualizado
        Empleado empleadoActualizado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado", id));

        return mapearAResponse(empleadoActualizado);
    }

    @Override
    public List<EmpleadoResponse> buscarPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new RuntimeException("El nombre de búsqueda no puede estar vacío");
        }
        
        return empleadoRepository.buscarPorNombre(nombre.trim())
                .stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    private EmpleadoResponse mapearAResponse(Empleado empleado) {
        EmpleadoResponse response = new EmpleadoResponse(
                empleado.getIdEmpleado(),
                empleado.getEmpNombre(),
                empleado.getEmpApellido(),
                empleado.getEmpFechaIngreso(),
                empleado.getEmpFechaNacimiento(),
                empleado.getEmpDui(),
                empleado.getEmpEstado(),
                empleado.getEmpNit(),
                empleado.getEmpNup(),
                empleado.getEmpIsss(),
                empleado.getEmpSalarioBase(),
                empleado.getEmpCorreoPersonal(),
                empleado.getEmpCorreoInstitucional(),
                empleado.getEmpRepresentanteLegal(),
                empleado.getSexo() != null ? empleado.getSexo().getIdSexo() : null,
                empleado.getEstadoCivil() != null ? empleado.getEstadoCivil().getIdEstadoCivil() : null,
                empleado.getProfesionOficio() != null ? empleado.getProfesionOficio().getIdProfesion() : null,
                empleado.getPuestoTrabajo() != null ? empleado.getPuestoTrabajo().getIdPuesto() : null,
                empleado.getEmpresa() != null ? empleado.getEmpresa().getIdEmpresa() : null,
                empleado.getUnidad() != null ? empleado.getUnidad().getIdUnidad() : null,
                empleado.getSuperior() != null ? empleado.getSuperior().getIdEmpleado() : null
        );

        // Cargar la primera dirección del empleado si existe
        List<DireccionEmpleado> direcciones = direccionEmpleadoRepository.findByEmpleado_IdEmpleado(empleado.getIdEmpleado());
        if (!direcciones.isEmpty()) {
            DireccionEmpleado dir = direcciones.get(0);
            DireccionEmpleadoResponse dirResponse = DireccionEmpleadoResponse.builder()
                    .idDireccion(dir.getIdDireccion())
                    .dirCalle(dir.getDirCalle())
                    .dirColonia(dir.getDirColonia())
                    .dirReferencia(dir.getDirReferencia())
                    .idMunicipio(dir.getMunicipio().getIdMunicipio())
                    .municipioNombre(dir.getMunicipio().getMunNombre())
                    .idEmpleado(dir.getEmpleado().getIdEmpleado())
                    .build();
            response.setDireccion(dirResponse);
        }

        return response;
    }
}
