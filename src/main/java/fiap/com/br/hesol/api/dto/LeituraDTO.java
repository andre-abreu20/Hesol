package fiap.com.br.hesol.api.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.hateoas.RepresentationModel;

public class LeituraDTO extends RepresentationModel<LeituraDTO> {

    private Long id_leitura;

    @NotNull(message = "O ID do sensor não pode ser nulo")
    private Integer id_sensor;

    @NotNull(message = "A temperatura não pode ser nula")
    private Double temperatura;

    @NotNull(message = "O CO2 não pode ser nulo")
    private Double co2;

    @NotNull(message = "A umidade não pode ser nula")
    private Double umidade;

    @NotNull(message = "A poluição não pode ser nula")
    private Double poluicao_aqi;

    private Double resultado;

    // Getters e Setters

    public Long getId_leitura() {
        return id_leitura;
    }

    public void setId_leitura(Long id_leitura) {
        this.id_leitura = id_leitura;
    }

    public Integer getId_sensor() {
        return id_sensor;
    }

    public void setId_sensor(Integer id_sensor) {
        this.id_sensor = id_sensor;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getCo2() {
        return co2;
    }

    public void setCo2(Double co2) {
        this.co2 = co2;
    }

    public Double getUmidade() {
        return umidade;
    }

    public void setUmidade(Double umidade) {
        this.umidade = umidade;
    }

    public Double getPoluicao_aqi() {
        return poluicao_aqi;
    }

    public void setPoluicao_aqi(Double poluicao_aqi) {
        this.poluicao_aqi = poluicao_aqi;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }
}
