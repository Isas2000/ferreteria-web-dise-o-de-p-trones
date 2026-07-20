package ferreteria.demo.Creacional_Singleton;

import ferreteria.demo.Proxy.InventarioProxy;
import java.util.Scanner;

public class SistemaAutenticacion {

    private static SistemaAutenticacion instancia;
    private static Scanner sc = new Scanner(System.in);
    private int contadorIntentos;
    private InventarioProxy proxy;

    private SistemaAutenticacion() {
        this.contadorIntentos = 0;
    }

    public static SistemaAutenticacion getInstancia() {
        if (instancia == null) {
            instancia = new SistemaAutenticacion();
        }
        return instancia;
    }

    public void procesarIngreso(int opcion) {
        if (opcion == 1) {
            loginEmpleado();
        } else if (opcion == 2) {
            iniciarComoInvitado();
        } else {
            System.out.println("Opcion invalida. Cerrando.");
            sc.close();
            System.exit(0);
        }
    }

    private void loginEmpleado() {
        boolean acceso = false;
        while (contadorIntentos < 3) {
            System.out.print("\nUsuario: ");
            String user = sc.nextLine();
            System.out.print("Contraseña: ");
            String pass = sc.nextLine();

            if (validarCredenciales(user, pass)) {
                acceso = true;
                proxy = new InventarioProxy("EMPLEADO");
                System.out.println("[Proxy] Proxy de inventario creado con rol EMPLEADO.");
                System.out.println("Bienvenido, " + user + ".");
                break;
            } else {
                contadorIntentos++;
                int restantes = 3 - contadorIntentos;
                if (restantes > 0) {
                    System.out.println("Credenciales incorrectas. Intentos restantes: " + restantes);
                }
            }
        }

        if (!acceso) {
            System.out.println("\nFallo al iniciar sesión.");
            sc.close();
            System.exit(0);
        }
    }

    private boolean validarCredenciales(String username, String password) {
        return username.equals("admin") && password.equals("1234");
    }

    private void iniciarComoInvitado() {
        proxy = new InventarioProxy("CLIENTE");
        System.out.println("[Proxy] Proxy de inventario creado con rol CLIENTE.");
        System.out.println("Bienvenido, cliente invitado.");
    }

    public InventarioProxy getProxy() {
        return proxy;
    }

    public void cerrarSesion() {
        System.out.println("Sesión cerrada.");
    }

// NUEVO — para uso desde el Controller web
public boolean loginEmpleadoWeb(String usuario, String password) {
    if (validarCredenciales(usuario, password)) {
        contadorIntentos = 0;
        proxy = new InventarioProxy("EMPLEADO");
        return true;
    } else {
        contadorIntentos++;
        return false;
    }
}

public int getIntentosRestantes() {
    return 3 - contadorIntentos;
}

public void iniciarComoInvitadoWeb() {
    proxy = new InventarioProxy("CLIENTE");
}
}