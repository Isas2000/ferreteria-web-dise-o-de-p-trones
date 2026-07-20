package ferreteria.demo.Builder;

import ferreteria.demo.ResponsabilidadUnica.Productos;


public class ProductoBuilder {

    private int     idProducto;
    private String  nombre;
    private String  marca;
    private double  precio;
    private int     stock;
    private String  categoria;
    private boolean disponibleOverride;
    private boolean usarDisponibleOverride;

    public ProductoBuilder() {
        reset();
    }

    /** Restaura todos los campos a valores por defecto seguros */
    private void reset() {
        idProducto             = 0;
        nombre                 = "Sin nombre";
        marca                  = "Sin marca";
        precio                 = 0.0;
        stock                  = 0;
        categoria              = "General";
        disponibleOverride     = false;
        usarDisponibleOverride = false;
    }

    public ProductoBuilder setIdProducto(int idProducto) {
        if (idProducto < 0) {
            System.out.println("  [Builder] ID inválido: se usará 0");
        } else {
            this.idProducto = idProducto;
        }
        return this;
    }

    public ProductoBuilder setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("  [Builder] Nombre vacío: se usará 'Sin nombre'");
        } else {
            this.nombre = nombre.trim();
        }
        return this;
    }

    public ProductoBuilder setMarca(String marca) {
        if (marca == null || marca.trim().isEmpty()) {
            System.out.println("  [Builder] Marca vacía: se usará 'Sin marca'");
        } else {
            this.marca = marca.trim();
        }
        return this;
    }

    public ProductoBuilder setPrecio(double precio) {
        if (precio < 0) {
            System.out.println("  [Builder] Precio negativo: se usará 0.0");
        } else {
            this.precio = precio;
        }
        return this;
    }

    public ProductoBuilder setStock(int stock) {
        if (stock < 0) {
            System.out.println("  [Builder] Stock negativo: se usará 0");
        } else {
            this.stock = stock;
        }
        return this;
    }

    public ProductoBuilder setCategoria(String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            System.out.println("  [Builder] Categoría vacía: se usará 'General'");
        } else {
            this.categoria = categoria.trim();
        }
        return this;
    }

    public ProductoBuilder setDisponible(boolean disponible) {
        this.disponibleOverride     = disponible;
        this.usarDisponibleOverride = true;
        return this;
    }

    /**
     * Construye el producto y resetea el Builder para la próxima
     * construcción. Un mismo Builder puede reutilizarse N veces.
     */
    public Productos build() {
        System.out.println("  [Builder] Construyendo → " + nombre
                + " | S/." + precio + " | Stock: " + stock);

        Productos p = new Productos(idProducto, nombre, marca, precio, stock, categoria);

        if (usarDisponibleOverride) {
            p.setDisponible(disponibleOverride);
        }

        reset(); // - listo para la próxima construcción
        return p;
    }
}