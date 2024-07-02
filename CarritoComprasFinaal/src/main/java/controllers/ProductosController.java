package controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Producto;
import repositories.ProductosRepoSingleton;
import repositories.interfaces.*;


@WebServlet("/productos")
public class ProductosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ProductoRepo productosRepo;

    public ProductosController() {
    	this.productosRepo = ProductosRepoSingleton.getInstance();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String accion = request.getParameter("accion");
		accion = Optional.ofNullable(accion).orElse("index");
		
		switch (accion) {
			case "index" -> getIndex(request, response);
			case "bienvenida" -> getBienvenida(request, response);
			case "show" -> getShow(request, response);
			case "edit" -> getEdit(request, response);
			case "create" -> getCreate(request, response);
			default -> response.sendError(404);
		}
	}
	

	private void getBienvenida(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/views/productos/bienvenida.jsp").forward(request, response);
		
	}


	private void getCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/productos/create.jsp").forward(request, response);
	}


	private void getEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sCodArt = request.getParameter("codArt");
		int codArt = Integer.parseInt(sCodArt);
		
		ProductoRepo repo = ProductosRepoSingleton.getInstance();
		
		Producto produc = repo.findById(codArt);
		
		request.setAttribute("producto", produc);
		
		request.getRequestDispatcher("/views/productos/edit.jsp").forward(request, response);
	}


	private void getShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sCodArt = request.getParameter("codArt");
		int codArt = Integer.parseInt(sCodArt);
		

		
		Producto produc = productosRepo.findById(codArt);
		
		request.setAttribute("producto", produc);
		
		request.getRequestDispatcher("/views/productos/show.jsp").forward(request, response);
		

	}


	private void getIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductoRepo repo = ProductosRepoSingleton.getInstance();
		List<Producto> listProduc = repo.getAll();
		
		request.setAttribute("productos", listProduc);
		
		request.getRequestDispatcher("/views/productos/index.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		
		if(accion == null) {
			response.sendError(400, "No se brindo una accion");
			return;
		}
		
		switch (accion) {
		case "insert" -> postInsert(request, response);
		case "update" -> postUpdate(request, response);
		case "delete" -> postDelete(request, response);
		default -> response.sendError(404);
		}
		

	}


	private void postDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String sCodArt = request.getParameter("codArt");
		int codArt = Integer.parseInt(sCodArt);
		
		productosRepo.delete(codArt);
		
		response.sendRedirect("productos");
	}


	private void postUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sCodArt = request.getParameter("codArt");
		int codArt = Integer.parseInt(sCodArt);
				
		String nombre = request.getParameter("nombre");
		
		String sPrecio = request.getParameter("precio");
		double precio = Double.parseDouble(sPrecio);
		
		String sStock = request.getParameter("stock");
		int stock = Integer.parseInt(sStock);
		
		Producto produc = productosRepo.findById(codArt);
		
		produc.setNombre(nombre);
		produc.setStock(stock);
		produc.setPrecio(precio);
		
		productosRepo.update(produc);
		
		response.sendRedirect("productos");
	
		
	}


	private void postInsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String nombre = request.getParameter("nombre");
		
		String sPrecio = request.getParameter("precio");
		double precio = Double.parseDouble(sPrecio);
		
		String sStock = request.getParameter("stock");
		int stock = Integer.parseInt(sStock);
		
		Producto produc = new Producto(nombre, stock, precio);
		
		productosRepo.insert(produc);
		
		response.sendRedirect("productos");;
	}

}
