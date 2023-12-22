package com.Infnet.JavaClass.JAvaAT.SpringBootApp;

import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CarroRepository {

    private final Map<Long, Carro> carros = new HashMap<>();
    private long nextId = 1;

    public Carro salvar(Carro moto) {
        moto.setId(nextId++);
        carros.put(moto.getId(), moto);
        return moto;
    }

    public List<Carro> listarTodos() {
        return carros.values().stream().collect(Collectors.toList());
    }

    public Optional<Carro> buscarPorId(Long id) {
        return Optional.ofNullable(carros.get(id));
    }

    public boolean atualizar(Carro carro) {
        if (carros.containsKey(carro.getId())) {
            carros.put(carro.getId(), carro);
            return true;
        }
        return false;
    }

    public boolean deletar(Long id) {
        return carros.remove(id) != null;
    }
}
