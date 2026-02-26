package modeloObjetos;

import java.util.ArrayList;

/**
 * Clase plato, contiene ingredientes de preparacion
 * 
 * @author Jesús Manrique y Marcos Villagómez
 * @version 1.0
 */
public class Plato extends Item {
	// atributos
	/**
	 * Ingredientes necesarios para preparar ese plato
	 */
	private ArrayList<String> ingredientes = new ArrayList<>();

	// constructor
	/**
	 * Constructor de plato
	 * @param nombre Nombre del plato
	 * @param id Id del plato
	 * @param precio Precio del plato
	 */
	public Plato(String nombre, String id, int precio) {
		super(nombre, id, precio);
		cantidad = 1;
	}

	// contructor para hacer copias
	/**
	 * Constructor para hacer copias de plato
	 * @param otro Plato a copiar
	 */
	public Plato(Item otro) {
		super(otro);
	}

	// getters y setters
	/**
	 * Getter de ingredientes
	 * @return Lista de ingredientes
	 */
	public ArrayList<String> getIngredientes() {
		return ingredientes;
	}

	// metodos propios
	/**
	 * Metodo para añadir ingredientes
	 * @param ingrediente Ingrediente a añadir
	 */
	public void addIngredientes(String ingrediente) {
		ingredientes.add(ingrediente);
	}

	@Override
	/**
	 * Metodo para comprobar que 2 platos sean iguales
	 * @return true si son false si no
	 */
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		Plato plato = (Plato) o;
		return id != null && id.equals(plato.id);
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
