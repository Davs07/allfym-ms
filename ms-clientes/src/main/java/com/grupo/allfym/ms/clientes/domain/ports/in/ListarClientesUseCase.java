package com.grupo.allfym.ms.clientes.domain.ports.in;

import com.grupo.allfym.ms.clientes.domain.models.entities.Cliente;
import com.grupo.allfym.ms.clientes.domain.models.enums.EstadoCliente;

import java.util.List;

/**
 * Puerto de entrada para listar clientes
 */
public interface ListarClientesUseCase {

    /**
     * Obtiene todos los clientes del sistema
     * @return Lista de todos los clientes
     */
    List<Cliente> listarTodos();

    /**
     * Obtiene clientes por estado
     * @param estado Estado de los clientes a buscar
     * @return Lista de clientes con el estado especificado
     */
    List<Cliente> listarPorEstado(EstadoCliente estado);

    /**
     * Obtiene solo los clientes activos
     * @return Lista de clientes activos
     */
    List<Cliente> listarActivos();

    /**
     * Cuenta el total de clientes
     * @return Número total de clientes
     */
    long contarClientes();

    /**
     * Cuenta clientes por estado
     * @param estado Estado a contar
     * @return Número de clientes con el estado especificado
     */
    long contarPorEstado(EstadoCliente estado);
}
