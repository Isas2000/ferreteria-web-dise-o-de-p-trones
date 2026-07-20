package ferreteria.demo.ResponsabilidadUnica;

public class Productos {

    private int idProducto;
    private String nombre;
    private String marca;
    private double precio;
    private int stock;
    private String categoria;
    private boolean disponible;

    public Productos(int idProducto, String nombre, String marca,
            double precio, int stock, String categoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.disponible = stock > 0;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
        this.disponible = stock > 0;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
