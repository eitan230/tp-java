package models;

public class Producto {
	private int codArt;
	private String nombre;
	private int stock;
	private double precio;
	
	public Producto() {
		super();
	}
	
	
	public Producto(int codArt, String nombre, int stock, double precio) {
		super();
		this.nombre = nombre;
		this.stock = stock;
		this.precio = precio;
	}


	public int getCodArt() {
		return codArt;
	}


	public void setCodArt(int id) {
		this.codArt = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int edad) {
		this.stock = edad;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public Producto(String nombre, int stock, double precio) {
		super();
		this.nombre = nombre;
		this.stock = stock;
		this.precio = precio;
	}



	
}
