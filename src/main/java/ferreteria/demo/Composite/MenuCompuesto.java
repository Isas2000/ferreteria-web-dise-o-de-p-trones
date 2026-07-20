package ferreteria.demo.Composite;

import java.util.ArrayList;
import java.util.List;

public class MenuCompuesto extends ComponenteMenu {
    private List<ComponenteMenu> hijos = new ArrayList<>();

    public MenuCompuesto(String nombre) {
        super(nombre);
    }

    public void agregar(ComponenteMenu componente) {
        hijos.add(componente);
    }

    public void eliminar(ComponenteMenu componente) {
        hijos.remove(componente);
    }

    @Override
    public void mostrar(String indentacion) {
        System.out.println(indentacion + "== " + nombre + " ==");
        for (ComponenteMenu hijo : hijos) {
            hijo.mostrar(indentacion + "  ");
        }
    }

    @Override
    public List<MenuItemView> obtenerVista(int nivel) {
        List<MenuItemView> resultado = new ArrayList<>();
        resultado.add(new MenuItemView(nombre, nivel, -1));
        for (ComponenteMenu hijo : hijos) {
            resultado.addAll(hijo.obtenerVista(nivel + 1));
        }
        return resultado;
    }
}
