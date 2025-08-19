package com.grupo.allfym.ms.reclamos.infrastructure.repositories;

import com.grupo.allfym.ms.reclamos.domain.models.classes.Reclamo;
import com.grupo.allfym.ms.reclamos.domain.models.enums.EstadoReclamo;
import com.grupo.allfym.ms.reclamos.domain.ports.out.ReclamoRepositoryPort;
import com.grupo.allfym.ms.reclamos.infrastructure.entities.ReclamoEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class JpaReclamoRepositoryAdapter implements ReclamoRepositoryPort {

    @Autowired
    private final JpaReclamoRepository repository;


    @Override
    public List<Reclamo> listar() {
        return repository.findAll().stream().
                map(ReclamoEntity::toDomainModel).collect(Collectors.toList());
    }

    @Override
    public Optional<Reclamo> porId(Long id) {
        return repository.findById(id).map(ReclamoEntity::toDomainModel);
    }

    @Override
    public Optional<Reclamo> actualizar(Long id, Reclamo reclamo) {
        if (repository.existsById(reclamo.getIdReclamo())){
            ReclamoEntity reclamoEntity = ReclamoEntity.fromDomainModel(reclamo);
            ReclamoEntity saveReclamo = repository.save(reclamoEntity);
            return Optional.of(saveReclamo.toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public Reclamo guardar(Reclamo reclamo) {
        ReclamoEntity reclamoEntity = repository.save(ReclamoEntity.fromDomainModel(reclamo));
        return reclamoEntity.toDomainModel();
    }

    @Override
    public void eliminar(Long id) {
        if (repository.existsById(id)) repository.deleteById(id);
    }

    @Override
    public Optional<Reclamo> cambiarEstado(String estado, Long id) {
        Optional<ReclamoEntity> op = repository.findById(id);
        if (op.isPresent()) {
            ReclamoEntity reclamoEntity = op.get();
                        reclamoEntity.setEstadoReclamo(EstadoReclamo.valueOf(estado));
            ReclamoEntity saveReclamo = repository.save(reclamoEntity);
            return Optional.of(saveReclamo.toDomainModel());
        }
        return Optional.empty();
    }
}
