package com.grupo.allfym.ms.reclamos.models.entity;

import com.grupo.allfym.ms.reclamos.enums.EstadoReclamo;
import com.grupo.allfym.ms.reclamos.models.Pago;
import com.grupo.allfym.ms.reclamos.vo.fechaReclamo;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ReclamoCliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReclamoCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReclamo;
    private String descripcion;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idPago")
    private ReclamoPago reclamoPago;
    @Transient
    private Pago pago;

    @Enumerated(EnumType.STRING)
    private EstadoReclamo estadoReclamo;

    @Embedded
    private fechaReclamo fechaReclamo;

}
