package com.example.carros.api;

import com.example.carros.domain.dto.CarroDTO;
import com.example.carros.domain.model.Carro;
import com.example.carros.domain.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/*
 * @project carros_api
 * @author Gabriel on 23/05/2020
 */
@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {

    @Autowired
    private CarroService service;

    @GetMapping
    public ResponseEntity<List<CarroDTO>> get() {
        return new ResponseEntity<>(service.getCarros(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroDTO> findById(@PathVariable Long id) {
        Optional<CarroDTO> optionalCarro = service.findById(id);
        return optionalCarro.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<CarroDTO>> findByTipo(@PathVariable String tipo) {
        List<CarroDTO> listaDeTipo = service.findByTipo(tipo);
        return listaDeTipo.isEmpty() ? ResponseEntity.noContent().build() :
                ResponseEntity.ok(listaDeTipo);
    }

    @PostMapping
    public String save(@RequestBody Carro carro) {
        service.save(carro);
        return "Carro salvo com sucesso " + carro.getId();
    }

    @PutMapping("/{id}")
    public String update(@RequestBody Carro carro, @PathVariable Long id) {
        Carro c = service.update(carro, id);
        return "Carro atualizado com sucesso " + carro.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Carro deletado com sucesso";
    }
}
