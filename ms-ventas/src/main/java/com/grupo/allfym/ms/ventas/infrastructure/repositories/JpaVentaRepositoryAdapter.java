package com.grupo.allfym.ms.ventas.infrastructure.repositories;

import com.grupo.allfym.ms.ventas.domain.models.entities.Venta;
import com.grupo.allfym.ms.ventas.domain.models.entities.DetalleVenta;
import com.grupo.allfym.ms.ventas.domain.models.enums.EstadoVenta;
import com.grupo.allfym.ms.ventas.domain.models.vo.FechaRegistro;
import com.grupo.allfym.ms.ventas.domain.ports.out.VentaRepositoryPort;
import com.grupo.allfym.ms.ventas.infrastructure.entities.VentaEntity;
import com.grupo.allfym.ms.ventas.infrastructure.entities.DetalleVentaEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaVentaRepositoryAdapter implements VentaRepositoryPort {

    private final JpaVentaRepository jpaRepository;

    public JpaVentaRepositoryAdapter(JpaVentaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Venta guardar(Venta venta) {
        VentaEntity entity = convertirAEntity(venta);
        VentaEntity savedEntity = jpaRepository.save(entity);
        return convertirADominio(savedEntity);
    }

    @Override
    public Optional<Venta> buscarPorId(Long id) {
        return jpaRepository.findById(id)
                .map(this::convertirADominio);
    }

    @Override
    public List<Venta> buscarTodas() {
        return jpaRepository.findAll().stream()
                .map(this::convertirADominio)
                .collect(Collectors.toList());
    }

    @Override
    public List<Venta> buscarPorClienteId(Long clienteId) {
        return jpaRepository.findByClienteId(clienteId).stream()
                .map(this::convertirADominio)
                .collect(Collectors.toList());
    }

    @Override
    public List<Venta> buscarPorEstado(EstadoVenta estado) {
        return jpaRepository.findByEstado(estado).stream()
                .map(this::convertirADominio)
                .collect(Collectors.toList());
    }

    @Override
    public List<Venta> buscarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return jpaRepository.findByFechaRegistroBetween(fechaInicio, fechaFin).stream()
                .map(this::convertirADominio)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarPorId(Long id) {
        jpaRepository.deleteById(id);
    }

    /**
     * Convierte entidad de dominio a entidad JPA.
     */
    private VentaEntity convertirAEntity(Venta venta) {
        VentaEntity entity = new VentaEntity();
        
        entity.setId(venta.getId());
        entity.setClienteId(venta.getClienteId());
        entity.setFechaRegistro(venta.getFechaRegistro().getFechaRegistro());
        entity.setMetodoPago(venta.getMetodoPago());
        entity.setEstado(venta.getEstado());
        entity.setTotal(venta.getTotal());

        // Convertir detalles
        List<DetalleVentaEntity> detallesEntity = venta.getDetalles().stream()
                .map(detalle -> convertirDetalleAEntity(detalle, entity))
                .collect(Collectors.toList());
        entity.setDetalles(detallesEntity);

        return entity;
    }

    /**
     * Convierte entidad JPA a entidad de dominio.
     */
    private Venta convertirADominio(VentaEntity entity) {
        Venta venta = new Venta();
        
        venta.setId(entity.getId());
        venta.setClienteId(entity.getClienteId());
        venta.setFechaRegistro(new FechaRegistro(entity.getFechaRegistro()));
        venta.setMetodoPago(entity.getMetodoPago());
        venta.setEstado(entity.getEstado());
        venta.setTotal(entity.getTotal());

        // Convertir detalles
        List<DetalleVenta> detallesDominio = entity.getDetalles().stream()
                .map(this::convertirDetalleADominio)
                .collect(Collectors.toList());
        venta.setDetalles(detallesDominio);

        return venta;
    }

    /**
     * Convierte detalle de dominio a entidad JPA.
     */
    private DetalleVentaEntity convertirDetalleAEntity(DetalleVenta detalle, VentaEntity ventaEntity) {
    DetalleVentaEntity entity = new DetalleVentaEntity();
        
        entity.setId(detalle.getId());
        entity.setVenta(ventaEntity);
        entity.setProductoId(detalle.getProductoId());
        entity.setCantidad(detalle.getCantidad());
        entity.setPrecioUnitario(detalle.getPrecioUnitario());
        entity.setSubtotal(detalle.getSubtotal());

        return entity;
    }

    /**
     * Convierte entidad JPA a detalle de dominio.
     */
    private DetalleVenta convertirDetalleADominio(DetalleVentaEntity entity) {
    DetalleVenta detalle = new DetalleVenta();
        
        detalle.setId(entity.getId());
        detalle.setProductoId(entity.getProductoId());
        detalle.setCantidad(entity.getCantidad());
        detalle.setPrecioUnitario(entity.getPrecioUnitario());
        detalle.setSubtotal(entity.getSubtotal());

        return detalle;
    }
}
