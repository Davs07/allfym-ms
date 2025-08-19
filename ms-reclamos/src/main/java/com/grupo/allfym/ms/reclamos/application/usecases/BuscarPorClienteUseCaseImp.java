package com.grupo.allfym.ms.reclamos.application.usecases;

import com.grupo.allfym.ms.reclamos.domain.models.Cliente;
import com.grupo.allfym.ms.reclamos.domain.models.classes.Reclamo;
import com.grupo.allfym.ms.reclamos.domain.ports.in.BuscarPorClienteUseCase;
import com.grupo.allfym.ms.reclamos.domain.ports.out.ClienteServicePort;
import com.grupo.allfym.ms.reclamos.domain.ports.out.ReclamoRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class BuscarPorClienteUseCaseImp implements BuscarPorClienteUseCase {
    private final ReclamoRepositoryPort repositoryPort;
    private final ClienteServicePort clienteServicePort;

    @Override
    public List<Reclamo> buscarCliente(String nombre) {
        List<Reclamo> reclamos = repositoryPort.listar();
        List<Reclamo> listaCliente = new ArrayList<>();

        String criterio = nombre.trim().toLowerCase();
        for (Reclamo reclamo : reclamos) {
            if (reclamo.getReclamoCliente() != null) {
                Cliente cliente = clienteServicePort.detalleCliente(reclamo.getReclamoCliente().getIdCliente());
                reclamo.setCliente(cliente);
                if (cliente != null) {
                    String nom = cliente.getNombre() != null ? cliente.getNombre().toLowerCase() : "";
                    String ape = cliente.getApellido() != null ? cliente.getApellido().toLowerCase() : "";
                    String nomComp = cliente.getNombreCompleto() != null ? cliente.getNombreCompleto().toLowerCase() : "";

                    if (nom.contains(criterio) || ape.contains(criterio) || nomComp.contains(criterio)) {
                        listaCliente.add(reclamo);
                    }
                }
            }
        }

        return listaCliente;
    }
}
