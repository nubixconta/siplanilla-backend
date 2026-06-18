package sv.edu.ues.fia.siplanilla_backend.modules.planilla.service;

import sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.response.DetallePlanillaResponse;
import java.util.List;

public interface DetallePlanillaService {
    List<DetallePlanillaResponse> findAll();
    DetallePlanillaResponse findById(Long id);
}