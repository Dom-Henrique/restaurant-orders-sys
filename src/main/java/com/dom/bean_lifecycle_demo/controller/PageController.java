package com.dom.bean_lifecycle_demo.controller;


import com.dom.bean_lifecycle_demo.model.Pedido;
import com.dom.bean_lifecycle_demo.model.Usuario;
import com.dom.bean_lifecycle_demo.repository.PedidoRepository;
import com.dom.bean_lifecycle_demo.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
// para fazer a integracao com o bd eu preciso injetar as classes
public class PageController {
    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    public PageController(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository){
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
    }
    @GetMapping("/")
    public String homepage(@RequestParam String nomeUsuario, Model model){
        model.addAttribute("nomeUsuario", nomeUsuario);
        return "homepage";
    }
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
//      System.out.println("Pedido feito com sucesso!");
        model.addAttribute("nome", nome);
        model.addAttribute("nomePedido", nomePedido);
        model.addAttribute("categoria", categoria);
        model.addAttribute("precoPedido", precoPedido);
        // criacao da classe Pedido para salvar no bd
        Pedido pedido = new Pedido(nomePedido, categoria, precoPedido);
        pedidoRepository.save(pedido);
        return "pedidoRecebido";
    }

    @GetMapping("/signup")
    public String cadastroUsuario(){
        return "signup";
    }

    @PostMapping("/signup/salvar")
    public String receberUsuario(@RequestParam String nomeUsuario, @RequestParam String emailUsuario, @RequestParam String senhaUsuario, Model model){
        model.addAttribute("nomeUsuario", nomeUsuario);
        model.addAttribute("emailUsuario", emailUsuario);
        model.addAttribute("senhaUsuario", senhaUsuario);
        Usuario usuario = new Usuario(nomeUsuario, emailUsuario, senhaUsuario);
        usuarioRepository.save(usuario);
        return "homepage";
    }

    @GetMapping("/debug/pedidos")
    @ResponseBody
    public List<Pedido> listarPedidos(){
        return pedidoRepository.findAll();
    }
}
