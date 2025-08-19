package com.grupo.allfym.ms.reclamos.infrastructure.clients;

import com.grupo.allfym.ms.reclamos.infrastructure.dtos.ClienteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-clientes", url = "http://localhost:8020/api/clientes")
public interface ClienteClient {
    @GetMapping
    List<ClienteDto> lista_cliente();
    @GetMapping("/{id}")
    ClienteDto detalle(@PathVariable Long id);
}
