package back.livros.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class LivroDto {

    private String nome;
    private BigDecimal preco;
    private String autor;

}
