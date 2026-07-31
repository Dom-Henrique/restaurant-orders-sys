package com.dom.bean_lifecycle_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
//    private String name;
    @GetMapping("/hello") // definição de rotas
    public String helloWorld(@RequestParam String name){
        return "Hello, World! My name is "+  name + " and i'm learning Spring Boot!! :D";
    }
}
