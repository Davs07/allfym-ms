package com.grupo.allfym.ms_proveedores.infrastructure.entities;

import com.grupo.allfym.ms_proveedores.domain.models.entities.Proveedor;
import com.grupo.allfym.ms_proveedores.domain.models.enums.Estado;
import com.grupo.allfym.ms_proveedores.domain.models.vo.Direccion;
import com.grupo.allfym.ms_proveedores.domain.models.vo.Email;
import com.grupo.allfym.ms_proveedores.domain.models.vo.FechaRegistro;
import com.grupo.allfym.ms_proveedores.domain.models.vo.Telefono;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "proveedores")
public class ProveedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Embedded
    @AttributeOverride(name = "numero", column = @Column(name = "telefono_numero"))
    private TelefonoEmbeddable telefono;

    @Column(name = "ruc_proveedor", nullable = false)
    private String ruc;

    @Embedded
    private EmailEmbeddable email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "calle", column = @Column(name = "direccion_calle")),
            @AttributeOverride(name = "ciudad", column = @Column(name = "direccion_ciudad")),
            @AttributeOverride(name = "codigoPostal", column = @Column(name = "direccion_codigo_postal")),
            @AttributeOverride(name = "pais", column = @Column(name = "direccion_pais"))
    })
    private DireccionEmbeddable direccion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @Embedded
    @AttributeOverride(name = "fecha", column = @Column(name = "fecha_registro"))
    private FechaDeRegistroEmbeddable fechaDeRegistro;


    public ProveedorEntity(String nombre, String ruc, TelefonoEmbeddable telefono,
                           EmailEmbeddable email, DireccionEmbeddable direccion,
                           Estado estado, FechaDeRegistroEmbeddable fechaDeRegistro) {
        this.nombre = nombre;
        this.ruc = ruc;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.estado = estado;
        this.fechaDeRegistro = fechaDeRegistro;
    }

    public static ProveedorEntity fromDomainModel(Proveedor proveedor){
        return new ProveedorEntity(
                proveedor.getNombre(),
                proveedor.getRuc(),
                new TelefonoEmbeddable(proveedor.getTelefono().getNumero()),
                new EmailEmbeddable(proveedor.getEmail().getEmail()),
                new DireccionEmbeddable(
                        proveedor.getDireccion().getCalle(),
                        proveedor.getDireccion().getCiudad(),
                        proveedor.getDireccion().getCodigoPostal(),
                        proveedor.getDireccion().getPais()
                ),
                proveedor.getEstado(),
                new FechaDeRegistroEmbeddable(proveedor.getFechaDeRegistro().getFecha()));
    }

    public static Proveedor toDomainModel(ProveedorEntity entity) {
        return new Proveedor(
                entity.getId(),
                entity.getNombre(),
                entity.getRuc(),
                new Telefono(entity.getTelefono().getNumero()),
                new Email(entity.getEmail().getEmail()),
                new Direccion(
                        entity.getDireccion().getCalle(),
                        entity.getDireccion().getCiudad(),
                        entity.getDireccion().getCodigoPostal(),
                        entity.getDireccion().getPais()
                ),
                entity.getEstado(),
                new FechaRegistro(entity.getFechaDeRegistro().getFecha())
        );
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public TelefonoEmbeddable getTelefono() {
        return telefono;
    }

    public String getRuc() {
        return ruc;
    }

    public EmailEmbeddable getEmail() {
        return email;
    }

    public DireccionEmbeddable getDireccion() {
        return direccion;
    }

    public Estado getEstado() {
        return estado;
    }

    public FechaDeRegistroEmbeddable getFechaDeRegistro() {
        return fechaDeRegistro;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(TelefonoEmbeddable telefono) {
        this.telefono = telefono;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public void setEmail(EmailEmbeddable email) {
        this.email = email;
    }

    public void setDireccion(DireccionEmbeddable direccion) {
        this.direccion = direccion;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setFechaDeRegistro(FechaDeRegistroEmbeddable fechaDeRegistro) {
        this.fechaDeRegistro = fechaDeRegistro;
    }
}
