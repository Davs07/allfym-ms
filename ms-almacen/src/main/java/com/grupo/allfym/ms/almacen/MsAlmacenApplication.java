package com.grupo.allfym.ms.almacen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsAlmacenApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAlmacenApplication.class, args);
	}

}
