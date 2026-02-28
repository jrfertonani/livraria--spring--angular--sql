package back.Cep;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.MINUTES;

import back.Cep.domain.Cep;
import back.Cep.utils.CEPUtils;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ViaCepClient {

    private static final String viaCepUrl = "https://viacep.com.br/ws/";
    private static final Gson gson = new Gson();

    public static Cep findCep(String cepString) {

        String cepLimpo = cepString.replaceAll("\\D", "");

        if (cepLimpo.length() != 8) {
            throw new IllegalArgumentException("CEP inválido: deve conter 8 dígitos numéricos.");
        }

        CEPUtils.validaCep(cepLimpo);

        try {
            HttpClient httpClient = HttpClient.newBuilder()
                    .connectTimeout(Duration.of(1, MINUTES))
                    .build();

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(viaCepUrl + cepLimpo + "/json")) // Usa o CEP limpo
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (httpResponse.statusCode() != 200) {
                log.error("[VIA CEP API] - Erro na requisição: Status {}", httpResponse.statusCode());
                throw new RuntimeException("Erro ao consultar ViaCEP: Status " + httpResponse.statusCode());
            }

            log.info("[VIA CEP API] - [RESULTADO DA BUSCA: {}]", httpResponse.body());

            if (httpResponse.body().contains("\"erro\": \"true\"") || httpResponse.body().contains("\"erro\": true")) {
                log.warn("[VIA CEP API] - CEP {} não encontrado na base de dados.", cepLimpo);
                return null;
            }

            return gson.fromJson(httpResponse.body(), Cep.class);

        } catch (IOException | InterruptedException e) {
            log.error("Erro ao conectar com ViaCEP", e);
            Thread.currentThread().interrupt();
            throw new RuntimeException("Falha na comunicação com o serviço de CEP", e);
        }
    }
}