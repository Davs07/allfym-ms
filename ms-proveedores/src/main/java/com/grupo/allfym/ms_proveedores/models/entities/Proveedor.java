package com.grupo.allfym.ms_proveedores.models.entities;


import com.grupo.allfym.ms_proveedores.models.enums.Estado;
import com.grupo.allfym.ms_proveedores.models.valueObjects.Direccion;
import com.grupo.allfym.ms_proveedores.models.valueObjects.Email;
import com.grupo.allfym.ms_proveedores.models.valueObjects.FechaDeRegistro;
import com.grupo.allfym.ms_proveedores.models.valueObjects.Telefono;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "proveedores")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "numero", column = @Column(name = "telefono_numero"))
    })
    private Telefono telefono;

    @Column(name = "ruc_proveedor")
    private String RUC;
    @Embedded
    private Email email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "calle", column = @Column(name = "direccion_calle")),
            @AttributeOverride(name = "ciudad", column = @Column(name = "direccion_ciudad")),
            @AttributeOverride(name = "codigoPostal", column = @Column(name = "direccion_codigo_postal")),
            @AttributeOverride(name = "pais", column = @Column(name = "direccion_pais"))
    })
    private Direccion direccion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fecha", column = @Column(name = "fecha_registro"))
    })
    private FechaDeRegistro fechaDeRegistro;

    public Proveedor(String nombre, String ruc, Telefono telefono, Email email, Direccion direccion) {
        this.nombre = nombre;
        this.RUC = ruc;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.estado = Estado.ACTIVO;
        this.fechaDeRegistro = new FechaDeRegistro();
    }

}
