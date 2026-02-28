package back.livros.service;

import back.livros.model.dto.LivroDto;
import back.livros.model.entity.Livros;
import back.livros.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivrosService {


    @Autowired
    public LivroRepository livroRepository;


    public Livros salvar(LivroDto livroDto){

        Livros entity = new Livros();
        entity.setNome(livroDto.getNome());
        entity.setAutor(livroDto.getAutor());
        entity.setPreco(livroDto.getPreco());

        return livroRepository.save(entity);
    }

    public List<Livros> findAll(){
        return livroRepository.findAll();
    }


    public Livros finbyid(Long id){

        return  livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado."));
    }


    public Livros upDate(Long id,LivroDto livroDto){

        Livros db = finbyid(id);
        db.setNome(livroDto.getNome());
        db.setAutor(livroDto.getAutor());
        db.setPreco(livroDto.getPreco());

        return livroRepository.save(db);
    }

    public void delete(Long id){
        finbyid(id);
        livroRepository.deleteById(id);
    };


}
