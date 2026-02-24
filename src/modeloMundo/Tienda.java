package modeloMundo;

import modeloJugador.Inventario;
import modeloObjetos.CanaPescar;
import modeloObjetos.Consumible;
import modeloObjetos.Item;
import modeloPersonajes.NPC;

public class Tienda {
	// atributos
	private Inventario stock;
	private NPC tendero;
	private Item item1;
	private Item item2;
	private Item item3;
	private Item item4;
	private Item item5;

	public Tienda(NPC tendero, Item item1, Item item2, Item item3, Item item4, Item item5) {
		this.tendero = tendero;
		stock = new Inventario();
		// hacer una copia de los items que se le pasa por constructor y anadirla al
		// stock
		this.item1 = new Item(item1);
		this.item2 = new Item(item2);
		this.item3 = new Item(item3);
		this.item4 = new Item(item4);
		this.item5 = new Item(item5);
		stock.anadirItem(this.item1);
		stock.anadirItem(this.item2);
		stock.anadirItem(this.item3);
		stock.anadirItem(this.item4);
		stock.anadirItem(this.item5);
		for (String i : stock.getItems().keySet()) {
			if (stock.getItems().get(i) instanceof CanaPescar) {
				stock.getItems().get(i).setCantidad(1);
			} else if (stock.getItems().get(i) instanceof Consumible) {
				stock.getItems().get(i).setCantidad(3);
			} else {
				stock.getItems().get(i).setCantidad(5);
			}
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
