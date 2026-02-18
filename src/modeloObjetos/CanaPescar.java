package modeloObjetos;

public class CanaPescar extends Item {
	// atributos propios
	private int linea;

	// constructor
	public CanaPescar(String nombre, String id, int precio, int linea) {
		super(nombre, id, precio);
		this.linea = linea;
	}

	// getters y setters
	public int getLinea() {
		return linea;
	}

	public void setLinea(int linea) {
		this.linea = linea;
	}

}
