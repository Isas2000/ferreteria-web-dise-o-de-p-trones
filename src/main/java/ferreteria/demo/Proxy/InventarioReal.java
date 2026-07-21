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
    public boolean actualizarStock(int idProducto, int cantidad) {
        boolean existe = inventario.getProductos().stream()
                .anyMatch(p -> p.getIdProducto() == idProducto);
        if (!existe) {
            System.out.println("[InventarioReal] Producto ID " + idProducto + " no encontrado.");
            return false;
        }
        inventario.actualizarStock(idProducto, cantidad);
        return true;
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
