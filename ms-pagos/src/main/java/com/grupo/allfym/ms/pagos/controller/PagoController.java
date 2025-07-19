package com.grupo.allfym.ms.pagos.controller;

import com.grupo.allfym.ms.pagos.entity.Pago;
import com.grupo.allfym.ms.pagos.ov.FechaEmision;
import com.grupo.allfym.ms.pagos.services.PagoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Pago")
public class PagoController {

    @Autowired
    private PagoServiceImp service;

    @GetMapping
    public List<Pago> lista(){
        return service.lista();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Pago> op = service.porId(id);
        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
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


}
