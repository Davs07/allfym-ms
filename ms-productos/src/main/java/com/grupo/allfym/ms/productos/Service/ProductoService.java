package com.grupo.allfym.ms.productos.Service;

import com.grupo.allfym.ms.productos.models.entity.Categoria;
import com.grupo.allfym.ms.productos.models.entity.Marca;
import com.grupo.allfym.ms.productos.models.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    Producto guardarProducto(Producto producto);
    List<Producto> listarProductos();
    Optional<Producto> obtenerProductoPorId(Long id);
    Optional<Producto> obtenerProductoPorNombre(String nombre);
    List<Producto> obtenerProductosPorCategoria(Categoria categoria);
    List<Producto> obtenerProductosPorMarca(Marca marca);
    void eliminarProducto(Long id) throws Exception;
}
