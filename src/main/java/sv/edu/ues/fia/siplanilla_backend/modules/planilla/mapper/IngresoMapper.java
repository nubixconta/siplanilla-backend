package sv.edu.ues.fia.siplanilla_backend.modules.planilla.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.request.IngresoRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.response.IngresoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.entity.Ingreso;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IngresoMapper {

    @Mapping(target = "idIngreso", ignore = true)
    @Mapping(target = "tipoIngreso", ignore = true)
    @Mapping(target = "detallePlanilla", ignore = true)
    Ingreso toEntity(IngresoRequest request);

    @Mapping(source = "tipoIngreso.idTipoIngreso", target = "idTipoIngreso")
    @Mapping(source = "tipoIngreso.tigNombre", target = "tigNombre")
    @Mapping(source = "detallePlanilla.idDetallePlanilla", target = "idDetallePlanilla")
    IngresoResponse toResponse(Ingreso entity);

    List<IngresoResponse> toResponseList(List<Ingreso> entities);
}