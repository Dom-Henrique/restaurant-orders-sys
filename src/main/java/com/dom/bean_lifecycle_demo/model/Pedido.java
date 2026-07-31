package com.dom.bean_lifecycle_demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// para a criacao de tabelas em bds preciso utilizar a classe ENTITY
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomePedido, categoriaPedido;
    private Float precoPedido;
    // eh necessaria a criacao de um construtor vazio
    public Pedido(){
        // aqui o Spring Database JPA vai usar pra alocar na memoria a tabela
    }
    public Pedido(String nomePedido, String categoriaPedido, Float precoPedido){
        this.nomePedido = nomePedido;
        this.categoriaPedido = categoriaPedido;
        this.precoPedido = precoPedido;
    }
    // getters and setters
    public Long getId(){
        return id;
    }
    public Float getPrecoPedido() {
        return precoPedido;
    }

    public void setPrecoPedido(Float precoPedido) {
        this.precoPedido = precoPedido;
    }

    public String getCategoriaPedido() {
        return categoriaPedido;
    }

    public void setCategoriaPedido(String categoriaPedido) {
        this.categoriaPedido = categoriaPedido;
    }

    public String getNomePedido() {
        return nomePedido;
    }

    public void setNomePedido(String nomePedido) {
        this.nomePedido = nomePedido;
    }
}
