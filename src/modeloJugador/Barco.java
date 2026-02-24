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
	public Barco(int opcionModo) {
		switch (opcionModo) {
		case 1:
			Tripulante charlie = new Tripulante("Charlie Kirky", 2);
			tripulacion[0] = charlie;
			Tripulante godofredo = new Tripulante("Godofredo Epsparrow", 3);
			tripulacion[1] = godofredo;
			Tripulante jesus = new Tripulante("Jesús Cristo García", 4);
			tripulacion[2] = jesus;
			Tripulante john = new Tripulante("John Patapalo", 1);
			tripulacion[3] = john;
			// asignar atributos a los tripulantes
			for (Tripulante trip : tripulacion) {
				switch (trip.getRol()) {
				// tanque
				case 1:
					trip.setSaludBase(150);
					trip.setFuerza(12);
					break;
				// equilibrado
				case 2:
					trip.setSaludBase(110);
					trip.setFuerza(18);
					break;
				// rapido pero blandito
				case 3:
					trip.setSaludBase(80);
					trip.setFuerza(20);
					break;
				// bueno en general pero un poquillo lento
				case 4:
					trip.setSaludBase(110);
					trip.setFuerza(22);
					break;
				}
			}
			break;
		case 2:
			break;
		}
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
