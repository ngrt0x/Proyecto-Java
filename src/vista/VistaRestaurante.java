package vista;

import java.util.ArrayList;

import controlador.MinijuegoRestaurante;
import modeloJugador.Jugador;
import modeloPersonajes.Cliente;

public class VistaRestaurante {
	// atributos
	private GestorVista gestorVista = new GestorVista();

	// metodos propios
	public void hacerPedido(Cliente c) {
		if (c.getPedido().length == 1) {
			gestorVista.imprimirMensaje("Buenas tardes! Ponme por favor un " + c.getPedido()[0].getNombre() + "!");
		} else if (c.getPedido().length == 2) {
			gestorVista.imprimirMensaje(
					"Aupa jefe, ponme un " + c.getPedido()[0].getNombre() + " y un " + c.getPedido()[1].getNombre() + "!");
		} else if (c.getPedido().length == 3) {
			gestorVista.imprimirMensaje(
					"Buen día capitán, ponme un " + c.getPedido()[0].getNombre() + ", un " + c.getPedido()[1].getNombre()
							+ " y un " + c.getPedido()[2].getNombre() + "! Y rapidito que estoy muerto de hambre!");
		}
		gestorVista.imprimirMensaje("");
	}

	// imprime el mensaje cuando inicias el minijuego de restaurante
	public void mensajeInicio(Jugador j) {
		gestorVista.imprimirMensaje("=== Comienza el turno de comidas, encended los fogones! ===");
		gestorVista.imprimirMensaje(
				"El barco restaurante de " + j.getNombre() + " abre sus puertas, poneros a la fila si queréis comer!");
		gestorVista.imprimirMensaje("");
	}

	// muestra el menu del minijuego y devuelve la opcion que seleccione el usuario
	public int menuRestaurante(MinijuegoRestaurante r) {
		int longitudBarra = 20;
		int opcion;
		// muestra menu
		gestorVista.imprimirMensaje("Los clientes no se van a servir solos, qué vas a hacer?");
		gestorVista.imprimirMensaje("1. Preparar plato\n" + "2. Servir cliente\n" + "3. Mostrar preparaciones");
		gestorVista.imprimirMensaje("");

		// muestra lo que queda de turno
		gestorVista.imprimirMensaje("Servicio: ");
		int turnoActual = r.getTurnoActual();
		int duracionTurno = r.getDuracionTurno();
		int bloquesLlenos = (int) ((double) turnoActual / duracionTurno * longitudBarra);
		int bloquesVacios = longitudBarra - bloquesLlenos;
		for (int j = 0; j < bloquesLlenos; j++) {
			gestorVista.imprimirMensajePegado("█");
		}
		for (int j = 0; j < bloquesVacios; j++) {
			gestorVista.imprimirMensajePegado("░");
		}
		gestorVista.imprimirMensaje("");
		gestorVista.imprimirMensaje("=======================================================");

		// el usuario introduce la el numero de la opcion que quiera realizar
		opcion = gestorVista.pedirNum();
		while (opcion < 1 || opcion > 3) {
			gestorVista.imprimirError("Selecciona una opción válda: ");
			opcion = gestorVista.pedirNum();
		}

		return opcion;
	}

	// muestra los clientes en la lista clientes junto a su nombre, sus pedidos, y
	// su paciencia
	public void mostrarClientes(ArrayList<Cliente> clientes) {
		int longitudBarra = 20;

		gestorVista.imprimirMensaje("====================== CLIENTES =======================");
		for (Cliente c : clientes) {
			// mostrar el pedido del cliente
			gestorVista.imprimirMensaje(c.getNombre() + " quiere:");
			for (int i = 0; i < c.getPedido().length; i++) {
				gestorVista.imprimirMensaje("➤ " + c.getPedido()[i].getNombre());
			}
			// mostrar la barra de paciencia
			gestorVista.imprimirMensaje("Paciencia:");
			int pacienciaActual = c.getPacienciaActual();
			int pacienciaTope = c.getPacienciaTope();
			int bloquesLlenos = (int) ((double) pacienciaActual / pacienciaTope * longitudBarra);
			int bloquesVacios = longitudBarra - bloquesLlenos;
			for (int j = 0; j < bloquesLlenos; j++) {
				gestorVista.imprimirMensajePegado("█");
			}
			for (int j = 0; j < bloquesVacios; j++) {
				gestorVista.imprimirMensajePegado("░");
			}
			gestorVista.imprimirMensaje("");
			gestorVista.imprimirMensaje("");
		}
		gestorVista.imprimirMensaje("=======================================================");
	}

	public void mensajeLlegaCliente() {
		gestorVista.imprimirMensaje("Ha llegado un cliente!");
	}

	public int mostarIngredientes(String[] ingredientes) {
		int opcion;
		for (int i = 0; i < ingredientes.length; i++) {
			gestorVista.imprimirMensaje((i + 1) + ". " + ingredientes[i]);
		}
		opcion = gestorVista.pedirNum();
		while (opcion < 1 || opcion > ingredientes.length) {
			gestorVista.imprimirError("Selecciona una opción válda: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}
}
