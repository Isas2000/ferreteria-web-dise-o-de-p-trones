package ferreteria.demo.Observer;

public class AlertaSesion implements ObservadorInventario {

    @Override
    public void actualizar(String evento, int dato) {
        System.out.println("[SESION - Observer] Evento registrado: "
                + evento + " | Código: " + dato);
    }
}