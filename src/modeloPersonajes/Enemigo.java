package modeloPersonajes;

import java.util.Random;

public class Enemigo implements ICombatiente {
	private final Random ALEATORIO = new Random();
	// atributos
	protected String nombre;
	protected int saludActual;
	protected int saludTope;
	protected int fuerza;

	// constructor
	public Enemigo(String nombre, int saludTope, int fuerza) {
		this.nombre = nombre;
		this.saludTope = saludTope;
		saludActual = saludTope;
		this.fuerza = fuerza;
	}

	// getters y setters
	public String getNombre() {
		return nombre;
	}

	public int getSaludActual() {
		return saludActual;
	}

	public int getSaludTope() {
		return saludTope;
	}

	public int getFuerza() {
		return fuerza;
	}

	// metodos de la interfaz
	public void atacar(ICombatiente objetivo) {
		objetivo.recibirDanio(fuerza);
	}

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
