package com.grupo.allfym.ms.reclamos.infrastructure.entities;

import com.grupo.allfym.ms.reclamos.domain.models.classes.ReclamoCliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ReclamoCliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReclamoClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "idCliente", unique = false)
    private Long idCliente;

    public static ReclamoClienteEntity fromDomainModel(ReclamoCliente reclamoCliente) {
        return new ReclamoClienteEntity(reclamoCliente.getId(), reclamoCliente.getIdCliente());
    }

    public ReclamoCliente toDomainModel () {
        return new ReclamoCliente(id,idCliente);
    }
}
