package back.caixa.repository;

import back.caixa.model.Enum.TipoPagamento;
import back.caixa.model.entity.Caixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface CaixaRepository extends JpaRepository<Caixa, Long> {

    @Query("SELECT SUM(c.total) FROM Caixa c WHERE c.tipoPagamento = :tipo")
    BigDecimal somarPorTipoPagamento(@Param("tipo")TipoPagamento tipo);

    @Query("SELECT SUM(c.total) FROM Caixa c")
    BigDecimal somarFaturamentoTotal();

}
