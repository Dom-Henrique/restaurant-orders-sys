package com.dom.bean_lifecycle_demo.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

@Service // serve para estabelecermos as regras de negócio
public class MotorService {
    public MotorService(){
        System.out.println("1. CONSTRUCTOR - Lógica definida");
    }
    @PostConstruct
    public void ativar(){
        System.out.println("2. ATIVADO - Fui ativado.");
    }
    public void ligar(){
        System.out.println(">> LIGADO - Posso ser usado.");
    }
    @PreDestroy
    public void desligar(){
        System.out.println("3. DESLIGADO - Serei destruído, adeus.");
    }
}