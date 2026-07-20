package ferreteria.demo.Observer;

import java.util.ArrayList;
import java.util.List;

public class AlertaStockBajo implements ObservadorInventario {

    private static final List<String> alertas = new ArrayList<>();

    @Override
    public void actualizar(String nombreProducto, int stockActual) {
        String mensaje = "Stock bajo: " + nombreProducto + " (quedan " + stockActual + " unidades)";
        System.out.println("[ALERTA - Observer] " + mensaje);
        alertas.add(0, mensaje);
        if (alertas.size() > 5) {
            alertas.remove(alertas.size() - 1);
        }
    }

    public static List<String> getAlertas() {
        return alertas;
    }
}
