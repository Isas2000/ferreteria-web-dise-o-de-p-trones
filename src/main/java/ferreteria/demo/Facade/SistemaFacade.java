
package ferreteria.demo.Facade;

import ferreteria.demo.AbiertoCerrado.Cliente;

import ferreteria.demo.Observer.AlertaStockBajo;

import ferreteria.demo.FactoryMethod.ClienteFactory;
import ferreteria.demo.FactoryMethod.EmpleadoFactory;
import ferreteria.demo.AbiertoCerrado.TipoPersona;

import ferreteria.demo.Composite.ComponenteMenu;
import ferreteria.demo.Composite.MenuCompuesto;
import ferreteria.demo.Composite.MenuHoja;
import ferreteria.demo.Composite.MenuItemView;


import ferreteria.demo.Command.RegistrarVentaComando;
import ferreteria.demo.Command.ActualizarStockComando;

import ferreteria.demo.Command.Comando;
import ferreteria.demo.Command.InvokerComandos;
import ferreteria.demo.Command.AgregarProductoComando;

import ferreteria.demo.Builder.DirectorProducto;
import ferreteria.demo.Builder.ProductoBuilder;
import ferreteria.demo.Creacional_Singleton.Inventario;
import ferreteria.demo.Creacional_Singleton.SistemaAutenticacion;
import ferreteria.demo.Creacional_Singleton.InicializadorInventario;
import ferreteria.demo.InversionDependencias.GestorProductos;
import ferreteria.demo.InversionDependencias.ListaProductos;
import ferreteria.demo.ResponsabilidadUnica.Productos;

/**
 * PATRÓN FACADE — FerreteriaSistemaFacade
 *
 * Subsistemas que coordina:
 *  - SistemaAutenticacion  (Singleton)
 *  - InicializadorInventario
 *  - Inventario             (Singleton)
 *  - ProductoBuilder + DirectorProducto  (Builder)
 *  - GestorProductos + ListaProductos    (DIP)
 */
public class SistemaFacade {

    private final SistemaAutenticacion auth;
    private final Inventario           inventario;
    private final DirectorProducto     director;
    private final InvokerComandos invoker;
    private final java.util.List<Cliente> clientesRegistrados = new java.util.ArrayList<>();

    public SistemaFacade() {
        this.auth       = SistemaAutenticacion.getInstancia();
        this.inventario = Inventario.getInstancia();
        this.director   = new DirectorProducto(new ProductoBuilder());
        this.invoker = new InvokerComandos();
    }

    // ── AUTENTICACIÓN ──────────────────────────────────────────────────
    public void iniciarSesion(int opcion) {
        System.out.println("\n[Facade] Iniciando sesión...");
        auth.procesarIngreso(opcion);
    }

    public void inicializarSistema() {
        System.out.println("[Facade] Cargando inventario inicial...");
        InicializadorInventario.cargar();
        System.out.println("[Facade] Sistema listo.\n");
    }

    public void cerrarSesion() {
        System.out.println("[Facade] Cerrando sesión...");
        auth.cerrarSesion();
    }

    // ── INVENTARIO ────────────────────────────────────────────────────
    public void verInventario() {
        inventario.mostrarInventario();
    }

    public void agregarProducto(int id, String nombre, String marca,
                                double precio, int stock, String categoria) {
        System.out.println("\n[Facade] Registrando producto con Builder...");
        Productos nuevo = director.construirProductoCompleto(
                id, nombre, marca, precio, stock, categoria);
        inventario.agregarProducto(nuevo);
    }


    public void buscarProducto(int id) {
        ListaProductos lista = new ListaProductos();
        inventario.getProductos().forEach(lista::agregar);
        GestorProductos gestor = new GestorProductos(lista);
        System.out.println("\n[Facade] Buscando producto ID " + id + "...");
        gestor.mostrarProducto(id);
    }

