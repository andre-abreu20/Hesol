package fiap.com.br.hesol.api.dto;

import fiap.com.br.hesol.domain.model.Sensor;
import fiap.com.br.hesol.domain.model.Usuario;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import org.springframework.hateoas.RepresentationModel;

public class LeituraDTO extends RepresentationModel<LeituraDTO> {
    private Integer id_leitura;

    private Integer usuario_id;

    private Integer sensor_id;

    @NotNull(message = "A temperatura não pode ser nula")
    private Double temperatura;

    @NotNull(message = "O CO2 não pode ser nulo")
    private Double co2;

    @NotNull(message = "A umidade não pode ser nula")
    private Double umidade;

    @NotNull(message = "A poluição não pode ser nula")
    private Double poluicao_aqi;

    private Number resultado;
}
