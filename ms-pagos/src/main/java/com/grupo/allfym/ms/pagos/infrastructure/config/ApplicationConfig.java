package com.grupo.allfym.ms.pagos.infrastructure.config;

import com.grupo.allfym.ms.pagos.application.services.PagoService;
import com.grupo.allfym.ms.pagos.application.usecases.*;
import com.grupo.allfym.ms.pagos.domain.ports.out.PagoRepositoryPort;
import com.grupo.allfym.ms.pagos.domain.ports.out.VentaServicePort;
import com.grupo.allfym.ms.pagos.infrastructure.adapters.VentaServiceAdapter;
import com.grupo.allfym.ms.pagos.infrastructure.clients.VentaClient;
import com.grupo.allfym.ms.pagos.infrastructure.repositories.JpaPagoRepository;
import com.grupo.allfym.ms.pagos.infrastructure.repositories.JpaPagoRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public PagoService pagoService(PagoRepositoryPort pagoRepositoryPort,
                                   VentaServicePort ventaServicePort) {
        return new PagoService(
                new ActualizarPagoUseCaseImp(pagoRepositoryPort),
                new AsignarVentaUseCaseImp(pagoRepositoryPort,ventaServicePort),
                new BuscarMetodoPagoUseCaseImp(pagoRepositoryPort),
                new EliminarPagoUseCaseImp(pagoRepositoryPort),
                new GuardarPagoUseCaseImp(pagoRepositoryPort),
                new ObtenerPagoUseCaseImp (pagoRepositoryPort,ventaServicePort),
                new RemoverVentaUseCaseImp (pagoRepositoryPort,ventaServicePort)
        );
    }

    @Bean
    public PagoRepositoryPort pagoRepositoryPort(JpaPagoRepository jpaPagoRepository){
        return new JpaPagoRepositoryAdapter(jpaPagoRepository);
    }

    @Bean
    public VentaServicePort ventaServicePort (VentaClient ventaClient) {
        return new VentaServiceAdapter(ventaClient);
    }

}
