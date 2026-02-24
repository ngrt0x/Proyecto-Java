package modeloJugador;

import modeloObjetos.ArmamentoBarco;
import modeloPersonajes.Tripulante;

public class Barco {
	// atributos
	private InventarioBarco inventarioB = new InventarioBarco();
	// armamento basico con el que empiezas
	private ArmamentoBarco armamentoBasico = new ArmamentoBarco("Armamento Básico", "arma_base", 20, 15, 1);
	// tripulantes
	private Tripulante[] tripulacion = new Tripulante[4];

	// constructor
	public Barco() {
		inventarioB.anadirArmamento(armamentoBasico);
	}

	// getters y setters
	public Tripulante[] getTripulacion() {
		return tripulacion;
	}

	public void setTripulacion(Tripulante[] tripulacion) {
		this.tripulacion = tripulacion;
	}

	public InventarioBarco getInventarioB() {
		return inventarioB;
	}
}
