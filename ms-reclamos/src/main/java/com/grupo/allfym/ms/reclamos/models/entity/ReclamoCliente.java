package com.grupo.allfym.ms.reclamos.models.entity;

import com.grupo.allfym.ms.reclamos.enums.EstadoReclamo;
import com.grupo.allfym.ms.reclamos.models.Cliente;
import com.grupo.allfym.ms.reclamos.vo.fechaReclamo;
import jakarta.persistence.*;
import lombok.*;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCliente")
    private ReclamoPago reclamoPago;
    @Transient
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    private EstadoReclamo estadoReclamo;

    @Embedded
    private fechaReclamo fechaReclamo;
}
