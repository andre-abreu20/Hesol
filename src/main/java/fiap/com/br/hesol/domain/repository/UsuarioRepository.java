package fiap.com.br.hesol.domain.repository;

import fiap.com.br.hesol.domain.model.Usuario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {


    @EntityGraph(attributePaths = "leituras")
    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findById(Integer id);
}
