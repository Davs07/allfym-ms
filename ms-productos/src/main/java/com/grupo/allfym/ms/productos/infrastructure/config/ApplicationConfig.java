package com.grupo.allfym.ms.productos.infrastructure.config;

import com.grupo.allfym.ms.productos.application.services.ProductoService;
import com.grupo.allfym.ms.productos.application.usecases.CreateProductoUseCaseImpl;
import com.grupo.allfym.ms.productos.application.usecases.DeleteProductoUseCaseImpl;
import com.grupo.allfym.ms.productos.application.usecases.RetrieveProductoUseCaseImpl;
import com.grupo.allfym.ms.productos.application.usecases.UpdateProductoUseCaseImpl;
import com.grupo.allfym.ms.productos.domain.ports.out.ExternalServicePort;
import com.grupo.allfym.ms.productos.domain.ports.out.ProductoRepositoryPort;
import com.grupo.allfym.ms.productos.infrastructure.repositories.JpaProductoRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ProductoService productoService(ProductoRepositoryPort productoRepositoryPort) {
        return new ProductoService(
                new CreateProductoUseCaseImpl(productoRepositoryPort),
                new DeleteProductoUseCaseImpl(productoRepositoryPort),
                new RetrieveProductoUseCaseImpl(productoRepositoryPort),
                new UpdateProductoUseCaseImpl(productoRepositoryPort)
        );
    }

    @Bean
    ProductoRepositoryPort productoRepositoryPort(JpaProductoRepositoryAdapter jpaProductoRepositoryAdapter) {
        return jpaProductoRepositoryAdapter;
    }

//    @Bean
//    public ExternalServicePort externalServicePort() {
//        return new ExternalServicePort();
//    }

}
