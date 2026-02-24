package modeloMundo;

import modeloJugador.Inventario;
import modeloObjetos.Item;
import modeloPersonajes.NPC;

public class Astillero {
	// atributos
	private Inventario stock;
	private NPC tendero;
	private Item mejora1;
	private Item mejora2;

	public Astillero(NPC tendero, Item mejora1, Item mejora2) {
		this.tendero = tendero;
		stock = new Inventario();
		// hacer una copia de los items que se le pasa por constructor y anadirla al
		// stock
		this.mejora1 = new Item(mejora1);
		this.mejora2 = new Item(mejora2);
		stock.anadirItem(this.mejora1);
		stock.anadirItem(this.mejora2);
		for (String i : stock.getItems().keySet()) {
			stock.getItems().get(i).setCantidad(1);
		}
	}

	// getters y setters
	public Inventario getStock() {
		return stock;
	}

	public void setStock(Inventario stock) {
		this.stock = stock;
	}

	public NPC getTendero() {
		return tendero;
	}

	public void setTendero(NPC tendero) {
		this.tendero = tendero;
	}
}
