package back.caixa.model.entity;

import back.caixa.model.Enum.TipoPagamento;
import back.clientes.model.entity.Clientes;
import back.livros.model.entity.Livros;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Caixa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataVenda;
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;

    private BigDecimal valorRecebido;
    private BigDecimal troco;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Clientes cliente;

    @OneToMany(mappedBy = "caixa", cascade = CascadeType.ALL)
    private List<Livros> livros = new ArrayList<>();

    public Caixa(){}


    public Caixa(LocalDateTime dataVenda, BigDecimal total, Clientes cliente, List<Livros> livros, TipoPagamento tipoPagamento) {
        this.dataVenda = dataVenda;
        this.total = total;
        this.cliente = cliente;
        this.livros = livros;
        this.tipoPagamento = tipoPagamento;
    }

    public LocalDateTime getDataVenda(){
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda){
        this.dataVenda = dataVenda;
    }

    public BigDecimal getTotal(){
        return total;
    }

    public void setTotal(BigDecimal total){
        this.total = total;
    }


    public Clientes getCliente(){
        return cliente;
    }

    public void setCliente(Clientes cliente){
        this.cliente = cliente;
    }

    public List<Livros> getLivros() {
        return livros;
    }

    public void setLivros(List<Livros> livros) {
        this.livros = livros;
    }

    public TipoPagamento getTipoPagamento(){
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento){
        this.tipoPagamento = tipoPagamento;
    }

    public BigDecimal getValorRecebido(){
        return valorRecebido;
    }

    public void setValorRecebido(BigDecimal valorRecebido){
        this.valorRecebido = valorRecebido;
    }

    public BigDecimal getTroco(){
        return troco;
    }

    public void setTroco(BigDecimal troco){
        this.troco = troco;
    }

}



