package com.grupo.allfym.ms.pagos.controller;

import com.grupo.allfym.ms.pagos.models.Venta;
import com.grupo.allfym.ms.pagos.models.entity.Pago;
import com.grupo.allfym.ms.pagos.ov.FechaEmision;
import com.grupo.allfym.ms.pagos.services.PagoServiceImp;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Pago")
public class PagoController {

    @Autowired
    private PagoServiceImp service;

    @GetMapping
    public List<Pago> lista(){
        List<Pago> lista_pagos = service.lista();
        List<Venta> lista_venta = service.listaVenta();

        for (Pago pago : lista_pagos) {
            if (pago.getPagoVenta() != null) {
                for (Venta venta : lista_venta) {
                    if (pago.getPagoVenta().getIdVenta().equals(venta.getId())) {
                        pago.setVenta(venta);
                        pago.setMonto(venta.getTotal().doubleValue());
                        break;
                    }
                }
            }
        }
        return lista_pagos;
    }

    @GetMapping("/filtrar_metodo_pago/{metodo}")
    public List<Pago> listaMetodoPago(@PathVariable String metodo){
        List<Pago> lista_pagos = service.lista();
        List<Pago> lista_filtrada = new ArrayList<>();
        for (Pago p : lista_pagos) {
            if (p.getComprobantePago().getMetodoPago().toString().equals(metodo)) lista_filtrada.add(p);
        }

        List<Venta> lista_venta = service.listaVenta();
        for (Pago pago : lista_filtrada) {
            if (pago.getPagoVenta() != null) {
                for (Venta venta : lista_venta) {
                    if (pago.getPagoVenta().getIdVenta().equals(venta.getId())) {
                        pago.setVenta(venta);
                        pago.setMonto(venta.getTotal().doubleValue());
                        break;
                    }
                }
            }
        }
        return lista_filtrada;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Pago> op = service.porId(id);
        if (op.isPresent()) {
            Pago pago = op.get();
            List<Venta> lista_venta = service.listaVenta();
            if (pago.getPagoVenta() != null) {
                for (Venta venta : lista_venta) {
                    if (pago.getPagoVenta().getIdVenta().equals(venta.getId())) {
                        pago.setVenta(venta);
                        pago.setMonto(venta.getTotal().doubleValue());
                        break;
                    }
                }
            }
            return ResponseEntity.ok(pago);
        } return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Pago pago) {
        if (pago.getComprobantePago().getFechaEmision() == null) {
            pago.getComprobantePago().setFechaEmision(new FechaEmision());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(pago));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Pago pago) {
        Optional<Pago> op = service.porId(id);
        if (op.isPresent()) {
            Pago pagoBD = op.get();
            pagoBD.setComprobantePago(pago.getComprobantePago());
            pagoBD.setEstadoPago(pago.getEstadoPago());
            pagoBD.setMonto(pago.getMonto());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(pagoBD));
        } return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Pago> op = service.porId(id);
        if (op.isPresent()) {
           service.eliminar(id);
            return ResponseEntity.noContent().build();
        } return ResponseEntity.notFound().build();
    }

    //Accesos Remotos
    @PutMapping("/asignar_venta/{pagoid}")
    public ResponseEntity<?> asignarPago (@RequestBody Venta venta, @PathVariable Long pagoid) {
        Optional<Venta> op;
        try {
            op = service.asignarVenta(venta,pagoid);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("Mensaje","No existe la venta por el id " + venta.getId() +
                            " o hubo error en la comunicación"+e.getMessage()));
        }

        if (op.isPresent())
            return ResponseEntity.status(HttpStatus.CREATED).body(op.get());
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/remover_venta/{pagoid}")
    public ResponseEntity<?> removerPago (@RequestBody Venta venta, @PathVariable Long pagoid) {
        Optional<Venta> op;
        try {
            op = service.removerVenta(venta,pagoid);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("Mensaje","No existe la venta por el id " + venta.getId() +
                            " o hubo error en la comunicación"+e.getMessage()));
        }

        if (op.isPresent())
            return ResponseEntity.status(HttpStatus.CREATED).body(op.get());
        return ResponseEntity.notFound().build();
    }


}
