package com.grupo.allfym.ms.reclamos.application.usecases;

import com.grupo.allfym.ms.reclamos.domain.models.Cliente;
import com.grupo.allfym.ms.reclamos.domain.models.classes.Reclamo;
import com.grupo.allfym.ms.reclamos.domain.ports.in.ObtenerReclamoUseCase;
import com.grupo.allfym.ms.reclamos.domain.ports.out.ClienteServicePort;
import com.grupo.allfym.ms.reclamos.domain.ports.out.ReclamoRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ObtenerReclamoUseCaseImp implements ObtenerReclamoUseCase {
    private final ReclamoRepositoryPort repositoryPort;
    private final ClienteServicePort clienteServicePort;

    @Override
    public List<Reclamo> listaReclamo() {
        List<Reclamo> reclamos = repositoryPort.listar();

        for (Reclamo reclamo : reclamos) {
            if (reclamo.getReclamoCliente() != null) {
                Long idCliente = reclamo.getReclamoCliente().getIdCliente();
                if (idCliente != null) {
                    Cliente cliente = clienteServicePort.detalleCliente(idCliente);
                    reclamo.setCliente(cliente);
                }
            }
        }
        return reclamos;
    }

    @Override
    public Optional<Reclamo> detalleReclamo(Long id) {
        Optional<Reclamo> op = repositoryPort.porId(id);
        if (op.isPresent()) {
            Reclamo reclamo = op.get();
            if (reclamo.getReclamoCliente() != null) {
                Long idCliente = reclamo.getReclamoCliente().getIdCliente();
                if (idCliente != null) {
                    Cliente cliente = clienteServicePort.detalleCliente(idCliente);
                    reclamo.setCliente(cliente);
                }
            }
            return Optional.of(reclamo);
        }
        return Optional.empty();
    }
}
