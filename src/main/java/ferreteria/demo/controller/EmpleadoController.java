package ferreteria.demo.controller;

import ferreteria.demo.Facade.SistemaFacade;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmpleadoController {

    @Autowired
    private SistemaFacade facade;

    @GetMapping("/panel/agregar")
    public String mostrarFormAgregar(HttpSession session, org.springframework.ui.Model model) {
        if (!"EMPLEADO".equals(session.getAttribute("rol"))) {
            return "redirect:/panel";
        }
        ferreteria.demo.Proxy.InventarioProxy proxy = (ferreteria.demo.Proxy.InventarioProxy) session
                .getAttribute("proxy");
        java.util.List<String> categorias = facade.obtenerInventario(proxy).stream()
                .map(ferreteria.demo.ResponsabilidadUnica.Productos::getCategoria)
                .distinct()
                .sorted()
                .toList();
        model.addAttribute("categorias", categorias);
        return "agregar-producto";
    }

    @PostMapping("/panel/agregar")
    public String agregarProducto(HttpSession session,
            @RequestParam int id,
            @RequestParam String nombre,
            @RequestParam String marca,
            @RequestParam double precio,
            @RequestParam int stock,
            @RequestParam String categoria) {
        if (!"EMPLEADO".equals(session.getAttribute("rol"))) {
            return "redirect:/panel";
        }
    ferreteria.demo.Proxy.InventarioProxy proxy = (ferreteria.demo.Proxy.InventarioProxy) session
                .getAttribute("proxy");
        facade.agregarProductoComando(proxy, id, nombre, marca, precio, stock, categoria);
        return "redirect:/panel";
    }

    @GetMapping("/panel/venta")
    public String mostrarFormVenta(HttpSession session, org.springframework.ui.Model model) {
        if (!"EMPLEADO".equals(session.getAttribute("rol"))) {
            return "redirect:/panel";
        }
        ferreteria.demo.Proxy.InventarioProxy proxy = (ferreteria.demo.Proxy.InventarioProxy) session
                .getAttribute("proxy");
        model.addAttribute("inventario", facade.obtenerInventario(proxy));
        return "registrar-venta";
    }

    @PostMapping("/panel/venta")
    public String registrarVenta(HttpSession session,
            @RequestParam int idProducto,
            @RequestParam int cantidad,
            @RequestParam String nombreCliente,
            @RequestParam String dni,
            org.springframework.ui.Model model) {
        if (!"EMPLEADO".equals(session.getAttribute("rol"))) {
            return "redirect:/panel";
        }
        ferreteria.demo.Proxy.InventarioProxy proxy = (ferreteria.demo.Proxy.InventarioProxy) session
                .getAttribute("proxy");

        ferreteria.demo.ResponsabilidadUnica.Productos producto = facade.obtenerInventario(proxy).stream()
                .filter(p -> p.getIdProducto() == idProducto)
                .findFirst()
                .orElse(null);

        // NUEVO: cortar si el producto no existe o no hay stock/DNI válido
        if (producto == null) {
            return "redirect:/panel/venta?error=producto";
        }
        if (cantidad <= 0 || cantidad > producto.getStock()) {
            return "redirect:/panel/venta?error=stock";
        }
        if (dni == null || !dni.matches("[0-9]{8}")) {
            return "redirect:/panel/venta?error=dni";
        }

        facade.registrarVentaComando(proxy, idProducto, cantidad, nombreCliente, dni);

        double precioUnitario = producto.getPrecio();
        model.addAttribute("nombreCliente", nombreCliente);
        model.addAttribute("dni", dni);
        model.addAttribute("fecha", java.time.LocalDateTime.now());
        model.addAttribute("nombreProducto", producto.getNombre());
        model.addAttribute("cantidad", cantidad);
        model.addAttribute("precioUnitario", precioUnitario);
        model.addAttribute("total", precioUnitario * cantidad);

        return "boleta";
    }

    @GetMapping("/panel/stock")
    public String mostrarFormStock(HttpSession session, org.springframework.ui.Model model) {
        if (!"EMPLEADO".equals(session.getAttribute("rol"))) {
            return "redirect:/panel";
        }
        ferreteria.demo.Proxy.InventarioProxy proxy = (ferreteria.demo.Proxy.InventarioProxy) session
                .getAttribute("proxy");
        model.addAttribute("inventario", facade.obtenerInventario(proxy));
        return "actualizar-stock";
    }

    @PostMapping("/panel/stock")
    public String actualizarStock(HttpSession session,
            @RequestParam int idProducto,
            @RequestParam int cantidad) {
        if (!"EMPLEADO".equals(session.getAttribute("rol"))) {
            return "redirect:/panel";
        }
        ferreteria.demo.Proxy.InventarioProxy proxy = (ferreteria.demo.Proxy.InventarioProxy) session
                .getAttribute("proxy");
        facade.actualizarStockComando(proxy, idProducto, cantidad);
        return "redirect:/panel";
    }

    @GetMapping("/panel/historial-ventas")
    public String verHistorialVentas(HttpSession session, org.springframework.ui.Model model) {
        if (!"EMPLEADO".equals(session.getAttribute("rol"))) {
            return "redirect:/panel";
        }
        model.addAttribute("ventas", facade.obtenerHistorialVentas());
        return "historial-ventas";
    }

    @GetMapping("/panel/menu")
    public String mostrarMenu(HttpSession session, org.springframework.ui.Model model) {
        if (!"EMPLEADO".equals(session.getAttribute("rol"))) {
            return "redirect:/panel";
        }
        model.addAttribute("items", facade.obtenerMenuEmpleado());
        return "menu-empleado";
    }

}