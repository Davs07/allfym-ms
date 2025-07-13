package com.grupo.allfym.ms.reclamos.Controllers;

import com.grupo.allfym.ms.reclamos.Service.ReclamoClienteServiceImp;
import com.grupo.allfym.ms.reclamos.enums.EstadoReclamo;
import com.grupo.allfym.ms.reclamos.models.Pago;
import com.grupo.allfym.ms.reclamos.models.entity.ReclamoCliente;
import com.grupo.allfym.ms.reclamos.vo.fechaReclamo;
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
@RequestMapping("/api/ReclamoCliente")
public class ReclamoClienteController {

    @Autowired
    private ReclamoClienteServiceImp service;

    @GetMapping
    public List<ReclamoCliente> listar(){
        List<ReclamoCliente> lista_reclamos = service.listar();
        List<Pago> lista_pagos = service.lista_pagos();

        for (ReclamoCliente reclamo : lista_reclamos) {
            if (reclamo.getReclamoPago() != null) {
                for (Pago pago : lista_pagos) {
                    if (reclamo.getReclamoPago().getIdPago().equals(pago.getIdPago())) {
                        reclamo.setPago(pago);
                        break;
                    }
                }
            }
        }
        return lista_reclamos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<ReclamoCliente> op = service.porId(id);
        if (op.isPresent()) {
            ReclamoCliente recBD = op.get();
            List<Pago> pagos = service.lista_pagos();

            if (recBD.getReclamoPago() != null) {
                for (Pago p : pagos) {
                    if (recBD.getReclamoPago().getIdPago().equals(p.getIdPago())) {
                        recBD.setPago(p);
                        break;
                    }
                }
            }
            return ResponseEntity.ok(recBD);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody ReclamoCliente reclamo) {
        if (reclamo.getFechaReclamo()==null)
            reclamo.setFechaReclamo(new fechaReclamo());
       return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(reclamo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@RequestBody ReclamoCliente reclamo, @PathVariable Long id) {
        Optional<ReclamoCliente> op = service.porId(id);
        if (op.isPresent()) {
          ReclamoCliente recBD = op.get();
            recBD.setDescripcion(reclamo.getDescripcion());
            recBD.setEstadoReclamo(reclamo.getEstadoReclamo());
            recBD.setFechaReclamo(reclamo.getFechaReclamo());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(recBD));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<ReclamoCliente> op = service.porId(id);
        if (op.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //Listar por estado
    @GetMapping("/listarPorEstado/{estado}")
    public List<ReclamoCliente> listar(@PathVariable String estado){
        List<ReclamoCliente> lista = new ArrayList<>();
        for (ReclamoCliente rec : service.listar()) {
            if (rec.getEstadoReclamo().toString().equals(estado)) lista.add(rec);
        }
        return lista;
    }

    //Accesos remotos
    @PutMapping("/asignar_pago/{reclamoid}")
    public ResponseEntity<?> asignarPago (@RequestBody Pago pago, @PathVariable Long reclamoid) {
        Optional<Pago> op;
        try {
            op = service.asignarPago(pago,reclamoid);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("Mensaje","No existe el pago por el id " + pago.getIdPago() +
                            " o hubo error en la comunicación"+e.getMessage()));
        }

       if (op.isPresent())
           return ResponseEntity.status(HttpStatus.CREATED).body(op.get());
       return ResponseEntity.notFound().build();
    }

    @PutMapping("/remover_pago/{reclamoid}")
    public ResponseEntity<?> removerPago (@RequestBody Pago pago, @PathVariable Long reclamoid) {
        Optional<Pago> op;
        try {
            op = service.removerPago(pago,reclamoid);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("Mensaje","No existe el pago por el id " + pago.getIdPago() +
                            " o hubo error en la comunicación"+e.getMessage()));
        }

        if (op.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(op.get());
        return ResponseEntity.notFound().build();
    }

}
