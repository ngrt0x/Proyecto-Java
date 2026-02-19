package vista;

import java.util.Map;

import controlador.GestorTienda;
import modeloObjetos.Item;

public class VistaTienda {
	// atributos
	private GestorVista gestorVista = new GestorVista();

	// metodos propios
	public int hablarTendero(GestorTienda t) {
		int opcion;
		gestorVista.imprimirMensaje("Hola " + t.getJugador().getNombre() + ", veo que estas en mi tienda, pasa a que charlemos un rato!\n"
				+ "1. Comprar\n"
				+ "2. Vender\n"
				+ "0. Salir");
		opcion = gestorVista.pedirNum();
		while(opcion < 0 || opcion > 2) {
			gestorVista.imprimirError("Introduce una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}
	
	public int mostrarStock(GestorTienda t) {
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
		while (opcion < 1 || opcion > 2) {
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
