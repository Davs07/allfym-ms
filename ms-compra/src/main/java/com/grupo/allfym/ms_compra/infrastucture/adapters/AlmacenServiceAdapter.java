package com.grupo.allfym.ms_compra.infrastucture.adapters;

import com.grupo.allfym.ms.compra.domain.models.Producto;
import com.grupo.allfym.ms.compra.domain.ports.out.AlmacenServicePort;
import com.grupo.allfym.ms.compra.infrastucture.clients.AlmacenClient;

import java.util.List;
import java.util.stream.Collectors;

public class AlmacenServiceAdapter implements AlmacenServicePort {
    private final AlmacenClient almacenClient;

    public AlmacenServiceAdapter(AlmacenClient almacenClient) {
        this.almacenClient = almacenClient;
    }
    @Override
    public List<Producto> obtenerProductos() {
        return almacenClient.obtenerProductos().stream()
                .map(dto -> new Producto(dto.getId(), dto.getNombre()))
                .collect(Collectors.toList());
    }

    @Override
    public void aumentarStock(Long productoId, Integer cantidad) {
        almacenClient.aumentarStock(productoId, cantidad);
    }
}
