package back.Endereco.utils;

import back.Endereco.exception.ViaCepException;
import back.Endereco.exception.ViaCepFormatException;

import java.util.Objects;

public class CEPUtils {
    public static void validaCep(String cep){
        if (Objects.isNull(cep) || cep.isEmpty() || cep.isBlank()) throw new ViaCepException("O cep informado não pode ser vazio");
        if (cep.length() > 8) throw new ViaCepFormatException("CEP fora do formato, caso esteja com hifen, use o metodo de formatacao");
        if (cep.length() < 8) throw new ViaCepException("CEP faltando numeros");
        if (cep == null || !cep.matches("\\d{8}")) {
            throw new IllegalArgumentException("CEP inválido! Use apenas 8 números.");
        }
    }

    public static String removeMascaraCep(String cep){
        try {
            validaCep(cep);
            return cep;
        } catch (ViaCepFormatException e){
            return cep.replace("-", "");
        }
    }
    public static String mascararCep(String cep){
        try {
            validaCep(cep);
            return cep.substring(0, 5) + "-" + cep.substring(5);
        } catch (ViaCepFormatException e){
            throw new ViaCepException("Cep ja formatado ou fora do padrao");
        }
    }

}