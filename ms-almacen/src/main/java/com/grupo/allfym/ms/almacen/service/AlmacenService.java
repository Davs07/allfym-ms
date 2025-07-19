package com.grupo.allfym.ms.almacen.service;

import com.grupo.allfym.ms.almacen.models.Producto;
import com.grupo.allfym.ms.almacen.models.entity.AlmacenProducto;
import com.grupo.allfym.ms.almacen.models.entity.Movimiento;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AlmacenService {

    AlmacenProducto agregarProductoAlmacen(Long idProducto, Integer stockInicial);
    AlmacenProducto aumentarStock(Long idProducto, Integer cantidad);
    AlmacenProducto reducirStock(Long idProducto, Integer cantidad);
    Optional<AlmacenProducto> obtenerProductoEnAlmacen(Long idProducto);
    List<Movimiento> obtenerHistorialMovimientos(Long idProducto);
    Producto obtenerDetalleProducto(Long idProducto);
    List<Map<String, Object>> listarProductosConDetalles();
    Integer consultarStock(Long idProducto);
    List<AlmacenProducto> listarProductosEnAlmacen();
}