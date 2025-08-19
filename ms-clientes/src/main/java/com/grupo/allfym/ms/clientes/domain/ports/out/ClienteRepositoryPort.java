package com.grupo.allfym.ms.clientes.domain.ports.out;

import com.grupo.allfym.ms.clientes.domain.models.entities.Cliente;
import com.grupo.allfym.ms.clientes.domain.models.enums.EstadoCliente;
import com.grupo.allfym.ms.clientes.domain.models.ov.EmailAddress;

import java.util.List;
import java.util.Optional;

public interface ClienteRepositoryPort {

    Cliente save(Cliente cliente);

    Optional<Cliente> findById(Long id);

    Optional<Cliente> findByEmail(EmailAddress email);

    Optional<Cliente> findByDni(String dni);

    boolean existsByEmail(EmailAddress email);

    boolean existsByDni(String dni);

    boolean existsByEmailAndIdNot(EmailAddress email, Long excludeId);

    boolean existsByDniAndIdNot(String dni, Long excludeId);

    List<Cliente> findAll();

    List<Cliente> findByEstado(EstadoCliente estado);

    void deleteById(Long id);

    long count();

    long countByEstado(EstadoCliente estado);
}
