package back.clientes.controller;

import back.clientes.model.dto.ClienteDto;
import back.clientes.model.entity.Clientes;
import back.clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {



    @Autowired
    public ClienteService clienteService;


    @PostMapping
    public ResponseEntity<Clientes> salvar(@RequestBody ClienteDto clienteDto){

        Clientes salvar = clienteService.salvar(clienteDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(salvar);
    }


    @GetMapping
    public ResponseEntity<List<Clientes>> findAll(){

        List<Clientes> list = clienteService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clientes> findById(@PathVariable Long id){

        Clientes cliente = clienteService.findById(id);

        return ResponseEntity.ok(cliente);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Clientes> upDate(@PathVariable Long id,
                                          @RequestBody ClienteDto clienteDto){
        Clientes entity = clienteService.upDate(id, clienteDto);

        return ResponseEntity.status(HttpStatus.OK).body(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        clienteService.remove(id);

        return ResponseEntity.noContent().build();
    }



}
