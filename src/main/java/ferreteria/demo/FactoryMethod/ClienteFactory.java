package ferreteria.demo.FactoryMethod;
import ferreteria.demo.AbiertoCerrado.Cliente;
import ferreteria.demo.AbiertoCerrado.TipoPersona;

public class ClienteFactory extends PersonaFactory {

    private final int    id;
    private final String nombre;
    private final String apellido;
    private final String telefono;
    private final String tipoCliente;   
    private final String direccion;

    public ClienteFactory(int id, String nombre, String apellido,
            String telefono, String tipoCliente, String direccion) { 
        this.id          = id;
        this.nombre      = nombre;
        this.apellido    = apellido;
        this.telefono    = telefono;
        this.tipoCliente = tipoCliente; 
        this.direccion   = direccion;
    }

    @Override
    public TipoPersona crearPersona() {
        System.out.println("  [Factory] ClienteFactory: creando Cliente...");
        return new Cliente(id, nombre, apellido, telefono, tipoCliente, direccion);
    }
}