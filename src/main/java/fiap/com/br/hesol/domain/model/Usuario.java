package fiap.com.br.hesol.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "HS_T_USUARIO")
@Getter @Setter
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id_usuario;

    private String nome;
    private String email;
    private String senha;

    public Usuario(Integer id_usuario, String nome, String email, String senha, List<Leitura> leituras) {
        this.id_usuario = getId_usuario();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.leituras = leituras;
    }

    @OneToMany(mappedBy = "usuario")
    private List<Leitura> leituras = new ArrayList<>();

    public void setId(Integer id) {
        this.id_usuario = id_usuario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setLeituras(List<Leitura> leituras) {
        this.leituras = leituras;
    }
}
