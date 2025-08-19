package com.grupo.allfym.ms.pagos.infrastructure.controllers;

import com.grupo.allfym.ms.pagos.application.services.PagoService;
import com.grupo.allfym.ms.pagos.domain.models.Venta;
import com.grupo.allfym.ms.pagos.domain.models.classes.Pago;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/Pago")
@AllArgsConstructor
public class PagoController {

    @Autowired
    private final PagoService service;

    @PostMapping
    public ResponseEntity<?> crearPago(@RequestBody Pago pago) {
        Pago pagoCreado = service.guardar(pago);
        return ResponseEntity.status(HttpStatus.CREATED).body(pagoCreado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detallePago(@PathVariable Long id) {
        Optional<Pago> op = service.obtenerPago(id);
        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Pago> listaPago() {
        return service.listaPago();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPago(@PathVariable Long id, @RequestBody Pago pago) {
        Optional<Pago> op = service.obtenerPago(id);
        if (op.isPresent()) {
            pago.setIdPago(id); //para que el id no sea nulo
            Optional<Pago> update = service.actualizarPago(id,pago);
            return ResponseEntity.ok(update.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPago(@PathVariable Long id) {
        Optional<Pago> op = service.obtenerPago(id);
        if (op.isPresent()) {
            service.eliminarPago(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/porMetodo/{metodo}")
    public List<Pago> listaMetodoPago(@PathVariable String metodo) {
        return service.buscarMetodoPago(metodo);
    }


    //Accesos Remotos
    @PutMapping("/asignarVenta/{pagoid}")
    public ResponseEntity<?> asignarVenta (@RequestBody Venta venta, @PathVariable Long pagoid) {
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

    @PutMapping("/removerVenta/{pagoid}")
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
