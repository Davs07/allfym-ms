package com.grupo.allfym.ms.reclamos.infrastructure.dtos;

import com.grupo.allfym.ms.reclamos.domain.models.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String nombreCompleto;
    private String email;
    private String dni;
    private String telefono;
    private String direccion;
    private LocalDateTime fechaRegistro;
    private Boolean activo;

    public Cliente toDomainModel() {
        return new Cliente(id,nombre,apellido,nombre,email,dni,telefono,direccion,fechaRegistro,activo);
    }

    public static ClienteDto fromDomainModel(Cliente cliente) {
        return new ClienteDto(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getNombreCompleto(),
                cliente.getEmail(),
                cliente.getDni(),
                cliente.getTelefono(),
                cliente.getDireccion(),
                cliente.getFechaRegistro(),
                cliente.getActivo()
        );
    }
}
