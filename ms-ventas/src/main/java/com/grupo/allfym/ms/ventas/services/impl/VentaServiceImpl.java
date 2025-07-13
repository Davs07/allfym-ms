package com.grupo.allfym.ms.ventas.services.impl;

import com.grupo.allfym.ms.ventas.clients.ClienteClient;
import com.grupo.allfym.ms.ventas.entity.DetalleVenta;
import com.grupo.allfym.ms.ventas.entity.Venta;
import com.grupo.allfym.ms.ventas.enums.EstadoVenta;
import com.grupo.allfym.ms.ventas.models.ClienteResponseDTO;
import com.grupo.allfym.ms.ventas.models.VentaRequestDTO;
import com.grupo.allfym.ms.ventas.models.VentaResponseDTO;
import com.grupo.allfym.ms.ventas.repositories.VentaRepository;
import com.grupo.allfym.ms.ventas.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
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
                DetalleVenta detalle = new DetalleVenta(
                    detalleDTO.getProducto(),
                    detalleDTO.getCantidad(),
                    detalleDTO.getPrecioUnitario()
                );
                venta.agregarDetalle(detalle);
            }
        }

        Venta ventaGuardada = ventaRepository.save(venta);
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
        List<VentaResponseDTO.DetalleVentaResponseDTO> detallesDTO = venta.getDetalles().stream()
            .map(detalle -> new VentaResponseDTO.DetalleVentaResponseDTO(
                detalle.getId(),
                detalle.getProducto(),
                detalle.getCantidad(),
                detalle.getPrecioUnitario(),
                detalle.getSubtotal()
            ))
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
