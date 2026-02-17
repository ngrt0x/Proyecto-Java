package vista;

import java.util.Map;

import modeloJugador.Jugador;
import modeloObjetos.Item;

public class VistaJuego {
	// atributos
	private GestorVista gestorVista = new GestorVista();

	public int menuInicio() {
		int opcion;

		gestorVista.imprimirMensaje("Qué quieres hacer capitán? Cuál es el plan?\n" + "1. Pescar\n"
				+ "2. Ver inventario\n" + "3. Entrar a la tienda\n" + "4. Combate\n" + "0. Salir");
		opcion = gestorVista.pedirNum();
		while (opcion > 4 || opcion < 0) {
			gestorVista.imprimirError("Selecciona una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public void mostrarInventario(Jugador j) {
		gestorVista.imprimirMensaje("=== INVENTARIO ===");
		gestorVista.imprimirMensaje("Tu oro: " + j.getOro());
		Map<String, Item> inventario = j.getInventario().getItems();
		if (inventario.isEmpty()) {
			gestorVista.imprimirMensaje("Tienes el inventario vacío!");
		} else {
			for (String i : inventario.keySet()) {
				gestorVista.imprimirMensaje("- " + inventario.get(i).getNombre() + ":");
				gestorVista.imprimirMensaje("\tPrecio: " + inventario.get(i).getPrecio() + "g");
				gestorVista.imprimirMensaje("\tCantidad: " + inventario.get(i).getCantidad());
			}
		}
	}

	public String pedirNombre() {
		String texto;
		gestorVista.imprimirMensaje("Cómo te llamas capitán?");
		texto = gestorVista.pedirString();
		return texto;
	}

	public void mensajeDespedida(Jugador j) {
		gestorVista.imprimirError("Hasta luego " + j.getNombre() + "!");
	}
}