package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.mapper;

import org.mapstruct.Mapper;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.request.TipoDescuentoRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.response.TipoDescuentoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.entity.TipoDescuento;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoDescuentoMapper {

    TipoDescuento toEntity(TipoDescuentoRequest request);
    TipoDescuentoResponse toResponse(TipoDescuento entity);
    List<TipoDescuentoResponse> toResponseList(List<TipoDescuento> entities);
}
