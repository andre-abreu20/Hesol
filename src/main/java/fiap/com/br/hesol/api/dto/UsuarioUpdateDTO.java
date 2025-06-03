package fiap.com.br.hesol.api.dto;

import lombok.Data;

@Data
public class UsuarioUpdateDTO {
    private String nome;
    private String email;

    public UsuarioUpdateDTO () {

    }

    public UsuarioUpdateDTO(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
