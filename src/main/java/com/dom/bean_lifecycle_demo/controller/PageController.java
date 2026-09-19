package com.dom.bean_lifecycle_demo.controller;


import com.dom.bean_lifecycle_demo.model.Pedido;
import com.dom.bean_lifecycle_demo.model.Usuario;
import com.dom.bean_lifecycle_demo.repository.PedidoRepository;
import com.dom.bean_lifecycle_demo.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String homepage(){
//        model.addAttribute("nomeUsuario", nomeUsuario);
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
    public String receberPedido(@RequestParam String nomePedido, @RequestParam String categoriaPedido, @RequestParam float precoPedido, Model model) {
//      System.out.println("Pedido feito com sucesso!");
//        model.addAttribute("nome", nome)
        model.addAttribute("nomePedido", nomePedido);
        model.addAttribute("categoriaPedido", categoriaPedido);
        model.addAttribute("precoPedido", precoPedido);
        // criacao da classe Pedido para salvar no bd
        Pedido pedido = new Pedido(nomePedido, categoriaPedido, precoPedido);
        if (pedidoRepository.existsByNomePedido(nomePedido)){
            model.addAttribute("erro", "Já existe um pedido com esse nome!");
            return "cadastroPedido";
        }
        pedidoRepository.save(pedido);
        List<Pedido> pedidos = pedidoRepository.findAll();
        model.addAttribute("pedidos", pedidos);
        return "cadastroPedido";
    }

    @GetMapping("/fazerPedido")
    public String fazerPedido(Model model){
//        pedidoRepository.findAll();
        // ever i want create a list of elements with database, i need to use List class to iterate all elements
        List<Pedido> pedidos = pedidoRepository.findAll();
        model.addAttribute("pedidos", pedidos);
        return "fazerPedido";
    }

    @GetMapping("/editarPedido/{id}")
    public String editarPEdido(@PathVariable Long id, Model model){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        model.addAttribute(pedido);
        return "editarPedido";
    }

    @PostMapping("/editarPedido/{id}/salvar")
    public String salvarEdicao(@PathVariable Long id, @RequestParam String nomePedido, @RequestParam String categoriaPedido, @RequestParam float precoPedido){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedido.setNomePedido(nomePedido);
        pedido.setCategoriaPedido(categoriaPedido);
        pedido.setPrecoPedido(precoPedido);
        pedidoRepository.save(pedido);
        return "redirect:/cadastroPedido";
    }

    @GetMapping("/deletarPedido/{id}")
    public String deletarPedido(@PathVariable Long id, Model model){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        model.addAttribute(pedido);
        return "cadastroPedido";
    }

    @PostMapping("/deletarPedido/{id}/salvar")
    public String salvarDelecao(@PathVariable Long id, @RequestParam String nomePedido, @RequestParam String categoriaPedido, @RequestParam float precoPedido){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedidoRepository.delete(pedido);
        return "redirect:/cadastroPedido";
    }

    @GetMapping("/signup")
    public String cadastroUsuario(){
        return "signup";
    }

    @GetMapping("/login")
    public String logarUsuario(@PathVariable Long id, @RequestParam String emailUsuario, @RequestParam String senhaUsuario){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.getNomeUsuario();
        usuario.getEmailUsuario();
        usuario.getSenhaUsuario();
        return "login";
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
