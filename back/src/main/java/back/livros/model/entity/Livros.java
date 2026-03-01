package back.livros.model.entity;

import back.caixa.model.entity.Caixa;
import back.clientes.model.entity.Clientes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Livros implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private BigDecimal preco;
    private String autor;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cliente_id",nullable = true)
    private Clientes cliente;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "caixa_id")
    private Caixa caixa;

    public BigDecimal getPreco(){
        return (this.preco == null) ? BigDecimal.ZERO : this.preco;
    }

}
