package modeloMundo;

import modeloPersonajes.NPC;

public class Isla {
	// atributos
	private String nombre;
	private NPC[] habitantes;
	private Tienda tiendaLocal;

	// Constructor
	public Isla(String nombre) {
		this.nombre = nombre;
	}

	// getters y setters
	public String getNombre() {
		return nombre;
	}
}
