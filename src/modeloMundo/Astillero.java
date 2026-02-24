package modeloMundo;

import modeloJugador.Inventario;
import modeloObjetos.ArmamentoBarco;
import modeloObjetos.Canon;
import modeloObjetos.Item;
import modeloPersonajes.NPC;

public class Astillero {
	// atributos
	private Inventario stock;
	private NPC tendero = new NPC("Rodrigo 'El Mañoso'");

	private Item canones = new Canon("Cañones oxidados", "canones_base", 300, 15, 1);
	private Item armamentoReforzado = new ArmamentoBarco("Armamento Reforzado", "armamento_refor", 175, 20, 2);

	public Astillero() {
		tendero.setPrimeraFrase(tendero.getNombre()
				+ ": 'En 'Astilleros El Mañoso S.L.' sólo usamos materiales de la más alta calidad! Realmente no, pero no hay mucha competencia\n"
				+ "por aquí, así que mis clientes no se pueden quejar del trabajo que hago.");
		stock = new Inventario();
		stock.anadirItem(canones);
		stock.anadirItem(armamentoReforzado);
		for (String i : stock.getItems().keySet()) {
			stock.getItems().get(i).setCantidad(2);
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
