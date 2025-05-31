package fiap.com.br.hesol.domain.service;

import fiap.com.br.hesol.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.stereotype.Service;
import fiap.com.br.hesol.api.mapper.UsuarioMapper;

@Service
@RequiredArgsConstructor
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class UsuarioService {
    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;
}
