package com.grupo.allfym.ms.ventas.infrastructure.config;

import com.grupo.allfym.ms.ventas.application.services.VentaApplicationService;
import com.grupo.allfym.ms.ventas.application.usecases.CrearVentaUseCaseImpl;
import com.grupo.allfym.ms.ventas.application.usecases.BuscarVentaUseCaseImpl;
import com.grupo.allfym.ms.ventas.application.usecases.GestionarVentaUseCaseImpl;
import com.grupo.allfym.ms.ventas.domain.ports.in.CrearVentaUseCase;
import com.grupo.allfym.ms.ventas.domain.ports.in.BuscarVentaUseCase;
import com.grupo.allfym.ms.ventas.domain.ports.in.GestionarVentaUseCase;
import com.grupo.allfym.ms.ventas.domain.ports.out.VentaRepositoryPort;
import com.grupo.allfym.ms.ventas.domain.ports.out.ClienteServicePort;
import com.grupo.allfym.ms.ventas.domain.ports.out.ProductoServicePort;
import com.grupo.allfym.ms.ventas.domain.ports.out.AlmacenServicePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Configuration
@EnableFeignClients(basePackages = "com.grupo.allfym.ms.ventas.infrastructure.clients")
public class ApplicationConfig {

    @Bean
    public CrearVentaUseCase crearVentaUseCase(VentaRepositoryPort ventaRepositoryPort,
                                             ClienteServicePort clienteServicePort,
                                             AlmacenServicePort almacenServicePort) {
        return new CrearVentaUseCaseImpl(
                ventaRepositoryPort,
                clienteServicePort,
                almacenServicePort
        );
    }

    @Bean
    public BuscarVentaUseCase buscarVentaUseCase(VentaRepositoryPort ventaRepositoryPort) {
        return new BuscarVentaUseCaseImpl(ventaRepositoryPort);
    }

    @Bean
    public GestionarVentaUseCase gestionarVentaUseCase(VentaRepositoryPort ventaRepositoryPort,
                                                     AlmacenServicePort almacenServicePort) {
        return new GestionarVentaUseCaseImpl(ventaRepositoryPort, almacenServicePort);
    }

    @Bean
    public VentaApplicationService ventaApplicationService(CrearVentaUseCase crearVentaUseCase,
                                                          BuscarVentaUseCase buscarVentaUseCase,
                                                          GestionarVentaUseCase gestionarVentaUseCase) {
        return new VentaApplicationService(
                crearVentaUseCase,
                buscarVentaUseCase,
                gestionarVentaUseCase
        );
    }
}
