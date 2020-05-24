package com.example.carros.domain.services;

import com.example.carros.domain.dto.CarroDTO;
import com.example.carros.domain.model.Carro;
import com.example.carros.domain.repositories.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
 * @project carros_api
 * @author Gabriel on 23/05/2020
 */
@Service
public class CarroService {

    @Autowired
    private CarroRepository repository;

    public List<CarroDTO> getCarros() {
        List<Carro> carros = repository.findAll();
        return carros.stream()
                .map(CarroDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<CarroDTO> findById(Long id) {
        return repository.findById(id).map(CarroDTO::new);
    }

    public List<CarroDTO> findByTipo(String tipo) {
        return repository.findByTipo(tipo)
                .stream()
                .map(CarroDTO::new)
                .collect(Collectors.toList());
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
