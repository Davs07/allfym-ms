package com.grupo.allfym.ms_compra.infrastucture.config;

import com.grupo.allfym.ms_compra.application.services.CompraService;
import com.grupo.allfym.ms_compra.application.usecases.*;
import com.grupo.allfym.ms_compra.domain.ports.out.AlmacenServicePort;
import com.grupo.allfym.ms_compra.domain.ports.out.CompraRepositoryPort;
import com.grupo.allfym.ms_compra.domain.ports.out.ProveedorServicePort;
import com.grupo.allfym.ms_compra.infrastucture.adapters.AlmacenServiceAdapter;
import com.grupo.allfym.ms_compra.infrastucture.adapters.ProveedorServiceAdapter;
import com.grupo.allfym.ms_compra.infrastucture.clients.AlmacenClient;
import com.grupo.allfym.ms_compra.infrastucture.clients.ProveedorClient;
import com.grupo.allfym.ms_compra.infrastucture.repositories.JpaCompraRepository;
import com.grupo.allfym.ms_compra.infrastucture.repositories.JpaCompraRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public CompraService compraService(CompraRepositoryPort compraRepositoryPort,
                                       ProveedorServicePort proveedorServicePort,
                                       AlmacenServicePort almacenServicePort) {
        return new CompraService(
                new BuscarCompraPorIdUseCaseImpl(compraRepositoryPort),
                new GuardarCompraUseCaseImpl(compraRepositoryPort, proveedorServicePort, almacenServicePort),
                new ListarComprasUseCaseImpl(compraRepositoryPort),
                new EliminarCompraUseCaseImpl(compraRepositoryPort),
                new BuscarComprasPorEstadoUseCaseImpl(compraRepositoryPort),
                new BuscarComprasPorProveedorUseCaseImpl(compraRepositoryPort, proveedorServicePort),
                new CambiarEstadoCompraUseCaseImpl(compraRepositoryPort),
                new AgregarDetalleCompraUseCaseImpl(compraRepositoryPort, almacenServicePort)
        );
    }

    @Bean
    public CompraRepositoryPort compraRepositoryPort(JpaCompraRepository jpaCompraRepository) {
        return new JpaCompraRepositoryAdapter(jpaCompraRepository);
    }

    @Bean
    public ProveedorServicePort proveedorServicePort(ProveedorClient proveedorClient) {
        return new ProveedorServiceAdapter(proveedorClient);
    }

    @Bean
    public AlmacenServicePort almacenServicePort(AlmacenClient almacenClient) {
        return new AlmacenServiceAdapter(almacenClient);
    }
}
