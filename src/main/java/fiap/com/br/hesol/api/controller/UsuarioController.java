package fiap.com.br.hesol.api.controller;

import fiap.com.br.hesol.api.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping("/{id}")
    public UsuarioDTO getById(@PathVariable int pagina, @RequestParam int itens) {
        return service.buscarPorId(id);
    }
}
