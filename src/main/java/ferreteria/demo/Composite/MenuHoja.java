package ferreteria.demo.Composite;

import java.util.List;

public class MenuHoja extends ComponenteMenu {
    private int opcion;

    public MenuHoja(int opcion, String nombre) {
        super(nombre);
        this.opcion = opcion;
    }

    @Override
    public void mostrar(String indentacion) {
        System.out.println(indentacion + opcion + ". " + nombre);
    }

    @Override
    public List<MenuItemView> obtenerVista(int nivel) {
        return List.of(new MenuItemView(nombre, nivel, opcion));
    }
}
