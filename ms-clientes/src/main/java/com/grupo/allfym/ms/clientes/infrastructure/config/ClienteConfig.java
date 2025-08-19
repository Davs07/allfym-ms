package com.grupo.allfym.ms.clientes.infrastructure.config;

import com.grupo.allfym.ms.clientes.application.usecases.*;
import com.grupo.allfym.ms.clientes.domain.ports.in.*;
import com.grupo.allfym.ms.clientes.domain.ports.out.ClienteRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteConfig {

    @Bean
    public CrearClienteUseCase crearClienteUseCase(ClienteRepositoryPort clienteRepositoryPort) {
        return new CrearClienteUseCaseImpl(clienteRepositoryPort);
    }

    @Bean
    public BuscarClienteUseCase buscarClienteUseCase(ClienteRepositoryPort clienteRepositoryPort) {
        return new BuscarClienteUseCaseImpl(clienteRepositoryPort);
    }

    @Bean
    public ActualizarClienteUseCase actualizarClienteUseCase(ClienteRepositoryPort clienteRepositoryPort) {
        return new ActualizarClienteUseCaseImpl(clienteRepositoryPort);
    }

    @Bean
    public ListarClientesUseCase listarClientesUseCase(ClienteRepositoryPort clienteRepositoryPort) {
        return new ListarClientesUseCaseImpl(clienteRepositoryPort);
    }

    @Bean
    public EliminarClienteUseCase eliminarClienteUseCase(ClienteRepositoryPort clienteRepositoryPort) {
        return new EliminarClienteUseCaseImpl(clienteRepositoryPort);
    }

    @Bean
    public GestionarEstadoClienteUseCase gestionarEstadoClienteUseCase(ClienteRepositoryPort clienteRepositoryPort) {
        return new GestionarEstadoClienteUseCaseImpl(clienteRepositoryPort);
    }
}
