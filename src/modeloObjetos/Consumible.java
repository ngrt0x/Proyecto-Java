package modeloObjetos;

public class Consumible extends Item {
	// atributos propios
	private String efecto;

	// constructor
	public Consumible(String nombre, String id, int precio, String efecto) {
		super(nombre, id, precio);
		this.efecto = efecto;
	}

	// constructor de copias
	public Consumible(Item otro) {
		super(otro);
	}

	// getters y setters
	public String getEfecto() {
		return efecto;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		Consumible consumible = (Consumible) o;
		return id != null && id.equals(consumible.id);
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}