package com.grupo.allfym.ms.clientes.infrastructure.repositories;

import com.grupo.allfym.ms.clientes.infrastructure.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA simple para Cliente
 */
@Repository
public interface JpaClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByEmail(String email);
    
    Optional<ClienteEntity> findByDni(String dni);
    
    boolean existsByEmail(String email);
    
    boolean existsByDni(String dni);
    
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM ClienteEntity c WHERE c.email = :email AND c.id <> :id")
    boolean existsByEmailAndIdNot(@Param("email") String email, @Param("id") Long id);
    
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM ClienteEntity c WHERE c.dni = :dni AND c.id <> :id")
    boolean existsByDniAndIdNot(@Param("dni") String dni, @Param("id") Long id);
    
    List<ClienteEntity> findByEstado(String estado);
    
    long countByEstado(String estado);
}
