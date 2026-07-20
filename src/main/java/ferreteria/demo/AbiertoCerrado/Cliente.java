package ferreteria.demo.AbiertoCerrado;

import ferreteria.demo.SegregacionInterfaces.AccionesCliente;
import ferreteria.demo.SustitucionLiskov.PersonaFerreteria;

public class Cliente extends PersonaFerreteria implements AccionesCliente {

    private String tipoCliente;
    private int puntosAcumulados;
    private String direccion;

    public Cliente(int id, String nombre, String apellido,
            String telefono, String tipoCliente, String direccion) {
        super(id, nombre, apellido, telefono);
        this.tipoCliente = tipoCliente;
        this.puntosAcumulados = 0;
        this.direccion = direccion;
    }

    @Override
    public String getTipo() {
        return "Cliente";
    }

    @Override
    public void mostrarBienvenida() {
        System.out.println("Bienvenido, " + getNombreCompleto()
                + " [" + tipoCliente + "]");
    }

    @Override
    public void ejecutarAcciones() {
        verProductos();
        verPromociones();
    }

    @Override
    public void verProductos() {
        System.out.println("  - Viendo catálogo...");
    }

    @Override
    public void agregarAlCarrito() {
        System.out.println("  - Producto al carrito.");
    }

    @Override
    public void verPromociones() {
        System.out.println("  - Promociones del día.");
    }

    @Override
    public void realizarCompra() {
        System.out.println("  - Procesando compra...");
    }
}
