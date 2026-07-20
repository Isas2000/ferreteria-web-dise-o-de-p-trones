package ferreteria.demo.Command;
import ferreteria.demo.Proxy.IInventario;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class RegistrarVentaComando implements Comando {
    private final IInventario inventario;
    private final int idProducto;
    private final int cantidad;
    private final String nombreCliente;
    private final LocalDateTime fecha;
    public RegistrarVentaComando(IInventario inventario, int idProducto, int cantidad, String nombreCliente) {
        this.inventario = inventario;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.nombreCliente = nombreCliente;
        this.fecha = LocalDateTime.now();
    }
    @Override
    public void ejecutar() {
        System.out.println("[Command] Venta a: " + nombreCliente);
        inventario.actualizarStock(idProducto, -cantidad);
        System.out.println("[Command] Venta registrada correctamente.");
    }
    public String getResumen() {
        String hora = fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        return hora + " -- Cliente: " + nombreCliente + " -- Producto ID " + idProducto + " x" + cantidad;
    }
}
