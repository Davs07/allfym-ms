package com.grupo.allfym.ms.reclamos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsReclamosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsReclamosApplication.class, args);
	}

}
