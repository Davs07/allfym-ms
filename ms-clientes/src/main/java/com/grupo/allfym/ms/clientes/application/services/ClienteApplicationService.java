package com.grupo.allfym.ms.clientes.application.services;

import com.grupo.allfym.ms.clientes.domain.models.entities.Cliente;
import com.grupo.allfym.ms.clientes.domain.models.enums.EstadoCliente;
import com.grupo.allfym.ms.clientes.domain.models.ov.EmailAddress;
import com.grupo.allfym.ms.clientes.domain.models.ov.Telefono;
import com.grupo.allfym.ms.clientes.domain.ports.in.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de aplicación que actúa como fachada simple
 * Delega todas las operaciones a los casos de uso correspondientes
 */
@Service
public class ClienteApplicationService {
    
    private final CrearClienteUseCase crearClienteUseCase;
    private final ActualizarClienteUseCase actualizarClienteUseCase;
    private final BuscarClienteUseCase buscarClienteUseCase;
    private final EliminarClienteUseCase eliminarClienteUseCase;
    private final ListarClientesUseCase listarClientesUseCase;
    private final GestionarEstadoClienteUseCase gestionarEstadoClienteUseCase;
    
    public ClienteApplicationService(
            CrearClienteUseCase crearClienteUseCase,
            ActualizarClienteUseCase actualizarClienteUseCase,
            BuscarClienteUseCase buscarClienteUseCase,
            EliminarClienteUseCase eliminarClienteUseCase,
            ListarClientesUseCase listarClientesUseCase,
            GestionarEstadoClienteUseCase gestionarEstadoClienteUseCase) {
        this.crearClienteUseCase = crearClienteUseCase;
        this.actualizarClienteUseCase = actualizarClienteUseCase;
        this.buscarClienteUseCase = buscarClienteUseCase;
        this.eliminarClienteUseCase = eliminarClienteUseCase;
        this.listarClientesUseCase = listarClientesUseCase;
        this.gestionarEstadoClienteUseCase = gestionarEstadoClienteUseCase;
    }
    
    public Cliente crear(String nombre, String apellido, String dni, EmailAddress email, 
                        Telefono telefono, String direccion) {
        return crearClienteUseCase.crear(nombre, apellido, dni, email, telefono, direccion);
    }
    
    public Cliente actualizar(Long id, String nombre, String apellido, String direccion) {
        return actualizarClienteUseCase.actualizarDatos(id, nombre, apellido, direccion);
    }
    
    public Cliente cambiarEmail(Long id, EmailAddress nuevoEmail) {
        return actualizarClienteUseCase.cambiarEmail(id, nuevoEmail);
    }
    
    public Cliente cambiarTelefono(Long id, Telefono nuevoTelefono) {
        return actualizarClienteUseCase.cambiarTelefono(id, nuevoTelefono);
    }
    
    public Optional<Cliente> obtenerPorId(Long id) {
        return buscarClienteUseCase.buscarPorId(id);
    }
    
    public Cliente obtener(Long id) {
        return buscarClienteUseCase.obtenerPorId(id);
    }
    
    public Optional<Cliente> obtenerPorEmail(EmailAddress email) {
        return buscarClienteUseCase.buscarPorEmail(email);
    }
    
    public Optional<Cliente> obtenerPorDni(String dni) {
        return buscarClienteUseCase.buscarPorDni(dni);
    }
    
    public List<Cliente> listarTodos() {
        return listarClientesUseCase.listarTodos();
    }
    
    public List<Cliente> listarPorEstado(EstadoCliente estado) {
        return listarClientesUseCase.listarPorEstado(estado);
    }
    
    public List<Cliente> listarActivos() {
        return listarClientesUseCase.listarActivos();
    }
    
    public void eliminar(Long id) {
        eliminarClienteUseCase.eliminar(id);
    }
    
    public Cliente activar(Long id) {
        return gestionarEstadoClienteUseCase.activar(id);
    }
    
    public Cliente desactivar(Long id) {
        return gestionarEstadoClienteUseCase.desactivar(id);
    }
    
    public Cliente suspender(Long id) {
        return gestionarEstadoClienteUseCase.suspender(id);
    }
}
