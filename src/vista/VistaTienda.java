package vista;

import java.util.HashMap;
import java.util.Map;

import controlador.GestorTienda;
import modeloJugador.Jugador;
import modeloMundo.Tienda;
import modeloObjetos.Item;

public class VistaTienda {
	// atributos
	private GestorVista gestorVista = new GestorVista();

	// metodos propios
	public int hablarTendero1(Tienda t) {
		int opcion;
		gestorVista.imprimirMensaje(
				t.getTendero().getNombre() + ": 'Buenas tardes.' El tendero te observa con cara de pocos amigos.\n"
						+ "'Eres nuevo por aquí verdad? Más te vale no andar armando jaleo. Qué te puedo ofrecer?'\n"
						+ "1. Comprar\n" + "2. Vender\n" + "0. Salir");
		opcion = gestorVista.pedirNum();
		while (opcion < 0 || opcion > 2) {
			gestorVista.imprimirError("Introduce una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public int hablarTendero2(Tienda t) {
		int opcion;
		gestorVista.imprimirMensaje(t.getTendero().getNombre() + ": 'Qué te puedo ofrecer?'\n" + "1. Comprar\n"
				+ "2. Vender\n" + "0. Salir");
		opcion = gestorVista.pedirNum();
		while (opcion < 0 || opcion > 2) {
			gestorVista.imprimirError("Introduce una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public int mostrarStock(GestorTienda gt, Tienda t) {
		int opcion;
		gestorVista.imprimirMensaje(t.getTendero().getPrimeraFrase());
		gestorVista.imprimirMensaje("");
		gestorVista.imprimirMensaje("======================== STOCK ========================");
		Map<String, Item> stock = t.getStock().getItems();
		int contador = 1;
		for (String i : stock.keySet()) {
			gestorVista.imprimirMensaje(contador + ". " + stock.get(i).getNombre() + ":");
			gestorVista.imprimirMensaje("\tPrecio: " + stock.get(i).getPrecio() + "g");
			gestorVista.imprimirMensaje("\tCantidad: " + stock.get(i).getCantidad());
			contador++;
		}
		gestorVista.imprimirMensaje("=======================================================");
		gestorVista.imprimirMensaje("Tu oro: " + gt.getJugador().getOro() + "g");
		gestorVista.imprimirMensaje("0. Salir de la tienda");
		opcion = gestorVista.pedirNum();
		while (opcion < 0 || opcion > stock.size()) {
			gestorVista.imprimirError("Introduce una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public int ventanaVenta(GestorTienda t, int opcionInventario) {
		Jugador j = t.getJugador();

		gestorVista.imprimirMensaje("Que quiere vender capitan?");
		int opcionVenta = gestorVista.pedirNum(); // PEDIR EL NUMERO DEL ITEM EN LA LISTA DEL INVENTARIO

		HashMap<String, Item> inventario = (opcionInventario == 1) ? j.getInventario().getItem() // SI OPCION INVENTARIO
																									// ES 1
				: j.getInventario().getPeces(); // SI OPCION INVENTARIO ES 2

		while (opcionVenta < 1 || opcionVenta > inventario.size()) {
			gestorVista.imprimirError("Elija un valor valido");
			opcionVenta = gestorVista.pedirNum();
		}

		return opcionVenta; // return DEL NUMERO DE ITEM
	}

	public int menuConfirmacionCompra(Item i) {
		int opcion;
		gestorVista
				.imprimirMensaje("Esto es lo que quieres comprar?\n" + i.getNombre() + " por " + i.getPrecio() + "g?");
		gestorVista.imprimirMensaje("1. Si\n2. No");
		opcion = gestorVista.pedirNum();
		while (opcion < 1 || opcion > 2) {
			gestorVista.imprimirError("Introduce una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public int menuConfirmacionVenta(Item i) {
		int opcion;
		gestorVista.imprimirMensaje("Seguro que quieres vender " + i.getNombre() + " por " + i.getPrecio() + "g?");
		gestorVista.imprimirMensaje("1. Si\n2. No");
		opcion = gestorVista.pedirNum();
		while (opcion < 1 || opcion > 2) {
			gestorVista.imprimirError("Introduce una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public void mensajeCompra(GestorTienda t) {
		gestorVista.imprimirMensaje("Gracias por su compra.");
	}

	public void mensajeVenta(GestorTienda t, Item itemVendido) {
		gestorVista.imprimirMensaje(
				"Has vendido x1 " + itemVendido.getNombre() + ", por " + itemVendido.getPrecio() + "g.");
	}

	public void imprimirMensaje(String msg) {
		gestorVista.imprimirMensaje(msg);
	}
}
