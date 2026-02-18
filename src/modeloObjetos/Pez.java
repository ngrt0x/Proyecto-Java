package modeloObjetos;

public class Pez extends Item {
	// atributos propios
	private int rareza;
	private int fuerza;

	// constructor
	public Pez(String nombre, String id, int precio, int rareza, int fuerza) {
		super(nombre, id, precio);
		this.rareza = rareza;
		this.fuerza = fuerza;
	}

	// getters y setters
	public int getRareza() {
		return rareza;
	}

	public void setRareza(int rareza) {
		this.rareza = rareza;
	}

	public int getFuerza() {
		return fuerza;
	}

	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}
}
