package modeloMundo;

import modeloPersonajes.NPC;

/**
 * Esta clase es la Isla con sus habitantes y tiendas
 * 
 * @author Jesús Manrique y Marcos Villagómez
 * @version 1.0
 */
public class Isla {
	// atributos
	/** El nombre representativo de la isla */
	private String nombre;
	/** Array de habitantes de la isla */
	private NPC[] habitantes;
	/** Tienda de la isla */
	private Tienda tiendaLocal;
	/** Astillero de la isla */
	private Astillero astilleroLocal;
	/** Variable de la isla para saber si el jugador ya ha visitado la isla */
	private boolean visitada;

	/**
	 * Constructor para Isla
	 * 
	 * @param nombre El nombre que tendra la isla
	 * @param habitantes Los habitantes que tendra la isla
	 * @param tienda La tienda que tendra la isla
	 * @param astilleroLocal El astillero de la isla
	 */
	public Isla(String nombre, NPC[] habitantes, Tienda tienda, Astillero astilleroLocal) {
		this.nombre = nombre;
		this.habitantes = habitantes;
		this.tiendaLocal = tienda;
		this.astilleroLocal = astilleroLocal;
	}

	/**
	 * Constructor secundario para una isla con solo nombre
	 * 
	 * @param nombre El nombre que tendra la isla
	 */
	public Isla(String nombre) {
		this.nombre = nombre;
	}

	// getters y setters
	/**
	 * Getter del nombre de la isla
	 * @return El nombre de la isla
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Setter del nombre, cambia el nombre de la isla
	 * @param nombre El nombre nuevo de la isla
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Getter para los habitantes
	 * @return Array de los habitantes de la isla
	 */
	public NPC[] getHabitantes() {
		return habitantes;
	}

	/**
	 * Setter de los habitantes
	 * @param habitantes Nuevos habitantes de la isla
	 */
	public void setHabitantes(NPC[] habitantes) {
		this.habitantes = habitantes;
	}

	/**
	 * Getter de la tienda
	 * @return La tienda de la isla
	 */
	public Tienda getTiendaLocal() {
		return tiendaLocal;
	}

	/**
	 * Setter de la tienda
	 * @param tiendaLocal Sobreescribe la tienda que tiene la isla
	 */
	public void setTiendaLocal(Tienda tiendaLocal) {
		this.tiendaLocal = tiendaLocal;
	}

	/**
	 * Getter del astillero
	 * @return El astillero de la isla
	 */
	public Astillero getAstilleroLocal() {
		return astilleroLocal;
	}

	/**
	 * Setter de visitada, vuelve a la isla visitada
	 */
	public void setVisitada() {
		visitada = true;
	}

	/**
	 * Getter de visitada
	 * @return Si ya ha sido visitada
	 */
	public boolean isVisitada() {
		return visitada;
	}
}
