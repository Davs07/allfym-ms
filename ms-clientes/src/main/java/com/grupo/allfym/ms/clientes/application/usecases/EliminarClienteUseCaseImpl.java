package com.grupo.allfym.ms.clientes.application.usecases;

import com.grupo.allfym.ms.clientes.domain.ports.in.EliminarClienteUseCase;
import com.grupo.allfym.ms.clientes.domain.ports.out.ClienteRepositoryPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación simple del caso de uso para eliminar clientes
 */
@Component
@Transactional
public class EliminarClienteUseCaseImpl implements EliminarClienteUseCase {
    
    private final ClienteRepositoryPort clienteRepositoryPort;
    
    public EliminarClienteUseCaseImpl(ClienteRepositoryPort clienteRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
    }
    
    @Override
    public void eliminar(Long id) {
        if (!clienteRepositoryPort.findById(id).isPresent()) {
            throw new RuntimeException("Cliente no encontrado con ID: " + id);
        }
        
        clienteRepositoryPort.deleteById(id);
    }
    
    @Override
    public boolean puedeEliminar(Long id) {
        return clienteRepositoryPort.findById(id).isPresent();
    }
}
