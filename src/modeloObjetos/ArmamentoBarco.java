package modeloObjetos;

public class ArmamentoBarco extends Item {
	// atributos propios
	private int danio;

	// constructor
	public ArmamentoBarco(String nombre, String id, int precio, int cantidad, int danio) {
		super(nombre, id, precio, cantidad);
		this.danio = danio;
	}

	// getters y setters
	public int getDanio() {
		return danio;
	}

}
