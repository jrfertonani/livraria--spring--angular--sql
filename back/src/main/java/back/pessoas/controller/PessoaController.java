package back.pessoas.controller;

import back.pessoas.model.dto.PessoaDto;
import back.pessoas.model.entity.Pessoas;
import back.pessoas.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {



    @Autowired
    public PessoaService pessoaService;


    @PostMapping
    public ResponseEntity<Pessoas> salvar(@RequestBody PessoaDto pessoaDto){

        Pessoas salvar = pessoaService.salvar(pessoaDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(salvar);
    }


    @GetMapping
    public ResponseEntity<List<Pessoas>> findAll(){

        List<Pessoas> list = pessoaService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoas> findById(@PathVariable Long id){

        Pessoas pessoa = pessoaService.findById(id);

        return ResponseEntity.ok(pessoa);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Pessoas> upDate(@PathVariable Long id,
                                          @RequestBody PessoaDto pessoaDto){
        Pessoas entity = pessoaService.upDate(id, pessoaDto);

        return ResponseEntity.status(HttpStatus.OK).body(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        pessoaService.remove(id);

        return ResponseEntity.noContent().build();
    }



}
