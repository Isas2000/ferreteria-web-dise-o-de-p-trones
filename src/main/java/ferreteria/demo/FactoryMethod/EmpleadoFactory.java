package ferreteria.demo.FactoryMethod;

import ferreteria.demo.AbiertoCerrado.Empleado;
import ferreteria.demo.AbiertoCerrado.TipoPersona;

public class EmpleadoFactory extends PersonaFactory {

    private final int    id;
    private final String nombre;
    private final String apellido;
    private final String telefono;
    private final String codigoEmpleado;
    private final String turno;

    public EmpleadoFactory(int id, String nombre, String apellido,
            String telefono, String codigoEmpleado, String turno) {
        this.id             = id;
        this.nombre         = nombre;
        this.apellido       = apellido;
        this.telefono       = telefono;
        this.codigoEmpleado = codigoEmpleado;
        this.turno          = turno;
    }

    @Override
    public TipoPersona crearPersona() {
        System.out.println("  [Factory] EmpleadoFactory: creando Empleado...");
        return new Empleado(id, nombre, apellido, telefono, codigoEmpleado, turno);
    }
}