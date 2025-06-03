package fiap.com.br.hesol.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest (

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email ou senha invalido")
        String email,

        @NotBlank(message = "Senha é obrigatória")
        String senha
){
}
