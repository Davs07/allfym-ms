package com.grupo.allfym.ms_compra.infrastucture.entities;

import com.grupo.allfym.ms_compra.domain.models.entities.Compra;
import com.grupo.allfym.ms_compra.domain.models.entities.DetalleCompra;
import com.grupo.allfym.ms_compra.domain.models.enums.Estado;
import com.grupo.allfym.ms_compra.domain.models.vo.Fecha;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "compras")
@Data
@NoArgsConstructor
public class CompraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name = "fecha", column = @Column(name = "fecha_emision"))
    private FechaEmbeddable fechaEmision;

    @Embedded
    @AttributeOverride(name = "fecha", column = @Column(name = "fecha_recepcion"))
    private FechaEmbeddable fechaRecepcion;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(name = "id_proveedor", nullable = false)
    private Long idProveedor;

    private Double montoTotal;
    private String observaciones;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DetalleCompraEntity> detalles;

    public CompraEntity(FechaEmbeddable fechaEmision, FechaEmbeddable fechaRecepcion,
                        Estado estado, Long idProveedor, Double montoTotal,
                        String observaciones, List<DetalleCompraEntity> detalles) {
        this.fechaEmision = fechaEmision;
        this.fechaRecepcion = fechaRecepcion;
        this.estado = estado;
        this.idProveedor = idProveedor;
        this.montoTotal = montoTotal;
        this.observaciones = observaciones;
        this.detalles = detalles;
    }

    public static CompraEntity fromDomainModel(Compra compra) {
        CompraEntity entity = new CompraEntity();
        entity.setId(compra.getId());
        entity.setFechaEmision(new FechaEmbeddable(compra.getFechaEmision().getFecha()));
        entity.setFechaRecepcion(compra.getFechaRecepcion() != null ?
                new FechaEmbeddable(compra.getFechaRecepcion().getFecha()) : null);
        entity.setEstado(compra.getEstado());
        entity.setIdProveedor(compra.getIdProveedor());
        entity.setMontoTotal(compra.getMontoTotal());
        entity.setObservaciones(compra.getObservaciones());

        List<DetalleCompraEntity> detallesEntity = compra.getDetalles().stream()
                .map(detalle -> DetalleCompraEntity.fromDomainModel(detalle, entity))
                .collect(Collectors.toList());
        entity.setDetalles(detallesEntity);

        return entity;
    }

    public static Compra toDomainModel(CompraEntity entity) {
        List<DetalleCompra> detalles = entity.getDetalles().stream()
                .map(DetalleCompraEntity::toDomainModel)
                .collect(Collectors.toList());

        return new Compra(
                entity.getId(),
                new Fecha(entity.getFechaEmision().getFecha()),
                entity.getFechaRecepcion() != null ? new Fecha(entity.getFechaRecepcion().getFecha()) : null,
                entity.getEstado(),
                entity.getIdProveedor(),
                entity.getMontoTotal(),
                entity.getObservaciones(),
                detalles
        );
    }
}
