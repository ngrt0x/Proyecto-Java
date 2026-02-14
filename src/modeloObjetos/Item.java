package modeloObjetos;

public class Item {
	// atributos
	protected String nombre;
	protected int precio;
	protected int cantidad;
	protected String id;

	public Item(String nombre, String id, int precio, int cantidad) {
		this.nombre = nombre;
		this.id = id;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	// getters y setters
	public int getCantidad() {
		return cantidad;
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
