package modeloJugador;

import modeloMundo.Isla;
import modeloObjetos.CanaPescar;
import modeloObjetos.Consumible;
import modeloObjetos.Item;

/**
 * Clase Jugador, el personaje que interactua con el mundo segun dicta el jugador
 * 
 * @author Jesús Manrique y Marcos Villagómez
 * @version 1.0
 */
public class Jugador {
	// atributos
	/**
	 * Nombre del jugador
	 */
	private String nombre;
	/**
	 * Inventario del jugador
	 */
	private Inventario inventario = new Inventario();
	/**
	 * Oro del jugador (moneda del juego)
	 */
	private int oro;
	/**
	 * Barco del jugador
	 */
	private Barco barco;
	/**
	 * Diario del jugador (Contiene las pistas que recolecte)
	 */
	private Diario diario;
	/**
	 * Isla en la que se encuentra el jugador
	 */
	private Isla islaActual;

	// constructor
	/**
	 * Contructor de jugador
	 * @param nombre Nombre del jugador
	 * @param opcionModo Modo de juego (Debug o normal)
	 */
	public Jugador(String nombre, int opcionModo) {
		barco = new Barco(opcionModo);
		this.nombre = nombre;
		diario = new Diario();
		
		Item canaBase = new CanaPescar("Caña de pescar básica", "cana_base", 5, 10);
		Item brebajeSalud = new Consumible("Brebaje de Salud", "pot_salud", 75, "curar");
		Item brebajeIniciativa = new Consumible("Brebaje de Iniciativa", "pot_init", 75, "iniciativa");
		switch (opcionModo) {
		case 1:
			oro = 50000000;
			inventario.anadirItem(canaBase);
			inventario.anadirItem(brebajeIniciativa);
			inventario.anadirItem(brebajeSalud);
			break;
		case 2:
			oro = 10;
			inventario.anadirItem(canaBase);
			break;
		}
	}

	// getters y setters
	/**
	 * Getter del Inventario del jugador
	 * @return El inventario del jugador
	 */
	public Inventario getInventario() {
		return inventario;
	}

	/**
	 * Getter del oro del jugador
	 * @return El oro del jugador
	 */
	public int getOro() {
		return oro;
	}

	/**
	 * Setter del oro del jugador
	 * @param oro Nueva cantidad de oro para el jugador
	 */
	public void setOro(int oro) {
		this.oro = oro;
	}

	/**
	 * Getter del nombre del jugador
	 * @return Nombre del jugador
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Getter del barco del jugador
	 * @return El barco del jugador
	 */
	public Barco getBarco() {
		return barco;
	}
	
	/**
	 * Getter del diario del jugadro
	 * @return Diario del jugador
	 */
	public Diario getDiario() {
		return diario;
	}

	/**
	 * Getter de la isla actual
	 * @return Isla en la que se encuentra el jugador
	 */
	public Isla getIslaActual() {
		return islaActual;
	}

	/**
	 * Setter de la isla actual
	 * @param islaActual Nueva isla en la que se encuentra el jugador
	 */
	public void setIslaActual(Isla islaActual) {
		this.islaActual = islaActual;
	}

	// metodos propios
	/**
	 * Metodo para sumar oro al jugador
	 * @param cantidad Cantidad de oro a sumar
	 */
	public void sumarOro(int cantidad) {
		oro = oro += cantidad;
	}

	/**
	 * Metodo para restar oro al jugador
	 * @param cantidad Cantidad de oro a restar
	 */
	public void restarOro(int cantidad) {
		oro = oro -= cantidad;
	}
}
