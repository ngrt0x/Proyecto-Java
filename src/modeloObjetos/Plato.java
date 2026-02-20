package modeloObjetos;

import java.util.ArrayList;

public class Plato extends Item {
	// atributos
	private ArrayList<String> ingredientes = new ArrayList<>();

	// constructor
	public Plato(String nombre, String id, int precio) {
		super(nombre, id, precio);
		cantidad = 1;
	}

	// contructor para hacer copias
	public Plato(Item otro) {
		super(otro);
		cantidad = 1;
	}

	// getters y setters
	public ArrayList<String> getIngredientes() {
		return ingredientes;
	}

	// metodos propios
	public void addIngredientes(String ingrediente) {
		ingredientes.add(ingrediente);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		Plato plato = (Plato) o;
		return id != null && id.equals(plato.id);
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

}
