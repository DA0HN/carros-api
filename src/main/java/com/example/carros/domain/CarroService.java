package com.example.carros.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

/*
 * @project carros_api
 * @author Gabriel on 23/05/2020
 */
@Service
public class CarroService {

    @Autowired
    private CarroRepository repository;

    public Iterable<Carro> getCarros() {
        return repository.findAll();
    }

    public Carro findById(Long id) {
        Optional<Carro> carro = repository.findById(id);
        return carro.orElseThrow(() -> new RuntimeException("Carro não encontrado"));
    }

    public Iterable<Carro> findByTipo(String tipo) {
        Iterable<Carro> byTipo = repository.findByTipo(tipo);
        return byTipo;
    }

    public Carro save(Carro carro) {
        Assert.isNull(carro, "Não foi possível salvar o registro");
        Carro c = repository.save(carro);
        return c;
    }

    public Carro update(Carro carro, Long id) {
        Assert.notNull(id, "Não foi possível atualizar o registro");
        Optional<Carro> carroDb = repository.findById(id);
        carroDb.ifPresent(c -> {
            c.setNome(carro.getNome());
            c.setTipo(carro.getTipo());
            repository.save(c);
        });
        return carroDb.orElseThrow(() -> new RuntimeException("Não foi possível atualizar o registro"));
    }

    public void delete(Long id) {
        repository.findById(id).ifPresent(c -> {
            repository.deleteById(id);
        });
    }
}
