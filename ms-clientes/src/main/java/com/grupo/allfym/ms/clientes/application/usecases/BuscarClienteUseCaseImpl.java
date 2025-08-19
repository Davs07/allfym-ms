package com.grupo.allfym.ms.clientes.application.usecases;

import com.grupo.allfym.ms.clientes.domain.models.entities.Cliente;
import com.grupo.allfym.ms.clientes.domain.models.ov.EmailAddress;
import com.grupo.allfym.ms.clientes.domain.ports.in.BuscarClienteUseCase;
import com.grupo.allfym.ms.clientes.domain.ports.out.ClienteRepositoryPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Implementación simple del caso de uso para buscar clientes
 */
@Component
@Transactional(readOnly = true)
public class BuscarClienteUseCaseImpl implements BuscarClienteUseCase {
    
    private final ClienteRepositoryPort clienteRepositoryPort;
    
    public BuscarClienteUseCaseImpl(ClienteRepositoryPort clienteRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
    }
    
    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepositoryPort.findById(id);
    }
    
    @Override
    public Optional<Cliente> buscarPorEmail(EmailAddress email) {
        return clienteRepositoryPort.findByEmail(email);
    }
    
    @Override
    public Optional<Cliente> buscarPorDni(String dni) {
        return clienteRepositoryPort.findByDni(dni);
    }
    
    @Override
    public Cliente obtenerPorId(Long id) {
        return clienteRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }
}
