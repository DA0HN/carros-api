package com.example.carros.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/*
 * @project carros_api
 * @author Gabriel on 23/05/2020
 */
public interface CarroRepository extends CrudRepository<Carro, Long> {
    List<Carro> findByTipo(String tipo);
}
