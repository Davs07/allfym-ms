package com.grupo.allfym.ms.pagos.infrastructure.repositories;

import com.grupo.allfym.ms.pagos.domain.models.Venta;
import com.grupo.allfym.ms.pagos.domain.models.classes.Pago;
import com.grupo.allfym.ms.pagos.domain.ports.out.PagoRepositoryPort;
import com.grupo.allfym.ms.pagos.infrastructure.entities.PagoEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class JpaPagoRepositoryAdapter implements PagoRepositoryPort {

    @Autowired
    private final JpaPagoRepository repository;

    @Override
    public List<Pago> lista() {
        return repository.findAll().stream().
                map(PagoEntity::toDomainModel).collect(Collectors.toList());
    }

    @Override
    public Optional<Pago> porId(Long id) {
        return repository.findById(id).map(PagoEntity::toDomainModel);
    }

    @Override
    public Pago guardar(Pago pago) {
        PagoEntity pagoEntity = repository.save(PagoEntity.fromDomainModel(pago));
        return pagoEntity.toDomainModel();
    }

    @Override
    public void eliminar(Long id) {
        if (repository.existsById(id)) repository.deleteById(id);
    }

    @Override
    public Optional<Pago> actualizar(Pago pago, Long id) {
        if (repository.existsById(pago.getIdPago())){
            PagoEntity pagoEntity = PagoEntity.fromDomainModel(pago);
            PagoEntity savedPago = repository.save(pagoEntity);
            return Optional.of(savedPago.toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public List<Pago> listaMetodoPago(String metodo) {
        List<Pago> lista = lista();
        List<Pago> lista_filtrada = new ArrayList<>();
        for (Pago pago : lista) {
            if (pago.getComprobantePago().getMetodoPago().toString().equals(metodo))
                lista_filtrada.add(pago);
        }
        return lista_filtrada;
    }

    @Override
    public Optional<Venta> asignarVenta(Venta venta, Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Venta> removerVenta(Venta venta, Long id) {
        return Optional.empty();
    }
}
