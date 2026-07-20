package ferreteria.demo.Proxy;
import ferreteria.demo.ResponsabilidadUnica.Productos;
import java.util.List;
public interface IInventario {
    void agregarProducto(Productos p);
    void actualizarStock(int idProducto, int cantidad);
    void mostrarInventario();
    List<Productos> obtenerProductos();
}
