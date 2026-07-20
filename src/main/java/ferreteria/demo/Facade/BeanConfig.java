package ferreteria.demo.Facade;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public SistemaFacade sistemaFacade() {
        return new SistemaFacade();
    }
}