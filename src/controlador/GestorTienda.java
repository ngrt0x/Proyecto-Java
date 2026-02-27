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

/**
 * Controlador del sistema de Tienda.
 * 
 * @author Jesús Manrique, Marcos Villagómez
 * @version 1.0
 */
public class GestorTienda {
	/**
	 * Instancia de VistaJuego.
	 */
	private VistaJuego vistaJuego = new VistaJuego();
	/**
	 * Instancia de VistaTienda, la vista correspondiente a los menús e información
	 * de la Tienda.
	 */
	private VistaTienda vistaTienda = new VistaTienda();
	/**
	 * Referencia al objeto Jugador.
	 */
	private Jugador j;
	/**
	 * Referencia al objeto Tienda a gestionar.
	 */
	private Tienda t;

	/**
	 * Constructor de la clase GestorTienda.
	 * 
	 * @param jugador Jugador que va a interactuar con la Tienda.
	 * @param tienda  Tienda a gestionar.
	 */
	public GestorTienda(Jugador jugador, Tienda tienda) {
		j = jugador;
		t = tienda;
	}

	// getters y setters
	/**
	 * Devuelve la referencia al Jugador que está interactuando con la Tienda.
	 * 
	 * @return j
	 */
	public Jugador getJugador() {
		return j;
	}

	/**
	 * Devuelve la referencia a la Tienda que se está gestionando.
	 * 
	 * @return t
	 */
	public Tienda getTienda() {
		return t;
	}

	/**
	 * Inicia la lógica de la Tienda. Conecta con vistaTienda para mostrar los menús
	 * y gestionar las opciones introducidas por el usuario.
	 */
	public void entrarTienda() {
		int opcion;
		opcion = vistaTienda.hablarTendero1(j.getIslaActual());
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
			opcion = vistaTienda.hablarTendero2(j.getIslaActual());
		}
	}

	/**
	 * Gestiona la compra de Item.
	 * <p>
	 * Conecta con el stock de la Tienda, crea una copia del Item seleccionado y lo
	 * añade al Inventario del jugador a la vez que lo resta o borra del stock de la
	 * tienda, y le resta al Jugador el oro correspondiente al valor del objeto.
	 * </p>
	 * 
	 * @param opcion Index del objeto a comprar del stock de la Tienda + 1.
	 */
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

	/**
	 * Gestiona la venta de Item.
	 * <p>
	 * Conecta con el Inventario del Jugador, resta o borra el Item del Inventario y
	 * le suma el oro correspondiente al valor del Item.
	 * </p>
	 * 
	 * @return Integer correspondiente a la opción que seleccione el usuario en el
	 *         menú ventanaVenta.
	 */
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
			vistaTienda.imprimirMensaje("==== No tienes items disponibles para la venta  ====");
			return 0; // cancela todo
		}

		vistaTienda.mostrarInventario(j, opcionInventario); // MUESTRA EL INVENTARIO CORRESPONDIENTE

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