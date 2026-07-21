package ferreteria.demo.Proxy;
import ferreteria.demo.ResponsabilidadUnica.Productos;
import java.util.List;
public class InventarioProxy implements IInventario {
    private final InventarioReal inventarioReal;
    private final String rolUsuario;
    public InventarioProxy(String rolUsuario) {
        this.inventarioReal = new InventarioReal();
        this.rolUsuario = rolUsuario;
    }
    @Override
    public void agregarProducto(Productos p) {
        if (rolUsuario.equals("EMPLEADO")) {
            System.out.println("[Proxy] Acceso autorizado para agregar producto.");
            inventarioReal.agregarProducto(p);
        } else {
            System.out.println("[Proxy] Acceso denegado. Solo empleados pueden agregar productos.");
        }
    }

    @Override
    public boolean actualizarStock(int idProducto, int cantidad) {
        if (rolUsuario.equals("EMPLEADO")) {
            System.out.println("[Proxy] Acceso autorizado para actualizar stock.");
            return inventarioReal.actualizarStock(idProducto, cantidad);
        } else {
            System.out.println("[Proxy] Acceso denegado. Solo empleados pueden actualizar stock.");
            return false;
        }
    }

    @Override
    public void mostrarInventario() {
        System.out.println("[Proxy] Acceso permitido para ver inventario.");
        inventarioReal.mostrarInventario();
    }
    @Override
    public List<Productos> obtenerProductos() {
        System.out.println("[Proxy] Rol '" + rolUsuario + "' consultando inventario.");
        return inventarioReal.obtenerProductos();
    }
}
