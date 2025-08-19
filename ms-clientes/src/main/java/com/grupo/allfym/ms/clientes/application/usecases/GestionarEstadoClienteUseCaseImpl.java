package com.grupo.allfym.ms.clientes.application.usecases;

import com.grupo.allfym.ms.clientes.domain.models.entities.Cliente;
import com.grupo.allfym.ms.clientes.domain.ports.in.GestionarEstadoClienteUseCase;
import com.grupo.allfym.ms.clientes.domain.ports.out.ClienteRepositoryPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class GestionarEstadoClienteUseCaseImpl implements GestionarEstadoClienteUseCase {
    
    private final ClienteRepositoryPort clienteRepositoryPort;
    
    public GestionarEstadoClienteUseCaseImpl(ClienteRepositoryPort clienteRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
    }
    
    @Override
    public Cliente activar(Long id) {
        Cliente cliente = clienteRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
        
        cliente.activar();
        return clienteRepositoryPort.save(cliente);
    }
    
    @Override
    public Cliente desactivar(Long id) {
        Cliente cliente = clienteRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
        
        cliente.desactivar();
        return clienteRepositoryPort.save(cliente);
    }
    
    @Override
    public Cliente suspender(Long id) {
        Cliente cliente = clienteRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
        
        cliente.suspender();
        return clienteRepositoryPort.save(cliente);
    }
}
