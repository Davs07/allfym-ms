package com.grupo.allfym.ms.reclamos.Service;

import com.grupo.allfym.ms.reclamos.models.Cliente;
import com.grupo.allfym.ms.reclamos.models.entity.ReclamoCliente;

import java.util.List;
import java.util.Optional;

public interface ReclamoClienteService {
    List<ReclamoCliente> listar();
    Optional<ReclamoCliente> porId(Long id);
    ReclamoCliente guardar(ReclamoCliente reclamoCliente);
    void eliminar(Long id);
    void cambiarEstado(String estado, Long id);

    //Metodos remotos
    Optional<Cliente> asignarCliente(Cliente cliente, Long id);
    Optional<Cliente> removerCliente(Cliente cliente, Long id);
    List<Cliente> lista_clientes();
    //Optional<Pago> crearPago();
}
