package modeloObjetos;

/**
 * Clase de cañones de barco
 * 
 * @author Jesús Manrique y Marcos Villagómez
 * @version 1.0
 */
public class Canon extends ArmamentoBarco {
	// constructor
	/**
	 * Constructor para cañones
	 * @param nombre Nombre del cañon
	 * @param id Id del cañon
	 * @param precio Precio del cañon
	 * @param danio Daño del cañon
	 * @param tier Tier del cañon
	 */
	public Canon(String nombre, String id, int precio, int danio, int tier) {
		super(nombre, id, precio, danio, tier);
	}
}