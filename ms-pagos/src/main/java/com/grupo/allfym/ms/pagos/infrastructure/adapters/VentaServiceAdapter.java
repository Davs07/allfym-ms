package com.grupo.allfym.ms.pagos.infrastructure.adapters;

import com.grupo.allfym.ms.pagos.domain.models.Venta;
import com.grupo.allfym.ms.pagos.domain.ports.out.VentaServicePort;
import com.grupo.allfym.ms.pagos.infrastructure.clients.VentaClient;
import com.grupo.allfym.ms.pagos.infrastructure.dtos.VentaDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class VentaServiceAdapter implements VentaServicePort {

    @Autowired
    private VentaClient ventaClient;

    @Override
    public List<Venta> listaVenta() {
        return ventaClient.listaVenta().stream().
                map(VentaDto::toDomainModel).collect(Collectors.toList());
    }

    @Override
    public Venta detalleVenta(Long id) {
        return ventaClient.detalleVenta(id).toDomainModel();
    }

}
