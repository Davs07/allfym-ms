package com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsReclamoProveedorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsReclamoProveedorApplication.class, args);
	}

}
