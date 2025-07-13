package com.grupo.allfym.ms_proveedores.services;

import com.grupo.allfym.ms_proveedores.models.dto.ProveedorRequestDTO;
import com.grupo.allfym.ms_proveedores.models.dto.ProveedorResponseDTO;
import com.grupo.allfym.ms_proveedores.models.entities.Proveedor;
import com.grupo.allfym.ms_proveedores.models.enums.Estado;
import com.grupo.allfym.ms_proveedores.models.valueObjects.Direccion;
import com.grupo.allfym.ms_proveedores.models.valueObjects.Email;
import com.grupo.allfym.ms_proveedores.models.valueObjects.FechaDeRegistro;
import com.grupo.allfym.ms_proveedores.models.valueObjects.Telefono;
import com.grupo.allfym.ms_proveedores.repositories.ProveedorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProveedorServicioImpl implements ProveedorServicio{

    private static final Logger logger = LoggerFactory.getLogger(ProveedorServicioImpl.class);

    @Autowired
    private ProveedorRepository repository;

    // === MÉTODOS OPTIMIZADOS CON DTOs ===

    @Override
    @Transactional
    public ProveedorResponseDTO crearProveedor(ProveedorRequestDTO proveedorRequest) {
        try {
            logger.info("Creando proveedor: {}", proveedorRequest.getNombre());

            // Crear value objects
            Telefono telefono = new Telefono(proveedorRequest.getTelefono());
            Email email = new Email(proveedorRequest.getEmail());
            Direccion direccion = new Direccion(
                proveedorRequest.getCalle(),
                proveedorRequest.getCiudad(),
                proveedorRequest.getCodigoPostal(),
                proveedorRequest.getPais()
            );

            // Crear proveedor
            Proveedor proveedor = new Proveedor();
            proveedor.setNombre(proveedorRequest.getNombre());
            proveedor.setRUC(proveedorRequest.getRuc());
            proveedor.setTelefono(telefono);
            proveedor.setEmail(email);
            proveedor.setDireccion(direccion);
            proveedor.setEstado(Estado.ACTIVO);
            proveedor.setFechaRegistro(new FechaDeRegistro());

            Proveedor proveedorGuardado = repository.save(proveedor);
            logger.info("Proveedor creado exitosamente con ID: {}", proveedorGuardado.getId());

            return convertirAProveedorResponseDTO(proveedorGuardado);
        } catch (Exception e) {
            logger.error("Error al crear proveedor: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProveedorResponseDTO> obtenerProveedorPorId(Long id) {
        return repository.findById(id)
                .map(this::convertirAProveedorResponseDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProveedorResponseDTO> obtenerTodosLosProveedores() {
        return repository.findAll().stream()
                .map(this::convertirAProveedorResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProveedorResponseDTO> obtenerProveedoresPorEstado(String estado) {
        try {
            Estado estadoEnum = Estado.valueOf(estado.toUpperCase());
            return repository.findByEstado(estadoEnum).stream()
                    .map(this::convertirAProveedorResponseDTO)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            logger.error("Estado inválido: {}", estado);
            throw new RuntimeException("Estado inválido: " + estado);
        }
    }

    @Override
    @Transactional
    public ProveedorResponseDTO actualizarProveedor(Long id, ProveedorRequestDTO proveedorRequest) {
        try {
            Proveedor proveedor = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

            // Actualizar campos
            proveedor.setNombre(proveedorRequest.getNombre());
            proveedor.setRUC(proveedorRequest.getRuc());
            proveedor.setTelefono(new Telefono(proveedorRequest.getTelefono()));
            proveedor.setEmail(new Email(proveedorRequest.getEmail()));
            proveedor.setDireccion(new Direccion(
                proveedorRequest.getCalle(),
                proveedorRequest.getCiudad(),
                proveedorRequest.getCodigoPostal(),
                proveedorRequest.getPais()
            ));

            Proveedor proveedorActualizado = repository.save(proveedor);
            logger.info("Proveedor {} actualizado exitosamente", id);

            return convertirAProveedorResponseDTO(proveedorActualizado);
        } catch (Exception e) {
            logger.error("Error al actualizar proveedor {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public void eliminarProveedor(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Proveedor no encontrado con ID: " + id);
        }
        repository.deleteById(id);
        logger.info("Proveedor {} eliminado exitosamente", id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProveedorResponseDTO> buscarPorRuc(String ruc) {
        return repository.findByRUC(ruc)
                .map(this::convertirAProveedorResponseDTO);
    }

    private ProveedorResponseDTO convertirAProveedorResponseDTO(Proveedor proveedor) {
        return new ProveedorResponseDTO(
            proveedor.getId(),
            proveedor.getNombre(),
            proveedor.getRUC(),
            proveedor.getTelefono() != null ? proveedor.getTelefono().getNumero() : null,
            proveedor.getEmail() != null ? proveedor.getEmail().getEmail() : null,
            proveedor.getDireccion() != null ? proveedor.getDireccion().getCalle() : null,
            proveedor.getDireccion() != null ? proveedor.getDireccion().getCiudad() : null,
            proveedor.getDireccion() != null ? proveedor.getDireccion().getCodigoPostal() : null,
            proveedor.getDireccion() != null ? proveedor.getDireccion().getPais() : null,
            proveedor.getEstado().toString(),
            proveedor.getFechaRegistro() != null ?
                proveedor.getFechaRegistro().getFecha().atStartOfDay() : null
        );
    }

    // === MÉTODOS LEGACY (Mantenidos para compatibilidad) ===

    @Override
    @Transactional(readOnly = true)
    public List<Proveedor> listar() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Proveedor> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional()
    public Proveedor guardar(Proveedor proveedor) {
        if (proveedor == null || proveedor.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del proveedor es obligatorio");
        }
        return repository.save(proveedor);
    }

    @Override
    @Transactional()
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Proveedor no encontrado con ID: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Proveedor> listaporEstado(Estado estado) {
        return repository.findByEstado(estado);
    }
}
