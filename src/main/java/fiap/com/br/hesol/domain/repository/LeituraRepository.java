package fiap.com.br.hesol.domain.repository;

import fiap.com.br.hesol.domain.model.Leitura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeituraRepository extends JpaRepository<Leitura, Integer> {
    Page<Leitura> findByUsuarioId(Integer idUsuario, Pageable pageable);
}
