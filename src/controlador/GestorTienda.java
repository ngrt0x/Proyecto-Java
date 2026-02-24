package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import modeloJugador.Jugador;
import modeloMundo.Tienda;
import modeloObjetos.ArmamentoBarco;
import modeloObjetos.Item;
import vista.VistaJuego;
import vista.VistaTienda;

public class GestorTienda {
	// atributos
	private VistaJuego vistaJuego = new VistaJuego();
	private VistaTienda vistaTienda = new VistaTienda();
	private Jugador j;
	private Tienda t;

	// constructor
	public GestorTienda(Jugador jugador, Tienda tienda) {
		j = jugador;
		t = tienda;
	}

	// getters y setters
	public Jugador getJugador() {
		return j;
	}

	public Tienda getTienda() {
		return t;
	}

	// metodos propios
	public void entrarTienda() {
		int opcion = vistaTienda.hablarTendero1(t);
		while (opcion != 0) {
			switch (opcion) {
			case 1 -> { // OPCION COMPRAR AL TENDERO
				int opcionCompra = vistaTienda.mostrarStock(this, t);
				while (opcionCompra != 0) {
					comprarItem(opcionCompra);
					opcionCompra = vistaTienda.mostrarStock(this, t);
				}
			}
			case 2 -> {
				int opcionVenta = venderItem();
				while (opcionVenta != -1) {
					opcionVenta = venderItem();
				}
			}
			}
			opcion = vistaTienda.hablarTendero2(t);
		}
	}

	private void comprarItem(int opcion) {
		int confirmacion;
		int contador = 1;
		Map<String, Item> itemsALaVenta = t.getStock().getItems();
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
					vistaTienda.imprimirMensaje("No nos quedan existencias de " + itemOriginal.getNombre() + ".");
				} else {
					// muestra un mensaje de confirmacion de la compra
					confirmacion = vistaTienda.menuConfirmacionCompra(itemOriginal);
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
						j.restarOro(itemAComprar.getPrecio());
						t.getStock().restarItem(itemOriginal.getId(), 1);
					}
				}
			}
			contador++;
		}
	}

	private int venderItem() {
		int opcionInventario = vistaJuego.menuInventarios(); // ELIGE ENTRE ITEMS O PECES

		if (opcionInventario == 0) {
			return -1;
		}

		// Obtenemos el mapa correspondiente según la elección
		Map<String, Item> mapaSeleccionado = (opcionInventario == 1) ? j.getInventario().getItem()
				: j.getInventario().getPeces();

		// Si no tienes items disponibles para vender
		if (mapaSeleccionado.isEmpty()) {
			vistaTienda.imprimirMensaje("==== No tiene items disponibles para la venta  ====");
			return 0; // cancela todo
		}

		vistaJuego.mostrarInventario(j, opcionInventario); // MUESTRA EL INVENTARIO CORRESPONDIENTE

		int opcionVenta = vistaTienda.ventanaVenta(this, opcionInventario);
		opcionVenta--;
		Item itemVender = null;

		// Validación de seguridad
		if (opcionVenta >= 0 && opcionVenta < mapaSeleccionado.size()) {
			// Convertimos los VALORES del mapa a una lista para acceder por posición
			List<Item> listaTemporal = new ArrayList<>(mapaSeleccionado.values());
			itemVender = listaTemporal.get(opcionVenta);
		}

		// Menu de confirmación de venta (Ultima forma de cancelar la venta)
		if (vistaTienda.menuConfirmacionVenta(itemVender) == 2) {
			return 0;
		}

		// Lógica de venta (si se encontró el ítem)
		if (itemVender != null) {
			j.sumarOro(itemVender.getPrecio()); // sumamos el oro al jugador
			mapaSeleccionado.remove(itemVender.getId()); // eliminamos el item del inventario
			vistaTienda.mensajeVenta(this, itemVender); // imprimimos el mensaje de item vendido
		}

		return opcionVenta;
	}

}