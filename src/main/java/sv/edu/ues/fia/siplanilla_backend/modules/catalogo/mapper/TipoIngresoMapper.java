package sv.edu.ues.fia.siplanilla_backend.modules.catalogo.mapper;

import org.mapstruct.Mapper;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.request.TipoIngresoRequest;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.dto.response.TipoIngresoResponse;
import sv.edu.ues.fia.siplanilla_backend.modules.catalogo.entity.TipoIngreso;

import java.util.List;

// PATRÓN DE REFERENCIA
@Mapper(componentModel = "spring")
public interface TipoIngresoMapper {

    TipoIngreso toEntity(TipoIngresoRequest request);
    TipoIngresoResponse toResponse(TipoIngreso entity);
    List<TipoIngresoResponse> toResponseList(List<TipoIngreso> entities);
}
