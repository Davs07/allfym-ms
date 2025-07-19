package com.grupo.allfym.ms.ventas.services.impl;


import com.grupo.allfym.ms.ventas.clients.AlmacenClient;
import com.grupo.allfym.ms.ventas.clients.ClienteClient;
import com.grupo.allfym.ms.ventas.clients.ProductoClient;
import com.grupo.allfym.ms.ventas.entity.DetalleVenta;
import com.grupo.allfym.ms.ventas.entity.Venta;
import com.grupo.allfym.ms.ventas.enums.EstadoVenta;
import com.grupo.allfym.ms.ventas.models.*;
import com.grupo.allfym.ms.ventas.repositories.VentaRepository;
import com.grupo.allfym.ms.ventas.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ClienteClient clienteClient;

    @Autowired
    private ProductoClient productoClient;

    @Autowired
    private AlmacenClient almacenCliente;

    @Override
    @Transactional
    public VentaResponseDTO agregarVenta(VentaRequestDTO ventaRequest) {
        // Validar que el cliente existe usando Feign
        ResponseEntity<ClienteResponseDTO> clienteResponse = clienteClient.obtenerClientePorId(ventaRequest.getClienteId());
        if (clienteResponse.getBody() == null) {
            throw new RuntimeException("Cliente no encontrado");
        }

        Venta venta = new Venta(ventaRequest.getClienteId(), ventaRequest.getMetodoPago());

        // Agregar detalles si existen
        if (ventaRequest.getDetalles() != null && !ventaRequest.getDetalles().isEmpty()) {
            for (VentaRequestDTO.DetalleVentaDTO detalleDTO : ventaRequest.getDetalles()) {
                // Verificar que existe el producto usando Feign
                ResponseEntity<Producto> productoResponse = productoClient.obtenerProductoPorId(detalleDTO.getProductoId());
                if (productoResponse.getBody() == null) {
                    throw new RuntimeException("Producto no encontrado: " + detalleDTO.getProductoId());
                }

                // Verificar stock disponible antes de procesar la venta
                Producto producto = productoResponse.getBody();

                DetalleVenta detalle = new DetalleVenta(
                        detalleDTO.getProductoId(),
                        detalleDTO.getCantidad(),
                        detalleDTO.getPrecioUnitario()
                );
                venta.agregarDetalle(detalle);
            }
        }

        // Guardar la venta primero
        Venta ventaGuardada = ventaRepository.save(venta);

        // Reducir stock después de guardar la venta exitosamente
        if (ventaRequest.getDetalles() != null && !ventaRequest.getDetalles().isEmpty()) {
            for (VentaRequestDTO.DetalleVentaDTO detalleDTO : ventaRequest.getDetalles()) {
                try {
                    almacenCliente.reducirStock(detalleDTO.getProductoId(), detalleDTO.getCantidad());
                } catch (Exception e) {
                    // En caso de error, podrías implementar compensación
                    throw new RuntimeException("Error al reducir stock del producto: " + detalleDTO.getProductoId(), e);
                }
            }
        }

        return convertirAVentaResponseDTO(ventaGuardada);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VentaResponseDTO> buscarPorId(Long id) {
        return ventaRepository.findById(id)
                .map(this::convertirAVentaResponseDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VentaResponseDTO> obtenerTodasLasVentas() {
        return ventaRepository.findAll().stream()
                .map(this::convertirAVentaResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VentaResponseDTO> buscarPorClienteId(Long clienteId) {
        return ventaRepository.findByClienteId(clienteId).stream()
                .map(this::convertirAVentaResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VentaResponseDTO> buscarPorEstado(EstadoVenta estado) {
        return ventaRepository.findByEstado(estado).stream()
                .map(this::convertirAVentaResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VentaResponseDTO> buscarPorFechaRegistro(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return ventaRepository.findByFechaRegistroBetween(fechaInicio, fechaFin).stream()
                .map(this::convertirAVentaResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VentaResponseDTO confirmarVenta(Long ventaId) {
        Venta venta = ventaRepository.findById(ventaId)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        venta.confirmarVenta();
        Venta ventaActualizada = ventaRepository.save(venta);
        return convertirAVentaResponseDTO(ventaActualizada);
    }

    @Override
    public VentaResponseDTO cancelarVenta(Long ventaId) {
        Venta venta = ventaRepository.findById(ventaId)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        venta.cancelarVenta();
        Venta ventaActualizada = ventaRepository.save(venta);
        return convertirAVentaResponseDTO(ventaActualizada);
    }

    @Override
    public void eliminarVenta(Long id) {
        ventaRepository.deleteById(id);
    }

    private VentaResponseDTO convertirAVentaResponseDTO(Venta venta) {

        // Convertir detalles de venta a DTO
        List<VentaResponseDTO.DetalleVentaResponseDTO> detallesDTO = venta.getDetalles().stream()
                .map(detalle -> {
                    // Obtener información del producto usando Feign
                    String productoNombre = "Producto no encontrado";
                    String productoDescripcion = "";
                    BigDecimal productoPrecio = BigDecimal.ZERO;
                    String productoCategoria = "";

                    try {
                        ResponseEntity<Producto> productoResponse = productoClient.obtenerProductoPorId(detalle.getProductoId());
                        if (productoResponse.getBody() != null) {
                            Producto producto = productoResponse.getBody();
                            productoNombre = producto.getNombre();
                            productoDescripcion = producto.getDescripcion();
                            productoPrecio = BigDecimal.valueOf(producto.getPrecio());
                            productoCategoria = producto.getCategoria();
                        }
                    } catch (Exception e) {
                        // Si hay error al obtener el producto, usar valores por defecto
                        productoNombre = "Producto ID: " + detalle.getProductoId();
                    }

                    return new VentaResponseDTO.DetalleVentaResponseDTO(
                            detalle.getId(),
                            detalle.getProductoId(),
                            productoNombre,
                            productoDescripcion,
                            productoPrecio,
                            productoCategoria,
                            detalle.getCantidad(),
                            detalle.getPrecioUnitario(),
                            detalle.getSubtotal()
                    );
                })
                .collect(Collectors.toList());

        // Obtener información del cliente usando Feign
        String nombreCliente = "Cliente no encontrado";
        try {
            ResponseEntity<ClienteResponseDTO> clienteResponse = clienteClient.obtenerClientePorId(venta.getClienteId());
            if (clienteResponse.getBody() != null) {
                nombreCliente = clienteResponse.getBody().getNombreCompleto();
            }
        } catch (Exception e) {
            // Si hay error al obtener el cliente, usar valor por defecto
            nombreCliente = "Cliente ID: " + venta.getClienteId();
        }

        return new VentaResponseDTO(
                venta.getId(),
                venta.getClienteId(),
                nombreCliente,
                venta.getFechaRegistro().getFechaRegistro(),
                venta.getMetodoPago(),
                venta.getEstado(),
                venta.getTotal(),
                detallesDTO
        );
    }
}