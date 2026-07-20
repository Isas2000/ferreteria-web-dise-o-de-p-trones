package ferreteria.demo.Observer;

import java.util.ArrayList;
import java.util.List;

public class GestorNotificaciones {

    private final List<ObservadorInventario> observadores = new ArrayList<>();

    public void suscribir(ObservadorInventario observador) {
        observadores.add(observador);
    }

    public void desuscribir(ObservadorInventario observador) {
        observadores.remove(observador);
    }

    public void notificar(String nombreProducto, int stockActual) {
        for (ObservadorInventario obs : observadores) {
            obs.actualizar(nombreProducto, stockActual);
        }
    }
}