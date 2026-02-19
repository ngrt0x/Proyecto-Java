package modeloPersonajes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import modeloJugador.Barco;
import modeloObjetos.ArmamentoBarco;
import modeloObjetos.Canon;

public class Tripulante extends Persona implements ICombatiente {
	private final Random ALEATORIO = new Random();
	// atributos propios
	private int saludBase;
	private int saludActual;
	private int saludTope;
	private int fuerza;
	private boolean defendiendo = false;
	private int iniciativa;
	private String estado;

	// constructor
	public Tripulante(String nombre, int saludBase, int fuerza) {
		this.nombre = nombre;
		this.saludBase = saludBase;
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

	public void setSaludTope(int saludTope) {
		this.saludTope = saludTope;
	}

	public void setSaludActual(int saludActual) {
		this.saludActual = saludActual;
	}

	public int getSaludBase() {
		return saludBase;
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
	// metodo atacar de la clase Tripulante. Comprueba que el Barco tenga Armamento,
	// crea una lista con esos Armamentos y la ordena en base al tier del armamento.
	// El danio que se aplica al ataque de los tripulantes es el del armamento de
	// mayor tier.
	public int atacar(Barco barco) {
		int danio;
		Map<String, ArmamentoBarco> inventarioB = barco.getInventarioB().getArmamentos();
		List<ArmamentoBarco> armamentos = new ArrayList<>();
		for (String i : inventarioB.keySet()) {
			if (!(inventarioB.get(i) instanceof Canon)) {
				armamentos.add(inventarioB.get(i));
			}
		}
		armamentos.sort((a, b) -> b.getTier() - a.getTier());
		danio = armamentos.get(0).getDanio();
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
