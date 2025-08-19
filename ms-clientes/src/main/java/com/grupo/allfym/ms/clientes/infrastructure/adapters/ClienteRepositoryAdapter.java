package com.grupo.allfym.ms.clientes.infrastructure.adapters;

import com.grupo.allfym.ms.clientes.domain.models.entities.Cliente;
import com.grupo.allfym.ms.clientes.domain.models.enums.EstadoCliente;
import com.grupo.allfym.ms.clientes.domain.models.ov.EmailAddress;
import com.grupo.allfym.ms.clientes.domain.models.ov.FechaRegistro;
import com.grupo.allfym.ms.clientes.domain.models.ov.Telefono;
import com.grupo.allfym.ms.clientes.domain.ports.out.ClienteRepositoryPort;
import com.grupo.allfym.ms.clientes.infrastructure.entities.ClienteEntity;
import com.grupo.allfym.ms.clientes.infrastructure.repositories.JpaClienteRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador simple que implementa el puerto de repositorio
 */
@Component
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {

    private final JpaClienteRepository jpaClienteRepository;

    public ClienteRepositoryAdapter(JpaClienteRepository jpaClienteRepository) {
        this.jpaClienteRepository = jpaClienteRepository;
    }

    @Override
    public Cliente save(Cliente cliente) {
        ClienteEntity entity = toEntity(cliente);
        ClienteEntity savedEntity = jpaClienteRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return jpaClienteRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<Cliente> findByEmail(EmailAddress email) {
        return jpaClienteRepository.findByEmail(email.getValor())
                .map(this::toDomain);
    }

    @Override
    public Optional<Cliente> findByDni(String dni) {
        return jpaClienteRepository.findByDni(dni)
                .map(this::toDomain);
    }

    @Override
    public boolean existsByEmail(EmailAddress email) {
        return jpaClienteRepository.existsByEmail(email.getValor());
    }

    @Override
    public boolean existsByDni(String dni) {
        return jpaClienteRepository.existsByDni(dni);
    }

    @Override
    public boolean existsByEmailAndIdNot(EmailAddress email, Long excludeId) {
        return jpaClienteRepository.existsByEmailAndIdNot(email.getValor(), excludeId);
    }

    @Override
    public boolean existsByDniAndIdNot(String dni, Long excludeId) {
        return jpaClienteRepository.existsByDniAndIdNot(dni, excludeId);
    }

    @Override
    public List<Cliente> findAll() {
        return jpaClienteRepository.findAll()
                .stream()
                .map(this::toDomain)
                .filter(cliente -> cliente != null) // Filtrar registros que no se pudieron convertir
                .collect(Collectors.toList());
    }

    @Override
    public List<Cliente> findByEstado(EstadoCliente estado) {
        return jpaClienteRepository.findByEstado(estado.name())
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaClienteRepository.deleteById(id);
    }

    @Override
    public long count() {
        return jpaClienteRepository.count();
    }

    @Override
    public long countByEstado(EstadoCliente estado) {
        return jpaClienteRepository.countByEstado(estado.name());
    }

    // Mappers súper simples - Solo convierte, no valida
    private Cliente toDomain(ClienteEntity entity) {
        // Si hay campos críticos nulos, simplemente saltamos este registro
        if (entity.getId() == null) {
            return null;
        }
        
        try {
            return new Cliente(
                    entity.getId(),
                    entity.getNombre(),
                    entity.getApellido(),
                    entity.getDni(),
                    entity.getEmail() != null ? new EmailAddress(entity.getEmail()) : null,
                    entity.getTelefono() != null ? new Telefono(entity.getTelefono()) : null,
                    entity.getDireccion(),
                    entity.getFechaRegistro() != null ? new FechaRegistro(entity.getFechaRegistro()) : null,
                    entity.getEstado() != null ? EstadoCliente.valueOf(entity.getEstado()) : null
            );
        } catch (Exception e) {
            // Si hay algún error al convertir, simplemente retornamos null
            // El registro se saltará
            return null;
        }
    }

    private ClienteEntity toEntity(Cliente cliente) {
        ClienteEntity entity = new ClienteEntity();
        entity.setId(cliente.getId());
        entity.setNombre(cliente.getNombre());
        entity.setApellido(cliente.getApellido());
        entity.setDni(cliente.getDni());
        entity.setEmail(cliente.getEmail().getValor());
        entity.setTelefono(cliente.getTelefono().getNumero());
        entity.setDireccion(cliente.getDireccion());
        entity.setEstado(cliente.getEstado().name());
        entity.setFechaRegistro(cliente.getFechaRegistro().getFechaRegistro());
        return entity;
    }
}
