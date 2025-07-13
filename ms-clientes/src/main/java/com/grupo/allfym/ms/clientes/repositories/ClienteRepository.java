package com.grupo.allfym.ms.clientes.repositories;

import com.grupo.allfym.ms.clientes.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(String nombre, String apellido);

    @Query("SELECT c FROM Cliente c WHERE CONCAT(c.nombre, ' ', c.apellido) LIKE %:nombreCompleto%")
    List<Cliente> findByNombreCompleto(@Param("nombreCompleto") String nombreCompleto);

    Optional<Cliente> findByEmailValor(String email);

    Optional<Cliente> findByDni(String dni);

    List<Cliente> findByActivoTrue();

    List<Cliente> findByActivoFalse();

    @Query("SELECT c FROM Cliente c WHERE c.telefono.numero = :telefono")
    Optional<Cliente> findByTelefono(@Param("telefono") String telefono);
}
