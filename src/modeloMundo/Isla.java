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

	// Constructor
	public Isla(String nombre) {
		this.nombre = nombre;
	}
	
	//getters y setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public NPC[] getHabitantes() {
		return habitantes;
	}

	public void setHabitantes(NPC[] habitantes) {
		this.habitantes = habitantes;
	}

	public Tienda getTiendaLocal() {
		return tiendaLocal;
	}

	public void setTiendaLocal(Tienda tiendaLocal) {
		this.tiendaLocal = tiendaLocal;
	}
}
