package ferreteria.demo.Command;
import ferreteria.demo.Proxy.IInventario;
public class ActualizarStockComando implements Comando {
    private final IInventario inventario;
    private final int idProducto;
    private final int cantidad;
    public ActualizarStockComando(IInventario inventario, int idProducto, int cantidad) {
        this.inventario = inventario;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    @Override
    public void ejecutar() {
        System.out.println("[Command] Ejecutando: ActualizarStock");
        boolean ok = inventario.actualizarStock(idProducto, cantidad);
        if (ok) {
            System.out.println("[Command] Stock actualizado correctamente.");
        } else {
            System.out.println("[Command] No se pudo actualizar el stock (denegado o producto inexistente).");
        }
    }
}
