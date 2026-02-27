package back.pessoas.model.dto;


public class PessoaDto {

    private String nome;
    private String email;

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void pessoaDto(){}

    public void pessoaDto(String nome, String email){
        this.nome = nome;
        this.email = email;
    }


}
