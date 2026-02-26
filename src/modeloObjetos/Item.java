package modeloObjetos;

/**
 * Clase item, contiene nombre, precio, cantidad e id de los items
 * 
 * @author Jesús Manrique y Marcos Villagómez
 * @version 1.0
 */
public class Item {
	// atributos
	/**
	 * Nombre del item
	 */
	protected String nombre;
	/**
	 * Precio del item
	 */
	protected int precio;
	/**
	 * Cantidad de dicho item
	 */
	protected int cantidad;
	/**
	 * Id del item
	 */
	protected String id;

	// constructor base
	/**
	 * Constructor para item
	 * @param nombre Nombre del item
	 * @param id Id del item
	 * @param precio Precio del item
	 */
	public Item(String nombre, String id, int precio) {
		this.nombre = nombre;
		this.id = id;
		this.precio = precio;
		this.cantidad = 1;
	}

	// constructor para crear copias
	/**
	 * Constructor para copias de item
	 * @param otro Item a copiar
	 */
	public Item(Item otro) {
		this.nombre = otro.nombre;
		this.id = otro.id;
		this.precio = otro.precio;
		this.cantidad = 1;
	}

	// getters y setters
	/**
	 * Getter de cantidad
	 * @return La cantidad de ese item
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * Setter de cantidad
	 * @param cantidad Nueva cantidad para ese item
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Getter del id
	 * @return Id del item
	 */
	public String getId() {
		return id;
	}

	/**
	 * Getter del nombre
	 * @return El nombre del item
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Getter del precio
	 * @return El precio del item
	 */
	public int getPrecio() {
		return precio;
	}

	/**
	 * Metodo para sumar cantidad a un item
	 * @param cantidad Cantidad a sumar
	 */
	public void sumarCantidad(int cantidad) {
		this.cantidad += cantidad;
	}

	/**
	 * Metodo para restar cantidad a un item
	 * @param cantidad Cantidad a restar
	 */
	public void restarCantidad(int cantidad) {
		this.cantidad -= cantidad;
	}

}
