package com.grupo.allfym.ms.pagos.infrastructure.clients;

import com.grupo.allfym.ms.pagos.infrastructure.dtos.VentaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-ventas", url = "http://localhost:8091/api/ventas")
public interface VentaClient {
    @GetMapping("/{id}")
    VentaDto detalleVenta(@PathVariable Long id);
    @GetMapping()
    List<VentaDto> listaVenta();
}
