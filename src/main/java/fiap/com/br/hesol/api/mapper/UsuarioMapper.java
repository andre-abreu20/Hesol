package fiap.com.br.hesol.api.mapper;

import fiap.com.br.hesol.api.dto.UsuarioDTO;
import fiap.com.br.hesol.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {


    Usuario toEntity(UsuarioDTO dto);

    @Mapping(target = "leituras", ignore = true)
    UsuarioDTO toDTO(Usuario entity);
}