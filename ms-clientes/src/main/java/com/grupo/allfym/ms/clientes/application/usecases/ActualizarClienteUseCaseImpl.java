package com.grupo.allfym.ms.clientes.application.usecases;

import com.grupo.allfym.ms.clientes.domain.models.entities.Cliente;
import com.grupo.allfym.ms.clientes.domain.models.ov.EmailAddress;
import com.grupo.allfym.ms.clientes.domain.models.ov.Telefono;
import com.grupo.allfym.ms.clientes.domain.ports.in.ActualizarClienteUseCase;
import com.grupo.allfym.ms.clientes.domain.ports.out.ClienteRepositoryPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación simple del caso de uso para actualizar clientes
 */
@Component
@Transactional
public class ActualizarClienteUseCaseImpl implements ActualizarClienteUseCase {
    
    private final ClienteRepositoryPort clienteRepositoryPort;
    
    public ActualizarClienteUseCaseImpl(ClienteRepositoryPort clienteRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
    }
    
    @Override
    public Cliente actualizarDatos(Long id, String nombre, String apellido, String direccion) {
        Cliente cliente = clienteRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
        
        cliente.actualizarDatos(nombre, apellido, direccion);
        return clienteRepositoryPort.save(cliente);
    }
    
    @Override
    public Cliente cambiarEmail(Long id, EmailAddress nuevoEmail) {
        Cliente cliente = clienteRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
        
        // Validar que el email no esté en uso por otro cliente
        if (clienteRepositoryPort.existsByEmailAndIdNot(nuevoEmail, id)) {
            throw new RuntimeException("Ya existe un cliente con el email: " + nuevoEmail.getValor());
        }
        
        cliente.cambiarEmail(nuevoEmail);
        return clienteRepositoryPort.save(cliente);
    }
    
    @Override
    public Cliente cambiarTelefono(Long id, Telefono nuevoTelefono) {
        Cliente cliente = clienteRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
        
        cliente.cambiarTelefono(nuevoTelefono);
        return clienteRepositoryPort.save(cliente);
    }
}
