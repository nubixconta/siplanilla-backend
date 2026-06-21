package sv.edu.ues.fia.siplanilla_backend.modules.empleado.service.impl;

import sv.edu.ues.fia.siplanilla_backend.exception.ResourceNotFoundException;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto.request.DireccionEmpleadoRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto.request.DireccionEmpleadoUpdateRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto.response.DireccionEmpleadoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.entity.DireccionEmpleado;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.entity.Empleado;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.repository.DireccionEmpleadoRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.repository.EmpleadoRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.service.DireccionEmpleadoService;
import sv.edu.ues.fia.siplanilla_backend.modules.geografico.entity.Municipio;
import sv.edu.ues.fia.siplanilla_backend.modules.geografico.repository.MunicipioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DireccionEmpleadoServiceImpl implements DireccionEmpleadoService {

    private final DireccionEmpleadoRepository direccionEmpleadoRepository;
    private final EmpleadoRepository empleadoRepository;
    private final MunicipioRepository municipioRepository;

    @Override
    public DireccionEmpleadoResponse crear(DireccionEmpleadoRequest request) {
        // Validar que el empleado existe
        Empleado empleado = empleadoRepository.findById(request.getIdEmpleado())
                .orElseThrow(() -> new ResourceNotFoundException("Empleado", request.getIdEmpleado()));

        // Validar que el municipio existe
        Municipio municipio = municipioRepository.findById(request.getIdMunicipio())
                .orElseThrow(() -> new ResourceNotFoundException("Municipio", request.getIdMunicipio()));

        // Crear nueva dirección
        DireccionEmpleado direccion = DireccionEmpleado.builder()
                .dirCalle(request.getDirCalle())
                .dirColonia(request.getDirColonia())
                .dirReferencia(request.getDirReferencia())
                .empleado(empleado)
                .municipio(municipio)
                .build();

        DireccionEmpleado direccionGuardada = direccionEmpleadoRepository.save(direccion);

        return mapearAResponse(direccionGuardada);
    }

    @Override
    public DireccionEmpleadoResponse obtenerPorId(Long id) {
        DireccionEmpleado direccion = direccionEmpleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DireccionEmpleado", id));

        return mapearAResponse(direccion);
    }

    @Override
    public List<DireccionEmpleadoResponse> obtenerPorEmpleado(Long idEmpleado) {
        // Validar que el empleado existe
        empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado", idEmpleado));

        return direccionEmpleadoRepository.findByEmpleado_IdEmpleado(idEmpleado)
                .stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DireccionEmpleadoResponse actualizar(Long id, DireccionEmpleadoUpdateRequest request) {
        DireccionEmpleado direccion = direccionEmpleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DireccionEmpleado", id));

        // Validar que el municipio existe
        Municipio municipio = municipioRepository.findById(request.getIdMunicipio())
                .orElseThrow(() -> new ResourceNotFoundException("Municipio", request.getIdMunicipio()));

        // Actualizar todos los campos
        direccion.setDirCalle(request.getDirCalle());
        direccion.setDirColonia(request.getDirColonia());
        direccion.setDirReferencia(request.getDirReferencia());
        direccion.setMunicipio(municipio);

        DireccionEmpleado direccionActualizada = direccionEmpleadoRepository.save(direccion);
        return mapearAResponse(direccionActualizada);
    }

    @Override
    public DireccionEmpleadoResponse actualizarParcial(Long id, DireccionEmpleadoUpdateRequest request) {
        DireccionEmpleado direccion = direccionEmpleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DireccionEmpleado", id));

        // Actualizar solo los campos que no son nulos
        if (request.getDirCalle() != null) {
            direccion.setDirCalle(request.getDirCalle());
        }
        if (request.getDirColonia() != null) {
            direccion.setDirColonia(request.getDirColonia());
        }
        if (request.getDirReferencia() != null) {
            direccion.setDirReferencia(request.getDirReferencia());
        }
        if (request.getIdMunicipio() != null) {
            Municipio municipio = municipioRepository.findById(request.getIdMunicipio())
                    .orElseThrow(() -> new ResourceNotFoundException("Municipio", request.getIdMunicipio()));
            direccion.setMunicipio(municipio);
        }

        DireccionEmpleado direccionActualizada = direccionEmpleadoRepository.save(direccion);
        return mapearAResponse(direccionActualizada);
    }

    private DireccionEmpleadoResponse mapearAResponse(DireccionEmpleado direccion) {
        return DireccionEmpleadoResponse.builder()
                .idDireccion(direccion.getIdDireccion())
                .dirCalle(direccion.getDirCalle())
                .dirColonia(direccion.getDirColonia())
                .dirReferencia(direccion.getDirReferencia())
                .idMunicipio(direccion.getMunicipio().getIdMunicipio())
                .municipioNombre(direccion.getMunicipio().getMunNombre())
                .idEmpleado(direccion.getEmpleado().getIdEmpleado())
                .build();
    }
}
