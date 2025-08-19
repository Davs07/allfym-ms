package com.grupo.allfym.ms.almacen.infrastructure.repositories;

import com.grupo.allfym.ms.almacen.domain.models.Movimiento;
import com.grupo.allfym.ms.almacen.domain.ports.out.MovimientoRepositoryPort;
import com.grupo.allfym.ms.almacen.infrastructure.entities.MovimientoEntity;
import com.grupo.allfym.ms.almacen.infrastructure.entities.TipoMovimiento;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Component
public class JpaMovimientoRepositoryAdapter implements MovimientoRepositoryPort {

    private final JpaMovimientoRepository jpaMovimientoRepository;

    public JpaMovimientoRepositoryAdapter(JpaMovimientoRepository jpaMovimientoRepository) {
        this.jpaMovimientoRepository = jpaMovimientoRepository;
    }

    @Override
    public Movimiento save(Movimiento movimiento) {
        MovimientoEntity movimientoEntity = MovimientoEntity.fromDomainModel(movimiento);
        MovimientoEntity savedEntity = jpaMovimientoRepository.save(movimientoEntity);
        return savedEntity.toDomainModel();
    }

    @Override
    public List<Movimiento> findAll() {
        return jpaMovimientoRepository.findAll().stream()
                .map(MovimientoEntity::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Movimiento> findById(Long id) {
        return jpaMovimientoRepository.findById(id)
                .map(MovimientoEntity::toDomainModel);
    }

    @Override
    public List<Movimiento> findByTipoMovimiento(TipoMovimiento tipoMovimiento) {
        return jpaMovimientoRepository.findByTipoMovimiento(tipoMovimiento).stream()
                .map(MovimientoEntity::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Movimiento> update(Movimiento movimiento) {
        if (jpaMovimientoRepository.existsById(movimiento.getIdMovimiento())) {
            MovimientoEntity movimientoEntity = MovimientoEntity.fromDomainModel(movimiento);
            MovimientoEntity updatedEntity = jpaMovimientoRepository.save(movimientoEntity);
            return Optional.of(updatedEntity.toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        if (jpaMovimientoRepository.existsById(id)) {
            jpaMovimientoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}