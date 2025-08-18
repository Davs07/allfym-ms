package com.grupo.allfym.ms.ventas.infrastructure.adapters;

import com.grupo.allfym.ms.ventas.domain.models.Cliente;
import com.grupo.allfym.ms.ventas.domain.ports.out.ClienteServicePort;
import com.grupo.allfym.ms.ventas.infrastructure.clients.ClienteClient;
import com.grupo.allfym.ms.ventas.infrastructure.dtos.ClienteDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Adaptador que implementa el puerto de servicio de clientes.
 * Utiliza ClienteClient (Feign) para comunicarse con el microservicio de clientes.
 */
@Component
public class ClienteServiceAdapter implements ClienteServicePort {

    private final ClienteClient clienteClient;

    public ClienteServiceAdapter(ClienteClient clienteClient) {
        this.clienteClient = clienteClient;
    }

    @Override
    public Optional<Cliente> buscarPorId(Long clienteId) {
        try {
            var response = clienteClient.obtenerClientePorId(clienteId);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                ClienteDto clienteDto = response.getBody();
                Cliente cliente = convertirADominio(clienteDto);
                return Optional.of(cliente);
            }
            return Optional.empty();
        } catch (Exception e) {
            System.err.println("Error al buscar cliente: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public boolean existeYEstaActivo(Long clienteId) {
        try {
            var response = clienteClient.obtenerClientePorId(clienteId);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                ClienteDto cliente = response.getBody();
                return cliente.isActivo();
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error al verificar cliente: " + e.getMessage());
            return false;
        }
    }

    /**
     * Convierte ClienteDto a modelo de dominio.
     */
    private Cliente convertirADominio(ClienteDto dto) {
        return new Cliente(
            dto.getId(),
            dto.getNombre(),
            dto.getApellido(),
            dto.getNombreCompleto(),
            dto.getEmail(),
            dto.getDni(),
            dto.getTelefono(),
            dto.getDireccion(),
            dto.getFechaRegistro(),
            dto.getActivo()
        );
    }
}
