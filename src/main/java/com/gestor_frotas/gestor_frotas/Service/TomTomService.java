package com.gestor_frotas.gestor_frotas.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class TomTomService {

    // informações da api que está sendo guardado application.propertiens
    @Value("${tomtom.api.base-url}")
    public String baseUrl;

    @Value("${tomtom.api.key}")
    private String apiKey;

    @Value("${tomtom.api.timeout-seconds}")
    private Integer timeout;

    @Value(value = "${tomtom.api.country-set}")
    private String countrySet;

    private ObjectMapper objectMapper;

    private TomTomService(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    public Coordenada geocodificar(String endereco) {
        try {
            URI uri = UriComponentsBuilder // constroi e manipular URLs e URIs de forma segura e estruturada
                    .fromUriString(baseUrl) // Define a URL base (ex: https://api.tomtom.com)
                    .pathSegment("search", "2", "geocode", endereco + ".json") // Monta o caminho (ex: /search/2/geocode/RuaX.json)

                    // queryParam = Parâmetros da busca
                    .queryParam("key", apiKey) // parametros de busca para autenticação da api
                    .queryParam("limit", 1) // para de busca do endereço, vai me retornar 1, em vez da lista de endereço
                    .queryParam("countrySet", countrySet) // limitar a busca de paises especificos
                    .queryParam("language", "pt-BR") // responder com os dados traduzidos
                    .build() // finaliza o retorno dos objeto criado

                    .encode() // Codifica a URL (ex: "São Paulo" vira "S%C3%A3o%20Paulo")
                    .toUri(); // Converte para o objeto URI do Java

            HttpClient client = HttpClient.newBuilder() // configura cliente antes de criá-lo
                    .connectTimeout(Duration.ofSeconds(timeout))// tempo definido para conectar ao servidor
                    .build(); // finaliza o retorno

            HttpRequest request = HttpRequest.newBuilder() // configura requisição das informações no pedido
                    .uri(uri) // destino da criado no meotodo "UriComponentsBuilder"
                    .timeout(Duration.ofSeconds(timeout)) // tempo máximo para a busca e devolver a resposta para leitura
                    .GET() // para consultar os dados
                    .build();

            HttpResponse<String> response = client.send( // requisição síncrona(o código pausa nessa linha e espera a resposta chegar)
                    request,
                    HttpResponse.BodyHandlers.ofString() // converção de json para String
            );

            JsonNode json = objectMapper.readTree(response.body()); // criando objeto json

            JsonNode resultados = json.get("results"); // pegando as informações json de resultado

            // validação se retorno algo, senão aparcece a mensagem de erro
            if (resultados == null || resultados.isEmpty()) {
                throw new RuntimeException("Endereco nao encontrado: " + endereco);
            }

            // pega o primeiro endereço encontrado e entrada no bloco de dados das coordenadas
            JsonNode position = resultados.get(0).get("position");

            // convertendo o json em numeros decimais
            Double latitude = position.get("lat").asDouble();
            Double longitude = position.get("lon").asDouble();

            // retorno de sucesso
            return new Coordenada(latitude, longitude);

        } catch (Exception e) {
            // mensagem de erro
            throw new RuntimeException("Erro ao buscar coordenadas na TomTom", e);
        }
    }

    public static class Coordenada {
        private Double latitude;
        private Double longitude;

        public Coordenada(Double latitude, Double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public Double getLatitude() {
            return latitude;
        }

        public Double getLongitude() {
            return longitude;
        }
    }
}
