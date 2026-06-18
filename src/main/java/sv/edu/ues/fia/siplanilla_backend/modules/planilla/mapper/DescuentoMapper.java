package sv.edu.ues.fia.siplanilla_backend.modules.planilla.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.request.DescuentoRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.dto.response.DescuentoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.planilla.entity.Descuento;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DescuentoMapper {

    @Mapping(target = "idDescuento", ignore = true)
    @Mapping(target = "tipoDescuento", ignore = true)
    @Mapping(target = "detallePlanilla", ignore = true)
    Descuento toEntity(DescuentoRequest request);

    @Mapping(source = "tipoDescuento.idTipoDescuento", target = "idTipoDescuento")
    @Mapping(source = "tipoDescuento.tidNombre", target = "tidNombre")
    @Mapping(source = "detallePlanilla.idDetallePlanilla", target = "idDetallePlanilla")
    DescuentoResponse toResponse(Descuento entity);

    List<DescuentoResponse> toResponseList(List<Descuento> entities);
}