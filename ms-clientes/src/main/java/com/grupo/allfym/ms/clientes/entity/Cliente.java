package com.grupo.allfym.ms.clientes.entity;

import com.grupo.allfym.ms.clientes.ov.EmailAddress;
import com.grupo.allfym.ms.clientes.ov.Telefono;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "clientes")
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Pattern(regexp = "^\\d{8}$", message = "El DNI debe tener exactamente 8 dígitos")
    @Column(name = "dni", unique = true)
    private String dni;

    @Embedded
    @AttributeOverride(name = "valor", column = @Column(name = "email"))
    private EmailAddress email;

    @Embedded
    @AttributeOverride(name = "valor", column = @Column(name = "telefono"))
    private Telefono telefono;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "activo")
    private Boolean activo;

    // Constructor por defecto
    public Cliente() {
        this.activo = true;
        this.fechaRegistro = LocalDateTime.now();
    }

    // Constructor con parámetros básicos
    public Cliente(String nombre, String apellido, String dni, EmailAddress email, Telefono telefono) {
        this();
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
    }

    // Método de conveniencia para obtener nombre completo
    public String getNombreCompleto() {
        return this.nombre + " " + this.apellido;
    }
}