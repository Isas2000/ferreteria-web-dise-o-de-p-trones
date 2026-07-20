package ferreteria.demo.SustitucionLiskov;

import ferreteria.demo.AbiertoCerrado.TipoPersona;

public abstract class PersonaFerreteria extends TipoPersona {

    protected int id;
    protected String nombre;
    protected String apellido;
    protected String telefono;

    public PersonaFerreteria(int id, String nombre,
            String apellido, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}
