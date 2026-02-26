package controlador;

import java.util.Map;

import modeloJugador.Jugador;
import modeloMundo.Astillero;
import modeloObjetos.ArmamentoBarco;
import modeloObjetos.Item;
import vista.VistaTienda;

public class GestorAstillero {
	// atributos
	private VistaTienda vistaTienda = new VistaTienda();
	private Jugador j;
	private Astillero ast;

	// constructor
	public GestorAstillero(Jugador jugador, Astillero ast) {
		j = jugador;
		this.ast = ast;
	}

	// getters y setters
	public Jugador getJugador() {
		return j;
	}

	public Astillero getAstillero() {
		return ast;
	}

	// metodos propios
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
