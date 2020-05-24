package com.example.carros.domain.repositories;

import com.example.carros.domain.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
 * @project carros_api
 * @author Gabriel on 23/05/2020
 */
public interface CarroRepository extends JpaRepository<Carro, Long> {
    List<Carro> findByTipo(String tipo);
}
