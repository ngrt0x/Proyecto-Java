package controlador;

import java.util.Map;

import modeloJugador.Inventario;
import modeloJugador.Jugador;
import modeloObjetos.ArmamentoBarco;
import modeloObjetos.Consumible;
import modeloObjetos.Canon;
import modeloObjetos.Item;
import modeloPersonajes.NPC;
import vista.VistaTienda;

public class GestorTienda {
	// atributos
	private VistaTienda vistaTienda = new VistaTienda();
	private Inventario stock;
	private NPC tendero = new NPC("Alexander el Tendero", "Buenos días capitán, que le puedo ofrecer hoy?");
	private Jugador j;
	// Items disponibles
	private Item canaReforzada = new Item("Caña reforzada", "cana_reforzada", 150, 1);
	private Item canaFlexible = new Item("Caña flexible", "cana_flexible", 100, 1);
	private Item canaMaestra = new Item("Caña maestra", "cana_maestra", 300, 1);
	private Item ceboBueno = new Item("Cebo de alta calidad", "cebo_bueno", 50, 5);
	private Item canones = new Canon("Cañones de banda", "canones_base", 300, 1, 20);
	private Item armamentoReforzado = new ArmamentoBarco("Armamento Reforzado", "armamento_refor", 175, 1, 20);
	private Item brebajeSalud = new Consumible("Brebaje de Salud", "pot_salud", 75, 5, "curar");
	private Item brebajeDefensa = new Consumible("Brebaje de Defensa", "pot_defensa", 75, 5, "defensa");
	private Item brebajeIniciativa = new Consumible("Brebaje de Iniciativa", "pot_init", 75, 5, "iniciativa");

	// constructor
	public GestorTienda(Jugador jugador) {
		j = jugador;
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
				// comprueba que el jugador tenga suficiente dinero para comprar el item
				if (j.getOro() - itemsALaVenta.get(i).getPrecio() < 0) {
					vistaTienda.imprimirMensaje("No tienes suficiente dinero para comprar eso!");
				} else {
					// muestra un mensaje de confirmacion de la compra
					confirmacion = vistaTienda.menuConfirmacion(itemsALaVenta.get(i));
					if (confirmacion != 1) {
						return;
					} else {
						// comprueba a ver si el item comprado es equipamiento de barco u otro tipo de
						// item, para anadirlo al inventario del barco o al del jugador
						if (itemsALaVenta.get(i) instanceof ArmamentoBarco) {
							j.getBarco().getInventarioB().anadirArmamento((ArmamentoBarco) itemsALaVenta.get(i));
						} else {
							j.getInventario().anadirItem(itemsALaVenta.get(i));
						}
						vistaTienda.mensajeCompra(this);
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