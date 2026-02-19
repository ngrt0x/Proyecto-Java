package modeloJugador;

import java.util.HashMap;

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
	
	public HashMap<String, Item> getItems(){
		HashMap<String, Item> itemsYPescados = inventario.get("Items");
		itemsYPescados.putAll(inventario.get("Peces"));
		return itemsYPescados;
	}
	
	public HashMap<String, Item> getItem(){
		return inventario.get("Items");
	}
	
	public HashMap<String, Item> getPeces(){
		return inventario.get("Peces");
	}
	
	// metodos propios
	public void anadirItem(Item nuevoItem) {
		boolean yaEsta = false;
		for(HashMap<String, Item> grupo : inventario.values()) {
			for(Item item : grupo.values()) {
				if(item.getId().equals(nuevoItem.getId())) {
					yaEsta = true;
				}
			}
		}
		
		if(yaEsta) {
			for(HashMap<String, Item> grupo : inventario.values()) {
				for(Item item : grupo.values()) {
					if(item.getId().equals(nuevoItem.getId())) {
						item.sumarCantidad(1);
					}
				}
			}
		}else if(nuevoItem instanceof Pez) {
			inventario.get("Peces").put(nuevoItem.getId(), nuevoItem);
		}else {
			inventario.get("Items").put(nuevoItem.getId(), nuevoItem);
		}
		
		
	}
	
	public void restarItem(String id, int cantidad) {
		for(HashMap<String, Item> grupo : inventario.values()) {
			for(Item item : grupo.values()) {
				if(item.getId().equals(id)) {
					item.restarCantidad(cantidad);
					if(item.getCantidad() <= 1) {
						grupo.remove(item.getId());
					}
				}
			}
		}
	}

}
