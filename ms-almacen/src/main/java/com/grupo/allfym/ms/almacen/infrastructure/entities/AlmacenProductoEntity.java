package com.grupo.allfym.ms.almacen.infrastructure.entities;

import com.grupo.allfym.ms.almacen.domain.models.AlmacenProducto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "almacen_productos")
public class AlmacenProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_almacen")
    private Long idAlmacen;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "id_producto", nullable = false, unique = true)
    private Long idProducto;

    @OneToMany(mappedBy = "almacenProducto", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private List<MovimientoEntity> movimientos = new ArrayList<>();

    public static AlmacenProductoEntity fromDomainModel(AlmacenProducto almacenModelProducto) {
        AlmacenProductoEntity entity = new AlmacenProductoEntity();
        entity.setIdAlmacen(almacenModelProducto.getIdAlmacen());
        entity.setStock(almacenModelProducto.getStock());
        entity.setIdProducto(almacenModelProducto.getIdProducto());

        if (almacenModelProducto.getMovimientos() != null) {
            List<MovimientoEntity> movimientoEntities = almacenModelProducto.getMovimientos().stream()
                    .map(mov -> {
                        MovimientoEntity movEntity = MovimientoEntity.fromDomainModel(mov);
                        movEntity.setAlmacenProducto(entity); // ← ESTO FALTABA
                        return movEntity;
                    })
                    .collect(java.util.stream.Collectors.toList());
            entity.setMovimientos(movimientoEntities);
        }

        return entity;
    }

    public AlmacenProducto toDomainModel(){
        return new AlmacenProducto(
                idAlmacen,
                stock,
                idProducto,
                movimientos != null
                        ? movimientos.stream()
                        .map(MovimientoEntity::toDomainModel)
                        .collect(java.util.stream.Collectors.toList())
                        : new ArrayList<>()
        );
    }
}