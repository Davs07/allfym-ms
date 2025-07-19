package com.grupo.allfym.ms.reclamos.client;

import com.grupo.allfym.ms.reclamos.models.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "ms-clientes", url = "http://localhost:8020/api/clientes")
public interface ClienteClientRest {
    @GetMapping
    List<Cliente> lista_cliente();
    @GetMapping("/{id}")
    Cliente detalle(@PathVariable Long id);
    @PostMapping
    Cliente crear(@RequestBody Cliente cliente);
}
