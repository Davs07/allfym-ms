package com.grupo.allfym.ms.reclamos.client;

import com.grupo.allfym.ms.reclamos.models.Pago;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "ms-pagos", url = "http://localhost:8040/api/Pago")
public interface PagoClientRest {
    @GetMapping
    List<Pago> lista_pagos();
    @GetMapping("/{id}")
    Pago detalle(@PathVariable Long id);
    @PostMapping
    Pago crear(@RequestBody Pago pago);
}
