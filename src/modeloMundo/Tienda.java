package modeloMundo;

import modeloJugador.Inventario;
import modeloObjetos.CanaPescar;
import modeloObjetos.Consumible;
import modeloObjetos.Item;
import modeloPersonajes.NPC;

/**
 * Clase Tienda, contiene Tendero e Items a la venta
 * 
 * @author Jesús Manrique y Marcos Villagómez
 * @version 1.0
 */
public class Tienda {
	// atributos
	/**
	 * Stock de items que tiene disponible la tienda
	 */
	private Inventario stock;
	/**
	 * NPC a cargo de la tienda
	 */
	private NPC tendero;
	/**
	 * Item1 disponible en la tienda
	 */
	private Item item1;
	/**
	 * Item2 disponible en la tienda
	 */
	private Item item2;
	/**
	 * Item3 disponible en la tienda
	 */
	private Item item3;
	/**
	 * Item4 disponible en la tienda
	 */
	private Item item4;
	/**
	 * Item5 disponible en la tienda
	 */
	private Item item5;

	/**
	 * Constructor de la tienda
	 * @param tendero NPC a cargo de la tienda
	 * @param item1 Item disponnible en la tienda
	 * @param item2 Item disponnible en la tienda
	 * @param item3 Item disponnible en la tienda
	 * @param item4 Item disponnible en la tienda
	 * @param item5 Item disponnible en la tienda
	 */
	public Tienda(NPC tendero, CanaPescar item1, Consumible item2, Consumible item3, Consumible item4, Item item5) {
		this.tendero = tendero;
		stock = new Inventario();
		// hacer una copia de los items que se le pasa por constructor y anadirla al
		// stock
		this.item1 = new CanaPescar(item1);
		this.item2 = new Consumible(item2);
		this.item3 = new Consumible(item3);
		this.item4 = new Consumible(item4);
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
	/**
	 * Getter del Inventario Stock
	 * @return Inventario de items a la venta
	 */
	public Inventario getStock() {
		return stock;
	}
	
	/**
	 * Setter de stock
	 * @param stock Nuevo Inventario de items para la tienda
	 */
	public void setStock(Inventario stock) {
		this.stock = stock;
	}

	/**
	 * Getter de tendero
	 * @return NPC tendero
	 */
	public NPC getTendero() {
		return tendero;
	}

	/**
	 * Setter de tendero
	 * @param tendero Nuevo tendero para la tienda
	 */
	public void setTendero(NPC tendero) {
		this.tendero = tendero;
	}
}
