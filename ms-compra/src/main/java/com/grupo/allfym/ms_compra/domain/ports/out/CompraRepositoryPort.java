package com.grupo.allfym.ms_compra.domain.ports.out;

import com.grupo.allfym.ms.compra.domain.models.entities.Compra;
import com.grupo.allfym.ms.compra.domain.models.enums.Estado;

import java.util.List;
import java.util.Optional;

public interface CompraRepositoryPort {
    List<Compra> listar();
    Optional<Compra> buscarPorId(Long id);
    Compra guardar(Compra compra);
    void eliminar(Long id);
    List<Compra> buscarPorEstado(Estado estado);
    List<Compra> buscarPorIdProveedor(Long idProveedor);
}
