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
    java.util.List<String> categorias = facade.obtenerInventario().stream()
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
        facade.agregarProductoComando(id, nombre, marca, precio, stock, categoria);
        return "redirect:/panel";
    }

    @GetMapping("/panel/venta")
public String mostrarFormVenta(HttpSession session, org.springframework.ui.Model model) {
    if (!"EMPLEADO".equals(session.getAttribute("rol"))) {
        return "redirect:/panel";
    }
    model.addAttribute("inventario", facade.obtenerInventario());
    return "registrar-venta";
}

@PostMapping("/panel/venta")
public String registrarVenta(HttpSession session,
                              @RequestParam int idProducto,
                              @RequestParam int cantidad,
                              @RequestParam String nombreCliente) {
    if (!"EMPLEADO".equals(session.getAttribute("rol"))) {
        return "redirect:/panel";
    }
    facade.registrarVentaComando(idProducto, cantidad, nombreCliente);
    return "redirect:/panel";
}


@GetMapping("/panel/stock")
public String mostrarFormStock(HttpSession session, org.springframework.ui.Model model) {
    if (!"EMPLEADO".equals(session.getAttribute("rol"))) {
        return "redirect:/panel";
    }
    model.addAttribute("inventario", facade.obtenerInventario());
    return "actualizar-stock";
}

@PostMapping("/panel/stock")
public String actualizarStock(HttpSession session,
                               @RequestParam int idProducto,
                               @RequestParam int cantidad) {
    if (!"EMPLEADO".equals(session.getAttribute("rol"))) {
        return "redirect:/panel";
    }
    facade.actualizarStockComando(idProducto, cantidad);
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