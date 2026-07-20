package ferreteria.demo.Facade;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InventarioInicializer implements CommandLineRunner {

    private final SistemaFacade facade;

    public InventarioInicializer(SistemaFacade facade) {
        this.facade = facade;
    }

    @Override
    public void run(String... args) {
        facade.inicializarSistema();
    }
}