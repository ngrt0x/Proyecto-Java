package modeloObjetos;

/**
 * Clase pez, contiene la rareza y fuerza de pez
 * 
 * @author Jesús Manrique y Marcos Villagómez
 * @version 1.0
 */
public class Pez extends Item {
	// atributos propios
	/**
	 * Rareza del pez
	 */
	private int rareza;
	/**
	 * Fuerza del pez
	 */
	private int fuerza;

	// constructor
	/**
	 * Contructor para pez
	 * @param nombre Nombre del pez
	 * @param id Id del pez
	 * @param precio Precio del pez
	 * @param rareza Rareza del pez
	 * @param fuerza Fuerza del pez
	 */
	public Pez(String nombre, String id, int precio, int rareza, int fuerza) {
		super(nombre, id, precio);
		this.rareza = rareza;
		this.fuerza = fuerza;
	}

	// getters y setters
	/**
	 * Getter de rareza
	 * @return El numero de rareza del pez
	 */
	public int getRareza() {
		return rareza;
	}

	/**
	 * Setter de rareza
	 * @param rareza La nueva rareza del pez
	 */
	public void setRareza(int rareza) {
		this.rareza = rareza;
	}

	/**
	 * Getter de fuerza
	 * @return La fuerza del pez
	 */
	public int getFuerza() {
		return fuerza;
	}

	/**
	 * Setter de fuerza
	 * @param fuerza La nueva fuerza del pezs
	 */
	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}
}
