package repositories;

import java.util.ArrayList;
import java.util.List;

import models.Producto;
import repositories.interfaces.ProductoRepo;

public class ProductosRepoSingleton implements ProductoRepo{
	
	private static ProductosRepoSingleton singleton;
	
	public static ProductosRepoSingleton getInstance() {
		if (singleton == null) {
			singleton = new ProductosRepoSingleton();
		} 
		return singleton;
	}
 
	private List<Producto> listaProductos;
	
	private ProductosRepoSingleton() {
		this.listaProductos = new ArrayList<Producto>();
		Producto producto1 = new Producto( "Ventilador", 29, 35000);
		Producto producto2 = new Producto( "Estufa", 20, 22000);
		Producto producto3 = new Producto( "Notebook", 39, 150000);
		Producto producto4 = new Producto( "Auricular", 19, 10000);
		this.insert(producto1);
		this.insert(producto2);
		this.insert(producto3);
		this.insert(producto4);
	}
	
	public List<Producto> getAll() {
		
		return new ArrayList<Producto>(this.listaProductos);
	}

	
	public Producto findById(int codArt) {
		
		return this.listaProductos.stream()
		.filter( (e) -> e.getCodArt() == codArt )
		.findAny()
		.orElse(null);
	}

	
	public void insert(Producto producto) {
		int ultimaId = this.listaProductos.stream()
				.map(Producto::getCodArt)
				.max(Integer::compare)
				.orElse(0);
		producto.setCodArt(ultimaId + 1);
		
		this.listaProductos.add(producto);
		
	}

	
	public void update(Producto producto) {
		// TODO Auto-generated method stub
		
	}

	
	public void delete(int id) {
		this.listaProductos.removeIf( (e)-> e.getCodArt() == id );
		
	}

}
