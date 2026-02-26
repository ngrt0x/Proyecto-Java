package modeloJugador;

import java.util.HashMap;
import java.util.Iterator;

import modeloObjetos.Item;
import modeloObjetos.Pez;

/**
 * Clase Inventario, sirve para contener items
 * 
 * @author Jesús Manrique y Marcos Villagómez
 * @version 1.0
 */
public class Inventario {
	// atributos
	/**
	 * El inventario, contiene String (Inventario elegido), map de String (nombre item) y el item
	 */
	HashMap<String, HashMap<String, Item>> inventario;

	/**
	 * Constructor de Inventario, inicializa el inventario y añade las 2 categorias que hay, Items y peces
	 */
	public Inventario() {
		inventario = new HashMap<String, HashMap<String, Item>>();
		inventario.put("Items", new HashMap<>());
		inventario.put("Peces", new HashMap<>());
	}

	// getters y setters
	/**
	 * Getter de inventario
	 * @return El inventario
	 */
	public HashMap<String, HashMap<String, Item>> getInventario() {
		return inventario;
	}

	/**
	 * Getter de los items
	 * @return Una lista de todos los items dentro del inventario, sumando Items y Peces
	 */
	public HashMap<String, Item> getItems() {
		HashMap<String, Item> itemsYPescados = new HashMap<>();
		itemsYPescados.putAll(inventario.get("Items"));
		itemsYPescados.putAll(inventario.get("Peces"));
		return itemsYPescados;
	}

	/**
	 * Getter de item
	 * @return La lista de unicamente items
	 */
	public HashMap<String, Item> getItem() {
		return inventario.get("Items");
	}

	/**
	 * Getter de peces
	 * @return La lista de unicamente peces
	 */
	public HashMap<String, Item> getPeces() {
		return inventario.get("Peces");
	}

	// metodos propios
	/**
	 * Metodo para añadir items al inventario
	 * @param nuevoItem Item que quiera añadir
	 */
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
	
	/**
	 * Metodo para restar item del inventario
	 * @param id Id del item a eliminar
	 * @param cantidad Cantidad de items a eliminar
	 */
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
