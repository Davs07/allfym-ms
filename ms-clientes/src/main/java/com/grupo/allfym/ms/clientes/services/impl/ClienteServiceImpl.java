package com.grupo.allfym.ms.clientes.services.impl;

import com.grupo.allfym.ms.clientes.entity.Cliente;
import com.grupo.allfym.ms.clientes.repositories.ClienteRepository;
import com.grupo.allfym.ms.clientes.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente agregarCliente(Cliente clienteRequest) {
        // Validar que el DNI no esté duplicado si se proporciona
        if (clienteRequest.getDni() != null && !clienteRequest.getDni().trim().isEmpty()) {
            Optional<Cliente> clienteExistente = clienteRepository.findByDni(clienteRequest.getDni());
            if (clienteExistente.isPresent()) {
                throw new RuntimeException("Ya existe un cliente con el DNI: " + clienteRequest.getDni());
            }
        }
        // Validar email y teléfono
        if (clienteRequest.getEmail() == null || clienteRequest.getEmail().getValor() == null) {
            throw new RuntimeException("El email es obligatorio");
        }
        if (clienteRequest.getTelefono() == null || clienteRequest.getTelefono() == null) {
            throw new RuntimeException("El teléfono es obligatorio");
        }
        Cliente clienteGuardado = clienteRepository.save(clienteRequest);
        return clienteGuardado;
    }

    @Override
    public Cliente actualizarCliente(Long id, Cliente clienteRequest) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        // Validar que el DNI no esté duplicado si se proporciona y es diferente al actual
        if (clienteRequest.getDni() != null && !clienteRequest.getDni().trim().isEmpty()) {
            Optional<Cliente> clienteExistente = clienteRepository.findByDni(clienteRequest.getDni());
            if (clienteExistente.isPresent() && !clienteExistente.get().getId().equals(id)) {
                throw new RuntimeException("Ya existe un cliente con el DNI: " + clienteRequest.getDni());
            }
        }
        cliente.setNombre(clienteRequest.getNombre());
        cliente.setApellido(clienteRequest.getApellido());
        cliente.setDni(clienteRequest.getDni());
        cliente.setEmail(clienteRequest.getEmail());
        cliente.setTelefono(clienteRequest.getTelefono());
        cliente.setDireccion(clienteRequest.getDireccion());
        Cliente clienteActualizado = clienteRepository.save(cliente);
        return clienteActualizado;
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> buscarPorNombre(String nombre) {
        return clienteRepository.findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(nombre, nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> obtenerTodosLosClientes() {
        List<Cliente> clientes = new ArrayList<>();
        clienteRepository.findAll().forEach(clientes::add);
        return clientes;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> obtenerClientesActivos() {
        return clienteRepository.findByActivoTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> buscarPorTelefono(String telefono) {
        return clienteRepository.findByTelefono(telefono);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> buscarPorDni(String dni) {
        return clienteRepository.findByDni(dni);
    }

    @Override
    public Cliente desactivarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        cliente.setActivo(false);
        Cliente clienteActualizado = clienteRepository.save(cliente);
        return clienteActualizado;
    }

    @Override
    public Cliente activarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        cliente.setActivo(true);
        Cliente clienteActualizado = clienteRepository.save(cliente);
        return clienteActualizado;
    }
}
