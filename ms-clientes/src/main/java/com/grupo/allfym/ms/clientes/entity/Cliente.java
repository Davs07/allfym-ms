package com.grupo.allfym.ms.clientes.entity;

import com.grupo.allfym.ms.clientes.ov.EmailAddress;
import com.grupo.allfym.ms.clientes.ov.FechaRegistro;
import com.grupo.allfym.ms.clientes.ov.Telefono;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "clientes")
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank
    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Pattern(regexp = "^\\d{8}$", message = "El DNI debe tener exactamente 8 dígitos")
    @Column(name = "dni", unique = true)
    private String dni;

    @Embedded
    @AttributeOverride(name = "valor", column = @Column(name = "email"))
    private EmailAddress email;

    @Embedded
    @AttributeOverride(name = "numero", column = @Column(name = "telefono"))
    private Telefono telefono;

    @Column(name = "direccion")
    private String direccion;

    @Embedded
    private FechaRegistro fechaRegistro;

    @Column(name = "activo")
    private Boolean activo;

    public Cliente() {
        this.activo = true;
        this.fechaRegistro = new FechaRegistro();
    }
}
