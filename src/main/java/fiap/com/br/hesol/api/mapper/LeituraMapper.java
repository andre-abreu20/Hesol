package fiap.com.br.hesol.api.mapper;

import fiap.com.br.hesol.api.dto.LeituraDTO;
import fiap.com.br.hesol.domain.model.Leitura;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LeituraMapper {

    @Mapping(source = "sensorId", target = "sensor.id")
    @Mapping(source = "usuarioId", target = "usuario.id")
    Leitura toEntity(LeituraDTO dto);
}