package fiap.com.br.hesol.api.dto;

import fiap.com.br.hesol.domain.model.Leitura;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDTO extends RepresentationModel<UsuarioDTO> {

    Integer id_usuario;

    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser em branco")
    String nome;

    @NotNull(message = "O email não pode ser nulo")
    @NotBlank(message = "O email não pode ser em branco")
    String email;

    @NotNull(message = "A senha não pode ser nula")
    @NotBlank(message = "A senha não pode ser em branco")
    String senha;

    List<Leitura> leituras = new ArrayList<Leitura>();

    public UsuarioDTO(){

    }

    public UsuarioDTO(Integer id_usuario, String nome, String email, String senha, List<Leitura> leituras) {
        super();
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.leituras = leituras;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
