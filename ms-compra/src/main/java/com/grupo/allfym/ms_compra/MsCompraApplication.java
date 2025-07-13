package com.grupo.allfym.ms_compra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsCompraApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCompraApplication.class, args);
	}

}
