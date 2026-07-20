package ferreteria.demo.Creacional_Singleton;
import ferreteria.demo.Builder.DirectorProducto;
import ferreteria.demo.Builder.ProductoBuilder;
public class InicializadorInventario {

    public static void cargar() {
        Inventario inv      = Inventario.getInstancia();
        DirectorProducto dir = new DirectorProducto(new ProductoBuilder());

        System.out.println("\n== Cargando inventario inicial =======");

        inv.agregarProducto(dir.construirProductoCompleto(
                1, "Martillo Carpintero", "Stanley", 35.90, 20, "Herramientas"));

        inv.agregarProducto(dir.construirProductoCompleto(
                2, "Clavos 2 pulgadas", "Fixman", 8.50, 200, "Ferretería"));

        inv.agregarProducto(dir.construirProductoCompleto(
                3, "Taladro Percutor", "Bosch", 289.00, 4, "Herramientas eléctricas"));
        // stock=4 < stockMinimo(5) → el Inventario lanzará alerta automáticamente

        inv.agregarProducto(dir.construirProductoAgotado(
                4, "Pintura Látex Blanca", "Tekno", 55.00, "Pinturas"));
        // producto reservado: sin stock y marcado no disponible

        inv.agregarProducto(dir.construirProductoCompleto(
                5, "Cinta Aislante", "3M", 4.20, 50, "Electricidad"));

        inv.agregarProducto(dir.construirProductoCompleto(
                6, "Llave Inglesa 12\"", "Tramontina", 28.00, 15, "Herramientas"));

        inv.agregarProducto(dir.construirProductoCompleto(
                7, "Cemento Portland 42.5kg", "Sol", 32.50, 80, "Construcción"));

        System.out.println("===================================\n");
    }
}