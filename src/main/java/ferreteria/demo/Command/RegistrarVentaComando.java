package ferreteria.demo.Command;
import ferreteria.demo.Proxy.IInventario;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistrarVentaComando implements Comando {
    private final IInventario inventario;
    private final int idProducto;
    private final int cantidad;
    private final String nombreCliente;
    private final String dni;
    private final LocalDateTime fecha;

    public RegistrarVentaComando(IInventario inventario, int idProducto, int cantidad, String nombreCliente, String dni) {
        this.inventario = inventario;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.nombreCliente = nombreCliente;
        this.dni = dni;
        this.fecha = LocalDateTime.now();
    }

    @Override
    public void ejecutar() {
        boolean ok = inventario.actualizarStock(idProducto, -cantidad);
        if (ok) {
            System.out.println("[Command] Venta a: " + nombreCliente + " (DNI: " + dni + ") registrada correctamente.");
        } else {
            System.out.println("[Command] Venta rechazada: no se pudo actualizar el stock.");
        }
    }

    public String getResumen() {
        String hora = fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        return hora + " -- Cliente: " + nombreCliente + " (DNI: " + dni + ") -- Producto ID " + idProducto + " x" + cantidad;
    }
}
