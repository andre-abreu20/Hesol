package fiap.com.br.hesol.domain.repository;

import fiap.com.br.hesol.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {}
