package com.grupo.allfym.ms.clientes.services.impl;

import com.grupo.allfym.ms.clientes.entity.Cliente;
import com.grupo.allfym.ms.clientes.models.ClienteRequestDTO;
import com.grupo.allfym.ms.clientes.models.ClienteResponseDTO;
import com.grupo.allfym.ms.clientes.ov.EmailAddress;
import com.grupo.allfym.ms.clientes.ov.Telefono;
import com.grupo.allfym.ms.clientes.repositories.ClienteRepository;
import com.grupo.allfym.ms.clientes.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ClienteResponseDTO agregarCliente(ClienteRequestDTO clienteRequest) {
        // Validar que el DNI no esté duplicado si se proporciona
        if (clienteRequest.getDni() != null && !clienteRequest.getDni().trim().isEmpty()) {
            Optional<Cliente> clienteExistente = clienteRepository.findByDni(clienteRequest.getDni());
            if (clienteExistente.isPresent()) {
                throw new RuntimeException("Ya existe un cliente con el DNI: " + clienteRequest.getDni());
            }
        }

        Cliente cliente = new Cliente();
        cliente.setNombre(clienteRequest.getNombre());
        cliente.setApellido(clienteRequest.getApellido());
        cliente.setDni(clienteRequest.getDni());
        cliente.setEmail(new EmailAddress(clienteRequest.getEmail()));
        cliente.setTelefono(new Telefono(clienteRequest.getTelefono()));
        cliente.setDireccion(clienteRequest.getDireccion());

        Cliente clienteGuardado = clienteRepository.save(cliente);
        return convertirAClienteResponseDTO(clienteGuardado);
    }

    @Override
    public ClienteResponseDTO actualizarCliente(Long id, ClienteRequestDTO clienteRequest) {
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
        cliente.setEmail(new EmailAddress(clienteRequest.getEmail()));
        cliente.setTelefono(new Telefono(clienteRequest.getTelefono()));
        cliente.setDireccion(clienteRequest.getDireccion());

        Cliente clienteActualizado = clienteRepository.save(cliente);
        return convertirAClienteResponseDTO(clienteActualizado);
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClienteResponseDTO> buscarPorId(Long id) {
        return clienteRepository.findById(id)
            .map(this::convertirAClienteResponseDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> buscarPorNombre(String nombre) {
        return clienteRepository.findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(nombre, nombre)
            .stream()
            .map(this::convertirAClienteResponseDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> obtenerTodosLosClientes() {
        return clienteRepository.findAll().stream()
            .map(this::convertirAClienteResponseDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> obtenerClientesActivos() {
        return clienteRepository.findByActivoTrue().stream()
            .map(this::convertirAClienteResponseDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClienteResponseDTO> buscarPorEmail(String email) {
        return clienteRepository.findByEmailValor(email)
            .map(this::convertirAClienteResponseDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClienteResponseDTO> buscarPorTelefono(String telefono) {
        return clienteRepository.findByTelefono(telefono)
            .map(this::convertirAClienteResponseDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClienteResponseDTO> buscarPorDni(String dni) {
        return clienteRepository.findByDni(dni)
            .map(this::convertirAClienteResponseDTO);
    }

    @Override
    public ClienteResponseDTO desactivarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cliente.setActivo(false);
        Cliente clienteActualizado = clienteRepository.save(cliente);
        return convertirAClienteResponseDTO(clienteActualizado);
    }

    @Override
    public ClienteResponseDTO activarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cliente.setActivo(true);
        Cliente clienteActualizado = clienteRepository.save(cliente);
        return convertirAClienteResponseDTO(clienteActualizado);
    }

    private ClienteResponseDTO convertirAClienteResponseDTO(Cliente cliente) {
        return new ClienteResponseDTO(
            cliente.getId(),
            cliente.getNombre(),
            cliente.getApellido(),
            cliente.getEmail() != null ? cliente.getEmail().getValor() : null,
            cliente.getDni(),
            cliente.getTelefono() != null ? cliente.getTelefono().getNumero() : null,
            cliente.getDireccion(),
            cliente.getFechaRegistro().getFechaRegistro(),
            cliente.getActivo()
        );
    }
}
