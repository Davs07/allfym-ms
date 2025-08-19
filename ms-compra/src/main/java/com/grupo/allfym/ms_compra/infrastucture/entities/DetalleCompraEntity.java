package com.grupo.allfym.ms_compra.infrastucture.entities;


import com.grupo.allfym.ms_compra.domain.models.entities.DetalleCompra;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalle_compra")
@Data
@NoArgsConstructor
public class DetalleCompraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_com")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_compra")
    private CompraEntity compra;
    @Column(name = "id_producto")
    private Long idProducto;
    private Integer cantidad;
    private Double precioCompra;
    private Double subtotal;

    public DetalleCompraEntity(CompraEntity compra, Long idProducto, Integer cantidad,
                               Double precioCompra, Double subtotal) {
        this.compra = compra;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
        this.subtotal = subtotal;
    }

    public static DetalleCompraEntity fromDomainModel(DetalleCompra detalle, CompraEntity compra) {
        return new DetalleCompraEntity(
                compra,
                detalle.getIdProducto(),
                detalle.getCantidad(),
                detalle.getPrecioCompra(),
                detalle.getSubtotal()
        );
    }

    public static DetalleCompra toDomainModel(DetalleCompraEntity entity) {
        return new DetalleCompra(
                entity.getId(),
                entity.getIdProducto(),
                entity.getCantidad(),
                entity.getPrecioCompra()
        );
    }
}
