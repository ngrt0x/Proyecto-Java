package modeloJugador;

import modeloObjetos.Armamento;
import modeloPersonajes.Tripulante;

public class Barco {
	// atributos
	private Tripulante[] tripulacion;
	private Inventario equipamiento;
	private Armamento armamento;

	// constructor
	public Barco() {
		Armamento armamentoBasico = new Armamento("Armamento Básico", "arma_base", 20, 1, 15);
		armamento = armamentoBasico;
		tripulacion = new Tripulante[4];
	}

	// getters y setters
	public Tripulante[] getTripulacion() {
		return tripulacion;
	}

	public Inventario getEquipamiento() {
		return equipamiento;
	}

	public Armamento getArmamento() {
		return armamento;
	}
}
