package modeloObjetos;

public class Armamento extends Item {
	// atributos propios
	private int danio;

	// constructor
	public Armamento(String nombre, String id, int precio, int cantidad, int danio) {
		super(nombre, id, precio, cantidad);
		this.danio = danio;
	}

	// getters y setters
	public int getDanio() {
		return danio;
	}

}
