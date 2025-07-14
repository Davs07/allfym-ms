package com.grupo.allfym.ms.productos.Service.impl;

import com.grupo.allfym.ms.productos.Service.ProductoService;
import com.grupo.allfym.ms.productos.models.entity.Categoria;
import com.grupo.allfym.ms.productos.models.entity.Marca;
import com.grupo.allfym.ms.productos.models.entity.Producto;
import com.grupo.allfym.ms.productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> listarProductos() {
        return (List<Producto>) productoRepository.findAll();
    }

    @Override
    public Optional<Producto> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Optional<Producto> obtenerProductoPorNombre(String nombre) {
        return productoRepository.findByNombre(nombre);
    }

    @Override
    public List<Producto> obtenerProductosPorCategoria(Categoria categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    @Override
    public List<Producto> obtenerProductosPorMarca(Marca marca) {
        return productoRepository.findByMarca(marca);
    }

    @Override
    public void eliminarProducto(Long id) throws  Exception{
        productoRepository.findById(id).
                orElseThrow(()-> new Exception("Producto con ID " + id + " no existe."));

        productoRepository.deleteById(id);
    }
}
