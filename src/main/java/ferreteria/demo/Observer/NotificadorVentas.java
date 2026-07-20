package ferreteria.demo.Observer;


public class NotificadorVentas implements ObservadorInventario {

    @Override
    public void actualizar(String nombreProducto, int stockActual) {
        System.out.println("[VENTAS - Observer] Producto " + nombreProducto
                + " actualizado. Stock disponible: " + stockActual);
    }
}
