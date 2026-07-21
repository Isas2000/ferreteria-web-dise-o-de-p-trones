package ferreteria.demo.controller;

import ferreteria.demo.Facade.SistemaFacade;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ferreteria.demo.AbiertoCerrado.Cliente;

@Controller
public class LoginController {

    @Autowired
    private SistemaFacade facade;

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @GetMapping("/login/cliente")
    public String mostrarLoginCliente() {
        return "login-cliente";
    }

    @PostMapping("/login/empleado")
    public String loginEmpleado(@RequestParam String usuario,
            @RequestParam String password,
            HttpSession session,
            Model model) {
        ferreteria.demo.Proxy.InventarioProxy proxy = facade.loginEmpleadoWeb(usuario, password);
        if (proxy != null) {
            session.setAttribute("proxy", proxy);
            session.setAttribute("intentos", 0);

            int idGenerado = (int) (System.currentTimeMillis() % 100000);
            ferreteria.demo.AbiertoCerrado.TipoPersona persona = facade.crearEmpleadoFactory(idGenerado, usuario,
                    "Empleado", "N/D", usuario, "Mañana");
            session.setAttribute("rol", "EMPLEADO");
            session.setAttribute("persona", persona);
            return "redirect:/panel";
        } else {
            Integer intentos = (Integer) session.getAttribute("intentos");
            intentos = (intentos == null) ? 1 : intentos + 1;
            session.setAttribute("intentos", intentos);

            String msg = (intentos >= 3)
                    ? "Demasiados intentos fallidos."
                    : "Credenciales incorrectas. Intentos restantes: " + (3 - intentos);
            model.addAttribute("error", msg);
            return "login";
        }
    }

    @GetMapping("/panel")
    public String panel(HttpSession session, Model model) {
        String rol = (String) session.getAttribute("rol");
        ferreteria.demo.Proxy.InventarioProxy proxy = (ferreteria.demo.Proxy.InventarioProxy) session
                .getAttribute("proxy");
        if (rol == null || proxy == null) {
            return "redirect:/login";
        }
        model.addAttribute("rol", rol);
        model.addAttribute("inventario", facade.obtenerInventario(proxy));
        model.addAttribute("alertas", facade.obtenerAlertasStock());
        if ("EMPLEADO".equals(rol)) {
            model.addAttribute("menuItems", facade.obtenerMenuEmpleado());
        }
        return "panel";
    }

    @PostMapping("/registro/cliente")
    public String registrarCliente(@RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String telefono,
            @RequestParam String tipoCliente,
            @RequestParam String direccion,
            HttpSession session) {
        int idGenerado = (int) (System.currentTimeMillis() % 100000);
        ferreteria.demo.AbiertoCerrado.TipoPersona persona = facade.crearClienteFactory(idGenerado, nombre, apellido,
                telefono, tipoCliente, direccion);
        ferreteria.demo.Proxy.InventarioProxy proxy = facade.loginInvitadoWeb();
        session.setAttribute("proxy", proxy);
        session.setAttribute("rol", "CLIENTE");
        session.setAttribute("persona", persona);
        return "redirect:/panel";
    }

    @PostMapping("/login/cliente/buscar")
    public String loginClienteBuscar(@RequestParam String telefono,
            HttpSession session,
            Model model) {
        Cliente cliente = facade.buscarClientePorTelefono(telefono);
        if (cliente == null) {
            model.addAttribute("error", "No encontramos un cliente registrado con ese teléfono. Regístrate primero.");
            return "login-cliente";
        }
        ferreteria.demo.Proxy.InventarioProxy proxy = facade.loginInvitadoWeb();
        session.setAttribute("proxy", proxy);
        session.setAttribute("rol", "CLIENTE");
        session.setAttribute("persona", cliente);
        return "redirect:/panel";
    }

    @GetMapping("/login/cliente/ingresar")
    public String ingresarComoClienteInvitado(HttpSession session) {
        ferreteria.demo.Proxy.InventarioProxy proxy = facade.loginInvitadoWeb();
        session.setAttribute("proxy", proxy);
        session.setAttribute("rol", "CLIENTE");
        return "redirect:/panel";
    }

}