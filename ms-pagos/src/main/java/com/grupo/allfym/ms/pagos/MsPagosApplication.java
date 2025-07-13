package com.grupo.allfym.ms.pagos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsPagosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPagosApplication.class, args);
	}

}
