package com.grupo.allfym.ms.reclamos.infrastructure.adapters;

import com.grupo.allfym.ms.reclamos.domain.models.Cliente;
import com.grupo.allfym.ms.reclamos.domain.ports.out.ClienteServicePort;
import com.grupo.allfym.ms.reclamos.infrastructure.clients.ClienteClient;
import com.grupo.allfym.ms.reclamos.infrastructure.dtos.ClienteDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ClienteServiceAdapter implements ClienteServicePort {

    @Autowired
    private final ClienteClient client;

    @Override
    public List<Cliente> lista_clientes() {
        return client.lista_cliente().stream().
                map(ClienteDto::toDomainModel).collect(Collectors.toList());
    }

    @Override
    public Cliente detalleCliente(Long id) {
        return client.detalle(id).toDomainModel();
    }
}
