package com.grupo.allfym.ms.reclamos.application.services;

import com.grupo.allfym.ms.reclamos.domain.models.Cliente;
import com.grupo.allfym.ms.reclamos.domain.models.classes.Reclamo;
import com.grupo.allfym.ms.reclamos.domain.ports.in.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ReclamoService implements ActualizarReclamoUseCase, AsignarClienteUseCase, BuscarPorClienteUseCase,
        EliminarReclamoUseCase, GuardarReclamoUseCase,ObtenerReclamoUseCase,RemoverClienteUseCase {
    private final ActualizarReclamoUseCase actualizarReclamoUseCase;
    private final AsignarClienteUseCase asignarClienteUseCase;
    private final BuscarPorClienteUseCase buscarPorClienteUseCase;
    private final EliminarReclamoUseCase eliminarReclamoUseCase;
    private final GuardarReclamoUseCase guardarReclamoUseCase;
    private final ObtenerReclamoUseCase obtenerReclamoUseCase;
    private final RemoverClienteUseCase removerClienteUseCase;

    @Override
    public Optional<Reclamo> actualizarReclamo(Long id, Reclamo reclamo) {
        return actualizarReclamoUseCase.actualizarReclamo(id,reclamo);
    }

    @Override
    public Optional<Cliente> asignarCliente(Cliente cliente, Long id) {
        return asignarClienteUseCase.asignarCliente(cliente, id);
    }

    @Override
    public List<Reclamo> buscarCliente(String nombre) {
        return buscarPorClienteUseCase.buscarCliente(nombre);
    }

    @Override
    public void eliminarReclamo(Long id) {
        eliminarReclamoUseCase.eliminarReclamo(id);
    }

    @Override
    public Reclamo guardar(Reclamo reclamo) {
        return guardarReclamoUseCase.guardar(reclamo);
    }

    @Override
    public List<Reclamo> listaReclamo() {
        return obtenerReclamoUseCase.listaReclamo();
    }

    @Override
    public Optional<Reclamo> detalleReclamo(Long id) {
        return obtenerReclamoUseCase.detalleReclamo(id);
    }

    @Override
    public Optional<Cliente> removerCliente(Cliente cliente, Long id) {
        return removerClienteUseCase.removerCliente(cliente, id);
    }
}
