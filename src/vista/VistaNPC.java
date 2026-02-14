package vista;

import modeloJugador.Jugador;
import modeloPersonajes.NPC;

public class VistaNPC {
	// atributos
	private GestorVista gestorVista = new GestorVista();

	// metodos
	public void primeraFrase(NPC npc) {
		gestorVista.imprimirMensaje(npc.getPrimeraFrase());
	}

	public void menuDialogo(NPC npc) {

	}

	public void hablar(NPC npc) {

	}

	public void entregarPista(NPC npc, Jugador j) {

	}

}
