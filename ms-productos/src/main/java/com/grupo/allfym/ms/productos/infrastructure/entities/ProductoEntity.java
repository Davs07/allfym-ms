package com.grupo.allfym.ms.productos.infrastructure.entities;

import com.grupo.allfym.ms.productos.domain.models.Producto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long IdProducto;
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;
    @Column(name = "precio", nullable = false)
    private Double precio;
    @Column(name = "categoria", nullable = false)
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    @Column(name = "marca", nullable = false)
    private Marca marca;

    public static ProductoEntity fromDomainModel(Producto producto) {
        return new ProductoEntity(
            producto.getId(),
            producto.getNombre(),
            producto.getDescripcion(),
            producto.getPrecio(),
            producto.getCategoria(),
            producto.getMarca()
        );
    }

    public Producto toDomainModel() {
        return new Producto(
            this.IdProducto,
            this.nombre,
            this.descripcion,
            this.precio,
            this.categoria,
            this.marca
        );
    }
}
