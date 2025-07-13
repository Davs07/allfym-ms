package com.grupo.allfym.ms.pagos.services;

import com.grupo.allfym.ms.pagos.clients.VentaClient;
import com.grupo.allfym.ms.pagos.entity.ComprobantePago;
import com.grupo.allfym.ms.pagos.entity.Pago;
import com.grupo.allfym.ms.pagos.enums.EstadoPago;
import com.grupo.allfym.ms.pagos.models.PagoRequestDTO;
import com.grupo.allfym.ms.pagos.models.PagoResponseDTO;
import com.grupo.allfym.ms.pagos.models.VentaResponseDTO;
import com.grupo.allfym.ms.pagos.ov.FechaEmision;
import com.grupo.allfym.ms.pagos.repositories.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PagoServiceImp implements PagoService{

    @Autowired
    private PagoRepository repository;

    @Autowired
    private VentaClient ventaClient;

    @Override
    @Transactional(readOnly = true)
    public List<Pago> lista() {
        return (List<Pago>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pago> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Pago guardar(Pago pago) {
        return repository.save(pago);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    // Nuevos métodos con DTOs y comunicación con ms-ventas
    @Override
    @Transactional
    public PagoResponseDTO crearPago(PagoRequestDTO pagoRequest) {
        // Validar que la venta existe usando Feign
        ResponseEntity<VentaResponseDTO> ventaResponse = ventaClient.obtenerVentaPorId(pagoRequest.getVentaId());
        if (ventaResponse.getBody() == null) {
            throw new RuntimeException("Venta no encontrada con ID: " + pagoRequest.getVentaId());
        }

        VentaResponseDTO venta = ventaResponse.getBody();

        // Crear el pago
        Pago pago = new Pago();
        pago.setVentaId(pagoRequest.getVentaId());
        pago.setMonto(pagoRequest.getMonto());
        pago.setEstadoPago(EstadoPago.POR_PAGAR);

        // Crear comprobante de pago
        ComprobantePago comprobante = new ComprobantePago();
        comprobante.setFechaEmision(new FechaEmision());
        // comprobante.setNumeroComprobante(pagoRequest.getNumeroComprobante());
        pago.setComprobantePago(comprobante);

        Pago pagoGuardado = repository.save(pago);
        return convertirAPagoResponseDTO(pagoGuardado, venta);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PagoResponseDTO> obtenerPagoPorId(Long id) {
        Optional<Pago> pagoOpt = repository.findById(id);
        if (pagoOpt.isPresent()) {
            Pago pago = pagoOpt.get();
            try {
                ResponseEntity<VentaResponseDTO> ventaResponse = ventaClient.obtenerVentaPorId(pago.getVentaId());
                VentaResponseDTO venta = ventaResponse.getBody();
                return Optional.of(convertirAPagoResponseDTO(pago, venta));
            } catch (Exception e) {
                // Si no se puede obtener la venta, devolver pago sin información de venta
                return Optional.of(convertirAPagoResponseDTO(pago, null));
            }
        }
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PagoResponseDTO> obtenerTodosLosPagos() {
        List<Pago> pagos = (List<Pago>) repository.findAll();
        return pagos.stream()
            .map(pago -> {
                try {
                    ResponseEntity<VentaResponseDTO> ventaResponse = ventaClient.obtenerVentaPorId(pago.getVentaId());
                    return convertirAPagoResponseDTO(pago, ventaResponse.getBody());
                } catch (Exception e) {
                    return convertirAPagoResponseDTO(pago, null);
                }
            })
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PagoResponseDTO> obtenerPagosPorVenta(Long ventaId) {
        return repository.findByVentaId(ventaId).stream()
            .map(pago -> {
                try {
                    ResponseEntity<VentaResponseDTO> ventaResponse = ventaClient.obtenerVentaPorId(ventaId);
                    return convertirAPagoResponseDTO(pago, ventaResponse.getBody());
                } catch (Exception e) {
                    return convertirAPagoResponseDTO(pago, null);
                }
            })
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PagoResponseDTO actualizarEstadoPago(Long id, String nuevoEstado) {
        Pago pago = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pago no encontrado"));

        pago.setEstadoPago(EstadoPago.valueOf(nuevoEstado.toUpperCase()));
        Pago pagoActualizado = repository.save(pago);

        try {
            ResponseEntity<VentaResponseDTO> ventaResponse = ventaClient.obtenerVentaPorId(pago.getVentaId());
            return convertirAPagoResponseDTO(pagoActualizado, ventaResponse.getBody());
        } catch (Exception e) {
            return convertirAPagoResponseDTO(pagoActualizado, null);
        }
    }

    private PagoResponseDTO convertirAPagoResponseDTO(Pago pago, VentaResponseDTO venta) {
        String numeroComprobante = null;
        String tipoComprobante = null;
        LocalDateTime fechaEmision = null;

        if (pago.getComprobantePago() != null) {
            ComprobantePago comprobante = pago.getComprobantePago();
            tipoComprobante = comprobante.getTipoComprobante() != null ? comprobante.getTipoComprobante().toString() : null;

            if (comprobante.getFechaEmision() != null) {
                FechaEmision fecha = comprobante.getFechaEmision();
                fechaEmision = LocalDateTime.of(fecha.getAño(), fecha.getMes(), fecha.getDia(), 0, 0);
            }
        }

        return new PagoResponseDTO(
            pago.getIdPago(),
            pago.getVentaId(),
            pago.getMonto(),
            pago.getEstadoPago().toString(),
            numeroComprobante, // Campo que no existe en ComprobantePago
            tipoComprobante,
            fechaEmision,
            venta != null ? venta.getNombreCliente() : "Cliente no disponible",
            venta != null ? venta.getEstado() : "Estado no disponible",
            venta != null ? venta.getTotal().doubleValue() : 0.0
        );
    }
}
