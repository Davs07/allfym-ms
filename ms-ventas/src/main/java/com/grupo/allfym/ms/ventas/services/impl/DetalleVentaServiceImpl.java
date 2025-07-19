package com.grupo.allfym.ms.ventas.services.impl;

import com.grupo.allfym.ms.ventas.entity.DetalleVenta;
import com.grupo.allfym.ms.ventas.entity.Venta;
import com.grupo.allfym.ms.ventas.models.VentaRequestDTO.DetalleVentaDTO;
import com.grupo.allfym.ms.ventas.repositories.DetalleVentaRepository;
import com.grupo.allfym.ms.ventas.repositories.VentaRepository;
import com.grupo.allfym.ms.ventas.services.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetalleVentaServiceImpl implements DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public DetalleVenta agregarDetalle(Long ventaId, DetalleVentaDTO detalleDTO) {
        Venta venta = ventaRepository.findById(ventaId)
            .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        DetalleVenta detalle = new DetalleVenta(
            detalleDTO.getProductoId(),
            detalleDTO.getCantidad(),
            detalleDTO.getPrecioUnitario()
        );

        venta.agregarDetalle(detalle);
        ventaRepository.save(venta);

        return detalle;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DetalleVenta> buscarPorId(Long id) {
        return detalleVentaRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleVenta> buscarPorVentaId(Long ventaId) {
        return detalleVentaRepository.findByVentaIdOrderById(ventaId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleVenta> buscarPorProducto(Long productoId) {
        return detalleVentaRepository.findByProductoId(productoId);
    }

    @Override
    public DetalleVenta actualizarDetalle(Long id, DetalleVentaDTO detalleDTO) {
        DetalleVenta detalle = detalleVentaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Detalle de venta no encontrado"));

        detalle.setProductoId(detalleDTO.getProductoId());
        detalle.setCantidad(detalleDTO.getCantidad());
        detalle.setPrecioUnitario(detalleDTO.getPrecioUnitario());

        // Recalcular el total de la venta
        detalle.getVenta().calcularTotal();

        return detalleVentaRepository.save(detalle);
    }

    @Override
    public void eliminarDetalle(Long id) {
        DetalleVenta detalle = detalleVentaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Detalle de venta no encontrado"));

        Venta venta = detalle.getVenta();
        venta.getDetalles().remove(detalle);
        venta.calcularTotal();

        detalleVentaRepository.deleteById(id);
        ventaRepository.save(venta);
    }
}
