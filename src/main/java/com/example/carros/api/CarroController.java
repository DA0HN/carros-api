package com.example.carros.api;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Iterable<Carro> get() {
        return service.getCarros();
    }

    @GetMapping("/{id}")
    public Carro findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/tipo/{tipo}")
    public Iterable<Carro> findByTipo(@PathVariable String tipo) {
        return service.findByTipo(tipo);
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
