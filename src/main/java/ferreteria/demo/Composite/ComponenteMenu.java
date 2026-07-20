package ferreteria.demo.Composite;

import java.util.List;

public abstract class ComponenteMenu {
    protected String nombre;

    public ComponenteMenu(String nombre) {
        this.nombre = nombre;
    }

    public abstract void mostrar(String indentacion);

    public abstract List<MenuItemView> obtenerVista(int nivel);
}
