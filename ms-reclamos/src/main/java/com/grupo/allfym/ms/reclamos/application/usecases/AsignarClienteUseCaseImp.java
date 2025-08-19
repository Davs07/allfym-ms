package com.grupo.allfym.ms.reclamos.application.usecases;

import com.grupo.allfym.ms.reclamos.domain.models.Cliente;
import com.grupo.allfym.ms.reclamos.domain.models.classes.Reclamo;
import com.grupo.allfym.ms.reclamos.domain.models.classes.ReclamoCliente;
import com.grupo.allfym.ms.reclamos.domain.ports.in.AsignarClienteUseCase;
import com.grupo.allfym.ms.reclamos.domain.ports.out.ClienteServicePort;
import com.grupo.allfym.ms.reclamos.domain.ports.out.ReclamoRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class AsignarClienteUseCaseImp implements AsignarClienteUseCase {
    private final ReclamoRepositoryPort repositoryPort;
    private final ClienteServicePort clienteServicePort;

    @Override
    public Optional<Cliente> asignarCliente(Cliente cliente, Long id) {
        Optional<Reclamo> op = repositoryPort.porId(id);
        if (op.isPresent()) {
            Cliente clienteMS = clienteServicePort.detalleCliente(cliente.getId());
            //Asigno la relacion a Reclamo
            Reclamo reclamoBD = op.get();
            ReclamoCliente reclamoCliente = new ReclamoCliente();
            reclamoCliente.setIdCliente(cliente.getId());
            //Lo guardo en la Base de datos
            reclamoBD.setReclamoCliente(reclamoCliente);
            repositoryPort.guardar(reclamoBD);
            return Optional.of(clienteMS);
        }
        return Optional.empty();
    }
}
