package modeloObjetos;

/**
 * Clase para consumibles, tiene efecto de consumible
 * 
 * @author Jesús Manrique y Marcos Villagómez
 * @version 1.0
 */
public class Consumible extends Item {
	// atributos propios
	/**
	 * Efecto del consumible
	 */
	private String efecto;

	// constructor
	/**
	 * Constructor del consumible
	 * 
	 * @param nombre Nombre del consumible
	 * @param id     Id del consumible
	 * @param precio Precio del consumible
	 * @param efecto Efecto del consumible
	 */
	public Consumible(String nombre, String id, int precio, String efecto) {
		super(nombre, id, precio);
		this.efecto = efecto;
	}

	// constructor de copias
	/**
	 * Constructor de copias de consumible
	 * 
	 * @param otro Consumible a copiar
	 */
	public Consumible(Consumible otro) {
		super(otro);
		this.efecto = otro.getEfecto();
	}

	// getters y setters
	/**
	 * Getter de efecto
	 * 
	 * @return Efecto del consumible
	 */
	public String getEfecto() {
		return efecto;
	}

	@Override
	/**
	 * Metodo para comprobar que 2 consumibles sean el mismo
	 * 
	 * @return True si son iguales false si son distintos
	 */
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		Consumible consumible = (Consumible) o;
		return id != null && id.equals(consumible.id);
	}

	@Override
	/**
	 * Genera un código hash para la instancia actual. * El cálculo se basa en el
	 * identificador único (id) de la entidad. Si el id es nulo, devuelve 0; de lo
	 * contrario, devuelve el hashCode del id.
	 * 
	 * @return Un valor entero que representa el código hash de este objeto.
	 */
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}