package modeloObjetos;

public class Plato extends Item {
	// atributos
	private String ingrediente1;
	private String ingrediente2;
	private String ingrediente3;
	private String ingrediente4;
	private String ingrediente5;

	// constructores
	// platos con 2 ingredientes
	public Plato(String nombre, String id, int precio, String ingrediente1, String ingrediente2) {
		super(nombre, id, precio);
		this.ingrediente1 = ingrediente1;
		this.ingrediente2 = ingrediente2;
	}

	// platos con 3 ingredientes
	public Plato(String nombre, String id, int precio, String ingrediente1, String ingrediente2, String ingrediente3) {
		super(nombre, id, precio);
		this.ingrediente1 = ingrediente1;
		this.ingrediente2 = ingrediente2;
		this.ingrediente3 = ingrediente3;
	}

	// platos con 4 ingredientes
	public Plato(String nombre, String id, int precio, String ingrediente1, String ingrediente2, String ingrediente3,
			String ingrediente4) {
		super(nombre, id, precio);
		this.ingrediente1 = ingrediente1;
		this.ingrediente2 = ingrediente2;
		this.ingrediente3 = ingrediente3;
		this.ingrediente4 = ingrediente4;
	}

	// getters y setters
	public String getIngrediente1() {
		return ingrediente1;
	}

	public void setIngrediente1(String ingrediente1) {
		this.ingrediente1 = ingrediente1;
	}

	public String getIngrediente2() {
		return ingrediente2;
	}

	public void setIngrediente2(String ingrediente2) {
		this.ingrediente2 = ingrediente2;
	}

	public String getIngrediente3() {
		return ingrediente3;
	}

	public void setIngrediente3(String ingrediente3) {
		this.ingrediente3 = ingrediente3;
	}

	public String getIngrediente4() {
		return ingrediente4;
	}

	public void setIngrediente4(String ingrediente4) {
		this.ingrediente4 = ingrediente4;
	}

	public String getIngrediente5() {
		return ingrediente5;
	}

	public void setIngrediente5(String ingrediente5) {
		this.ingrediente5 = ingrediente5;
	}

}
