package modeloJugador;

import modeloObjetos.Armamento;
import modeloPersonajes.Tripulante;

public class Barco {
	// atributos
	private Inventario equipamiento;
	// armamento basico con el que empiezas
	private Armamento armamentoBasico = new Armamento("Armamento Básico", "arma_base", 20, 1, 15);
	private Armamento armamento = armamentoBasico;
	// tripulantes
	private Tripulante charlie = new Tripulante("Charlie Kirky", 100, 15);
	private Tripulante godofredo = new Tripulante("Godofredo Epsparrow", 75, 25);
	private Tripulante john = new Tripulante("John Patapalo", 125, 10);
	private Tripulante jesus = new Tripulante("Jesús Cristo García", 100, 15);
	private Tripulante[] tripulacion = { charlie, godofredo, john, jesus };

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
