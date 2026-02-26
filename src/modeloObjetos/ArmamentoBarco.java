package modeloObjetos;

/**
 * Clase ArmamentoBarco, son las mejoras que puede tener el barco, tiene cantidad de daño y tier
 * 
 * @author Jesús Manrique y Marcos Villagómez
 * @version 1.0
 */
public class ArmamentoBarco extends Item {
	// atributos propios
	/**
	 * Dañao de la mejora
	 */
	private int danio;
	/**
	 * Tier de la mejora
	 */
	private int tier;

	// constructor
	/**
	 * Contructor de ArmamentoBarco
	 * @param nombre Nombre de la mejora
	 * @param id Id de la mejora
	 * @param precio Precio de la mejora
	 * @param danio Daño de la mejora
	 * @param tier Tier de la mejora
	 */
	public ArmamentoBarco(String nombre, String id, int precio, int danio, int tier) {
		super(nombre, id, precio);
		this.danio = danio;
		this.tier = tier;
	}

	// getters y setters
	/**
	 * Getter del daño
	 * @return Daño de la mejora
	 */
	public int getDanio() {
		return danio;
	}
	
	/**
	 * Getter del tier
	 * @return Tier de la mejora
	 */
	public int getTier() {
		return tier;
	}

}
