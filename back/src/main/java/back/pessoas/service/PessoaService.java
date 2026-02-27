package back.pessoas.service;

import back.pessoas.model.dto.PessoaDto;
import back.pessoas.model.entity.Pessoas;
import back.pessoas.reposiory.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    public PessoaRepository pessoaRepository;


    public Pessoas salvar(PessoaDto pessoaDto) {

        Pessoas entity = new Pessoas();
        entity.setNome(pessoaDto.getNome());
        entity.setEmail(pessoaDto.getEmail());

        return pessoaRepository.save(entity);
    }


    public List<Pessoas> findAll() {

        return pessoaRepository.findAll();
    }


    public Pessoas findById(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> {
                    return new RuntimeException("Pessoa com ID : " + id + " não encontrado no sistema!");
                });
    }

    public Pessoas upDate(Long id, PessoaDto pessoaDto) {
        Pessoas db = findById(id);
        db.setNome(pessoaDto.getNome());
        db.setEmail(pessoaDto.getEmail());

        return pessoaRepository.save(db);
    }

    public void remove(Long id) {
        findById(id);
        pessoaRepository.deleteById(id);
    }


}
