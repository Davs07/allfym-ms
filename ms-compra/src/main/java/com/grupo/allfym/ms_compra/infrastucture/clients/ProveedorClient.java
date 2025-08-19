package com.grupo.allfym.ms_compra.infrastucture.clients;

import com.grupo.allfym.ms_compra.infrastucture.dtos.ProveedorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-proveedores", url = "http://localhost:8060/api/proveedor")
public interface  ProveedorClient {
    @GetMapping
    List<ProveedorDto> listar();

    @GetMapping("/{id}")
    ProveedorDto buscarPorId(@PathVariable Long id);
}
