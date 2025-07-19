package com.grupo.allfym.ms.pagos.services;

import com.grupo.allfym.ms.pagos.client.VentaRestCli;
import com.grupo.allfym.ms.pagos.models.Venta;
import com.grupo.allfym.ms.pagos.models.entity.Pago;
import com.grupo.allfym.ms.pagos.models.entity.Pago_venta;
import com.grupo.allfym.ms.pagos.repositories.PagoRepository;
import com.grupo.allfym.ms.pagos.repositories.PagoVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PagoServiceImp implements PagoService{

    @Autowired
    private VentaRestCli client;
    @Autowired
    private PagoRepository repository;
    @Autowired
    private PagoVentaRepository pagoVentaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Pago> lista() {
        return (List<Pago>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pago> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Pago guardar(Pago pago) {
        return repository.save(pago);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    //METODOS REMOTOS
    @Override
    @Transactional(readOnly = true)
    public List<Venta> listaVenta() {
        return (List<Venta>) client.listaVenta();
    }

    @Override
    @Transactional
    public Optional<Venta> asignarVenta(Venta venta, Long id) {
        Optional<Pago> op = repository.findById(id);
        if (op.isPresent()) {
            Venta ventaMS = client.detalleVenta(venta.getId());

            Pago pagoBD  = op.get();
            Pago_venta pagoVenta = new Pago_venta();
            pagoVenta.setIdVenta(ventaMS.getId());

            pagoBD.setPagoVenta(pagoVenta);
            repository.save(pagoBD);
            return Optional.of(ventaMS);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Venta> removerVenta(Venta venta, Long id) {
        Optional<Pago> op = repository.findById(id);
        if (op.isPresent()) {
            Venta ventaMS = client.detalleVenta(venta.getId());

            Pago pagoBD = op.get();
            pagoVentaRepository.deleteById(pagoBD.getPagoVenta().getId()); //Elimina la relación previa
            pagoBD.setPagoVenta(null);

            repository.save(pagoBD);
            return Optional.of(ventaMS);
        }
        return Optional.empty();
    }


}
