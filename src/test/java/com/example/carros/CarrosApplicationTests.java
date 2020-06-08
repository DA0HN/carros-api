package com.example.carros;

import com.example.carros.domain.dto.CarroDTO;
import com.example.carros.domain.model.Carro;
import com.example.carros.domain.services.CarroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CarrosApplicationTests {

    @Autowired
    private CarroService carroService;

    @Test
    public void insertTest() {
        Carro carro = new Carro();
        carro.setNome("Ferrari");
        carro.setTipo("esportivos");
        CarroDTO c = carroService.save(carro);
        assertNotNull(c);
        Long id = c.getId();
        assertNotNull(id);
        // buscar o objeto
        Optional<CarroDTO> opt = carroService.findById(id);
        assertTrue(opt.isPresent());
        CarroDTO carroDTO = opt.get();
        assertEquals(carro.getNome(), carroDTO.getNome());
        assertEquals(carro.getTipo(), carroDTO.getTipo());

        // deletar objeto
        carroService.delete(id);
        assertFalse(carroService.findById(id).isPresent());
    }

    @Test
    public void listaTest() {
        List<CarroDTO> carros = carroService.getCarros();
        assertEquals(30, carros.size());
    }

    @Test
    public void listaPorTipoTest() {
        assertEquals(10, carroService.findByTipo("esportivos").size());
        assertEquals(10, carroService.findByTipo("luxo").size());
        assertEquals(10, carroService.findByTipo("classicos").size());

        assertEquals(0, carroService.findByTipo("x").size());
    }
}
