package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import modeloJugador.Inventario;
import modeloJugador.Jugador;
import modeloObjetos.ArmamentoBarco;
import modeloObjetos.CanaPescar;
import modeloObjetos.Canon;
import modeloObjetos.Consumible;
import modeloObjetos.Item;
import modeloPersonajes.NPC;
import vista.VistaJuego;
import vista.VistaTienda;

public class GestorTienda {
	// atributos
	private VistaJuego vistaJuego = new VistaJuego();
	private VistaTienda vistaTienda = new VistaTienda();
	private Inventario stock;
	private NPC tendero = new NPC("Alexander el Tendero");
	private Jugador j;
	// Items disponibles
	private Item canaReforzada = new CanaPescar("Caña reforzada", "cana_reforzada", 150, 15);
	private Item canaFlexible = new CanaPescar("Caña flexible", "cana_flexible", 100, 17);
	private Item canaMaestra = new CanaPescar("Caña maestra", "cana_maestra", 300, 20);
	private Item ceboBueno = new Item("Cebo de alta calidad", "cebo_bueno", 50);
	private Item canones = new Canon("Cañones oxidados", "canones_base", 300, 15, 1);
	private Item armamentoReforzado = new ArmamentoBarco("Armamento Reforzado", "armamento_refor", 175, 20, 2);
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
			stock.getItems().get(i).setCantidad(2);
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
		int opcion = vistaTienda.hablarTendero(this);
		while (opcion != 0) {
			switch (opcion) {
			case 1 -> { // OPCION COMPRAR AL TENDERO
				int opcionCompra = vistaTienda.mostrarStock(this);
				while (opcionCompra != 0) {
					comprarItem(opcionCompra);
					opcionCompra = vistaTienda.mostrarStock(this);
				}
			}
			case 2 -> {
				int opcionVenta = venderItem();
				while (opcionVenta != -1) {
					opcionVenta = venderItem();
				}
			}
			}
			opcion = vistaTienda.hablarTendero(this);
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
						stock.restarItem(itemOriginal.getId(), 1);
					}
				}
			}
			contador++;
		}
	}

	private int venderItem() {
		int opcionInventario = vistaJuego.menuInventarios(); // ELIGE ENTRE ITEMS O PECES
		
		if (opcionInventario == 0) {
			return opcionInventario;
		}
		
		vistaJuego.mostrarInventario(j, opcionInventario); // MUESTRA EL INVENTARIO CORRESPONDIENTE
		
		int opcionVenta = vistaTienda.ventanaVenta(this, opcionInventario);
		opcionVenta--;
		Item itemVender = null;

		// Obtenemos el mapa correspondiente según la elección
		Map<String, Item> mapaSeleccionado = (opcionInventario == 1) ? j.getInventario().getItem()
				: j.getInventario().getPeces();

		// Validación de seguridad
		if (opcionVenta >= 0 && opcionVenta < mapaSeleccionado.size()) {
			// Convertimos los VALORES del mapa a una lista para acceder por posición
			List<Item> listaTemporal = new ArrayList<>(mapaSeleccionado.values());
			itemVender = listaTemporal.get(opcionVenta);
		}

		// Lógica de venta (si se encontró el ítem)
		if (itemVender != null) {
			j.sumarOro(itemVender.getPrecio()); // sumamos el oro al jugador
			mapaSeleccionado.remove(itemVender.getId()); // eliminamos el item del inventario
		}

		return opcionVenta;
	}
	
	
}