    public void actualizarStock(int id, int cantidad) {
        System.out.println("[Facade] Actualizando stock...");
        inventario.actualizarStock(id, cantidad);
    }

    public void registrarVentaComando(int idProducto, int cantidad, String nombreCliente) {
    Comando cmd = new RegistrarVentaComando(auth.getProxy(), idProducto, cantidad, nombreCliente);
    invoker.ejecutar(cmd);
    }

    public void actualizarStockComando(int idProducto, int cantidad) {
    Comando cmd = new ActualizarStockComando(auth.getProxy(), idProducto, cantidad);
    invoker.ejecutar(cmd);
    }

    // ── PROMOCIONES ───────────────────────────────────────────────────
    public void mostrarPromociones() {
        System.out.println("\n── Promociones del día ─────────────────");
        System.out.println("  - 10% de descuento en herramientas eléctricas");
        System.out.println("  - 2x1 en cinta aislante");
        System.out.println("  - Envío gratis en compras mayores a S/.150");
    }
    
    // ── MÉTODOS WEB (sin Scanner, sin System.exit) ──────────────────────
    public boolean loginEmpleadoWeb(String usuario, String password) {
        return auth.loginEmpleadoWeb(usuario, password);
    }

    public void loginInvitadoWeb() {
        auth.iniciarComoInvitadoWeb();
    }

    public int getIntentosRestantes() {
        return auth.getIntentosRestantes();
    }
    

    public java.util.List<Productos> obtenerInventario() {
    return auth.getProxy().obtenerProductos();
    }

    public java.util.List<String> obtenerHistorialVentas() {
    return invoker.getHistorial().stream()
            .filter(c -> c instanceof RegistrarVentaComando)
            .map(c -> ((RegistrarVentaComando) c).getResumen())
            .toList();
    }

public void agregarProductoComando(int id, String nombre, String marca,
                                    double precio, int stock, String categoria) {
    Productos nuevo = director.construirProductoCompleto(id, nombre, marca, precio, stock, categoria);
    Comando cmd = new AgregarProductoComando(auth.getProxy(), nuevo);
    invoker.ejecutar(cmd);
}

public java.util.List<MenuItemView> obtenerMenuEmpleado() {
    MenuCompuesto raiz = new MenuCompuesto("Panel Empleado");

    MenuCompuesto grupoInventario = new MenuCompuesto("Inventario");
    grupoInventario.agregar(new MenuHoja(1, "Agregar producto"));
    grupoInventario.agregar(new MenuHoja(2, "Actualizar stock"));

    MenuCompuesto grupoVentas = new MenuCompuesto("Ventas");
    grupoVentas.agregar(new MenuHoja(3, "Registrar venta"));
    grupoVentas.agregar(new MenuHoja(4, "Ver historial"));

    raiz.agregar(grupoInventario);
    raiz.agregar(grupoVentas);

    return raiz.obtenerVista(0);
}


public TipoPersona crearClienteFactory(int id, String nombre, String apellido,
                                        String telefono, String tipoCliente, String direccion) {
    ClienteFactory factory = new ClienteFactory(id, nombre, apellido, telefono, tipoCliente, direccion);
    TipoPersona persona = factory.crearPersona();
    clientesRegistrados.add((Cliente) persona);
    return persona;
}


public TipoPersona crearEmpleadoFactory(int id, String nombre, String apellido,
                                         String telefono, String codigoEmpleado, String turno) {
    EmpleadoFactory factory = new EmpleadoFactory(id, nombre, apellido, telefono, codigoEmpleado, turno);
    return factory.crearPersona();
}

public java.util.List<String> obtenerAlertasStock() {
    return AlertaStockBajo.getAlertas();
}

public Cliente buscarClientePorTelefono(String telefono) {
    for (Cliente c : clientesRegistrados) {
        if (c.getTelefono().equals(telefono)) {
            return c;
        }
    }
    return null;
}

}