package com.neoris.aplicationprogramminginterface.infrastructure.conf;

import com.neoris.aplicationprogramminginterface.application.service.cliente.ClienteService;
import com.neoris.aplicationprogramminginterface.application.service.cliente.ClienteServiceImpl;
import com.neoris.aplicationprogramminginterface.application.service.cuenta.CuentaService;
import com.neoris.aplicationprogramminginterface.application.service.cuenta.CuentaServiceImpl;
import com.neoris.aplicationprogramminginterface.domain.port.ClienteRepository;
import com.neoris.aplicationprogramminginterface.domain.port.CuentaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    ClienteService clienteBeanService(final ClienteRepository clienteRepository) {
        return new ClienteServiceImpl(clienteRepository);
    }

    @Bean
    CuentaService cuentaBeanService(final CuentaRepository cuentaRepository) {
        return new CuentaServiceImpl(cuentaRepository);
    }
}
