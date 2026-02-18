package modeloPersonajes;

import java.util.Map;
import java.util.Random;

import modeloJugador.Barco;
import modeloObjetos.ArmamentoBarco;
import modeloObjetos.Canon;

public class Tripulante extends Persona implements ICombatiente {
	private final Random ALEATORIO = new Random();
	// atributos propios
	private int saludActual;
	private int saludTope;
	private int fuerza;
	private boolean defendiendo = false;
	private int iniciativa;
	private String estado;

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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	// metodos propios
	public int atacar(Barco barco) {
		// si el barco no tiene NINGUN ARMAMENTO que es practicamente imposible creo el
		// danio seria 0, osea que los tripulantes solo pegarian por su stat de fuerza
		int danio = 0;
		Map<String, ArmamentoBarco> inventarioB = barco.getInventarioB().getArmamentos();
		for (String i : inventarioB.keySet()) {
			if (!(inventarioB.get(i) instanceof Canon)) {
				danio = inventarioB.get(i).getDanio();
			}
		}
		return danio + fuerza;
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
