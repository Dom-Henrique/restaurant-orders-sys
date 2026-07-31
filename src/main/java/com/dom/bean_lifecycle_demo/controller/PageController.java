package com.dom.bean_lifecycle_demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
    @GetMapping("/ola")
    public String page(@RequestParam(defaultValue = "Visitante") String nome, Model model) {
        model.addAttribute("nome", nome);
        return "index";
    }

    @GetMapping("/cadastroPedido")
    public String cadastroPedido(@RequestParam(defaultValue = "visitante") String nome, Model model) {
        model.addAttribute("nome", nome);
        return "cadastroPedido"; // will returns the html page
    }

    @PostMapping("/cadastroPedido/salvar")
    public String receberPedido(@RequestParam(defaultValue = "Visitante") String nome, @RequestParam String nomePedido, @RequestParam String categoria, @RequestParam float precoPedido, Model model) {
//        System.out.println("Pedido feito com sucesso!");
        model.addAttribute("nome", nome);
        model.addAttribute("nomePedido", nomePedido);
        model.addAttribute("categoria", categoria);
        model.addAttribute("precoPedido", precoPedido);
        return "pedidoRecebido";
    }
}
