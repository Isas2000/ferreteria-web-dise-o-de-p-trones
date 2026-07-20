package ferreteria.demo.Proxy;
import ferreteria.demo.Creacional_Singleton.Inventario;
import ferreteria.demo.ResponsabilidadUnica.Productos;
import java.util.List;
public class InventarioReal implements IInventario {
    private final Inventario inventario;
    public InventarioReal() {
        this.inventario = Inventario.getInstancia();
    }
    @Override
    public void agregarProducto(Productos p) {
        inventario.agregarProducto(p);
    }
    @Override
    public void actualizarStock(int idProducto, int cantidad) {
        inventario.actualizarStock(idProducto, cantidad);
    }
    @Override
    public void mostrarInventario() {
        inventario.mostrarInventario();
    }
    @Override
    public List<Productos> obtenerProductos() {
        return inventario.getProductos();
    }
}
