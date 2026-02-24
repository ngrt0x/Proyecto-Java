package controlador;

import modeloJugador.Jugador;
import modeloMundo.Mundo;
import vista.GestorVista;

public class GestorMundo {
	//atributos
	private GestorVista gestorVista = new GestorVista();
	
	private Mundo mundo;
	private Jugador j;
	
	//Constructor
	public GestorMundo(Jugador j) {
		this.mundo = new Mundo();
		this.j = j;
	}
	
	//metodos propios
	
	public void bajarAIsla() {
		
	}
	
	public void cambiarIsla() {
		gestorVista.imprimirMensaje("A que isla te gustaria viajar?");
		String opcionIsla = gestorVista.pedirString(); // cogemos el nombre de la isla a la que quiere viajar el jugador
		
		while(!mundo.getIslasDisponibles().containsKey(opcionIsla)) { // si la isla que ha elegido el jugador no existe le volvemos a pedir el string
			gestorVista.imprimirError("Elige una de las islas disponibles (Asegurate de escribir bien el nombre)");
			opcionIsla = gestorVista.pedirString();
		}
		
		if(opcionIsla.equals(mundo.getUbicacionActual().getNombre())) { // si la isla elegida es la misma en la que esta le salta error
			gestorVista.imprimirError("No puedes viajar a la misma isla en la que estas, vuelve a elegir");
		}
	}
	
	public void generarEventoAleatorio() {
		
	}

}
