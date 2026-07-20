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
        inventario.actualizarStock(idProducto, cantidad);
    }
}
