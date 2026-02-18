package modeloObjetos;

public class Consumible extends Item {
	// atributos propios
	private String efecto;

	// constructor
	public Consumible(String nombre, String id, int precio, String efecto) {
		super(nombre, id, precio);
		this.efecto = efecto;
	}

	// getters y setters
	public String getEfecto() {
		return efecto;
	}
}
