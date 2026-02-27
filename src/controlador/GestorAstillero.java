package controlador;

import java.util.Map;

import modeloJugador.Jugador;
import modeloMundo.Astillero;
import modeloObjetos.ArmamentoBarco;
import modeloObjetos.Item;
import vista.VistaTienda;

/**
 * Controlador del sistema del Astillero. Casi idéntico al GestorTienda solo que
 * sin la función de vender Item.
 * 
 * @author Jesús Manrique, Marcos Villagómez
 * @version 1.0
 */
public class GestorAstillero {
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
	 * Referencia al objeto Astillero a gestionar.
	 */
	private Astillero ast;

	/**
	 * Constructor de la clase GestorAstillero.
	 * 
	 * @param jugador El Jugador que va a interactuar con la Tienda.
	 * @param ast     El Astillero a gestionar.
	 */
	public GestorAstillero(Jugador jugador, Astillero ast) {
		j = jugador;
		this.ast = ast;
	}

	/**
	 * Devuelve la referencia al Jugador que está interactuando con la Tienda.
	 * 
	 * @return j
	 */
	public Jugador getJugador() {
		return j;
	}

	/**
	 * Devuelve la referencia al Astillero que se está gestionando.
	 * 
	 * @return ast
	 */
	public Astillero getAstillero() {
		return ast;
	}

	// metodos propios
	/**
	 * Inicia la lógica del Astillero. Conecta con vistaTienda para mostrar los
	 * menús y gestionar las opciones introducidas por el usuario.
	 */
	public void entrarTienda() {
		int opcion;
		opcion = vistaTienda.hablarAstillero1(j.getIslaActual());
		while (opcion != 0) {
			int opcionCompra = vistaTienda.mostrarMejoras(this, ast);
			while (opcionCompra != 0) {
				comprarMejora(opcionCompra);
				opcionCompra = vistaTienda.mostrarMejoras(this, ast);
			}
			opcion = vistaTienda.hablarAstillero2(j.getIslaActual());
		}
	}

	/**
	 * Gestiona la compra de Item. Conecta con el stock del Astillero, crea una
	 * copia del Item seleccionado y lo añade al Inventario del jugador a la vez que
	 * lo resta o borra del stock de la tienda.
	 * 
	 * @param opcion Corresponde al index del objeto a comprar del stock del
	 *               Astillero + 1.
	 */
	private void comprarMejora(int opcion) {
		int confirmacion;
		int contador = 1;
		Map<String, Item> itemsALaVenta = ast.getStock().getItems();
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
						j.getBarco().getInventarioB().anadirArmamento((ArmamentoBarco) itemAComprar);
						vistaTienda.mensajeMejora(this);
						// tras la compra se resta 1 al stock
						j.restarOro(itemAComprar.getPrecio());
						ast.getStock().restarItem(itemOriginal.getId(), 1);
					}
				}
			}
			contador++;
		}
	}
}
