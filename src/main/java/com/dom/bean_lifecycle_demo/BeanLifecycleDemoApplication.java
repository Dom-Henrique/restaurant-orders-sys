package com.dom.bean_lifecycle_demo;

import com.dom.bean_lifecycle_demo.service.MotorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication // combo de @Configuration @EnableAutoConfiguration e @ComponentScan
public class BeanLifecycleDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeanLifecycleDemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner test(MotorService motorService){
		return args -> {
			System.out.println("Ligando...");
			motorService.ligar();
		};
	}
}
