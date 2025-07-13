package com.grupo.allfym.ms.clientes.services;

import com.grupo.allfym.ms.clientes.models.ClienteRequestDTO;
import com.grupo.allfym.ms.clientes.models.ClienteResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    // Método de dominio: agregar cliente
    ClienteResponseDTO agregarCliente(ClienteRequestDTO clienteRequest);

    // Método de dominio: actualizar cliente
    ClienteResponseDTO actualizarCliente(Long id, ClienteRequestDTO clienteRequest);

    // Método de dominio: eliminar cliente
    void eliminarCliente(Long id);

    // Método de dominio: buscar por ID
    Optional<ClienteResponseDTO> buscarPorId(Long id);

    // Método de dominio: buscar por nombre
    List<ClienteResponseDTO> buscarPorNombre(String nombre);

    List<ClienteResponseDTO> obtenerTodosLosClientes();

    List<ClienteResponseDTO> obtenerClientesActivos();

    Optional<ClienteResponseDTO> buscarPorEmail(String email);

    Optional<ClienteResponseDTO> buscarPorDni(String dni);

    Optional<ClienteResponseDTO> buscarPorTelefono(String telefono);

    ClienteResponseDTO desactivarCliente(Long id);

    ClienteResponseDTO activarCliente(Long id);
}
