package com.grupo.allfym.ms.reclamos.domain.models.classes;

public class ReclamoCliente {
    private Long id;
    private Long idCliente;

    public ReclamoCliente(){}

    public ReclamoCliente(Long id, Long idCliente) {
        this.id = id;
        this.idCliente = idCliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
}
