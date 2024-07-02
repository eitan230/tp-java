package repositories.interfaces;

import java.util.List;

import models.Producto;

public interface ProductoRepo {
	
	 public List<Producto> getAll();
	 
	 public Producto findById(int id);
	 
	 public void insert(Producto producto);
	 
	 public void update(Producto producto);
	 
	 public void delete(int id);

}
