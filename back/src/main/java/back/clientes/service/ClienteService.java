package back.clientes.service;

import back.Endereco.ViaCepClient;
import back.Endereco.domain.Cep;
import back.Endereco.domain.EnderecoDTO;
import back.Endereco.exception.ViaCepFormatException;
import back.Endereco.utils.CEPUtils;
import back.clientes.model.dto.ClienteDto;
import back.clientes.model.entity.Clientes;
import back.clientes.reposiory.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    public ClienteRepository pessoaRepository;


   public Clientes salvar(ClienteDto clienteDto){

       String cepLimpo = CEPUtils.removeMascaraCep(clienteDto.getEndereco().getCep());

       Cep dadosApi = ViaCepClient.findCep(cepLimpo);

       if(dadosApi != null){
           throw new ViaCepFormatException("CEP não encontrado na base nascional.");
       }

       Clientes entity = new Clientes();
       entity.setNome(clienteDto.getNome());
       entity.setTelefone(clienteDto.getTelefone());
       entity.setLivros(clienteDto.getLivros());

       EnderecoDTO endereco = new EnderecoDTO();
       endereco.setCep(dadosApi.getCep());
       endereco.setLogradouro(dadosApi.getLogradouro());
       endereco.setBairro(dadosApi.getBairro());
       endereco.setLocalidade(dadosApi.getLocalidade());
       endereco.setUf(dadosApi.getUf());
       endereco.setComplemento(dadosApi.getComplemento());

       entity.setEndereco(endereco);

       return pessoaRepository.save(entity);
   }

    public List<Clientes> findAll() {

        return pessoaRepository.findAll();
    }


    public Clientes findById(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> {
                    return new RuntimeException("Pessoa com ID : " + id + " não encontrado no sistema!");
                });
    }

    public Clientes upDate(Long id, ClienteDto ClienteDto) {
        Clientes db = findById(id);
        db.setNome(ClienteDto.getNome());
        db.setTelefone(ClienteDto.getTelefone());
        db.setLivros(ClienteDto.getLivros());
        db.setEndereco(ClienteDto.getEndereco());

        return pessoaRepository.save(db);
    }

    public void remove(Long id) {
        findById(id);
        pessoaRepository.deleteById(id);
    }


}
