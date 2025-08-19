package com.grupo.allfym.ms_compra.application.services;

import com.grupo.allfym.ms_compra.domain.models.entities.Compra;
import com.grupo.allfym.ms_compra.domain.models.entities.DetalleCompra;
import com.grupo.allfym.ms_compra.domain.models.enums.Estado;
import com.grupo.allfym.ms_compra.domain.ports.in.*;

import java.util.List;
import java.util.Optional;

// patron facade, porque esta agrupando casos de uso en un solo servicio
public class CompraService implements BuscarCompraPorIdUseCase, GuardarCompraUseCase,
        ListarComprasUseCase, EliminarCompraUseCase, BuscarComprasPorEstadoUseCase,
        BuscarComprasPorProveedorUseCase, CambiarEstadoCompraUseCase, AgregarDetalleCompraUseCase {


    private final BuscarCompraPorIdUseCase buscarCompraPorIdUseCase;
    private final GuardarCompraUseCase guardarCompraUseCase;
    private final ListarComprasUseCase listarComprasUseCase;
    private final EliminarCompraUseCase eliminarCompraUseCase;
    private final BuscarComprasPorEstadoUseCase buscarComprasPorEstadoUseCase;
    private final BuscarComprasPorProveedorUseCase buscarComprasPorProveedorUseCase;
    private final CambiarEstadoCompraUseCase cambiarEstadoCompraUseCase;
    private final AgregarDetalleCompraUseCase agregarDetalleCompraUseCase;

    public CompraService(BuscarCompraPorIdUseCase buscarCompraPorIdUseCase,
                         GuardarCompraUseCase guardarCompraUseCase,
                         ListarComprasUseCase listarComprasUseCase,
                         EliminarCompraUseCase eliminarCompraUseCase,
                         BuscarComprasPorEstadoUseCase buscarComprasPorEstadoUseCase,
                         BuscarComprasPorProveedorUseCase buscarComprasPorProveedorUseCase,
                         CambiarEstadoCompraUseCase cambiarEstadoCompraUseCase,
                         AgregarDetalleCompraUseCase agregarDetalleCompraUseCase) {
        this.buscarCompraPorIdUseCase = buscarCompraPorIdUseCase;
        this.guardarCompraUseCase = guardarCompraUseCase;
        this.listarComprasUseCase = listarComprasUseCase;
        this.eliminarCompraUseCase = eliminarCompraUseCase;
        this.buscarComprasPorEstadoUseCase = buscarComprasPorEstadoUseCase;
        this.buscarComprasPorProveedorUseCase = buscarComprasPorProveedorUseCase;
        this.cambiarEstadoCompraUseCase = cambiarEstadoCompraUseCase;
        this.agregarDetalleCompraUseCase = agregarDetalleCompraUseCase;
    }

    @Override
    public Compra agregarDetalle(Long idCompra, DetalleCompra detalleCompra) {
        return agregarDetalleCompraUseCase.agregarDetalle(idCompra, detalleCompra);
    }

    @Override
    public Optional<Compra> buscarPorId(Long id) {
        return buscarCompraPorIdUseCase.buscarPorId(id);
    }

    @Override
    public List<Compra> buscarPorEstado(Estado estado) {
        return buscarComprasPorEstadoUseCase.buscarPorEstado(estado);
    }

    @Override
    public List<Compra> buscarPorIdProveedor(Long idProveedor) {
        return buscarComprasPorProveedorUseCase.buscarPorIdProveedor(idProveedor);
    }

    @Override
    public List<Compra> buscarPorNombreProveedor(String nombre) {
        return buscarComprasPorProveedorUseCase.buscarPorNombreProveedor(nombre);
    }

    @Override
    public void cambiarEstado(Long id, String estado) {
        cambiarEstadoCompraUseCase.cambiarEstado(id, estado);
    }

    @Override
    public void eliminar(Long id) {
        eliminarCompraUseCase.eliminar(id);
    }

    @Override
    public Compra guardar(Compra compra) {
        return guardarCompraUseCase.guardar(compra);
    }

    @Override
    public List<Compra> listar() {
        return listarComprasUseCase.listar();
    }
}
