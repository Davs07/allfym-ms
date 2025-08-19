package com.grupo.allfym.ms.clientes.domain.ports.in;

import com.grupo.allfym.ms.clientes.domain.models.entities.Cliente;
import com.grupo.allfym.ms.clientes.domain.models.enums.EstadoCliente;

import java.util.List;

public interface ListarClientesUseCase {

    List<Cliente> listarTodos();

    List<Cliente> listarPorEstado(EstadoCliente estado);

    List<Cliente> listarActivos();

    long contarClientes();

    long contarPorEstado(EstadoCliente estado);
}
