package com.grupo.allfym.ms.clientes.application.usecases;

import com.grupo.allfym.ms.clientes.domain.models.entities.Cliente;
import com.grupo.allfym.ms.clientes.domain.models.enums.EstadoCliente;
import com.grupo.allfym.ms.clientes.domain.ports.in.ListarClientesUseCase;
import com.grupo.allfym.ms.clientes.domain.ports.out.ClienteRepositoryPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class ListarClientesUseCaseImpl implements ListarClientesUseCase {
    
    private final ClienteRepositoryPort clienteRepositoryPort;
    
    public ListarClientesUseCaseImpl(ClienteRepositoryPort clienteRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
    }
    
    @Override
    public List<Cliente> listarTodos() {
        return clienteRepositoryPort.findAll();
    }
    
    @Override
    public List<Cliente> listarPorEstado(EstadoCliente estado) {
        return clienteRepositoryPort.findByEstado(estado);
    }
    
    @Override
    public List<Cliente> listarActivos() {
        return clienteRepositoryPort.findByEstado(EstadoCliente.ACTIVO);
    }
    
    @Override
    public long contarClientes() {
        return clienteRepositoryPort.count();
    }
    
    @Override
    public long contarPorEstado(EstadoCliente estado) {
        return clienteRepositoryPort.countByEstado(estado);
    }
}
