package com.grupo.allfym.ms.clientes.ms_clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.grupo.allfym.ms.clientes")
@ComponentScan(basePackages = "com.grupo.allfym.ms.clientes")
@EntityScan(basePackages = "com.grupo.allfym.ms.clientes.entity")
@EnableJpaRepositories(basePackages = "com.grupo.allfym.ms.clientes.repositories")
public class MsClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsClientesApplication.class, args);
	}

}
