package modeloJugador;

import java.util.HashMap;
import java.util.Iterator;

import modeloObjetos.Item;
import modeloObjetos.Pez;

public class Inventario {
	// atributos
	HashMap<String, HashMap<String, Item>> inventario;

	public Inventario() {
		inventario = new HashMap<String, HashMap<String, Item>>();
		inventario.put("Items", new HashMap<>());
		inventario.put("Peces", new HashMap<>());
	}

	// getters y setters
	public HashMap<String, HashMap<String, Item>> getInventario() {
		return inventario;
	}

	public HashMap<String, Item> getItems() {
		HashMap<String, Item> itemsYPescados = new HashMap<>();
		itemsYPescados.putAll(inventario.get("Items"));
		itemsYPescados.putAll(inventario.get("Peces"));
		return itemsYPescados;
	}

	public HashMap<String, Item> getItem() {
		return inventario.get("Items");
	}

	public HashMap<String, Item> getPeces() {
		return inventario.get("Peces");
	}

	// metodos propios
	public void anadirItem(Item nuevoItem) {
		boolean yaEsta = false;

		if (getItems().containsKey(nuevoItem.getId())) {
			yaEsta = true;
		}

		if (yaEsta) {
			if (nuevoItem instanceof Pez) {
				inventario.get("Peces").get(nuevoItem.getId()).sumarCantidad(1);
			} else {
				inventario.get("Items").get(nuevoItem.getId()).sumarCantidad(1);
			}
		} else {
			if (nuevoItem instanceof Pez) {
				inventario.get("Peces").put(nuevoItem.getId(), nuevoItem);
			} else {
				inventario.get("Items").put(nuevoItem.getId(), nuevoItem);
			}
		}
	}

	// modificado la funcion restarItem porque me he dado cuenta que para restar el
	// item bien hay que hacerlo mientras recorres la colecion con un iterator, que
	// sino peta de vez en cuando. perdon jesus
	public void restarItem(String id, int cantidad) {
		for (HashMap<String, Item> grupo : inventario.values()) {
			Iterator<Item> it = grupo.values().iterator();
			while (it.hasNext()) {
				Item item = it.next();
				if (item.getId().equals(id)) {
					item.restarCantidad(cantidad);
					if (item.getCantidad() <= 0) {
						it.remove();
					}
					return;
				}
			}
		}
	}

}
