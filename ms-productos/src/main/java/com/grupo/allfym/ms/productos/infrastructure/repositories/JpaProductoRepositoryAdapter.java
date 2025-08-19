package com.grupo.allfym.ms.productos.infrastructure.repositories;

import com.grupo.allfym.ms.productos.domain.models.Producto;
import com.grupo.allfym.ms.productos.domain.ports.out.ProductoRepositoryPort;
import com.grupo.allfym.ms.productos.infrastructure.entities.Categoria;
import com.grupo.allfym.ms.productos.infrastructure.entities.Marca;
import com.grupo.allfym.ms.productos.infrastructure.entities.ProductoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaProductoRepositoryAdapter implements ProductoRepositoryPort {

   @Autowired
   JpaProductoRepository jpaProductoRepository;

    @Override
    public Producto save(Producto producto) {
        ProductoEntity productoEntity =  ProductoEntity.fromDomainModel(producto);
        ProductoEntity savedProductoEntity = jpaProductoRepository.save(productoEntity);
        return savedProductoEntity.toDomainModel();
    }

    @Override
    public List<Producto> findAll() {
        return jpaProductoRepository.findAll().stream().map(ProductoEntity::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return jpaProductoRepository.findById(id).map(ProductoEntity::toDomainModel);
    }

    @Override
    public List<Producto> findByCategoria(Categoria categoria) {
        return jpaProductoRepository.findByCategoria(categoria)
                .stream()
                .map(ProductoEntity::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Producto> findByMarca(Marca marca) {
        return jpaProductoRepository.findByMarca(marca)
                .stream()
                .map(ProductoEntity::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Producto> update(Producto producto) {
        if (jpaProductoRepository.existsById(producto.getId())){
            ProductoEntity productoEntity =  ProductoEntity.fromDomainModel(producto);
            ProductoEntity updatedProductoEntity = jpaProductoRepository.save(productoEntity);
            return Optional.of(updatedProductoEntity.toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        if (jpaProductoRepository.existsById(id)){
            jpaProductoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
