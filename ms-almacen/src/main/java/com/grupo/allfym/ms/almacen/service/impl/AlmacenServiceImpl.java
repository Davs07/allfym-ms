package com.grupo.allfym.ms.almacen.service.impl;

import com.grupo.allfym.ms.almacen.client.ProductoClientRest;
import com.grupo.allfym.ms.almacen.models.Producto;
import com.grupo.allfym.ms.almacen.models.entity.*;
import com.grupo.allfym.ms.almacen.repository.AlmacenProductoRepository;
import com.grupo.allfym.ms.almacen.repository.MovimientoRepository;
import com.grupo.allfym.ms.almacen.service.AlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class AlmacenServiceImpl implements AlmacenService {

    @Autowired
    private AlmacenProductoRepository almacenProductoRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private ProductoClientRest productoClientRest;

    @Override
    public AlmacenProducto agregarProductoAlmacen(Long idProducto, Integer stockInicial) {
        // Validar que el producto existe en el microservicio de productos
        Producto producto = validarProductoExiste(idProducto);

        Optional<AlmacenProducto> existente = almacenProductoRepository.findByIdProducto(idProducto);

        if (existente.isPresent()) {
            throw new RuntimeException("El producto ya existe en el almacén");
        }

        AlmacenProducto almacenProducto = new AlmacenProducto();
        almacenProducto.setIdProducto(idProducto);
        almacenProducto.setStock(stockInicial);

        AlmacenProducto saved = almacenProductoRepository.save(almacenProducto);

        // Crear movimiento inicial
        crearMovimiento(saved, TipoMovimiento.ENTRADA, stockInicial);

        return saved;
    }

    @Override
    public AlmacenProducto aumentarStock(Long idProducto, Integer cantidad) {
        // Validar que el producto existe
        validarProductoExiste(idProducto);

        AlmacenProducto almacenProducto = almacenProductoRepository.findByIdProducto(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en almacén"));

        almacenProducto.setStock(almacenProducto.getStock() + cantidad);
        AlmacenProducto saved = almacenProductoRepository.save(almacenProducto);

        crearMovimiento(saved, TipoMovimiento.ENTRADA, cantidad);

        return saved;
    }

    @Override
    public AlmacenProducto reducirStock(Long idProducto, Integer cantidad) {
        // Validar que el producto existe
        validarProductoExiste(idProducto);

        AlmacenProducto almacenProducto = almacenProductoRepository.findByIdProducto(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en almacén"));

        if (almacenProducto.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }

        almacenProducto.setStock(almacenProducto.getStock() - cantidad);
        AlmacenProducto saved = almacenProductoRepository.save(almacenProducto);

        crearMovimiento(saved, TipoMovimiento.SALIDA, cantidad);

        return saved;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlmacenProducto> obtenerProductoEnAlmacen(Long idProducto) {
        return almacenProductoRepository.findByIdProducto(idProducto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movimiento> obtenerHistorialMovimientos(Long idProducto) {
        return movimientoRepository.findByAlmacenProductoIdProducto(idProducto);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto obtenerDetalleProducto(Long idProducto) {
        return validarProductoExiste(idProducto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlmacenProducto> listarProductosEnAlmacen() {
        Iterable<AlmacenProducto> productosIterable = almacenProductoRepository.findAll();
        List<AlmacenProducto> productosEnAlmacen = new ArrayList<>();
        productosIterable.forEach(productosEnAlmacen::add);
        return productosEnAlmacen;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> listarProductosConDetalles() {
        Iterable<AlmacenProducto> productosIterable = almacenProductoRepository.findAll();
        List<AlmacenProducto> productosEnAlmacen = new ArrayList<>();
        productosIterable.forEach(productosEnAlmacen::add);

        List<Map<String, Object>> respuesta = new ArrayList<>();

        for (AlmacenProducto almacenProducto : productosEnAlmacen) {
            try {
                Producto producto = productoClientRest.obtenerProductoPorId(almacenProducto.getIdProducto());

                Map<String, Object> item = new HashMap<>();
                item.put("idAlmacen", almacenProducto.getIdAlmacen());
                item.put("stock", almacenProducto.getStock());
                item.put("idProducto", almacenProducto.getIdProducto());
                item.put("producto", producto);

                respuesta.add(item);
            } catch (Exception e) {
                // Si no se puede obtener el detalle del producto, solo mostrar info básica
                Map<String, Object> item = new HashMap<>();
                item.put("idAlmacen", almacenProducto.getIdAlmacen());
                item.put("stock", almacenProducto.getStock());
                item.put("idProducto", almacenProducto.getIdProducto());
                item.put("producto", null);
                item.put("error", "No se pudo obtener detalle del producto");

                respuesta.add(item);
            }
        }
        return respuesta;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer consultarStock(Long idProducto) {
        Optional<AlmacenProducto> almacenProducto = almacenProductoRepository.findByIdProducto(idProducto);
        if (almacenProducto.isEmpty()) {
            throw new RuntimeException("Producto no encontrado en almacén");
        }
        return almacenProducto.get().getStock();
    }

    private Producto validarProductoExiste(Long idProducto) {
        try {
            return productoClientRest.obtenerProductoPorId(idProducto);
        } catch (Exception e) {
            throw new RuntimeException("Producto no encontrado en el catálogo: " + idProducto);
        }
    }

    private void crearMovimiento(AlmacenProducto almacenProducto, TipoMovimiento tipo, Integer cantidad) {
        LocalDate fecha = LocalDate.now();
        FechaMovimiento fechaMovimiento = new FechaMovimiento(fecha.getDayOfMonth(), fecha.getMonthValue(), fecha.getYear());

        Movimiento movimiento = new Movimiento();
        movimiento.setFechaMovimiento(fechaMovimiento);
        movimiento.setTipoMovimiento(tipo);
        movimiento.setCantidadDeMovimientos(cantidad);
        movimiento.setAlmacenProducto(almacenProducto);

        movimientoRepository.save(movimiento);
    }
}