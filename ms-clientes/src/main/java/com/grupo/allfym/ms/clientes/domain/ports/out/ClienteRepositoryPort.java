package com.grupo.allfym.ms.clientes.domain.ports.out;

import com.grupo.allfym.ms.clientes.domain.models.entities.Cliente;
import com.grupo.allfym.ms.clientes.domain.models.enums.EstadoCliente;
import com.grupo.allfym.ms.clientes.domain.models.ov.EmailAddress;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida para la persistencia de clientes
 * Define el contrato que debe cumplir cualquier implementación de repositorio
 */
public interface ClienteRepositoryPort {

    /**
     * Guarda un cliente en el repositorio
     * @param cliente Cliente a guardar
     * @return Cliente guardado con ID asignado
     */
    Cliente save(Cliente cliente);

    /**
     * Busca un cliente por su ID
     * @param id ID del cliente
     * @return Optional con el cliente si existe
     */
    Optional<Cliente> findById(Long id);

    /**
     * Busca un cliente por su email
     * @param email Email del cliente
     * @return Optional con el cliente si existe
     */
    Optional<Cliente> findByEmail(EmailAddress email);

    /**
     * Busca un cliente por su DNI
     * @param dni DNI del cliente
     * @return Optional con el cliente si existe
     */
    Optional<Cliente> findByDni(String dni);

    /**
     * Verifica si existe un cliente con el email dado
     * @param email Email a verificar
     * @return true si existe un cliente con ese email
     */
    boolean existsByEmail(EmailAddress email);

    /**
     * Verifica si existe un cliente con el DNI dado
     * @param dni DNI a verificar
     * @return true si existe un cliente con ese DNI
     */
    boolean existsByDni(String dni);

    /**
     * Verifica si existe un cliente con el email dado, excluyendo un ID específico
     * @param email Email a verificar
     * @param excludeId ID a excluir de la búsqueda
     * @return true si existe otro cliente con ese email
     */
    boolean existsByEmailAndIdNot(EmailAddress email, Long excludeId);

    /**
     * Verifica si existe un cliente con el DNI dado, excluyendo un ID específico
     * @param dni DNI a verificar
     * @param excludeId ID a excluir de la búsqueda
     * @return true si existe otro cliente con ese DNI
     */
    boolean existsByDniAndIdNot(String dni, Long excludeId);

    /**
     * Obtiene todos los clientes
     * @return Lista de todos los clientes
     */
    List<Cliente> findAll();

    /**
     * Obtiene clientes por estado
     * @param estado Estado de los clientes a buscar
     * @return Lista de clientes con el estado especificado
     */
    List<Cliente> findByEstado(EstadoCliente estado);

    /**
     * Elimina un cliente por su ID
     * @param id ID del cliente a eliminar
     */
    void deleteById(Long id);

    /**
     * Cuenta el total de clientes
     * @return Número total de clientes
     */
    long count();

    /**
     * Cuenta clientes por estado
     * @param estado Estado a contar
     * @return Número de clientes con el estado especificado
     */
    long countByEstado(EstadoCliente estado);
}
