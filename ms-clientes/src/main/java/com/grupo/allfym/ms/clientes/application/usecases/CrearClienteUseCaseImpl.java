package com.grupo.allfym.ms.clientes.application.usecases;

import com.grupo.allfym.ms.clientes.domain.models.entities.Cliente;
import com.grupo.allfym.ms.clientes.domain.models.ov.EmailAddress;
import com.grupo.allfym.ms.clientes.domain.models.ov.Telefono;
import com.grupo.allfym.ms.clientes.domain.ports.in.CrearClienteUseCase;
import com.grupo.allfym.ms.clientes.domain.ports.out.ClienteRepositoryPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CrearClienteUseCaseImpl implements CrearClienteUseCase {
    
    private final ClienteRepositoryPort clienteRepositoryPort;
    
    public CrearClienteUseCaseImpl(ClienteRepositoryPort clienteRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
    }
    
    @Override
    public Cliente crear(String nombre, String apellido, String dni, EmailAddress email, 
                        Telefono telefono, String direccion) {
        
        // Validar email único
        if (clienteRepositoryPort.existsByEmail(email)) {
            throw new RuntimeException("Ya existe un cliente con el email: " + email.getValor());
        }
        
        // Validar DNI único
        if (clienteRepositoryPort.existsByDni(dni)) {
            throw new RuntimeException("Ya existe un cliente con el DNI: " + dni);
        }
        
        // Crear nueva entidad del dominio
        Cliente nuevoCliente = new Cliente(nombre, apellido, dni, email, telefono, direccion);
        
        // Guardar y retornar
        return clienteRepositoryPort.save(nuevoCliente);
    }
}
