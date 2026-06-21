package sv.edu.ues.fia.siplanilla_backend.modules.empleado.service;

import sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto.request.DireccionEmpleadoRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto.request.DireccionEmpleadoUpdateRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto.response.DireccionEmpleadoResponse;
import java.util.List;

public interface DireccionEmpleadoService {
    DireccionEmpleadoResponse crear(DireccionEmpleadoRequest request);
    DireccionEmpleadoResponse obtenerPorId(Long id);
    List<DireccionEmpleadoResponse> obtenerPorEmpleado(Long idEmpleado);
    DireccionEmpleadoResponse actualizar(Long id, DireccionEmpleadoUpdateRequest request);
    DireccionEmpleadoResponse actualizarParcial(Long id, DireccionEmpleadoUpdateRequest request);
}
