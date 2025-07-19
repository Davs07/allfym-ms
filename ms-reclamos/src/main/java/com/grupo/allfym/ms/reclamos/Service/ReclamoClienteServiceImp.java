package com.grupo.allfym.ms.reclamos.Service;

import com.grupo.allfym.ms.reclamos.Repositories.ReclamoClienteRepository;
import com.grupo.allfym.ms.reclamos.Repositories.ReclamoPagoRepository;
import com.grupo.allfym.ms.reclamos.client.ClienteClientRest;
import com.grupo.allfym.ms.reclamos.enums.EstadoReclamo;
import com.grupo.allfym.ms.reclamos.models.Cliente;
import com.grupo.allfym.ms.reclamos.models.entity.ReclamoCliente;
import com.grupo.allfym.ms.reclamos.models.entity.ReclamoPago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReclamoClienteServiceImp implements ReclamoClienteService{

    @Autowired
    private ReclamoClienteRepository repository;

    @Autowired
    private ClienteClientRest client;

    @Autowired
    private ReclamoPagoRepository reclamoClienteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ReclamoCliente> listar() {
        return (List<ReclamoCliente>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReclamoCliente> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public ReclamoCliente guardar(ReclamoCliente reclamoCliente) {
        return repository.save(reclamoCliente);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void cambiarEstado(String estadoN, Long id) {
        Optional<ReclamoCliente> op = repository.findById(id);
        if(op.isPresent()){
            ReclamoCliente reclamo = op.get();
            try{
                EstadoReclamo estado = EstadoReclamo.valueOf(estadoN.toUpperCase());
                reclamo.setEstadoReclamo(estado);
                repository.save(reclamo);
            }catch (IllegalArgumentException  e){
                throw new IllegalArgumentException("Estado inválido: " + estadoN);
            }
        }else {
            throw new IllegalArgumentException("No se encontro el reclamo");
        }
    }

    //Metodos remotos

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> lista_clientes() {
        return client.lista_cliente();
    }

    @Override
    @Transactional
    public Optional<Cliente> asignarCliente(Cliente cliente, Long id) {
        Optional<ReclamoCliente> op = repository.findById(id);
        if (op.isPresent()) {
            Cliente clienteMS = client.detalle(cliente.getId());

            ReclamoCliente rec = op.get();
            ReclamoPago recPag = new ReclamoPago();
            recPag.setIdCliente(clienteMS.getId());

            rec.setReclamoPago(recPag);
            repository.save(rec);
            return Optional.of(clienteMS);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Cliente> removerCliente(Cliente cliente, Long id) {
        Optional<ReclamoCliente> op = repository.findById(id);
        if (op.isPresent()) {
            Cliente clienteMS = client.detalle(cliente.getId());

            ReclamoCliente recBD = op.get();

            reclamoClienteRepository.deleteById(recBD.getReclamoPago().getId()); //Elimina la relación previa
            recBD.setReclamoPago(null);

            repository.save(recBD);
            return Optional.of(clienteMS);
        }
        return Optional.empty();
    }


}
