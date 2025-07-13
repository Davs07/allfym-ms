package com.grupo.allfym.ms.reclamos.Service;

import com.grupo.allfym.ms.reclamos.models.Pago;
import com.grupo.allfym.ms.reclamos.models.entity.ReclamoCliente;

import java.util.List;
import java.util.Optional;

public interface ReclamoClienteService {
    List<ReclamoCliente> listar();
    Optional<ReclamoCliente> porId(Long id);
    ReclamoCliente guardar(ReclamoCliente reclamoCliente);
    void eliminar(Long id);

    //Metodos remotos
    Optional<Pago> asignarPago(Pago pago, Long id);
    Optional<Pago> removerPago(Pago pago, Long id);
    List<Pago> lista_pagos();
    //Optional<Pago> crearPago();
}
