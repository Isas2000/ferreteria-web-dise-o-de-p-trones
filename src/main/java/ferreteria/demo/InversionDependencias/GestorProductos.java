package ferreteria.demo.InversionDependencias;

import ferreteria.demo.ResponsabilidadUnica.Productos;
import java.util.List;

public class GestorProductos {

    private final RepositorioProductos repositorio;  // ← depende de la interfaz

    // Se inyecta desde afuera - Inyección de dependencias
    public GestorProductos(RepositorioProductos repositorio) {
        this.repositorio = repositorio;
    }

    public void agregarProducto(Productos producto) {
        repositorio.agregar(producto);
    }

    public void eliminarProducto(int id) {
        repositorio.eliminar(id);
    }

    public void mostrarProducto(int id) {
        Productos p = repositorio.buscar(id);
        if (p != null) {
            System.out.println("======================");
            System.out.println("ID       : " + p.getIdProducto());
            System.out.println("Nombre   : " + p.getNombre());
            System.out.println("Marca    : " + p.getMarca());
            System.out.println("Precio   : S/. " + p.getPrecio());
            System.out.println("Stock    : " + p.getStock());
            System.out.println("Categoría: " + p.getCategoria());
            System.out.println("Disponible: " + (p.isDisponible() ? "Sí" : "No"));
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public void mostrarTodos() {
        List<Productos> todos = repositorio.listar();
        if (todos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        System.out.println("========================");
        for (Productos p : todos) {
            System.out.println("[" + p.getIdProducto() + "] "
                    + p.getNombre() + " - S/. " + p.getPrecio()
                    + " | Stock: " + p.getStock());
        }
    }
}
