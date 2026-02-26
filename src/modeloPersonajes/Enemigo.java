package modeloPersonajes;

import java.util.Random;

/**
 * Clase para enemigo, contiene todas las estadisticas del enemigo
 * 
 * @author Jesús Manrique y Marcos Villagómez
 * @version 1.0
 */
public class Enemigo implements ICombatiente {
	/**
	 * Instacia de random
	 */
	private final Random ALEATORIO = new Random();
	// atributos
	/**
	 * Nombre del enemigo
	 */
	private String nombre;
	/**
	 * Salud actual del enemigo
	 */
	private int saludActual;
	/**
	 * Salud maxima del enemigo
	 */
	private int saludTope;
	/**
	 * Fuerza del enemigo
	 */
	private int fuerza;
	/**
	 * Iniciativa del enemigo
	 */
	private int iniciativa;

	// constructor
	/**
	 * Constructor de enemigo
	 * @param nombre Nombre del enemigo
	 * @param saludTope Salud tope del enemigo
	 * @param fuerza Fuerza del enemigo
	 * @param iniciativa Iniciativa del enemigo
	 */
	public Enemigo(String nombre, int saludTope, int fuerza, int iniciativa) {
		this.nombre = nombre;
		this.saludTope = saludTope;
		saludActual = saludTope;
		this.fuerza = fuerza;
		this.iniciativa = iniciativa;
	}

	// getters y setters
	/**
	 * Getter de salud actual
	 * @return Salud Actual
	 */
	public int getSaludActual() {
		return saludActual;
	}

	/**
	 * Getter salud tope
	 * @return Salud Tope
	 */
	public int getSaludTope() {
		return saludTope;
	}

	/**
	 * Getter de fuerza
	 * @return Fuerza del enemigo
	 */
	public int getFuerza() {
		return fuerza;
	}

	/**
	 * Getter de iniciativa
	 * @return Iniciativa del enemigo
	 */
	public int getIniciativa() {
		return iniciativa;
	}

	/**
	 * Setter de iniciativa
	 * @param iniciativa Iniciativa nueva del enemigo
	 */
	public void setIniciativa(int iniciativa) {
		this.iniciativa = iniciativa;
	}

	// metodos propios
	/**
	 * Metodo para atacar
	 * @return La cantidad de daño a hacer
	 */
	public int atacar() {
		return fuerza;
	}

	// metodos de la interfaz
	@Override
	/**
	 * Getter del nombre
	 * @return Nombre
	 */
	public String getNombre() {
		return nombre;
	}

	@Override
	/**
	 * Metodo para recibir daño
	 * @param danio Daño que va a recibir el enemigo
	 */
	public void recibirDanio(int danio) {
		saludActual = Math.max(0, saludActual - danio);
	}

	@Override
	/**
	 * Metodo para esquivar, tienes una probabilidad dictada por la iniciativa de esquivar el ataque
	 */
	public boolean intentarEsquivar() {
		int aleatorio = ALEATORIO.nextInt(100);
		if (aleatorio < iniciativa) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	/**
	 * Metodo para ver si sigue vivo, si la vida es mayor que 0 sigue vivo
	 * @return Si esta vivo
	 */
	public boolean estaVivo() {
		return saludActual > 0;
	}

}
