package fiap.com.br.hesol.api.dto;

import fiap.com.br.hesol.domain.model.Leitura;
import org.mapstruct.Mapper;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Mapper(componentModel = "spring")
public class UsuarioDTO extends RepresentationModel<UsuarioDTO> {

    private Integer id_usuario;
    private String nome;
    private String email;
    private List<Leitura> leituras;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Integer id_usuario, String nome, String email, List<Leitura> leituras) {
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.email = email;
        this.leituras = leituras;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
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

    public List<Leitura> getLeituras() {
        return leituras;
    }

    public void setLeituras(List<Leitura> leituras) {
        this.leituras = leituras;
    }
}
