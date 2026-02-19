package modeloJugador;

import modeloObjetos.ArmamentoBarco;
import modeloPersonajes.Tripulante;

public class Barco {
	// atributos
	private InventarioBarco inventarioB = new InventarioBarco();
	// armamento basico con el que empiezas
	private ArmamentoBarco armamentoBasico = new ArmamentoBarco("Armamento Básico", "arma_base", 20, 15, 1);
	// tripulantes
	private Tripulante charlie = new Tripulante("Charlie Kirky", 110, 18); // rol medio
	private Tripulante godofredo = new Tripulante("Godofredo Epsparrow", 80, 28); // dps
	private Tripulante john = new Tripulante("John Patapalo", 150, 12); // tanque
	private Tripulante jesus = new Tripulante("Jesús Cristo García", 100, 16); // rol medio
	private Tripulante[] tripulacion = { charlie, godofredo, john, jesus };

	// constructor
	public Barco() {
		inventarioB.anadirArmamento(armamentoBasico);
	}

	// getters y setters
	public Tripulante[] getTripulacion() {
		return tripulacion;
	}

	public InventarioBarco getInventarioB() {
		return inventarioB;
	}
}
