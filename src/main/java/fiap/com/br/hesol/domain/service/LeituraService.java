package fiap.com.br.hesol.domain.service;

import fiap.com.br.hesol.api.dto.LeituraDTO;
import fiap.com.br.hesol.api.mapper.LeituraMapper;
import fiap.com.br.hesol.domain.model.Leitura;
import fiap.com.br.hesol.domain.repository.LeituraRepository;
import fiap.com.br.hesol.domain.repository.SensorRepository;
import fiap.com.br.hesol.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeituraService {

    private final LeituraRepository leituraRepository;
    private final UsuarioRepository usuarioRepository;
    private final SensorRepository sensorRepository;
    private final LeituraMapper mapper;

    @CacheEvict(value = "leiturasCache", allEntries = true)
    public Double registrarLeitura(LeituraDTO dto, String emailUsuario) {
        var usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        var sensor = sensorRepository.findById(dto.getId_sensor())
                .orElseThrow(() -> new RuntimeException("Sensor não encontrado"));

        Leitura leitura = new Leitura();
        leitura.setTemperatura(dto.getTemperatura());
        leitura.setCo2(dto.getCo2());
        leitura.setUmidade(dto.getUmidade());
        leitura.setPoluicao_aqi(dto.getPoluicao_aqi());
        leitura.setUsuario(usuario);
        leitura.setSensor(sensor);
        leitura.setResultado(leitura.CalcularResultado());

        leituraRepository.save(leitura);
        return leitura.getResultado();
    }

    @Cacheable(value = "leiturasCache", key = "#emailUsuario + '-' + #page + '-' + #size")
    public Page<LeituraDTO> listarLeituras(String emailUsuario, int page, int size) {
        var usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Pageable pageable = PageRequest.of(page, size);
        Page<Leitura> leituras = leituraRepository.findByUsuarioId(usuario.getId_usuario(), pageable);
        return leituras.map(mapper::toDTO);
    }
}
