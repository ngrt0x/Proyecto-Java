package vista;

import java.util.Map;

import controlador.GestorTienda;
import modeloObjetos.Item;

public class VistaTienda {
	// atributos
	private GestorVista gestorVista = new GestorVista();

	// metodos propios
	public int mostrarStock(GestorTienda t) {
		int opcion;
		gestorVista.imprimirMensaje(t.getTendero().getPrimeraFrase());
		gestorVista.imprimirMensaje("");
		gestorVista.imprimirMensaje("=== STOCK ===");
		Map<String, Item> stock = t.getStock().getItems();
		for (String i : stock.keySet()) {
			int contador = 1;
			gestorVista.imprimirMensaje(contador + ". " + stock.get(i).getNombre() + ":");
			gestorVista.imprimirMensaje("\tPrecio: " + stock.get(i).getPrecio() + "g");
			gestorVista.imprimirMensaje("\tCantidad: " + stock.get(i).getCantidad());
			contador++;
		}
		gestorVista.imprimirMensaje("0. Salir de la tienda");
		opcion = gestorVista.pedirNum();
		while (opcion < 0 || opcion > stock.size()) {
			gestorVista.imprimirError("Introduce una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public int menuConfirmacion(Item i) {
		int opcion;
		gestorVista
				.imprimirMensaje("Esto es lo que quieres comprar?\n" + i.getNombre() + " por " + i.getPrecio() + "g?");
		gestorVista.imprimirMensaje("1. Si\n2. No");
		opcion = gestorVista.pedirNum();
		while (opcion != 1 || opcion != 2) {
			gestorVista.imprimirError("Introduce una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public void mensajeCompra(GestorTienda t) {
		gestorVista.imprimirMensaje(
				"Gracias por su compra " + t.getJugador().getNombre() + ". Espero volver a verle pronto!");
	}

	public void imprimirMensaje(String msg) {
		gestorVista.imprimirMensaje(msg);
	}
}
