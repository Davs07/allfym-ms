package com.grupo.allfym.ms_proveedores.infrastructure.repositories;

import com.grupo.allfym.ms_proveedores.domain.models.entities.Proveedor;
import com.grupo.allfym.ms_proveedores.domain.models.enums.Estado;
import com.grupo.allfym.ms_proveedores.domain.ports.out.ProveedorRepositoryPort;
import com.grupo.allfym.ms_proveedores.infrastructure.entities.ProveedorEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JpaProveedorRepositoryAdapter implements ProveedorRepositoryPort {


    private final JpaProveedorRepository crudProveedorRepository;

    public JpaProveedorRepositoryAdapter(JpaProveedorRepository crudProveedorRepository) {
        this.crudProveedorRepository = crudProveedorRepository;
    }

    @Override
    public List<Proveedor> listar() {
        return crudProveedorRepository.findAll().stream()//SOLO SE PUEDE con el Jpa
                .map(ProveedorEntity::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Proveedor> buscarPorId(Long id) {
        return crudProveedorRepository.findById(id)
                .map(ProveedorEntity::toDomainModel);
    }

    @Override
    public Proveedor guardar(Proveedor proveedor) {
        ProveedorEntity proveedorEntity = ProveedorEntity.fromDomainModel(proveedor);
        ProveedorEntity proveedorEntitySaved = crudProveedorRepository.save(proveedorEntity);
        return ProveedorEntity.toDomainModel(proveedorEntitySaved);
    }

    @Override
    public void eliminar(Long id) {
        //if(crudProveedorRepository.existsById(id)){
            crudProveedorRepository.deleteById(id);
       // }
    }

    @Override
    public List<Proveedor> listaporEstado(Estado estado) {
        return crudProveedorRepository.findByEstado(estado).stream()
                .map(ProveedorEntity::toDomainModel)
                .collect(Collectors.toList());
    }
}
