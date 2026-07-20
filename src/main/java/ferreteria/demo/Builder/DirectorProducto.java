package ferreteria.demo.Builder;

import ferreteria.demo.ResponsabilidadUnica.Productos;


public class DirectorProducto {

    private final ProductoBuilder builder;

    public DirectorProducto(ProductoBuilder builder) {
        this.builder = builder;
    }

    /** Producto completo con todos los campos */
    public Productos construirProductoCompleto(
            int id, String nombre, String marca,
            double precio, int stock, String categoria) {

        return builder
                .setIdProducto(id)
                .setNombre(nombre)
                .setMarca(marca)
                .setPrecio(precio)
                .setStock(stock)
                .setCategoria(categoria)
                .build();
    }

    /** Producto mínimo: solo nombre y precio, el resto queda por defecto */
    public Productos construirProductoMinimo(String nombre, double precio) {
        return builder
                .setNombre(nombre)
                .setPrecio(precio)
                .build();
    }

    /**
     * Producto agotado: se fuerza disponible=false aunque el stock
     * sea > 0 (por ejemplo, producto reservado o en cuarentena).
     */
    public Productos construirProductoAgotado(
            int id, String nombre, String marca,
            double precio, String categoria) {

        return builder
                .setIdProducto(id)
                .setNombre(nombre)
                .setMarca(marca)
                .setPrecio(precio)
                .setStock(0)
                .setCategoria(categoria)
                .setDisponible(false)
                .build();
    }
}