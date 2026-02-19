package modeloObjetos;

public class Item {
	// atributos
	protected String nombre;
	protected int precio;
	protected int cantidad;
	protected String id;

	// constructor base
	public Item(String nombre, String id, int precio) {
		this.nombre = nombre;
		this.id = id;
		this.precio = precio;
		this.cantidad = 1;
	}

	// constructor para crear copias
	public Item(Item otro) {
		this.nombre = otro.nombre;
		this.id = otro.id;
		this.precio = otro.precio;
		this.cantidad = 1;
	}

	// getters y setters
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public void sumarCantidad(int cantidad) {
		this.cantidad += cantidad;
	}

	public void restarCantidad(int cantidad) {
		this.cantidad -= cantidad;
	}

}
