package com.grupo.allfym.ms.reclamos.domain.models.classes;

import com.grupo.allfym.ms.reclamos.domain.models.Cliente;
import com.grupo.allfym.ms.reclamos.domain.models.enums.EstadoReclamo;
import com.grupo.allfym.ms.reclamos.domain.models.vo.FechaReclamo;

public class Reclamo {
    private Long idReclamo;
    private String descripcion;
    private EstadoReclamo estadoReclamo;
    private FechaReclamo fechaReclamo;
    private ReclamoCliente reclamoCliente;
    private Cliente cliente;

    public Reclamo(Long idReclamo, String descripcion, EstadoReclamo estadoReclamo, FechaReclamo fechaReclamo, ReclamoCliente reclamoCliente, Cliente cliente) {
        this.idReclamo = idReclamo;
        this.descripcion = descripcion;
        this.estadoReclamo = estadoReclamo;
        this.fechaReclamo = fechaReclamo;
        this.reclamoCliente = reclamoCliente;
        this.cliente = cliente;
    }

    public Long getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(Long idReclamo) {
        this.idReclamo = idReclamo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoReclamo getEstadoReclamo() {
        return estadoReclamo;
    }

    public void setEstadoReclamo(EstadoReclamo estadoReclamo) {
        this.estadoReclamo = estadoReclamo;
    }

    public FechaReclamo getFechaReclamo() {
        return fechaReclamo;
    }

    public void setFechaReclamo(FechaReclamo fechaReclamo) {
        this.fechaReclamo = fechaReclamo;
    }

    public ReclamoCliente getReclamoCliente() {
        return reclamoCliente;
    }

    public void setReclamoCliente(ReclamoCliente reclamoCliente) {
        this.reclamoCliente = reclamoCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
