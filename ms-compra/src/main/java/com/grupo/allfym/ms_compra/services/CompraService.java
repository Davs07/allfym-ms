package com.grupo.allfym.ms_compra.services;

import com.grupo.allfym.ms_compra.models.entities.Compra;
import com.grupo.allfym.ms_compra.models.entities.DetalleCompra;
import com.grupo.allfym.ms_compra.models.enums.Estado;

import java.util.List;
import java.util.Optional;

public interface CompraService {
    Compra guardar(Compra compra);
    void eliminar(Long id);
    List<Compra> listar();
    Optional<Compra> buscarPorId(Long idCompra);
    List<Compra> buscarPorEstado(Estado estado);
    List<Compra> buscarPorIdProveedor(Long idProveedor);
    List<Compra> buscarPorNombreProveedor(String nombre);
    void cambiarEstado(Long id, String estado);

    Compra agregarDetalle(Long idCompra, DetalleCompra detalleCompra);
}
