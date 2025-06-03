package fiap.com.br.hesol.api.controller;

import fiap.com.br.hesol.api.dto.LeituraDTO;
import fiap.com.br.hesol.api.mapper.LeituraMapper;
import fiap.com.br.hesol.domain.model.Leitura;
import fiap.com.br.hesol.domain.repository.LeituraRepository;
import fiap.com.br.hesol.domain.repository.SensorRepository;
import fiap.com.br.hesol.domain.repository.UsuarioRepository;
import fiap.com.br.hesol.domain.service.LeituraService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/leituras")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class LeituraController {

    private final LeituraService leituraService;

    @PostMapping
    public ResponseEntity<?> registrarLeitura(@RequestBody @Valid LeituraDTO dto,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        try {
            Double resultado = leituraService.registrarLeitura(dto, userDetails.getUsername());
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Page<LeituraDTO>> listarLeituras(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        Page<LeituraDTO> leituras = leituraService.listarLeituras(userDetails.getUsername(), page, size);
        return ResponseEntity.ok(leituras);
    }
}
