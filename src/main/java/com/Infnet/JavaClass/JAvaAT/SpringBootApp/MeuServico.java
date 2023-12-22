package com.Infnet.JavaClass.JAvaAT.SpringBootApp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class MeuServico {

    private final String URL_API = "https://parallelum.com.br/fipe/api/v1";

    public void fazerChamadaHttp() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<MinhaResposta> resposta = restTemplate.getForEntity(URL_API, MinhaResposta.class);

            log.info("Status Code: {}", resposta.getStatusCodeValue());

            MinhaResposta minhaResposta = resposta.getBody();
            log.info("Resposta: {}", minhaResposta.getMensagem());
        } catch (Exception e) {
            log.error("Erro ao fazer a chamada HTTP", e);
            throw new RuntimeException("Erro ao fazer a chamada HTTP", e);
        }
    }
}
