package back.caixa.controller;


import back.caixa.model.Enum.TipoPagamento;
import back.caixa.model.dto.CaixaDto;
import back.caixa.model.entity.Caixa;
import back.caixa.repository.CaixaRepository;
import back.caixa.service.CaixaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/caixa")
public class CaixaController {


    @Autowired
    public CaixaService caixaService;
    @Autowired
    private CaixaRepository caixaRepository;

    @PostMapping("/finalizar")
    public ResponseEntity<?> finalizarVenda(@RequestBody CaixaDto caixaDto) {
        try {
            Caixa caixa = caixaService.realizarVenda(caixaDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(caixa);
        } catch (RuntimeException e) {
            // Retorna o erro 400 (Bad Request) com a mensagem: "Pagamento insuficiente..."
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/saldo-total")
    public ResponseEntity<BigDecimal> consultarSaldo(){
        BigDecimal saldo = caixaService.saltoTotal();
        return ResponseEntity.ok(saldo);
    }

    @GetMapping("/saldo-por-tipo/{tipo}")
    public ResponseEntity<BigDecimal> saldoPorTipo(@PathVariable TipoPagamento tipo){
        BigDecimal saldo = caixaService.saldoPorTipo(tipo);
        return ResponseEntity.ok(saldo);
    }

}
