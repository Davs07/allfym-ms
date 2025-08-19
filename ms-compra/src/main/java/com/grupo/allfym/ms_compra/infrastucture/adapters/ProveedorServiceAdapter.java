package com.grupo.allfym.ms_compra.infrastucture.adapters;

import com.grupo.allfym.ms_compra.domain.models.Proveedor;
import com.grupo.allfym.ms_compra.domain.ports.out.ProveedorServicePort;
import com.grupo.allfym.ms_compra.infrastucture.clients.ProveedorClient;
import com.grupo.allfym.ms_compra.infrastucture.dtos.ProveedorDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProveedorServiceAdapter implements ProveedorServicePort {
    private final ProveedorClient proveedorClient;

    public ProveedorServiceAdapter(ProveedorClient proveedorClient) {
        this.proveedorClient = proveedorClient;
    }
    @Override
    public Optional<Proveedor> buscarPorId(Long id) {
        try {
            ProveedorDto dto = proveedorClient.buscarPorId(id);
            return Optional.of(new Proveedor(dto.getId(), dto.getNombre()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Proveedor> listar() {
        return proveedorClient.listar().stream()
                .map(dto -> new Proveedor(dto.getId(), dto.getNombre()))
                .collect(Collectors.toList());
    }
}
