package back.caixa.model.dto;

import back.caixa.model.Enum.TipoPagamento;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class CaixaDto {

    private LocalDateTime dataVenda;
    private BigDecimal total;

    private Long clienteId;
    private List<Long> livrosIds;

    private TipoPagamento tipoPagamento;

    private BigDecimal valorRecebido;
    private BigDecimal troco;



}