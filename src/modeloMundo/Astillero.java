package modeloMundo;

import modeloJugador.Inventario;
import modeloObjetos.Item;
import modeloPersonajes.NPC;

/**
 * Esta clase representa la tienda de mejoras para el barco (que ayudaran en combate).
 * 
 * @author Jesús Manrique y Marcos Villagómez
 * @version 1.0
 */
public class Astillero {
	// atributos
	/** El stock (lo que se vende) en la tienda */
	private Inventario stock;
	/** El NPC que te atiende dentro de la tienda */
	private NPC tendero;
	/** Primera mejora disponible en la tienda */
	private Item mejora1;
	/** Segunda mejora disponible en la tienda */
	private Item mejora2;

	/**
	 * Constructor de Astillero
	 * 
	 * @param tendero NPC de la tienda
	 * @param mejora1 Mejora1 disponible en la tienda
	 * @param mejora2 Mejora2 disponible en la tienda
	 */
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
	/**
	 * Getter del stock
	 * @return El inventario con lo que hay a la venta en la tienda
	 */
	public Inventario getStock() {
		return stock;
	}

	/**
	 * Setter del stock
	 * @param stock Le das un Inventario stock y lo sobrepone en el inventario de la tienda
	 */
	public void setStock(Inventario stock) {
		this.stock = stock;
	}

	/**
	 * Getter del tendero
	 * @return El NPC de la tienda
	 */
	public NPC getTendero() {
		return tendero;
	}

	/**
	 * Setter del tender
	 * @param tendero Le das un NPC (de tienda) y lo coloca en la tienda
	 */
	public void setTendero(NPC tendero) {
		this.tendero = tendero;
	}
}
