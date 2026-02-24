package modeloMundo;

import java.util.HashMap;

import modeloPersonajes.NPC;

public class Mundo {
	// atributos
	private Isla[] mapamundi;
	private Isla[] islasDescubiertas;
	private Isla ubicacionActual;
	
	// Islas disponibles
	private HashMap<String, Isla> islasDisponibles = new HashMap<String, Isla>();
	
	private NPC[] habitantes1 = {new NPC()};
	
	public Mundo(Isla inicio) {
		mapamundi = new Isla[1];
		islasDescubiertas = new Isla[1];
		ubicacionActual = inicio;
	}

}
