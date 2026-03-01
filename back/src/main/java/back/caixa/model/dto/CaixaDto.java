package back.caixa.model.dto;

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


}
