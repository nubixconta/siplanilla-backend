package sv.edu.ues.fia.siplanilla_backend.modules.planilla.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.response.DetallePlanillaResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.entity.DetallePlanilla;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DetallePlanillaMapper {

    @Mapping(source = "empleado.idEmpleado", target = "idEmpleado")
    @Mapping(source = "empleado.empNombre", target = "nombreEmpleado")
    @Mapping(source = "planilla.idPlanilla", target = "idPlanilla")
    @Mapping(source = "planilla.periodo", target = "periodo")
    DetallePlanillaResponse toResponse(DetallePlanilla entity);

    List<DetallePlanillaResponse> toResponseList(List<DetallePlanilla> entities);
}