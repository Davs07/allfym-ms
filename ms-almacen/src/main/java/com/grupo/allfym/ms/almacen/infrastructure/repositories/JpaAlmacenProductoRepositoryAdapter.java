package com.grupo.allfym.ms.almacen.infrastructure.repositories;

import com.grupo.allfym.ms.almacen.domain.models.AlmacenProducto;
import com.grupo.allfym.ms.almacen.domain.ports.out.AlmacenProductoRepositoryPort;
import com.grupo.allfym.ms.almacen.infrastructure.entities.AlmacenProductoEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Component
public class JpaAlmacenProductoRepositoryAdapter implements AlmacenProductoRepositoryPort {

    private final JpaAlmacenProductoRepository jpaAlmacenProductoRepository;

    public JpaAlmacenProductoRepositoryAdapter(JpaAlmacenProductoRepository jpaAlmacenProductoRepository) {
        this.jpaAlmacenProductoRepository = jpaAlmacenProductoRepository;
    }

    @Override
    public AlmacenProducto save(AlmacenProducto almacenProducto) {
        AlmacenProductoEntity almacenProductoEntity = AlmacenProductoEntity.fromDomainModel(almacenProducto);
        AlmacenProductoEntity savedEntity = jpaAlmacenProductoRepository.save(almacenProductoEntity);
        return savedEntity.toDomainModel();
    }

   @Override
   public Optional<AlmacenProducto> findByIdProducto(Long idProducto) {
       return jpaAlmacenProductoRepository.findByIdAlmacen(idProducto) // ← CORREGIDO
               .map(AlmacenProductoEntity::toDomainModel);
   }

    @Override
    public boolean deleteByIdAlmacen(Long idAlmacen) {
        Optional<AlmacenProductoEntity> entity = jpaAlmacenProductoRepository.findByIdAlmacen(idAlmacen);
        if (entity.isPresent()) {
            // Usar el idAlmacen, no el idProducto para eliminar
            jpaAlmacenProductoRepository.deleteById(entity.get().getIdAlmacen());
            return true;
        }
        return false;
    }

    @Override
    public List<AlmacenProducto> findAll() {
        return jpaAlmacenProductoRepository.findAll().stream().map(AlmacenProductoEntity::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AlmacenProducto> findByIdAlmacen(Long idAlmacen) {
        return jpaAlmacenProductoRepository.findByIdAlmacen(idAlmacen).map(AlmacenProductoEntity::toDomainModel);
    }

    @Override
    public Optional<AlmacenProducto> update(AlmacenProducto almacenProducto) {
        if (jpaAlmacenProductoRepository.existsById(almacenProducto.getIdAlmacen())) {
            AlmacenProductoEntity almacenProductoEntity = AlmacenProductoEntity.fromDomainModel(almacenProducto);
            AlmacenProductoEntity updatedEntity = jpaAlmacenProductoRepository.save(almacenProductoEntity);
            return Optional.of(updatedEntity.toDomainModel());
        }
        return Optional.empty();
    }

}
