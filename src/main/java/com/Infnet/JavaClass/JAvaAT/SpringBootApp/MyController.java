package com.Infnet.JavaClass.JAvaAT.SpringBootApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/carros")
public class MyController {

    private final CarroRepository carroRepository;

    @Autowired
    public MyController(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    @PostMapping("/criar")
    public ResponseEntity<Carro> criarCarro(@RequestBody Carro carro) {
        try {
            if (carro.getNome() == null || carro.getNome().isEmpty() || carro.getNumero() <= 0) {
                throw new IllegalArgumentException("Dados de entrada inválidos");
            }

            Carro carroCriado = carroRepository.salvar(carro);
            return new ResponseEntity<>(carroCriado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Carro>> listarCarro(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String marca) {
        List<Carro> carros = carroRepository.listarTodos();

        if (nome != null && !nome.isEmpty()) {
            // Filtrar por nome
            carros = carros.stream()
                    .filter(carro -> carro.getNome().toLowerCase().contains(nome.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (marca != null && !marca.isEmpty()) {
            carros = carros.stream()
                    .filter(carro -> carro.getMarca().toLowerCase().contains(marca.toLowerCase()))
                    .collect(Collectors.toList());
        }

        return new ResponseEntity<>(carros, HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Carro> buscarCarro(@PathVariable Long id) {
        Optional<Carro> carro = carroRepository.buscarPorId(id);

        return carro.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Carro> atualizarCarro(@PathVariable Long id, @RequestBody Carro carroAtualizado) {
        carroAtualizado.setId(id);
        if (carroRepository.atualizar(carroAtualizado)) {
            return new ResponseEntity<>(carroAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarCarro(@PathVariable Long id) {
        if (carroRepository.deletar(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
