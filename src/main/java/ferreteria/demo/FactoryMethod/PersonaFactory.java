package ferreteria.demo.FactoryMethod;

import ferreteria.demo.AbiertoCerrado.TipoPersona;

public abstract class PersonaFactory {
    public abstract TipoPersona crearPersona();
      public void registrarPersona() {
        TipoPersona persona = crearPersona();   
        System.out.println("\n  [Factory] Tipo creado: " + persona.getTipo());
        persona.mostrarBienvenida();
        persona.ejecutarAcciones();
    }
}

