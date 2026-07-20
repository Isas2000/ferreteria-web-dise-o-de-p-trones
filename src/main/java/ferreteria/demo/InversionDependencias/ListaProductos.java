package ferreteria.demo.InversionDependencias;

import ferreteria.demo.ResponsabilidadUnica.Productos;
import java.util.ArrayList;
import java.util.List;

public class ListaProductos implements RepositorioProductos {

    private final List<Productos> lista = new ArrayList<>();

    @Override
    public void agregar(Productos producto) {
        lista.add(producto);
        System.out.println("Producto agregado: " + producto.getNombre());
    }

    @Override
    public void eliminar(int idProducto) {
        lista.removeIf(p -> p.getIdProducto() == idProducto);
        System.out.println("Producto eliminado. ID: " + idProducto);
    }

    @Override
    public Productos buscar(int idProducto) {
        return lista.stream()
                .filter(p -> p.getIdProducto() == idProducto)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Productos> listar() {
        return lista;
    }
}
