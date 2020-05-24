package com.example.carros.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @project carros_api
 * @author Gabriel on 22/05/2020
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String get() {
        return "Api dos Carros";
    }
}
