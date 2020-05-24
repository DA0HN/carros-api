package com.example.carros.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
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

    public Optional<Carro> findById(Long id) {
        return repository.findById(id);
    }

    public List<Carro> findByTipo(String tipo) {
        return repository.findByTipo(tipo);
    }

    public Carro save(Carro carro) {
        Assert.notNull(carro, "Não foi possível salvar o registro");
        return repository.save(carro);
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
        repository.findById(id).ifPresent(c -> repository.deleteById(id));
    }
}
