package back.caixa.service;

import back.caixa.controller.CaixaController;
import back.caixa.model.dto.CaixaDto;
import back.caixa.model.entity.Caixa;
import back.caixa.repository.CaixaRepository;
import back.clientes.model.entity.Clientes;
import back.clientes.reposiory.ClienteRepository;
import back.livros.model.entity.Livros;
import back.livros.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public Caixa realizarVenda(Long clienteId, List<Long> livrosId) {
        Clientes cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        BigDecimal total = BigDecimal.ZERO;
        List<Livros> livrosVendidos = new ArrayList<>();

        // Percorremos a lista de IDs
        for (Long id : livrosId) {
            Livros livro = livroRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Livro ID " + id + " não encontrado."));

            // Somamos o preço (mesmo se o livro for repetido)
            total = total.add(livro.getPreco());

            // Vinculamos ao cliente e à venda
            livro.setCliente(cliente);
            livrosVendidos.add(livro);
        }

        Caixa caixa = new Caixa();
        caixa.setCliente(cliente);
        caixa.setTotal(total);
        caixa.setDataVenda(LocalDateTime.now());
        caixa.setLivros(livrosVendidos);

        for (Livros l : livrosVendidos) {
            l.setCaixa(caixa);
        }

        return caixaRepository.save(caixa);
    }


}
