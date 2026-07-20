package ferreteria.demo.Creacional_Singleton;

import ferreteria.demo.Observer.GestorNotificaciones;
import ferreteria.demo.Observer.AlertaStockBajo;
import ferreteria.demo.Observer.NotificadorVentas;
import ferreteria.demo.ResponsabilidadUnica.Productos;
import java.util.ArrayList;
import java.util.List;

public class Inventario {

    private static Inventario instancia;
    private List<Productos> productos;
    private int stockMinimo;
    private GestorNotificaciones gestorNotificaciones;

    private Inventario() {
        this.productos = new ArrayList<>();
        this.stockMinimo = 5;
        this.gestorNotificaciones = new GestorNotificaciones();
        gestorNotificaciones.suscribir(new AlertaStockBajo());
        gestorNotificaciones.suscribir(new NotificadorVentas());
    }

    public static Inventario getInstancia() {
        if (instancia == null) {
            instancia = new Inventario();
        }
        return instancia;
    }

    public void agregarProducto(Productos p) {
        productos.add(p);
        System.out.println("Inventario: producto agregado -> " + p.getNombre());
    }

    public void actualizarStock(int idProducto, int cantidad) {
        for (Productos p : productos) {
            if (p.getIdProducto() == idProducto) {
                p.setStock(p.getStock() + cantidad);
                System.out.println("Stock actualizado: " + p.getNombre()
                        + " → " + p.getStock() + " unidades");
                verificarStockMinimo(p);
                return;
            }
        }
        System.out.println("Producto no encontrado en inventario.");
    }

    public void verificarStockMinimo(Productos p) {
        if (p.getStock() < stockMinimo) {
            gestorNotificaciones.notificar(p.getNombre(), p.getStock());
        }
    }

    public void notificarEvento(String evento, int codigo) {
        gestorNotificaciones.notificar(evento, codigo);
    }

    public void mostrarInventario() {
        if (productos.isEmpty()) {
            System.out.println("Inventario vacío.");
            return;
        }
        System.out.println("\n== INVENTARIO ===============");
        for (Productos p : productos) {
            System.out.println("[" + p.getIdProducto() + "] "
                    + p.getNombre()
                    + " | Stock: " + p.getStock()
                    + " | Precio: S/. " + p.getPrecio()
                    + (p.getStock() < stockMinimo ? "   BAJO" : ""));
        }
        System.out.println("===============================");
    }

    public List<Productos> getProductos() {
        return productos;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int minimo) {
        this.stockMinimo = minimo;
    }
}