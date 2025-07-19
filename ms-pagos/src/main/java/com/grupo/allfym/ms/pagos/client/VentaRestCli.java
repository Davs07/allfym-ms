package com.grupo.allfym.ms.pagos.client;

import com.grupo.allfym.ms.pagos.models.Venta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "ms-ventas", url = "http://localhost:8090/api/ventas")
public interface VentaRestCli {
    @GetMapping("/{id}")
    Venta detalleVenta(@PathVariable Long id);
    @GetMapping()
    List<Venta> listaVenta();
}
