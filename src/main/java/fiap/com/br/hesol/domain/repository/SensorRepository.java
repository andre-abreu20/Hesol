package fiap.com.br.hesol.domain.repository;

import fiap.com.br.hesol.domain.model.Sensor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {

    @NotNull Optional<Sensor> findById(Integer id);
}
