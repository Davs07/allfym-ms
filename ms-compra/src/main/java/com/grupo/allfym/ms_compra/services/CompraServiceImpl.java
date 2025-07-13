package com.grupo.allfym.ms_compra.services;

import com.grupo.allfym.ms_compra.clients.ProveedorCliente;
import com.grupo.allfym.ms_compra.models.dto.CompraRequestDTO;
import com.grupo.allfym.ms_compra.models.dto.CompraResponseDTO;
import com.grupo.allfym.ms_compra.models.dto.ProveedorResponseDTO;
import com.grupo.allfym.ms_compra.models.entities.Compra;
import com.grupo.allfym.ms_compra.models.entities.DetalleCompra;
import com.grupo.allfym.ms_compra.models.enums.Estado;
import com.grupo.allfym.ms_compra.models.valueObjects.Fecha;
import com.grupo.allfym.ms_compra.repositories.CompraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompraServiceImpl implements CompraService{

    private static final Logger logger = LoggerFactory.getLogger(CompraServiceImpl.class);

    @Autowired
    private CompraRepository repositoryCompra;

    @Autowired
    private ProveedorCliente proveedorClient;

    // === MÉTODOS OPTIMIZADOS CON DTOs ===

    @Override
    @Transactional
    public CompraResponseDTO crearCompra(CompraRequestDTO compraRequest) {
        try {
            logger.info("Creando compra para proveedor ID: {}", compraRequest.getProveedorId());

            // Validar que el proveedor existe usando Feign
            ResponseEntity<ProveedorResponseDTO> proveedorResponse = proveedorClient.obtenerProveedorPorId(compraRequest.getProveedorId());
            if (proveedorResponse.getBody() == null) {
                throw new RuntimeException("Proveedor no encontrado con ID: " + compraRequest.getProveedorId());
            }

            ProveedorResponseDTO proveedor = proveedorResponse.getBody();

            // Crear la compra
            Compra compra = new Compra();
            compra.setIdProveedor(compraRequest.getProveedorId());
            compra.setMontoTotal(compraRequest.getMontoTotal());
            compra.setObservaciones(compraRequest.getObservaciones());
            compra.setEstado(Estado.PENDIENTE);
            compra.setFechaEmision(new Fecha());

            // Agregar detalles si existen
            if (compraRequest.getDetalles() != null && !compraRequest.getDetalles().isEmpty()) {
                List<DetalleCompra> detalles = compraRequest.getDetalles().stream()
                    .map(detalleDTO -> {
                        DetalleCompra detalle = new DetalleCompra();
                        detalle.setIdProducto(Long.valueOf(detalleDTO.getProducto())); // Asumiendo que producto es un ID
                        detalle.setCantidad(detalleDTO.getCantidad());
                        detalle.setPrecioCompra(detalleDTO.getPrecioUnitario());
                        detalle.setSubtotal(detalleDTO.getCantidad() * detalleDTO.getPrecioUnitario());
                        detalle.setCompra(compra);
                        return detalle;
                    })
                    .collect(Collectors.toList());
                compra.setDetalles(detalles);
            }

            Compra compraGuardada = repositoryCompra.save(compra);
            logger.info("Compra creada exitosamente con ID: {}", compraGuardada.getId());

            return convertirACompraResponseDTO(compraGuardada, proveedor);
        } catch (Exception e) {
            logger.error("Error al crear compra: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CompraResponseDTO> obtenerCompraPorId(Long id) {
        try {
            Optional<Compra> compraOpt = repositoryCompra.findById(id);
            if (compraOpt.isPresent()) {
                Compra compra = compraOpt.get();
                try {
                    ResponseEntity<ProveedorResponseDTO> proveedorResponse = proveedorClient.obtenerProveedorPorId(compra.getIdProveedor());
                    ProveedorResponseDTO proveedor = proveedorResponse.getBody();
                    return Optional.of(convertirACompraResponseDTO(compra, proveedor));
                } catch (Exception e) {
                    logger.warn("No se pudo obtener información del proveedor para compra {}", id);
                    return Optional.of(convertirACompraResponseDTO(compra, null));
                }
            }
            return Optional.empty();
        } catch (Exception e) {
            logger.error("Error al obtener compra por ID {}: {}", id, e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompraResponseDTO> obtenerTodasLasCompras() {
        try {
            List<Compra> compras = (List<Compra>) repositoryCompra.findAll();
            return compras.stream()
                .map(compra -> {
                    try {
                        ResponseEntity<ProveedorResponseDTO> proveedorResponse = proveedorClient.obtenerProveedorPorId(compra.getIdProveedor());
                        return convertirACompraResponseDTO(compra, proveedorResponse.getBody());
                    } catch (Exception e) {
                        return convertirACompraResponseDTO(compra, null);
                    }
                })
                .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error al obtener todas las compras: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompraResponseDTO> obtenerComprasPorProveedor(Long proveedorId) {
        try {
            // Validar que el proveedor existe
            ResponseEntity<ProveedorResponseDTO> proveedorResponse = proveedorClient.obtenerProveedorPorId(proveedorId);
            if (proveedorResponse.getBody() == null) {
                throw new RuntimeException("Proveedor no encontrado con ID: " + proveedorId);
            }

            ProveedorResponseDTO proveedor = proveedorResponse.getBody();
            List<Compra> compras = repositoryCompra.findByIdProveedor(proveedorId);

            return compras.stream()
                .map(compra -> convertirACompraResponseDTO(compra, proveedor))
                .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error al obtener compras por proveedor {}: {}", proveedorId, e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public CompraResponseDTO actualizarEstadoCompra(Long id, String nuevoEstado) {
        try {
            Compra compra = repositoryCompra.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

            compra.setEstado(Estado.valueOf(nuevoEstado.toUpperCase()));

            // Si se recibe la compra, establecer fecha de recepción
            if (Estado.RECIBIDA.toString().equals(nuevoEstado.toUpperCase())) {
                compra.setFechaRecepcion(new Fecha());
            }

            Compra compraActualizada = repositoryCompra.save(compra);

            try {
                ResponseEntity<ProveedorResponseDTO> proveedorResponse = proveedorClient.obtenerProveedorPorId(compra.getIdProveedor());
                return convertirACompraResponseDTO(compraActualizada, proveedorResponse.getBody());
            } catch (Exception e) {
                return convertirACompraResponseDTO(compraActualizada, null);
            }
        } catch (Exception e) {
            logger.error("Error al actualizar estado de compra {}: {}", id, e.getMessage());
            throw e;
        }
    }

    private CompraResponseDTO convertirACompraResponseDTO(Compra compra, ProveedorResponseDTO proveedor) {
        List<CompraResponseDTO.DetalleCompraResponseDTO> detallesDTO = new ArrayList<>();

        if (compra.getDetalles() != null) {
            detallesDTO = compra.getDetalles().stream()
                .map(detalle -> new CompraResponseDTO.DetalleCompraResponseDTO(
                    detalle.getIdDetalleCom(),
                    detalle.getIdProducto().toString(),
                    detalle.getCantidad(),
                    detalle.getPrecioCompra(),
                    detalle.getSubtotal()
                ))
                .collect(Collectors.toList());
        }

        LocalDateTime fechaEmision = compra.getFechaEmision() != null ?
            compra.getFechaEmision().getFecha().atStartOfDay() : null;
        LocalDateTime fechaRecepcion = compra.getFechaRecepcion() != null ?
            compra.getFechaRecepcion().getFecha().atStartOfDay() : null;

        return new CompraResponseDTO(
            compra.getId(),
            compra.getIdProveedor(),
            proveedor != null ? proveedor.getNombre() : "Proveedor no disponible",
            proveedor != null ? proveedor.getRuc() : null,
            proveedor != null ? proveedor.getEmail() : null,
            fechaEmision,
            fechaRecepcion,
            compra.getEstado().toString(),
            compra.getMontoTotal(),
            compra.getObservaciones(),
            detallesDTO
        );
    }

    // === MÉTODOS LEGACY (Mantenidos para compatibilidad) ===
    @Override
    public void eliminar(Long id) {
        repositoryCompra.deleteById(id);
    }

    @Override
    public List<Compra> listar() {
        return (List<Compra>) repositoryCompra.findAll();
    }

    @Override
    public Optional<Compra> buscarPorId(Long idCompra) {
        return repositoryCompra.findById(idCompra);
    }

    @Override
    public List<Compra> buscarPorEstado(Estado estado) {
        return repositoryCompra.findByEstado(estado);
    }

    @Override
    public List<Compra> buscarPorIdProveedor(Long idProveedor) {
        try {
            // Validar que el proveedor existe usando el nuevo cliente Feign
            ResponseEntity<ProveedorResponseDTO> proveedorResponse = proveedorClient.obtenerProveedorPorId(idProveedor);
            if (proveedorResponse.getBody() == null) {
                throw new IllegalArgumentException("no se encontro el proveedor");
            }
            return repositoryCompra.findByIdProveedor(idProveedor);
        } catch (Exception e) {
            throw new IllegalArgumentException("no se encontro el proveedor");
        }
    }

    @Override
    public List<Compra> buscarPorNombreProveedor(String nombre) {
        try {
            // Obtener todos los proveedores usando el nuevo cliente Feign
            ResponseEntity<List<ProveedorResponseDTO>> proveedoresResponse = proveedorClient.obtenerTodosLosProveedores();
            List<ProveedorResponseDTO> proveedores = proveedoresResponse.getBody();

            if (proveedores == null) {
                return new ArrayList<>();
            }

            List<Long> ids = new ArrayList<>();
            for (ProveedorResponseDTO p : proveedores) {
                if (p.getNombre().equalsIgnoreCase(nombre)) {
                    ids.add(p.getId());
                }
            }

            List<Compra> todasLasCompras = (List<Compra>) repositoryCompra.findAll();
            List<Compra> resultado = new ArrayList<>();

            for (Compra c : todasLasCompras) {
                if (ids.contains(c.getIdProveedor())) {
                    resultado.add(c);
                }
            }
            return resultado;
        } catch (Exception e) {
            logger.error("Error al buscar compras por nombre de proveedor: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void cambiarEstado(Long id, String estadoN) {
        Optional<Compra> Op = repositoryCompra.findById(id);
        if(Op.isPresent()){
            Compra compra = Op.get();
            try{
                Estado estado = Estado.valueOf(estadoN.toUpperCase());
                compra.setEstado(estado);
                repositoryCompra.save(compra);
            }catch (IllegalArgumentException  e){
                throw new IllegalArgumentException("Estado inválido: " + estadoN);
            }
        }else {
            throw new IllegalArgumentException("No se encontro la compra");
        }

    }

    @Override
    public Compra guardar(Compra compra) {
        try {
            Long proveedorId = compra.getIdProveedor();
            logger.info("Intentando guardar compra para proveedor ID: {}", proveedorId);

            // Validar que el ID del proveedor no sea null
            if (proveedorId == null) {
                throw new IllegalArgumentException("El ID del proveedor no puede ser null");
            }

            // Validar que el proveedor existe usando el nuevo cliente Feign
            logger.debug("Consultando proveedor con ID: {}", proveedorId);
            ResponseEntity<ProveedorResponseDTO> proveedorResponse = proveedorClient.obtenerProveedorPorId(proveedorId);

            if (proveedorResponse == null || proveedorResponse.getBody() == null) {
                logger.error("Proveedor no encontrado con ID: {}", proveedorId);
                throw new IllegalArgumentException("Proveedor no encontrado con ID: " + proveedorId);
            }

            logger.info("Proveedor encontrado: {}", proveedorResponse.getBody().getNombre());

            List<DetalleCompra> detalles = compra.getDetalles();
            double total = 0.0;

            if (detalles != null) {
                for (DetalleCompra detalle : detalles) {
                    double subtotal = detalle.getCantidad() * detalle.getPrecioCompra();
                    detalle.setSubtotal(subtotal);
                    detalle.setCompra(compra);
                    total += subtotal;
                }
            }

            compra.setMontoTotal(total);
            Compra compraGuardada = repositoryCompra.save(compra);
            logger.info("Compra guardada exitosamente con ID: {}", compraGuardada.getId());
            return compraGuardada;
        } catch (Exception e) {
            logger.error("Error al guardar compra: {}", e.getMessage(), e);
            throw new IllegalArgumentException("Error al guardar compra: " + e.getMessage());
        }
    }

}
