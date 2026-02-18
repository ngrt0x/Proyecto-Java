package modeloPersonajes;

import java.util.Random;

public class Enemigo implements ICombatiente {
	private final Random ALEATORIO = new Random();
	// atributos
	private String nombre;
	private int saludActual;
	private int saludTope;
	private int fuerza;
	private int iniciativa;

	// constructor
	public Enemigo(String nombre, int saludTope, int fuerza, int iniciativa) {
		this.nombre = nombre;
		this.saludTope = saludTope;
		saludActual = saludTope;
		this.fuerza = fuerza;
		this.iniciativa = iniciativa;
	}

	// getters y setters
	public int getSaludActual() {
		return saludActual;
	}

	public int getSaludTope() {
		return saludTope;
	}

	public int getFuerza() {
		return fuerza;
	}

	public int getIniciativa() {
		return iniciativa;
	}

	public void setIniciativa(int iniciativa) {
		this.iniciativa = iniciativa;
	}

	// metodos propios
	public int atacar() {
		return fuerza;
	}

	// metodos de la interfaz
	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public void recibirDanio(int danio) {
		saludActual = Math.max(0, saludActual - danio);
	}

	@Override
	public boolean intentarEsquivar() {
		int aleatorio = ALEATORIO.nextInt(100);
		if (aleatorio < iniciativa) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean estaVivo() {
		return saludActual > 0;
	}

}
