package modeloMundo;

import modeloPersonajes.NPC;

public class Isla {
	// atributos
	private String nombre;
	private NPC[] habitantes;
	private Tienda tiendaLocal;
	
	//Constructor
	public Isla(String nombre, NPC[] habitantes, Tienda tienda) {
		this.nombre = nombre;
		this.habitantes = habitantes;
		this.tiendaLocal = tienda;
	}
	
	

}
