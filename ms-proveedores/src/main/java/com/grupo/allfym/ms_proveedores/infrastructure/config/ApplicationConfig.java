package com.grupo.allfym.ms_proveedores.infrastructure.config;


import com.grupo.allfym.ms_proveedores.application.services.ProveedorService;
import com.grupo.allfym.ms_proveedores.application.usecases.*;
import com.grupo.allfym.ms_proveedores.domain.ports.out.ProveedorRepositoryPort;
import com.grupo.allfym.ms_proveedores.infrastructure.repositories.JpaProveedorRepository;
import com.grupo.allfym.ms_proveedores.infrastructure.repositories.JpaProveedorRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class ApplicationConfig {
    @Bean
    public ProveedorService proveedorService (ProveedorRepositoryPort proveedorRepositoryPort){
        return new ProveedorService(
                new BuscarProveedorPorIdUseCaseImpl(proveedorRepositoryPort),
                new EliminarProveedorUseCaseImpl(proveedorRepositoryPort),
                new GuardarProveedorUseCaseImpl(proveedorRepositoryPort),
                new ListarProveedorPorEstadoUseCaseImpl(proveedorRepositoryPort),
                new ListarProveedorUseCaseImpl(proveedorRepositoryPort),
                new ActualizarProveedorPorIdUseCaseImpl(proveedorRepositoryPort));
    }

    @Bean
    public ProveedorRepositoryPort proveedorRepositoryPort(JpaProveedorRepository jpaProveedorRepository){
        return new JpaProveedorRepositoryAdapter(jpaProveedorRepository);
    }

}
