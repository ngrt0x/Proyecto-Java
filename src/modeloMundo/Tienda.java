package modeloMundo;

import modeloJugador.Inventario;
import modeloObjetos.ArmamentoBarco;
import modeloObjetos.CanaPescar;
import modeloObjetos.Canon;
import modeloObjetos.Consumible;
import modeloObjetos.Item;
import modeloPersonajes.NPC;

public class Tienda {
	// atributos
	private Inventario stock;
	private NPC tendero = new NPC("Alexander el Tendero");
	
	private Item canaReforzada = new CanaPescar("Caña reforzada", "cana_reforzada", 150, 15);
	private Item canaFlexible = new CanaPescar("Caña flexible", "cana_flexible", 100, 17);
	private Item canaMaestra = new CanaPescar("Caña maestra", "cana_maestra", 300, 20);
	private Item ceboBueno = new Item("Cebo de alta calidad", "cebo_bueno", 50);
	private Item canones = new Canon("Cañones oxidados", "canones_base", 300, 15, 1);
	private Item armamentoReforzado = new ArmamentoBarco("Armamento Reforzado", "armamento_refor", 175, 20, 2);
	private Item brebajeSalud = new Consumible("Brebaje de Salud", "pot_salud", 75, "curar");
	private Item brebajeDefensa = new Consumible("Brebaje de Defensa", "pot_defensa", 75, "defensa");
	private Item brebajeIniciativa = new Consumible("Brebaje de Iniciativa", "pot_init", 75, "iniciativa");
	
	public Tienda() {
		tendero.setPrimeraFrase(tendero.getNombre()+": 'Echa un ojo si quieres, pero si no vas a comprar nada no me hagas perder el tiempo.'");
		stock = new Inventario();
		stock.anadirItem(canaFlexible);
		stock.anadirItem(canaReforzada);
		stock.anadirItem(canaMaestra);
		stock.anadirItem(ceboBueno);
		stock.anadirItem(canones);
		stock.anadirItem(armamentoReforzado);
		stock.anadirItem(brebajeSalud);
		stock.anadirItem(brebajeDefensa);
		stock.anadirItem(brebajeIniciativa);
		for (String i : stock.getItems().keySet()) {
			stock.getItems().get(i).setCantidad(2);
		}
	}

	//getters y setters
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
