package modeloObjetos;

public class Consumible extends Item {
	// atributos propios
	private String efecto;

	// constructor
	public Consumible(String nombre, String id, int precio, int cantidad, String efecto) {
		super(nombre, id, precio, cantidad);
		this.efecto = efecto;
	}

	// getters y setters
	public String getEfecto() {
		return efecto;
	}
}
