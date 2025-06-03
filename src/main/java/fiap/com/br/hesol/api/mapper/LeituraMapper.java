package fiap.com.br.hesol.api.mapper;

import fiap.com.br.hesol.api.dto.LeituraDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import fiap.com.br.hesol.domain.model.*;

@Mapper(componentModel = "spring")
public interface LeituraMapper {

    @Mapping(source = "id_sensor", target = "sensor", qualifiedByName = "fromSensorId")
    Leitura toEntity(LeituraDTO dto);

    @Mapping(source = "sensor.id", target = "id_sensor")
    LeituraDTO toDTO(Leitura leitura);

    @Named("fromSensorId")
    default Sensor fromSensorId(Integer id) {
        if (id == null) return null;
        Sensor sensor = new Sensor();
        sensor.setId(id);
        return sensor;
    }

    @Named("fromUsuarioId")
    default Usuario fromUsuarioId(Integer id) {
        if (id == null) return null;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return usuario;
    }
}
