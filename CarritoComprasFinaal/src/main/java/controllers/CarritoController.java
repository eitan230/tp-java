package controllers;

import models.*;
import repositories.*;
import repositories.interfaces.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/carrito")
public class CarritoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProductoRepo productosRepo;
    private UsuariosRepoSingleton usuarioRepo;

    public void init() {
        this.productosRepo = ProductosRepoSingleton.getInstance();
        this.usuarioRepo = UsuariosRepoSingleton.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		String accion = request.getParameter("accion");
		accion = Optional.ofNullable(accion).orElse("cliente");
    	
		//String accion = request.getParameter("accion");
        // accion = accion == null ? "ver" : accion;

        switch (accion) {
        	case "cliente":
        		getClienteProductos(request, response);
	            break;
            case "ver":
                verCarrito(request, response);
                break;
            case "agregar":
                agregarProducto(request, response);
                break;
            case "eliminar":
                eliminarProducto(request, response);
                break;
            case "actualizar":
                actualizarCantidad(request, response);
                break;
            case "comprar":
                comprarCarrito(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
    
    private void getClienteProductos(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<Producto> listProduc = productosRepo.getAll();
        request.setAttribute("productos", listProduc);
        request.getRequestDispatcher("/views/carrito/cliente.jsp").forward(request, response);
    }

    private void verCarrito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Carrito carrito = (Carrito) request.getSession().getAttribute("carrito");
        request.getRequestDispatcher("/views/carrito/ver.jsp").forward(request, response);
    }

    private void agregarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int codArt = Integer.parseInt(request.getParameter("codArt"));
        Producto producto = productosRepo.findById(codArt);
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        // Obtener usuario actual (suponiendo que está en sesión)
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        // Obtener carrito del usuario o crear uno nuevo si no existe
        Carrito carrito = (Carrito) request.getSession().getAttribute("carrito");
        if (carrito == null) {
            carrito = new Carrito(usuario);
            request.getSession().setAttribute("carrito", carrito);
        }

        // Agregar producto al carrito
        carrito.agregarProducto(producto, cantidad);

        // Redirigir a la página del carrito
        response.sendRedirect("carrito");
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int codArt = Integer.parseInt(request.getParameter("codArt"));

        // Obtener carrito del usuario
        Carrito carrito = (Carrito) request.getSession().getAttribute("carrito");
        if (carrito != null) {
            carrito.eliminarProducto(codArt);
        }
        
        response.sendRedirect("carrito");

    }

    private void actualizarCantidad(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int codArt = Integer.parseInt(request.getParameter("codArt"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        // Obtener carrito del usuario
        Carrito carrito = (Carrito) request.getSession().getAttribute("carrito");
        if (carrito != null) {
            carrito.actualizarCantidad(codArt, cantidad);
        }

        // Redirigir a la página del carrito
        response.sendRedirect(request.getContextPath() + "/carrito?accion=ver");
    }

    private void comprarCarrito(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Obtener carrito del usuario
        Carrito carrito = (Carrito) request.getSession().getAttribute("carrito");
        if (carrito != null) {
            // Implementar lógica para finalizar la compra, actualizar stocks y saldo del usuario
            // Esto podría incluir persistencia en base de datos y otros cálculos necesarios
            // Por ejemplo:
            List<ItemCarrito> items = carrito.getItems();
            for (ItemCarrito item : items) {
                Producto producto = item.getProducto();
                int cantidad = item.getCantidad();

                // Actualizar stock del producto
                producto.setStock(producto.getStock() - cantidad);
                productosRepo.update(producto);
            }

            // Limpiar carrito después de la compra
            carrito.getItems().clear();
        }

        // Redirigir a la página de confirmación de compra o a donde corresponda
        response.sendRedirect(request.getContextPath() + "/compra-confirmada.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}