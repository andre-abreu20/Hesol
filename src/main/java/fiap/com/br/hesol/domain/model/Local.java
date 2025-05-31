package fiap.com.br.hesol.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "HS_T_LOCAL")
public class Local {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id_local")
    private Integer id_local;

    @OneToMany(mappedBy = "local")
    private List<Sensor> sensores = new ArrayList<>();

    private String latitude;
    private String longitude;
    private String descricao;
    private String pais;
}
