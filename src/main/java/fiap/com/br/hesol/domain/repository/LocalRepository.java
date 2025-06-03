package fiap.com.br.hesol.domain.repository;

import fiap.com.br.hesol.domain.model.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalRepository extends JpaRepository<Local, Integer> {}
