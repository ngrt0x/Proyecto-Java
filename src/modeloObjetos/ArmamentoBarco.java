package modeloObjetos;

public class ArmamentoBarco extends Item {
	// atributos propios
	private int danio;
	private int tier;

	// constructor
	public ArmamentoBarco(String nombre, String id, int precio, int danio, int tier) {
		super(nombre, id, precio);
		this.danio = danio;
		this.tier = tier;
	}

	// getters y setters
	public int getDanio() {
		return danio;
	}

	public int getTier() {
		return tier;
	}

}
