package com.grupo.allfym.ms.reclamos.application.usecases;

import com.grupo.allfym.ms.reclamos.domain.models.Cliente;
import com.grupo.allfym.ms.reclamos.domain.models.classes.Reclamo;
import com.grupo.allfym.ms.reclamos.domain.ports.in.RemoverClienteUseCase;
import com.grupo.allfym.ms.reclamos.domain.ports.out.ClienteServicePort;
import com.grupo.allfym.ms.reclamos.domain.ports.out.ReclamoRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class RemoverClienteUseCaseImp implements RemoverClienteUseCase {
    private final ReclamoRepositoryPort repositoryPort;
    private final ClienteServicePort clienteServicePort;

    @Override
    public Optional<Cliente> removerCliente(Cliente cliente, Long id) {
        Optional<Reclamo> op = repositoryPort.porId(id);
        if (op.isPresent()) {
            Cliente clienteMS = clienteServicePort.detalleCliente(cliente.getId());
            //Elimino la relacion con la venta
            Reclamo reclamoBD = op.get();
            if (reclamoBD.getReclamoCliente() != null &&
                    reclamoBD.getReclamoCliente().getIdCliente().equals(cliente.getId())) {
                // Rompo la relación
                reclamoBD.setReclamoCliente(null);
                repositoryPort.guardar(reclamoBD);
                return Optional.of(clienteMS);
            }
        }
        return Optional.empty();
    }
}
