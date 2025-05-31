package fiap.com.br.hesol.domain.repository;

import fiap.com.br.hesol.domain.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {}
