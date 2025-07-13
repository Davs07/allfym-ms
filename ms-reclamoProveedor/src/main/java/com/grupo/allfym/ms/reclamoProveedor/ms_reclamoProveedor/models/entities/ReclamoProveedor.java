package com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.models.entities;

import com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.models.enums.EstadoReclamo;
import com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.models.valueObjects.FechaCreacion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reclamos_proveedor")
public class ReclamoProveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reclamo")
    private Long idReclamo;

    @Column(name = "descripcion", nullable = false, length = 1000)
    private String descripcion;

    @Column(name = "id_proveedor", nullable = false)
    private Long idProveedor;

    @Column(name = "nro_orden")
    private String nroOrden;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_reclamo", nullable = false)
    private EstadoReclamo estadoReclamo;

    @Embedded
    @AttributeOverride(name = "fecha", column = @Column(name = "fecha_creacion"))
    private FechaCreacion fechaCreacion;

    // === MÉTODOS DE DOMINIO ===

    /**
     * Crear un nuevo reclamo
     */
    public static ReclamoProveedor reclamo(String descripcion, Long idProveedor, String nroOrden) {
        ReclamoProveedor reclamo = new ReclamoProveedor();
        reclamo.setDescripcion(descripcion);
        reclamo.setIdProveedor(idProveedor);
        reclamo.setNroOrden(nroOrden);
        reclamo.setEstadoReclamo(EstadoReclamo.PENDIENTE);
        reclamo.setFechaCreacion(new FechaCreacion());
        return reclamo;
    }

    /**
     * Agregar información adicional al reclamo
     */
    public void agregarReclamo(String descripcionAdicional) {
        if (this.descripcion != null && !this.descripcion.trim().isEmpty()) {
            this.descripcion += "\n--- INFORMACIÓN ADICIONAL ---\n" + descripcionAdicional;
        } else {
            this.descripcion = descripcionAdicional;
        }
    }

    /**
     * Cambiar estado del reclamo
     */
    public void cambiarEstado(EstadoReclamo nuevoEstado) {
        this.estadoReclamo = nuevoEstado;
    }

    /**
     * Verificar si el reclamo puede ser modificado
     */
    public boolean puedeSerModificado() {
        return this.estadoReclamo == EstadoReclamo.PENDIENTE ||
               this.estadoReclamo == EstadoReclamo.EN_INVESTIGACION;
    }

    /**
     * Verificar si el reclamo está cerrado
     */
    public boolean estaCerrado() {
        return this.estadoReclamo == EstadoReclamo.CERRADO ||
               this.estadoReclamo == EstadoReclamo.RECHAZADO ||
               this.estadoReclamo == EstadoReclamo.ACEPTADO;
    }
}
