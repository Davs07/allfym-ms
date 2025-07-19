package com.grupo.allfym.ms.reclamos.Service;

import com.grupo.allfym.ms.reclamos.Repositories.ReclamoClienteRepository;
import com.grupo.allfym.ms.reclamos.client.PagoClientRest;
import com.grupo.allfym.ms.reclamos.models.Pago;
import com.grupo.allfym.ms.reclamos.models.entity.ReclamoCliente;
import com.grupo.allfym.ms.reclamos.models.entity.ReclamoPago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReclamoClienteServiceImp implements ReclamoClienteService{

    @Autowired
    private ReclamoClienteRepository repository;

    @Autowired
    private PagoClientRest client;

    @Override
    @Transactional(readOnly = true)
    public List<ReclamoCliente> listar() {
        return (List<ReclamoCliente>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReclamoCliente> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public ReclamoCliente guardar(ReclamoCliente reclamoCliente) {
        return repository.save(reclamoCliente);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    //Metodos remotos

    @Override
    @Transactional(readOnly = true)
    public List<Pago> lista_pagos() {
        return client.lista_pagos();
    }

    @Override
    @Transactional
    public Optional<Pago> asignarPago(Pago pago, Long id) {
        Optional<ReclamoCliente> op = repository.findById(id);
        if (op.isPresent()) {
            Pago pagoMS = client.detalle(pago.getIdPago());

            ReclamoCliente rec = op.get();
            ReclamoPago recPag = new ReclamoPago();
            recPag.setIdPago(pagoMS.getIdPago());

            rec.setReclamoPago(recPag);
            repository.save(rec);
            return Optional.of(pagoMS);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Pago> removerPago(Pago pago, Long id) {
        Optional<ReclamoCliente> op = repository.findById(id);
        if (op.isPresent()) {
          Pago pagoMS = client.detalle(pago.getIdPago());

          ReclamoCliente rec = op.get();
          ReclamoPago reclamoPago = new ReclamoPago();
          reclamoPago.setIdPago(pagoMS.getIdPago());

          rec.setReclamoPago(null);
          repository.save(rec);
          return Optional.of(pagoMS);
        } return Optional.empty();
    }


}
