package fiap.com.br.hesol.domain.service;

import fiap.com.br.hesol.domain.model.Usuario;
import fiap.com.br.hesol.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import fiap.com.br.hesol.api.mapper.UsuarioMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@EnableSpringDataWebSupport
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;


    public Optional<Usuario> getUsuarioLogado() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) return Optional.empty();

        Object principal = auth.getPrincipal();
        String email;

        if (principal instanceof UserDetails userDetails) {
            email = userDetails.getUsername();
        } else {
            email = principal.toString(); // fallback
        }

        return repository.findByEmail(email);
    }
}
