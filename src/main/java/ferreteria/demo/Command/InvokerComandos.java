package ferreteria.demo.Command;

import java.util.ArrayList;
import java.util.List;

public class InvokerComandos {

    private final List<Comando> historial = new ArrayList<>();

    public void ejecutar(Comando comando) {
        comando.ejecutar();
        historial.add(comando);
    }

    public void mostrarHistorial() {
        System.out.println("\n[Invoker] Historial de comandos ejecutados:");
        for (int i = 0; i < historial.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + historial.get(i).getClass().getSimpleName());
        }
    }

    public List<Comando> getHistorial() {
    return historial;
    }
}