package com.grupo.allfym.ms.reclamos.Controllers;

import com.grupo.allfym.ms.reclamos.Service.ReclamoClienteServiceImp;
import com.grupo.allfym.ms.reclamos.models.Cliente;
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
        List<Cliente> lista_clientes = service.lista_clientes();

        for (ReclamoCliente reclamo : lista_reclamos) {
            if (reclamo.getReclamoPago() != null) {
                for (Cliente cliente : lista_clientes) {
                    if (reclamo.getReclamoPago().getIdCliente().equals(cliente.getId())) {
                        reclamo.setCliente(cliente);
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
            List<Cliente> lista_clientes = service.lista_clientes();

            if (recBD.getReclamoPago() != null) {
                for (Cliente c : lista_clientes) {
                    if (recBD.getReclamoPago().getIdCliente().equals(c.getId())) {
                        recBD.setCliente(c);
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

        List<Cliente> lista_clientes = service.lista_clientes();
        for (ReclamoCliente reclamo : lista) {
            if (reclamo.getReclamoPago() != null) {
                for (Cliente cliente : lista_clientes) {
                    if (reclamo.getReclamoPago().getIdCliente().equals(cliente.getId())) {
                        reclamo.setCliente(cliente);
                        break;
                    }
                }
            }
        }
        return lista;
    }

    @PutMapping("/cambiar_estado/{estado}/{id}")
    public ResponseEntity<?> cambiarEstado (@PathVariable String estado, @PathVariable Long id) {
        Optional<ReclamoCliente> op = service.porId(id);
        if (op.isPresent()) {
           service.cambiarEstado(estado,id);
           return ResponseEntity.ok("Estado Actualizado");
        }
        return ResponseEntity.notFound().build();
    }

    //Accesos remotos
    @PutMapping("/asignar_cliente/{reclamoid}")
    public ResponseEntity<?> asignarCliente (@RequestBody Cliente cliente, @PathVariable Long reclamoid) {
        Optional<Cliente> op;
        try {
            op = service.asignarCliente(cliente,reclamoid);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("Mensaje","No existe el pago por el id " + cliente.getId() +
                            " o hubo error en la comunicación"+e.getMessage()));
        }

       if (op.isPresent())
           return ResponseEntity.status(HttpStatus.CREATED).body(op.get());
       return ResponseEntity.notFound().build();
    }

    @PutMapping("/remover_cliente/{reclamoid}")
    public ResponseEntity<?> removerCliente (@RequestBody Cliente cliente, @PathVariable Long reclamoid) {
        Optional<Cliente> op;
        try {
            op = service.removerCliente(cliente,reclamoid);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("Mensaje","No existe el pago por el id " + cliente.getId() +
                            " o hubo error en la comunicación"+e.getMessage()));
        }

        if (op.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(op.get());
        return ResponseEntity.notFound().build();
    }

}
