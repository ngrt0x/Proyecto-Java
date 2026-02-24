package modeloMundo;

import java.util.HashMap;

import controlador.GestorNPC;

public class Mundo {
	// atributos
	private GestorNPC gestorNPC = new GestorNPC();
	private Tienda t = new Tienda();
	private Astillero ast = new Astillero();

	private Isla[] mapamundi; // mapa de islas que se podra visualizar
	private Isla ubicacionActual; // isla en la que se encuentra el jugador actualmente

	private HashMap<String, Isla> islasDisponibles = new HashMap<String, Isla>(); // islas totales del juego

	public Mundo() {
		mapamundi = new Isla[1];

		islasDisponibles.put("Inicio", new Isla("Inicio", gestorNPC.getHabitantesIsla1(), t, ast));

		ubicacionActual = islasDisponibles.get("Inicio");
	}

	// getters y setters

	public Isla[] getMapamundi() {
		return mapamundi;
	}

	public void setMapamundi(Isla[] mapamundi) {
		this.mapamundi = mapamundi;
	}

	public Isla getUbicacionActual() {
		return ubicacionActual;
	}

	public void setUbicacionActual(Isla ubicacionActual) {
		this.ubicacionActual = ubicacionActual;
	}

	public HashMap<String, Isla> getIslasDisponibles() {
		return islasDisponibles;
	}
}