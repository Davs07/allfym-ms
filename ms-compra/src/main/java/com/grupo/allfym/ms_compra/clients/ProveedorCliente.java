package com.grupo.allfym.ms_compra.clients;

import com.grupo.allfym.ms_compra.models.Proveedor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-proveedores", url = "http://localhost:8010/api/proveedor")
public interface ProveedorCliente {
    @GetMapping
    List<Proveedor> listar();

    @GetMapping("/{id}")
    Proveedor buscarporId(@PathVariable Long id);
}
