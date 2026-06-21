package sv.edu.ues.fia.siplanilla_backend.modules.empleado.service;

import sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto.request.EmpleadoRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto.request.EmpleadoUpdateRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.empleado.dto.response.EmpleadoResponse;
import java.util.List;

public interface EmpleadoService {
    EmpleadoResponse crear(EmpleadoRequest request);
    EmpleadoResponse obtenerPorId(Long id);
    List<EmpleadoResponse> obtenerTodos();
    EmpleadoResponse actualizar(Long id, EmpleadoRequest request);
    EmpleadoResponse actualizarParcial(Long id, EmpleadoUpdateRequest request);
    EmpleadoResponse cambiarEstado(Long id, Integer estado);
    List<EmpleadoResponse> buscarPorNombre(String nombre);
}
