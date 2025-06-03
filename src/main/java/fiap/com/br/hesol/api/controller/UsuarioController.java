package fiap.com.br.hesol.api.controller;

import fiap.com.br.hesol.api.dto.UsuarioDTO;
import fiap.com.br.hesol.api.dto.UsuarioUpdateDTO;
import fiap.com.br.hesol.api.mapper.UsuarioMapper;
import fiap.com.br.hesol.domain.model.Usuario;
import fiap.com.br.hesol.domain.repository.UsuarioRepository;
import fiap.com.br.hesol.domain.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioMapper mapper;
    private final UsuarioRepository repository;


    @GetMapping("/me")
    public ResponseEntity<UsuarioDTO> getUsuarioLogado() {
        return service.getUsuarioLogado()
                .map(usuario -> ResponseEntity.ok(mapper.toDTO(usuario)))
                .orElseGet(() -> ResponseEntity.status(401).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarUsuario (@PathVariable Integer id,
                                              @RequestBody UsuarioUpdateDTO dto,  @AuthenticationPrincipal UserDetails userDetails) {
        var usuarioOptional = repository.findById(id);


        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var usuario = usuarioOptional.get();

        if (!usuario.getEmail().equals(userDetails.getUsername())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Você não tem permissão para altera outro usuário");
        }

        if (dto.getNome() != null) {
            usuario.setNome(dto.getNome());
        }

        if (dto.getEmail() != null) {
            usuario.setEmail(dto.getEmail());
        }

        repository.save(usuario);

        return ResponseEntity.ok("Usuário atualizado com sucesso.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Integer id,
                                            @AuthenticationPrincipal UserDetails userDetails) {
        var usuario = repository.findById(id).orElse(null);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        if (!usuario.getEmail().equals(userDetails.getUsername())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Você não tem permissão para deletar este usuário");
        }

        repository.delete(usuario);
        return ResponseEntity.noContent().build();
    }


}
