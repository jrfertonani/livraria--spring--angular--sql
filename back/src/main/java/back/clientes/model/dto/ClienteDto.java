package back.clientes.model.dto;

import back.Endereco.domain.EnderecoDTO;
import back.livros.model.entity.Livros;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {

    private String nome;
    private String telefone;

    private List<Livros> livros;

    private EnderecoDTO endereco;

}
