package com.grupo.allfym.ms.ventas.entity;

import com.grupo.allfym.ms.ventas.enums.EstadoVenta;
import com.grupo.allfym.ms.ventas.enums.MetodoPago;
import com.grupo.allfym.ms.ventas.ov.FechaRegistro;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ventas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "detalles")
@EqualsAndHashCode(of = "id")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @NotNull
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @Embedded
    private FechaRegistro fechaRegistro;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false)
    private MetodoPago metodoPago;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoVenta estado;

    @Positive
    @Column(name = "total", precision = 10, scale = 2)
    private BigDecimal total;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleVenta> detalles = new ArrayList<>();

    @Builder
    public Venta(Long clienteId, MetodoPago metodoPago) {
        this.fechaRegistro = new FechaRegistro();
        this.estado = EstadoVenta.PENDIENTE;
        this.total = BigDecimal.ZERO;
        this.clienteId = clienteId;
        this.metodoPago = metodoPago;
    }

    // Métodos de dominio
    public void agregarDetalle(DetalleVenta detalle) {
        detalle.setVenta(this);
        this.detalles.add(detalle);
        calcularTotal();
    }

    public void calcularTotal() {
        this.total = detalles.stream()
                .map(DetalleVenta::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void confirmarVenta() {
        if (this.estado == EstadoVenta.PENDIENTE && !detalles.isEmpty()) {
            this.estado = EstadoVenta.CONFIRMADA;
        }
    }

    public void cancelarVenta() {
        if (this.estado == EstadoVenta.PENDIENTE) {
            this.estado = EstadoVenta.CANCELADA;
        }
    }
}