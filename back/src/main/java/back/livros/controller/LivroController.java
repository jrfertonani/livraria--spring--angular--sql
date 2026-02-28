package back.livros.controller;


import back.livros.model.dto.LivroDto;
import back.livros.model.entity.Livros;
import back.livros.service.LivrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/livros")
public class LivroController {


    @Autowired
    public LivrosService livrosService;

    @PostMapping
    public ResponseEntity<Livros> salvar(@RequestBody LivroDto livroDto){

        Livros livro = livrosService.salvar(livroDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(livro);
    }

    @GetMapping
    public ResponseEntity<List<Livros>> findAll(){

        List<Livros> list = livrosService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livros> findbyid(@PathVariable Long id){

        Livros find = livrosService.finbyid(id);

        return ResponseEntity.status(HttpStatus.OK).body(find);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livros> update(@PathVariable Long id,
                                         @RequestBody LivroDto livroDto){
        Livros entity = livrosService.upDate(id, livroDto);

        return ResponseEntity.status(HttpStatus.OK).body(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        livrosService.delete(id);
        return ResponseEntity.notFound().build();
    }

}
