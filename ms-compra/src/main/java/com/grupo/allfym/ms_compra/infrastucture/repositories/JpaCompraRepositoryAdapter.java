package com.grupo.allfym.ms_compra.infrastucture.repositories;

import com.grupo.allfym.ms_compra.domain.models.entities.Compra;
import com.grupo.allfym.ms_compra.domain.models.enums.Estado;
import com.grupo.allfym.ms_compra.domain.ports.out.CompraRepositoryPort;
import com.grupo.allfym.ms_compra.infrastucture.entities.CompraEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JpaCompraRepositoryAdapter implements CompraRepositoryPort {
    private final JpaCompraRepository jpaCompraRepository;

    public JpaCompraRepositoryAdapter(JpaCompraRepository jpaCompraRepository) {
        this.jpaCompraRepository = jpaCompraRepository;
    }

    @Override
    public List<Compra> listar() {
        return jpaCompraRepository.findAll().stream()
                .map(CompraEntity::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Compra> buscarPorId(Long id) {
        return jpaCompraRepository.findById(id)
                .map(CompraEntity::toDomainModel);
    }

    @Override
    public Compra guardar(Compra compra) {
        CompraEntity entity = CompraEntity.fromDomainModel(compra);
        CompraEntity savedEntity = jpaCompraRepository.save(entity);
        return CompraEntity.toDomainModel(savedEntity);
    }

    @Override
    public void eliminar(Long id) {
        jpaCompraRepository.deleteById(id);
    }

    @Override
    public List<Compra> buscarPorEstado(Estado estado) {
        return jpaCompraRepository.findByEstado(estado).stream()
                .map(CompraEntity::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Compra> buscarPorIdProveedor(Long idProveedor) {
        return jpaCompraRepository.findByIdProveedor(idProveedor).stream()
                .map(CompraEntity::toDomainModel)
                .collect(Collectors.toList());
    }
}
