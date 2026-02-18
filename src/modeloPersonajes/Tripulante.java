package modeloPersonajes;

import java.util.Random;

import modeloJugador.Barco;

public class Tripulante extends Persona implements ICombatiente {
	private final Random ALEATORIO = new Random();
	// atributos propios
	private int saludActual;
	private int saludTope;
	private int fuerza;
	private boolean defendiendo = false;
	private int iniciativa;

	// constructor
	public Tripulante(String nombre, int saludTope, int fuerza) {
		this.nombre = nombre;
		this.saludTope = saludTope;
		saludActual = saludTope;
		this.fuerza = fuerza;
	}

	// getters y setters
	public boolean isDefendiendo() {
		return defendiendo;
	}

	public void setDefendiendo(boolean defendiendo) {
		this.defendiendo = defendiendo;
	}

	public int getSaludActual() {
		return saludActual;
	}

	public int getSaludTope() {
		return saludTope;
	}

	public int getIniciativa() {
		return iniciativa;
	}

	public void setIniciativa(int iniciativa) {
		this.iniciativa = iniciativa;
	}

	// metodos propios
	public int atacar(Barco barco) {
		return barco.getArmamento().getDanio() + fuerza;
	}

	public void defender() {
		defendiendo = true;
	}

	public void recuperarSalud(int saludRecuperada) {
		saludActual = Math.min(saludTope, saludActual + saludRecuperada);
	}

	// metodos de la interfaz
	@Override
	public void recibirDanio(int danio) {
		saludActual = Math.max(0, saludActual - danio);
	}

	@Override
	public boolean intentarEsquivar() {
		int aleatorio = ALEATORIO.nextInt(10);
		return aleatorio == 1;
	}

	@Override
	public boolean estaVivo() {
		return saludActual > 0;
	}

}
