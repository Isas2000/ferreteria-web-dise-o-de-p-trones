package ferreteria.demo.AbiertoCerrado;

import ferreteria.demo.SegregacionInterfaces.AccionesEmpleado;
import ferreteria.demo.SustitucionLiskov.PersonaFerreteria;

public class Empleado extends PersonaFerreteria implements AccionesEmpleado {

    private String codigoEmpleado;
    private String turno;
    private double comision;

    public Empleado(int id, String nombre, String apellido,
            String telefono, String codigoEmpleado, String turno) {
        super(id, nombre, apellido, telefono);
        this.codigoEmpleado = codigoEmpleado;
        this.turno = turno;
        this.comision = 0.0;
    }

    @Override
    public String getTipo() {
        return "Empleado";
    }

    @Override
    public void mostrarBienvenida() {
        System.out.println("Bienvenido, " + getNombreCompleto()
                + " | Código: " + codigoEmpleado
                + " | Turno: " + turno);
    }

    @Override
    public void ejecutarAcciones() {
        gestionarProductos();
        verReportes();
    }

    @Override
    public void registrarVenta() {
        System.out.println("  - Registrando venta...");
    }

    @Override
    public void gestionarProductos() {
        System.out.println("  - Gestionando productos...");
    }

    @Override
    public void verReportes() {
        System.out.println("  - Viendo reportes...");
    }

    @Override
    public void gestionarInventario() {
        System.out.println("  - Revisando inventario...");
    }
}
