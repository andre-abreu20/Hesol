package fiap.com.br.hesol.domain.model;

import fiap.com.br.hesol.domain.converter.StatusAtivoConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "HS_T_SENSOR")
public class Sensor {

    @Id
    @Column(name = "id_sensor")
    private Integer id;

    @Column(name = "status")
    @Convert(converter = StatusAtivoConverter.class)
    private Boolean ativo;

    private String modelo;

    @ManyToOne
    @JoinColumn(name = "HS_T_LOCAL_id_local")
    private Local local;

    public Sensor (){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
