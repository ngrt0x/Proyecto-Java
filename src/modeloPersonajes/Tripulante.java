package modeloPersonajes;

import java.util.Random;

import modeloJugador.Barco;

public class Tripulante extends Persona implements ICombatiente {
	private final Random ALEATORIO = new Random();
	// atributos propios
	private int saludActual;
	private int saludTope;
	private boolean estaDefendiendo = false;

	// constructor
	public Tripulante(String nombre, int saludTope) {
		this.nombre = nombre;
		this.saludTope = saludTope;
		saludActual = saludTope;
	}

	// metodos de la interfaz
	public void atacar(ICombatiente objetivo, Barco barco) {
		objetivo.recibirDanio(barco.getArmamento().getDanio());
	}

	public void defender() {
		estaDefendiendo = true;
	}

	@Override
	public void recibirDanio(int danio) {
		if (!estaDefendiendo) {
			saludActual = Math.max(0, saludActual - danio);
		} else {
			saludActual = Math.max(0, saludActual - (danio / 2));
		}
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
