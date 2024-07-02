package models;

import java.util.ArrayList;
import java.util.List;

import repositories.ProductosRepoSingleton;

public class Carrito {
    private List<ItemCarrito> items;
    private Usuario usuario; // Usuario asociado al carrito

    public Carrito(Usuario usuario) {
        this.usuario = usuario;
        this.items = new ArrayList<>();
    }

    public void agregarProducto(Producto producto, int cantidad) {
        // Verificar stock suficiente
        if (producto.getStock() >= cantidad) {
            // Verificar saldo suficiente si el usuario es cliente
            if (usuario.getTipo().equals("cliente") && usuario.getSaldo() >= producto.getPrecio() * cantidad) {
                // Agregar producto al carrito
                for (ItemCarrito item : items) {
                    if (item.getProducto().getCodArt() == producto.getCodArt()) {
                        item.setCantidad(item.getCantidad() + cantidad);
                        return;
                    }
                }
                items.add(new ItemCarrito(producto, cantidad));

                // Descuento del saldo del cliente
                usuario.decrementarSaldo(producto.getPrecio() * cantidad);

                // Actualización del stock del producto
                producto.setStock(producto.getStock() - cantidad);
            }
        }
    }

    public void eliminarProducto(int codArt) {
        items.removeIf(item -> item.getProducto().getCodArt() == codArt);
    }

    public void actualizarCantidad(int codArt, int cantidad) {
        for (ItemCarrito item : items) {
            if (item.getProducto().getCodArt() == codArt) {
                // Obtener producto del repositorio para verificar stock
                Producto producto = ProductosRepoSingleton.getInstance().findById(codArt);
                if (producto != null && producto.getStock() >= cantidad) {
                    // Verificar saldo suficiente si el usuario es cliente
                    if (usuario.getTipo().equals("cliente") && usuario.getSaldo() >= producto.getPrecio() * cantidad) {
                        item.setCantidad(cantidad);

                        // Descuento del saldo del cliente
                        usuario.decrementarSaldo(producto.getPrecio() * cantidad);

                        // Actualización del stock del producto
                        producto.setStock(producto.getStock() - cantidad);
                    }
                }
                return;
            }
        }
    }

    public double calcularTotal() {
        return items.stream()
                .mapToDouble(item -> item.getProducto().getPrecio() * item.getCantidad())
                .sum();
    }

    public List<ItemCarrito> getItems() {
        return new ArrayList<>(items);
    }
}

