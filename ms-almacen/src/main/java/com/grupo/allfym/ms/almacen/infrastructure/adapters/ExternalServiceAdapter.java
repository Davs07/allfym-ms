package com.grupo.allfym.ms.almacen.infrastructure.adapters;

import com.grupo.allfym.ms.almacen.domain.ports.out.ExternalServicePort;
import com.grupo.allfym.ms.almacen.infrastructure.entities.pojo.Producto;
import org.springframework.web.client.RestTemplate;

public class ExternalServiceAdapter implements ExternalServicePort {

    private final RestTemplate restTemplate;

    public ExternalServiceAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Producto getProductoById(Long idProducto) {
        return restTemplate.getForObject("http://localhost:8050/api/productos/buscar/" + idProducto, Producto.class);
    }
}
