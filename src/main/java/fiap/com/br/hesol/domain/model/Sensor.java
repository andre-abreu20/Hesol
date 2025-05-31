package fiap.com.br.hesol.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "HS_T_SENSOR")
public class Sensor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sensor")
    private Integer id_sensor;

    private Boolean status;

    private String modelo;

    @ManyToOne
    @JoinColumn(name = "HS_T_LOCAL_id_local")
    private Local local;

}
