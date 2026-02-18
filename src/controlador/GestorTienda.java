package controlador;

import java.util.Map;

import modeloJugador.Inventario;
import modeloJugador.Jugador;
import modeloObjetos.ArmamentoBarco;
import modeloObjetos.CanaPescar;
import modeloObjetos.Consumible;
import modeloObjetos.Canon;
import modeloObjetos.Item;
import modeloPersonajes.NPC;
import vista.VistaTienda;

public class GestorTienda {
	// atributos
	private VistaTienda vistaTienda = new VistaTienda();
	private Inventario stock;
	private NPC tendero = new NPC("Alexander el Tendero");
	private Jugador j;
	// Items disponibles
	private Item canaReforzada = new CanaPescar("Caña reforzada", "cana_reforzada", 150, 15);
	private Item canaFlexible = new CanaPescar("Caña flexible", "cana_flexible", 100, 17);
	private Item canaMaestra = new CanaPescar("Caña maestra", "cana_maestra", 300, 20);
	private Item ceboBueno = new Item("Cebo de alta calidad", "cebo_bueno", 50);
	private Item canones = new Canon("Cañones de banda", "canones_base", 300, 20);
	private Item armamentoReforzado = new ArmamentoBarco("Armamento Reforzado", "armamento_refor", 175, 20);
	private Item brebajeSalud = new Consumible("Brebaje de Salud", "pot_salud", 75, "curar");
	private Item brebajeDefensa = new Consumible("Brebaje de Defensa", "pot_defensa", 75, "defensa");
	private Item brebajeIniciativa = new Consumible("Brebaje de Iniciativa", "pot_init", 75, "iniciativa");

	// constructor
	public GestorTienda(Jugador jugador) {
		j = jugador;
		tendero.setPrimeraFrase("Buenas tarde capitán, qué le puedo ofrecer hoy?");
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
			stock.getItems().get(i).setCantidad(1);
		}
	}

	// getters y setters
	public Inventario getStock() {
		return stock;
	}

	public NPC getTendero() {
		return tendero;
	}

	public Jugador getJugador() {
		return j;
	}

	// metodos propios
	public void entrarTienda() {
		int opcion = vistaTienda.mostrarStock(this);
		while (opcion != 0) {
			comprarItem(opcion);
			opcion = vistaTienda.mostrarStock(this);
		}
	}

	private void comprarItem(int opcion) {
		int confirmacion;
		int contador = 1;
		Map<String, Item> itemsALaVenta = stock.getItems();
		// busca el item que el usuario ha seleccionado en el menu previamente, inicia
		// un contador y compara ese contador hasta que la opcion indica el item
		// seleccionado
		for (String i : itemsALaVenta.keySet()) {
			if (opcion == contador) {
				Item itemOriginal = itemsALaVenta.get(i);
				Item itemAComprar = new Item(itemOriginal);
				// comprueba que el jugador tenga suficiente dinero para comprar el item
				if (j.getOro() - itemOriginal.getPrecio() < 0) {
					vistaTienda.imprimirMensaje("No tienes suficiente dinero para comprar eso!");
					// comprueba que hay stock del item seleccionado
				} else if (itemOriginal.getCantidad() <= 0) {
					vistaTienda.imprimirMensaje(
							"No nos quedan existencias de " + itemOriginal.getNombre() + ", mil disculpas!");
				} else {
					// muestra un mensaje de confirmacion de la compra
					confirmacion = vistaTienda.menuConfirmacion(itemOriginal);
					if (confirmacion != 1) {
						return;
					} else {
						// comprueba a ver si el item comprado es equipamiento de barco u otro tipo de
						// item, para anadirlo al inventario del barco o al del jugador
						if (itemOriginal instanceof ArmamentoBarco) {
							j.getBarco().getInventarioB().anadirArmamento((ArmamentoBarco) itemAComprar);
						} else {
							j.getInventario().anadirItem(itemAComprar);
						}
						vistaTienda.mensajeCompra(this);
						// tras la compra se resta 1 al stock
						itemOriginal.restarCantidad(1);
					}
				}
			}
			contador++;
		}
	}

	// hay que crear un menu en vistaTienda que te de la opcion de hablar con el
	// tendero, comprar o vender items, imprimir ese menu desde entrarTienda(), de
	// ahi con la opcion que introduzca el usuario empezar con la logica de mostrar
	// stock para comprar, mostrar el inventario del jugador para vender, y hacer lo
	// correspondiente. Hablar con el tendero por lo pronto nos lo podemos ahorrar.
	private void venderItem() {

	}
}