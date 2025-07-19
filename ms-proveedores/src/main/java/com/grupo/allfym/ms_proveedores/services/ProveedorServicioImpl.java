package com.grupo.allfym.ms_proveedores.services;

import com.grupo.allfym.ms_proveedores.models.entities.Proveedor;
import com.grupo.allfym.ms_proveedores.models.enums.Estado;
import com.grupo.allfym.ms_proveedores.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServicioImpl implements ProveedorServicio{

    @Autowired
    private ProveedorRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Proveedor> listar() {
        return (List<Proveedor>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Proveedor> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional()
    public Proveedor guardar(Proveedor proveedor) {
        if (proveedor == null || proveedor.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del proveedor es obligatorio");
        }
        return repository.save(proveedor);
    }

    @Override
    @Transactional()
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Proveedor no encontrado con ID: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Proveedor> listaporEstado(Estado estado) {
        return repository.findByEstado(estado);
    }
}
