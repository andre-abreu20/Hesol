package fiap.com.br.hesol.domain.service;

import fiap.com.br.hesol.api.dto.AuthResponse;
import fiap.com.br.hesol.api.dto.LoginRequest;
import fiap.com.br.hesol.api.dto.RegisterRequest;
import fiap.com.br.hesol.api.dto.UsuarioDTO;
import fiap.com.br.hesol.domain.model.Usuario;
import fiap.com.br.hesol.domain.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JWTTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> register(@NotNull RegisterRequest request) {
        if (request.email() == null || request.senha() == null || request.nome() == null ||
                request.email().isBlank() || request.senha().isBlank() || request.nome().isBlank()) {
            return ResponseEntity.badRequest().body("Todos os campos são obrigatórios.");
        }

        if (repository.findByEmail(request.email()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já está em uso.");
        }

        var usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(passwordEncoder.encode(request.senha()));
        repository.save(usuario);

        var token = jwtTokenService.generateToken(usuario.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse(token));
    }

    public ResponseEntity<?> login(@NotNull LoginRequest request) {
        if (request.email() == null || request.senha() == null || request.email().isBlank() || request.senha().isBlank()) {
            return ResponseEntity.badRequest().body("Email e senha são obrigatórios.");
        }

        try {
            var auth = new UsernamePasswordAuthenticationToken(request.email(), request.senha());
            authenticationManager.authenticate(auth);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas.");
        }

        var usuario = repository.findByEmail(request.email());
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado.");
        }

        var token = jwtTokenService.generateToken(request.email());
        return ResponseEntity.ok(new AuthResponse(token));
    }


}