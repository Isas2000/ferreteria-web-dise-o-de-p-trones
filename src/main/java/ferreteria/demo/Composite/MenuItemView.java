package ferreteria.demo.Composite;

public class MenuItemView {
    private final String nombre;
    private final int nivel;
    private final int opcion;

    public MenuItemView(String nombre, int nivel, int opcion) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.opcion = opcion;
    }

    public String getNombre() { return nombre; }
    public int getNivel() { return nivel; }
    public int getOpcion() { return opcion; }
    public boolean isGrupo() { return opcion == -1; }

    public String getEstiloIndent() {
        double rem = 1.5 + (nivel * 1.2);
        return "padding-left: " + rem + "rem";
    }
}
