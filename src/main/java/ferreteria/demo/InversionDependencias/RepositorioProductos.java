package ferreteria.demo.InversionDependencias;

import ferreteria.demo.ResponsabilidadUnica.Productos;
import java.util.List;

public interface RepositorioProductos {

    void agregar(Productos producto);
    void eliminar(int idProducto);
    Productos buscar(int idProducto);

    List<Productos> listar();
}
