package com.grupo.allfym.ms.clientes.services;

import com.grupo.allfym.ms.clientes.entity.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteService {

    // Método de dominio: agregar cliente
    Cliente agregarCliente(Cliente clienteRequest);

    // Método de dominio: actualizar cliente
    Cliente actualizarCliente(Long id, Cliente clienteRequest);

    // Método de dominio: eliminar cliente
    void eliminarCliente(Long id);

    // Método de dominio: buscar por ID
    Optional<Cliente> buscarPorId(Long id);

    // Método de dominio: buscar por nombre
    List<Cliente> buscarPorNombre(String nombre);

    List<Cliente> obtenerTodosLosClientes();

    List<Cliente> obtenerClientesActivos();

    Optional<Cliente> buscarPorEmail(String email);

    Optional<Cliente> buscarPorDni(String dni);

    Optional<Cliente> buscarPorTelefono(String telefono);

    Cliente desactivarCliente(Long id);

    Cliente activarCliente(Long id);
}
