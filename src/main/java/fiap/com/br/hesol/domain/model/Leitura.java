package fiap.com.br.hesol.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "HS_T_LEITURA")
@Getter @Setter
public class Leitura {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "leitura_seq")
    @SequenceGenerator(name = "leitura_seq", sequenceName = "LEITURA_SEQ", allocationSize = 1)
    @Column(name = "id_leitura")
    private Long id_leitura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HS_T_USUARIO_id_usuario", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "HS_T_SENSOR_id_sensor", nullable = false)
    private Sensor sensor;

    private Double temperatura;

    private Double co2;

    private Double umidade;

    private Double poluicao_aqi;

    private Double resultado;

    public Leitura() {

    }

    public Leitura(Long id_leitura, Double temperatura, Double co2, Double umidade, Double poluicao_aqi, Double resultado) {
        this.id_leitura = id_leitura;
        this.temperatura = temperatura;
        this.co2 = co2;
        this.umidade = umidade;
        this.poluicao_aqi = poluicao_aqi;
        this.resultado = resultado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public Long getId_leitura() {
        return id_leitura;
    }

    public void setId_leitura(Long id_leitura) {
        this.id_leitura = id_leitura;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
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
        this.resultado = CalcularResultado();
    }

    public Double CalcularResultado(){
        double total = 0;

        // Temperatura
        if (this.temperatura >= 18 && temperatura <= 25) {
            total += 25;
        } else if ((temperatura >= 10 && temperatura <= 17) || (temperatura >= 26 && temperatura <= 32)) {
            total += 15;
        } else {
            total += 5;
        }

        // CO₂
        if (co2 < 500) {
            total += 25;
        } else if (co2 <= 800) {
            total += 15;
        } else {
            total += 5;
        }

        // Umidade
        if (umidade >= 40 && umidade <= 70) {
            total += 25;
        } else if ((umidade >= 30 && umidade <= 39) || (umidade >= 71 && umidade <= 80)) {
            total += 15;
        } else {
            total += 5;
        }

        // Poluição
        if (poluicao_aqi >= 0 && poluicao_aqi <= 50) {
            total += 25;
        } else if (poluicao_aqi <= 100) {
            total += 15;
        } else {
            total += 5;
        }

        return total;
    };
}
