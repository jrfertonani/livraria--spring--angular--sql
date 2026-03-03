package back.caixa.service;

import back.caixa.controller.CaixaController;
import back.caixa.model.Enum.TipoPagamento;
import back.caixa.model.dto.CaixaDto;
import back.caixa.model.entity.Caixa;
import back.caixa.repository.CaixaRepository;
import back.clientes.model.entity.Clientes;
import back.clientes.reposiory.ClienteRepository;
import back.livros.model.entity.Livros;
import back.livros.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CaixaService {


    @Autowired
    public CaixaRepository caixaRepository;

    @Autowired
    public ClienteRepository clienteRepository;

    @Autowired
    public LivroRepository livroRepository;

    @Transactional
    public Caixa realizarVenda(CaixaDto caixaDto) {
        // 1. Busca e Prepara dados básicos
        Clientes cliente = clienteRepository.findById(caixaDto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        List<Livros> livrosVendidos = buscarLivros(caixaDto.getLivrosIds(), cliente);
        BigDecimal totalOriginal = calcularTotal(livrosVendidos);

        // 2. Cria a instância da venda
        Caixa caixa = new Caixa();
        caixa.setCliente(cliente);
        caixa.setDataVenda(LocalDateTime.now());
        caixa.setTipoPagamento(caixaDto.getTipoPagamento());
        caixa.setLivros(livrosVendidos);
        livrosVendidos.forEach(l -> l.setCaixa(caixa));

        // 3. Aplica a Regra de Negócio Financeira (A "Mágica")
        aplicarLogicaFinanceira(caixa, caixaDto, totalOriginal);

        return caixaRepository.save(caixa);
    }

// MÉTODOS AUXILIARES PARA LIMPAR O CÓDIGO
    private List<Livros> buscarLivros(List<Long> ids, Clientes cliente) {
        List<Livros> lista = new ArrayList<>();
        for (Long id : ids) {
            Livros livro = livroRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Livro ID " + id + " não encontrado."));
            livro.setCliente(cliente);
            lista.add(livro);
        }
        return lista;
    }

    private BigDecimal calcularTotal(List<Livros> livros) {
        return livros.stream()
                .map(Livros::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void aplicarLogicaFinanceira(Caixa caixa, CaixaDto dto, BigDecimal total) {
        // Normaliza todos os valores para 2 casas decimais
        total = total.setScale(2, RoundingMode.HALF_UP);
        BigDecimal valorRecebido = (dto.getValorRecebido() != null)
                ? dto.getValorRecebido().setScale(2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;

        if (dto.getTipoPagamento() == TipoPagamento.PIX) {
            BigDecimal totalComDesconto = total.multiply(new BigDecimal("0.90"))
                    .setScale(2, RoundingMode.HALF_UP);

            validarPagamento(valorRecebido, totalComDesconto);

            caixa.setTotal(totalComDesconto);
            caixa.setValorRecebido(valorRecebido);
            caixa.setTroco(valorRecebido.subtract(totalComDesconto).setScale(2, RoundingMode.HALF_UP));

        } else if (dto.getTipoPagamento() == TipoPagamento.DINHEIRO) {
            validarPagamento(valorRecebido, total);

            caixa.setTotal(total);
            caixa.setValorRecebido(valorRecebido);
            caixa.setTroco(valorRecebido.subtract(total).setScale(2, RoundingMode.HALF_UP));

        } else {
            caixa.setTotal(total);
            caixa.setValorRecebido(total);
            caixa.setTroco(BigDecimal.ZERO.setScale(2));
        }
    }

    private void validarPagamento(BigDecimal recebido, BigDecimal minimo) {
        if (recebido == null || recebido.compareTo(minimo) < 0) {
            throw new RuntimeException("Pagamento insuficiente. Mínimo esperado: " + minimo);
        }
    }

    public BigDecimal saltoTotal(){
        BigDecimal saldo = caixaRepository.somarFaturamentoTotal();
        return (saldo != null) ? saldo : BigDecimal.ZERO;
    }

    public BigDecimal saldoPorTipo(TipoPagamento tipo) {
        BigDecimal saldo = caixaRepository.somarPorTipoPagamento(tipo);
        return saldo != null ? saldo : BigDecimal.ZERO;
    }
}
