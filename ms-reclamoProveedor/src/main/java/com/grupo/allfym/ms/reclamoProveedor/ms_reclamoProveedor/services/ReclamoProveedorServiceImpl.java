package com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.services;

import com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.clients.CompraCliente;
import com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.clients.CompraResponseDTO;
import com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.clients.ProveedorCliente;
import com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.clients.ProveedorResponseDTO;
import com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.models.dto.ReclamoProveedorRequestDTO;
import com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.models.dto.ReclamoProveedorResponseDTO;
import com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.models.entities.ReclamoProveedor;
import com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.models.enums.EstadoReclamo;
import com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.repositories.ReclamoProveedorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReclamoProveedorServiceImpl implements ReclamoProveedorService {

    private static final Logger logger = LoggerFactory.getLogger(ReclamoProveedorServiceImpl.class);

    @Autowired
    private ReclamoProveedorRepository repository;

    @Autowired
    private ProveedorCliente proveedorCliente;

    @Autowired
    private CompraCliente compraCliente;

    // === MÉTODOS OPTIMIZADOS CON DTOs ===

    @Override
    @Transactional
    public ReclamoProveedorResponseDTO crearReclamo(ReclamoProveedorRequestDTO reclamoRequest) {
        try {
            logger.info("Creando reclamo para proveedor ID: {}", reclamoRequest.getIdProveedor());

            // 1. VALIDAR QUE EL PROVEEDOR EXISTE
            var proveedorResponse = proveedorCliente.obtenerProveedorPorId(reclamoRequest.getIdProveedor());
            if (proveedorResponse.getBody() == null) {
                throw new RuntimeException("Proveedor no encontrado con ID: " + reclamoRequest.getIdProveedor());
            }

            // 2. VALIDAR COMPRA SI SE PROPORCIONA NRO_ORDEN
            Object compraInfo = null;
            if (reclamoRequest.getNroOrden() != null && !reclamoRequest.getNroOrden().trim().isEmpty()) {
                try {
                    // Buscar compra por número de orden (asumiendo que nroOrden corresponde al ID de compra)
                    Long idCompra = Long.parseLong(reclamoRequest.getNroOrden());
                    var compraResponse = compraCliente.obtenerCompraPorId(idCompra);
                    compraInfo = compraResponse.getBody();

                    if (compraInfo == null) {
                        logger.warn("No se encontró compra con ID: {}", idCompra);
                    }
                } catch (NumberFormatException e) {
                    logger.warn("Número de orden no es un ID válido: {}", reclamoRequest.getNroOrden());
                }
            }

            // 3. CREAR EL RECLAMO USANDO MÉTODO DE DOMINIO
            ReclamoProveedor reclamo = ReclamoProveedor.reclamo(
                reclamoRequest.getDescripcion(),
                reclamoRequest.getIdProveedor(),
                reclamoRequest.getNroOrden()
            );

            ReclamoProveedor reclamoGuardado = repository.save(reclamo);
            logger.info("Reclamo creado exitosamente con ID: {}", reclamoGuardado.getIdReclamo());

            return convertirAReclamoResponseDTO(reclamoGuardado, proveedorResponse.getBody(), compraInfo);

        } catch (Exception e) {
            logger.error("Error al crear reclamo: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReclamoProveedorResponseDTO> obtenerReclamoPorId(Long id) {
        try {
            Optional<ReclamoProveedor> reclamoOpt = repository.findById(id);
            if (reclamoOpt.isPresent()) {
                ReclamoProveedor reclamo = reclamoOpt.get();

                // Obtener información del proveedor
                Object proveedorInfo = null;
                Object compraInfo = null;

                try {
                    var proveedorResponse = proveedorCliente.obtenerProveedorPorId(reclamo.getIdProveedor());
                    proveedorInfo = proveedorResponse.getBody();
                } catch (Exception e) {
                    logger.warn("No se pudo obtener información del proveedor para reclamo {}", id);
                }

                // Obtener información de la compra si existe nroOrden
                if (reclamo.getNroOrden() != null) {
                    try {
                        Long idCompra = Long.parseLong(reclamo.getNroOrden());
                        var compraResponse = compraCliente.obtenerCompraPorId(idCompra);
                        compraInfo = compraResponse.getBody();
                    } catch (Exception e) {
                        logger.warn("No se pudo obtener información de la compra para reclamo {}", id);
                    }
                }

                return Optional.of(convertirAReclamoResponseDTO(reclamo, proveedorInfo, compraInfo));
            }
            return Optional.empty();
        } catch (Exception e) {
            logger.error("Error al obtener reclamo por ID {}: {}", id, e.getMessage());
            return Optional.empty();
        }
    }

    // === MÉTODOS DE DOMINIO IMPLEMENTADOS ===

    @Override
    @Transactional
    public ReclamoProveedorResponseDTO reclamo(String descripcion, Long idProveedor, String nroOrden) {
        logger.info("Creando reclamo básico para proveedor ID: {}", idProveedor);

        // Validar proveedor
        var proveedorResponse = proveedorCliente.obtenerProveedorPorId(idProveedor);
        if (proveedorResponse.getBody() == null) {
            throw new RuntimeException("Proveedor no encontrado con ID: " + idProveedor);
        }

        ReclamoProveedor reclamo = ReclamoProveedor.reclamo(descripcion, idProveedor, nroOrden);
        ReclamoProveedor reclamoGuardado = repository.save(reclamo);

        return convertirAReclamoResponseDTO(reclamoGuardado, proveedorResponse.getBody(), null);
    }

    @Override
    @Transactional
    public ReclamoProveedorResponseDTO agregarReclamo(Long idReclamo, String descripcionAdicional) {
        logger.info("Agregando información al reclamo ID: {}", idReclamo);

        ReclamoProveedor reclamo = repository.findById(idReclamo)
            .orElseThrow(() -> new RuntimeException("Reclamo no encontrado con ID: " + idReclamo));

        if (!reclamo.puedeSerModificado()) {
            throw new RuntimeException("El reclamo no puede ser modificado en su estado actual: " + reclamo.getEstadoReclamo());
        }

        reclamo.agregarReclamo(descripcionAdicional);
        ReclamoProveedor reclamoActualizado = repository.save(reclamo);

        // Obtener información adicional
        Object proveedorInfo = null;
        try {
            var proveedorResponse = proveedorCliente.obtenerProveedorPorId(reclamo.getIdProveedor());
            proveedorInfo = proveedorResponse.getBody();
        } catch (Exception e) {
            logger.warn("No se pudo obtener información del proveedor");
        }

        return convertirAReclamoResponseDTO(reclamoActualizado, proveedorInfo, null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReclamoProveedorResponseDTO> filtrarPorEstado(String estado) {
        try {
            EstadoReclamo estadoEnum = EstadoReclamo.valueOf(estado.toUpperCase().replace(" ", "_"));
            List<ReclamoProveedor> reclamos = repository.findByEstadoReclamo(estadoEnum);

            return reclamos.stream()
                .map(reclamo -> {
                    try {
                        var proveedorResponse = proveedorCliente.obtenerProveedorPorId(reclamo.getIdProveedor());
                        return convertirAReclamoResponseDTO(reclamo, proveedorResponse.getBody(), null);
                    } catch (Exception e) {
                        return convertirAReclamoResponseDTO(reclamo, null, null);
                    }
                })
                .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            logger.error("Estado inválido: {}", estado);
            throw new RuntimeException("Estado inválido: " + estado);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReclamoProveedorResponseDTO> filtrarPorIdProveedor(Long idProveedor) {
        // Validar que el proveedor existe
        var proveedorResponse = proveedorCliente.obtenerProveedorPorId(idProveedor);
        if (proveedorResponse.getBody() == null) {
            throw new RuntimeException("Proveedor no encontrado con ID: " + idProveedor);
        }

        List<ReclamoProveedor> reclamos = repository.findByIdProveedor(idProveedor);
        Object proveedorInfo = proveedorResponse.getBody();

        return reclamos.stream()
            .map(reclamo -> convertirAReclamoResponseDTO(reclamo, proveedorInfo, null))
            .collect(Collectors.toList());
    }

    // === MÉTODOS ADICIONALES ===

    @Override
    @Transactional(readOnly = true)
    public List<ReclamoProveedorResponseDTO> obtenerTodosLosReclamos() {
        List<ReclamoProveedor> reclamos = repository.findAll();
        return reclamos.stream()
            .map(reclamo -> {
                try {
                    var proveedorResponse = proveedorCliente.obtenerProveedorPorId(reclamo.getIdProveedor());
                    return convertirAReclamoResponseDTO(reclamo, proveedorResponse.getBody(), null);
                } catch (Exception e) {
                    return convertirAReclamoResponseDTO(reclamo, null, null);
                }
            })
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ReclamoProveedorResponseDTO cambiarEstadoReclamo(Long id, String nuevoEstado) {
        ReclamoProveedor reclamo = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reclamo no encontrado"));

        EstadoReclamo estadoEnum = EstadoReclamo.valueOf(nuevoEstado.toUpperCase().replace(" ", "_"));
        reclamo.cambiarEstado(estadoEnum);

        ReclamoProveedor reclamoActualizado = repository.save(reclamo);

        // Obtener información del proveedor
        Object proveedorInfo = null;
        try {
            var proveedorResponse = proveedorCliente.obtenerProveedorPorId(reclamo.getIdProveedor());
            proveedorInfo = proveedorResponse.getBody();
        } catch (Exception e) {
            logger.warn("No se pudo obtener información del proveedor");
        }

        return convertirAReclamoResponseDTO(reclamoActualizado, proveedorInfo, null);
    }

    @Override
    @Transactional
    public ReclamoProveedorResponseDTO actualizarReclamo(Long id, ReclamoProveedorRequestDTO reclamoRequest) {
        ReclamoProveedor reclamo = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reclamo no encontrado"));

        // Actualizar campos
        reclamo.setDescripcion(reclamoRequest.getDescripcion());
        if (reclamoRequest.getNroOrden() != null) {
            reclamo.setNroOrden(reclamoRequest.getNroOrden());
        }

        ReclamoProveedor reclamoActualizado = repository.save(reclamo);

        // Obtener información del proveedor
        Object proveedorInfo = null;
        try {
            var proveedorResponse = proveedorCliente.obtenerProveedorPorId(reclamo.getIdProveedor());
            proveedorInfo = proveedorResponse.getBody();
        } catch (Exception e) {
            logger.warn("No se pudo obtener información del proveedor");
        }

        return convertirAReclamoResponseDTO(reclamoActualizado, proveedorInfo, null);
    }

    @Override
    @Transactional
    public void eliminarReclamo(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Reclamo no encontrado con ID: " + id);
        }
        repository.deleteById(id);
        logger.info("Reclamo {} eliminado exitosamente", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReclamoProveedorResponseDTO> obtenerReclamosPorProveedorYEstado(Long idProveedor, String estado) {
        EstadoReclamo estadoEnum = EstadoReclamo.valueOf(estado.toUpperCase().replace(" ", "_"));
        List<ReclamoProveedor> reclamos = repository.findByIdProveedorAndEstadoReclamo(idProveedor, estadoEnum);

        return reclamos.stream()
            .map(reclamo -> convertirAReclamoResponseDTO(reclamo, null, null))
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReclamoProveedorResponseDTO> obtenerReclamosPendientesPorProveedor(Long idProveedor) {
        List<ReclamoProveedor> reclamos = repository.findReclamosPendientesByProveedor(idProveedor);

        return reclamos.stream()
            .map(reclamo -> convertirAReclamoResponseDTO(reclamo, null, null))
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReclamoProveedorResponseDTO> buscarPorNroOrden(String nroOrden) {
        Optional<ReclamoProveedor> reclamoOpt = repository.findByNroOrden(nroOrden);
        return reclamoOpt.map(reclamo -> convertirAReclamoResponseDTO(reclamo, null, null));
    }

    // === MÉTODO DE CONVERSIÓN ===

    private ReclamoProveedorResponseDTO convertirAReclamoResponseDTO(ReclamoProveedor reclamo, Object proveedorInfo, Object compraInfo) {
        LocalDateTime fechaCreacion = reclamo.getFechaCreacion() != null ?
            reclamo.getFechaCreacion().getFecha().atStartOfDay() : null;

        ReclamoProveedorResponseDTO dto = new ReclamoProveedorResponseDTO(
            reclamo.getIdReclamo(),
            reclamo.getDescripcion(),
            reclamo.getIdProveedor(),
            reclamo.getNroOrden(),
            reclamo.getEstadoReclamo().toString(),
            fechaCreacion
        );

        // Agregar información del proveedor si está disponible
        if (proveedorInfo != null) {
            // Cast dinámico para acceder a los campos del proveedor
            try {
                var proveedor = (ProveedorResponseDTO) proveedorInfo;
                dto.setNombreProveedor(proveedor.getNombre());
                dto.setEmailProveedor(proveedor.getEmail());
            } catch (Exception e) {
                logger.warn("Error al procesar información del proveedor: {}", e.getMessage());
            }
        }

        // Agregar información de la compra si está disponible
        if (compraInfo != null) {
            try {
                var compra = (CompraResponseDTO) compraInfo;
                dto.setIdCompra(compra.getId());
                dto.setMontoCompra(compra.getMontoTotal());
            } catch (Exception e) {
                logger.warn("Error al procesar información de la compra: {}", e.getMessage());
            }
        }

        return dto;
    }

    // === MÉTODOS LEGACY ===

    @Override
    @Transactional(readOnly = true)
    public List<ReclamoProveedor> listar() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReclamoProveedor> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public ReclamoProveedor guardar(ReclamoProveedor reclamo) {
        return repository.save(reclamo);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
