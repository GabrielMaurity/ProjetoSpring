package com.Infnet.JavaClass.JAvaAT.SpringBootApp;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MeuServicoTestTest {

    @Test
    void testarChamadaHttp() {
        MeuServicoTest meuServicoTest = new MeuServicoTest();
        MinhaResposta resposta = meuServicoTest.fazerChamadaHttp();

        assertThat(resposta).isNotNull();
        assertThat(resposta.getMensagem()).isEqualTo("ValorEsperado");
    }

    @Test
    void testarChamadaHttpComErro() {
        MeuServicoTest meuServicoTest = new MeuServicoTest();

        // A URL_API precisa ser inválida ou não existente para simular um erro
        assertThrows(RuntimeException.class, meuServicoTest::fazerChamadaHttp);
    }
}
