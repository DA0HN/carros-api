package com.example.carros.domain.dto;

import com.example.carros.domain.model.Carro;
import lombok.Data;
import org.modelmapper.ModelMapper;

/*
 * @project carros_api
 * @author Gabriel on 24/05/2020
 */
@Data
public class CarroDTO {
    private Long id;
    private String nome;
    private String tipo;

    public static CarroDTO create(Carro carro) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(carro, CarroDTO.class);
    }
}
