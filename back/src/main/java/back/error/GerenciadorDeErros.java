package back.error;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class GerenciadorDeErros {

    private static final Logger log = LoggerFactory.getLogger(GerenciadorDeErros.class);

    @ExceptionHandler(RuntimeException.class)
    public String tratarErroLindo(RuntimeException ex) {
        // Isso imprime apenas UMA linha limpa no seu console do IntelliJ
        log.error("ALERTA: {}", ex.getMessage());

        // Isso é o que o seu Angular vai receber
        return ex.getMessage();
    }
}