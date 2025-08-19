package com.grupo.allfym.ms.reclamos.infrastructure.entities;

import com.grupo.allfym.ms.reclamos.domain.models.classes.Reclamo;
import com.grupo.allfym.ms.reclamos.domain.models.enums.EstadoReclamo;
import com.grupo.allfym.ms.reclamos.domain.models.vo.FechaReclamo;
import com.grupo.allfym.ms.reclamos.infrastructure.dtos.ClienteDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Reclamo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReclamoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReclamo;
    private String descripcion;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idCliente")
    private ReclamoClienteEntity reclamoClienteEntity;

    @Transient
    private ClienteDto clienteDto;

    @Enumerated(EnumType.STRING)
    private EstadoReclamo estadoReclamo;

    @Embedded
    private FechaReclamoEmbeddeable fechaReclamoEmbeddeable;

    public static ReclamoEntity fromDomainModel(Reclamo reclamo) {
        return new ReclamoEntity(
                reclamo.getIdReclamo(),
                reclamo.getDescripcion(),
                reclamo.getReclamoCliente() != null ? ReclamoClienteEntity.fromDomainModel(reclamo.getReclamoCliente()) : null,
                reclamo.getCliente() != null ? ClienteDto.fromDomainModel(reclamo.getCliente()) : null,
                reclamo.getEstadoReclamo(),
                reclamo.getFechaReclamo() != null ?  new FechaReclamoEmbeddeable(reclamo.getFechaReclamo().getFecha()) :  new FechaReclamoEmbeddeable());
    }

    public Reclamo toDomainModel(){
        return new Reclamo(
                idReclamo,
                descripcion,
                estadoReclamo != null ? estadoReclamo : null,
                new FechaReclamo(fechaReclamoEmbeddeable.getFecha()),
                reclamoClienteEntity != null ? reclamoClienteEntity.toDomainModel() : null,
                clienteDto != null ? clienteDto.toDomainModel() : null);
    }
}
