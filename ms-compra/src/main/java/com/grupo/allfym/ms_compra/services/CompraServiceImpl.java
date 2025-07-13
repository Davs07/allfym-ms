package com.grupo.allfym.ms_compra.services;

import com.grupo.allfym.ms_compra.clients.ProveedorCliente;
import com.grupo.allfym.ms_compra.models.Proveedor;
import com.grupo.allfym.ms_compra.models.entities.Compra;
import com.grupo.allfym.ms_compra.models.entities.DetalleCompra;
import com.grupo.allfym.ms_compra.models.enums.Estado;
import com.grupo.allfym.ms_compra.repositories.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompraServiceImpl implements CompraService{

    @Autowired
    private CompraRepository repositoryCompra;

    @Autowired
    private ProveedorCliente client;

    @Override
    public void eliminar(Long id) {
        repositoryCompra.deleteById(id);
    }

    @Override
    public List<Compra> listar() {
        return (List<Compra>) repositoryCompra.findAll();
    }

    @Override
    public Optional<Compra> buscarPorId(Long idCompra) {
        return repositoryCompra.findById(idCompra);
    }

    @Override
    public List<Compra> buscarPorEstado(Estado estado) {
        return repositoryCompra.findByEstado(estado);
    }

    @Override
    public List<Compra> buscarPorIdProveedor(Long idProveedor) {
        Proveedor prove = client.buscarporId(idProveedor);
        if(prove == null){
            throw new IllegalArgumentException("no se encontro el proveedor");
        }
        return repositoryCompra.findByIdProveedor(idProveedor);
//        List<Compra> listacom = (List<Compra>)repositoryCompra.findAll();
//        List<Compra> comprasPro = new ArrayList<>();
//
//        for(Compra c : listacom){
//            if(c.getIdProveedor()==prove.getId()){
//                comprasPro.add(c);
//            }
//        }
//        return comprasPro;
    }

    @Override
    public List<Compra> buscarPorNombreProveedor(String nombre) {
        List<Proveedor> proveedores = client.listar();
        List<Long> ids = new ArrayList<>();

        for (Proveedor p : proveedores) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                ids.add(p.getId());
            }
        }

        List<Compra> todasLasCompras = (List<Compra>) repositoryCompra.findAll();
        List<Compra> resultado = new ArrayList<>();

        for (Compra c : todasLasCompras) {
            if (ids.contains(c.getIdProveedor())) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    @Override
    public void cambiarEstado(Long id, String estadoN) {
        Optional<Compra> Op = repositoryCompra.findById(id);
        if(Op.isPresent()){
            Compra compra = Op.get();
            try{
                Estado estado = Estado.valueOf(estadoN.toUpperCase());
                compra.setEstado(estado);
                repositoryCompra.save(compra);
            }catch (IllegalArgumentException  e){
                throw new IllegalArgumentException("Estado inválido: " + estadoN);
            }
        }else {
            throw new IllegalArgumentException("No se encontro la compra");
        }

    }

    @Override
    public Compra guardar(Compra compra) {

        Long proveedorId = compra.getIdProveedor();
        Proveedor proveedor = client.buscarporId(proveedorId);

        if (proveedor == null) {
            throw new IllegalArgumentException("Proveedor no encontrado con ID: " + proveedorId);
        }

        List<DetalleCompra> detalles = compra.getDetalles();
        double total = 0.0;

        for (DetalleCompra detalle : detalles) {
            double subtotal = detalle.getCantidad() * detalle.getPrecioCompra();
            detalle.setSubtotal(subtotal);
            detalle.setCompra(compra);
            total += subtotal;
        }

        compra.setMontoTotal(total);

        return repositoryCompra.save(compra);
    }

}
