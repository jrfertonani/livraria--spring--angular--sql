package back.caixa.controller;


import back.caixa.model.dto.CaixaDto;
import back.caixa.model.entity.Caixa;
import back.caixa.repository.CaixaRepository;
import back.caixa.service.CaixaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/caixa")
public class CaixaController {


    @Autowired
    public CaixaService caixaService;

    @PostMapping("/finalizar")
    public ResponseEntity<Caixa> finalizarVenda(@RequestBody CaixaDto caixaDto){

        Caixa caixa = caixaService.realizarVenda(
                caixaDto.getClienteId(),
                caixaDto.getLivrosIds());

        return ResponseEntity.status(HttpStatus.CREATED).body(caixa);
    }


}
