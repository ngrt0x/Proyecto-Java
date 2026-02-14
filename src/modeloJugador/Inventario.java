package modeloJugador;

import java.util.Map;

import modeloObjetos.Item;

import java.util.HashMap;

public class Inventario {
	// atributos
	Map<String, Item> items;

	public Inventario() {
		items = new HashMap<>();
	}

	// getters y setters
	public Map<String, Item> getItems() {
		return items;
	}

	// metodos propios
	public void anadirItem(Item nuevoItem) {
		String id = nuevoItem.getId();

		if (items.containsKey(id)) {
			items.get(id).sumarCantidad(1);
		} else {
			items.put(id, nuevoItem);
		}
	}

	public void restarItem(String id, int cantidad) {
		Item item = items.get(id);

		item.restarCantidad(cantidad);
		if (item.getCantidad() <= 0) {
			items.remove(id);
		}
	}

}
