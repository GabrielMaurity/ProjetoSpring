package com.Infnet.JavaClass.JAvaAT.SpringBootApp;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class MyControllerTest {

    @Test
    void testarCriarMotoComDadosInvalidos() {
        CarroRepository carroRepository = new CarroRepository();
        MyController myController = new MyController(carroRepository);

        Carro motoInvalida = new Carro();
        ResponseEntity<Carro> resposta = myController.criarCarro(motoInvalida);

        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(carroRepository.listarTodos()).isEmpty();
    }

    @Test
    void testarListarMotos() {
        CarroRepository carroRepository = new CarroRepository();
        MyController myController = new MyController(carroRepository);

        myController.criarCarro(new Carro(1L, "Carro1", 123, "Marca1"));
        myController.criarCarro(new Carro(2L, "Carro2", 456, "Marca2"));

        ResponseEntity<List<Carro>> resposta = myController.listarCarro(null, null);

        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resposta.getBody()).hasSize(2);
    }

    @Test
    void testarBuscarMotoExistente() {
        CarroRepository motoRepository = new CarroRepository();
        MyController myController = new MyController(motoRepository);

        myController.criarCarro(new Carro(1L, "Carro1", 123, "Marca1"));

        ResponseEntity<Carro> resposta = myController.buscarCarro(1L);

        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resposta.getBody()).isNotNull();
        assertThat(resposta.getBody().getNome()).isEqualTo("Carro1");
    }

    @Test
    void testarBuscarMotoNaoExistente() {
        CarroRepository motoRepository = new CarroRepository();
        MyController myController = new MyController(motoRepository);

        ResponseEntity<Carro> resposta = myController.buscarCarro(1L);

        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(resposta.getBody()).isNull();
    }

    @Test
    void testarAtualizarMotoExistente() {
        CarroRepository motoRepository = new CarroRepository();
        MyController myController = new MyController(motoRepository);

        myController.criarCarro(new Carro(1L, "Carro1", 123, "Marca1"));

        Carro motoAtualizada = new Carro(1L, "CarroAtualizado", 456, "MarcaAtualizada");

        ResponseEntity<Carro> resposta = myController.atualizarCarro(1L, motoAtualizada);

        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resposta.getBody()).isNotNull();
        assertThat(resposta.getBody().getNome()).isEqualTo("CarroAtualizado");
    }

    @Test
    void testarAtualizarMotoNaoExistente() {
        CarroRepository motoRepository = new CarroRepository();
        MyController myController = new MyController(motoRepository);


        Carro motoAtualizada = new Carro(1L, "CarroAtualizado", 456, "MarcaAtualizada");

        ResponseEntity<Carro> resposta = myController.atualizarCarro(1L, motoAtualizada);

        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(resposta.getBody()).isNull();
    }

    @Test
    void testarDeletarMotoExistente() {
        CarroRepository motoRepository = new CarroRepository();
        MyController myController = new MyController(motoRepository);

        myController.criarCarro(new Carro(1L, "Carro1", 123, "Marca1"));

        ResponseEntity<Void> resposta = myController.deletarCarro(1L);

        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(motoRepository.listarTodos()).isEmpty();
    }

    @Test
    void testarDeletarMotoNaoExistente() {
        CarroRepository motoRepository = new CarroRepository();
        MyController myController = new MyController(motoRepository);

        ResponseEntity<Void> resposta = myController.deletarCarro(1L);

        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(motoRepository.listarTodos()).isEmpty();
    }
}

