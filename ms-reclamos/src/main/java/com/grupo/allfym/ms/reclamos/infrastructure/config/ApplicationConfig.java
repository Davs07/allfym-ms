package com.grupo.allfym.ms.reclamos.infrastructure.config;

import com.grupo.allfym.ms.reclamos.application.services.ReclamoService;
import com.grupo.allfym.ms.reclamos.application.usecases.*;
import com.grupo.allfym.ms.reclamos.domain.ports.out.ClienteServicePort;
import com.grupo.allfym.ms.reclamos.domain.ports.out.ReclamoRepositoryPort;
import com.grupo.allfym.ms.reclamos.infrastructure.adapters.ClienteServiceAdapter;
import com.grupo.allfym.ms.reclamos.infrastructure.clients.ClienteClient;
import com.grupo.allfym.ms.reclamos.infrastructure.repositories.JpaReclamoRepository;
import com.grupo.allfym.ms.reclamos.infrastructure.repositories.JpaReclamoRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public ReclamoService reclamoService(ReclamoRepositoryPort repositoryPort,
                                         ClienteServicePort clienteServicePort) {
        return new ReclamoService(
                new ActualizarReclamoUseCaseImp(repositoryPort),
                new AsignarClienteUseCaseImp(repositoryPort, clienteServicePort),
                new BuscarPorClienteUseCaseImp(repositoryPort, clienteServicePort),
                new EliminarReclamoUseCaseImp(repositoryPort),
                new GuardarReclamoUseCaseImp(repositoryPort),
                new ObtenerReclamoUseCaseImp(repositoryPort, clienteServicePort),
                new RemoverClienteUseCaseImp(repositoryPort, clienteServicePort)
        );
    }

    @Bean
    public ReclamoRepositoryPort repositoryPort (JpaReclamoRepository repository) {
        return new JpaReclamoRepositoryAdapter(repository);
    }

    @Bean
    public ClienteServicePort clienteServicePort (ClienteClient client){
        return new ClienteServiceAdapter(client);
    }

}
