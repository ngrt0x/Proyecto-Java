package modeloObjetos;

/**
 * Clase para las cañas de pescar, contiene la linea de la caña
 * 
 * @author Jesús Manrique y Marcos Villagómez
 * @version 1.0
 */
public class CanaPescar extends Item {
	// atributos propios
	/**
	 * Linea de la caña
	 */
	private int linea;

	// constructor
	/**
	 * Constructor de la cañas de pescar
	 * @param nombre Nombre de la caña
	 * @param id Id de la caña
	 * @param precio Precio de la caña
	 * @param linea Linea de la caña
	 */
	public CanaPescar(String nombre, String id, int precio, int linea) {
		super(nombre, id, precio);
		this.linea = linea;
	}
	
	/**
	 * Contructor para crear copias de cañas
	 * @param otra La caña que quieras copiar
	 */
	public CanaPescar(CanaPescar otra) {
		super(otra);
		this.linea = otra.getLinea();
	}

	// getters y setters
	/**
	 * Getter de la linea
	 * @return La linea de la caña de pesca
	 */
	public int getLinea() {
		return linea;
	}

	/**
	 * Setter de la linea
	 * @param linea La nueva linea de la caña
	 */
	public void setLinea(int linea) {
		this.linea = linea;
	}

}
