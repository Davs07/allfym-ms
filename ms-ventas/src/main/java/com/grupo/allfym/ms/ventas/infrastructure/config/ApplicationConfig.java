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

/**
 * Configuración principal de la aplicación.
 * Define los beans para la inyección de dependencias de la arquitectura hexagonal.
 */
@Configuration
@EnableFeignClients(basePackages = "com.grupo.allfym.ms.ventas.infrastructure.clients")
public class ApplicationConfig {

    /**
     * Bean para el caso de uso de crear ventas.
     */
    @Bean
    public CrearVentaUseCase crearVentaUseCase(VentaRepositoryPort ventaRepositoryPort,
                                             ClienteServicePort clienteServicePort,
                                             ProductoServicePort productoServicePort,
                                             AlmacenServicePort almacenServicePort) {
        return new CrearVentaUseCaseImpl(
                ventaRepositoryPort,
                clienteServicePort,
                productoServicePort,
                almacenServicePort
        );
    }

    /**
     * Bean para el caso de uso de buscar ventas.
     */
    @Bean
    public BuscarVentaUseCase buscarVentaUseCase(VentaRepositoryPort ventaRepositoryPort) {
        return new BuscarVentaUseCaseImpl(ventaRepositoryPort);
    }

    /**
     * Bean para el caso de uso de gestionar ventas.
     */
    @Bean
    public GestionarVentaUseCase gestionarVentaUseCase(VentaRepositoryPort ventaRepositoryPort,
                                                     AlmacenServicePort almacenServicePort) {
        return new GestionarVentaUseCaseImpl(ventaRepositoryPort, almacenServicePort);
    }

    /**
     * Bean para el servicio de aplicación de ventas.
     * Este es el punto de entrada principal desde la infraestructura.
     */
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
