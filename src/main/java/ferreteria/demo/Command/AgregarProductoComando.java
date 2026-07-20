package ferreteria.demo.Command;
import ferreteria.demo.Proxy.IInventario;
import ferreteria.demo.ResponsabilidadUnica.Productos;
public class AgregarProductoComando implements Comando {
    private final IInventario inventario;
    private final Productos producto;
    public AgregarProductoComando(IInventario inventario, Productos producto) {
        this.inventario = inventario;
        this.producto = producto;
    }
    @Override
    public void ejecutar() {
        System.out.println("[Command] Ejecutando: AgregarProducto");
        inventario.agregarProducto(producto);
    }
}
