package vista;

import java.util.ArrayList;
import java.util.Random;

import controlador.MinijuegoRestaurante;
import modeloJugador.Jugador;
import modeloObjetos.Plato;
import modeloPersonajes.Cliente;

public class VistaRestaurante {
	// atributos
	private GestorVista gestorVista = new GestorVista();
	private final Random ALEATORIO = new Random();

	// metodos propios
	public void hacerPedido(Cliente c) {
		if (c.getPedido().size() == 1) {
			gestorVista.imprimirMensaje(
					c.getNombre() + ": 'Buenas tardes! Ponme por favor un " + c.getPedido().get(0).getNombre() + "!'");
		} else if (c.getPedido().size() == 2) {
			gestorVista.imprimirMensaje(c.getNombre() + ": 'Aupa jefe, ponme un " + c.getPedido().get(0).getNombre()
					+ " y un " + c.getPedido().get(1).getNombre() + "!'");
		} else if (c.getPedido().size() == 3) {
			gestorVista.imprimirMensaje(c.getNombre() + ": 'Buen día capitán, ponme un "
					+ c.getPedido().get(0).getNombre() + ", un " + c.getPedido().get(1).getNombre() + " y un "
					+ c.getPedido().get(2).getNombre() + "! Y rapidito que estoy muerto de hambre!'");
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
	public int menuRestaurante(MinijuegoRestaurante r, boolean consumirTurno) {
		int longitudBarra = 20;
		int opcion;
		// muestra menu
		gestorVista.imprimirMensaje("Los clientes no se van a servir solos, qué vas a hacer?");
		gestorVista.imprimirMensaje("1. Preparar plato\n" + "2. Servir cliente\n" + "3. Mostrar preparaciones");
		gestorVista.imprimirMensaje("");

		// muestra lo que queda de turno
		if (consumirTurno) {
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
		}

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
			for (int i = 0; i < c.getPedido().size(); i++) {
				gestorVista.imprimirMensaje("➤ " + c.getPedido().get(i).getNombre());
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
		gestorVista.imprimirMensaje("");
		gestorVista.imprimirMensaje("Ha llegado un cliente!");
	}

	public int mostarIngredientes(String[] ingredientes) {
		int opcion;
		gestorVista.imprimirMensaje("Qué ingrediente quieres añadir a tu plato?");
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

	public int menuPreparacion() {
		int opcion;
		gestorVista.imprimirMensaje("Qué quieres hacer ahora?\n1. Añadir otro ingrediente\n2. Cocinar plato");
		opcion = gestorVista.pedirNum();
		while (opcion < 1 || opcion > 2) {
			gestorVista.imprimirError("Selecciona una opción válda: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public int mostrarClientesAServir(ArrayList<Cliente> clientes) {
		int opcion;
		gestorVista.imprimirMensaje("A qué cliente quieres servirle su comanda?");
		for (int i = 0; i < clientes.size(); i++) {
			Cliente c = clientes.get(i);
			gestorVista.imprimirMensaje((i + 1) + ". " + c.getNombre() + " quiere:");
			for (int j = 0; j < c.getPedido().size(); j++) {
				gestorVista.imprimirMensaje("➤ " + c.getPedido().get(j).getNombre());
			}
		}
		gestorVista.imprimirMensaje("\n0. Atrás");

		opcion = gestorVista.pedirNum();
		while (opcion < 0 || opcion > clientes.size()) {
			gestorVista.imprimirError("Selecciona una opción válda: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public int mostrarPlatosPreparados(ArrayList<Plato> platosPreparados) {
		int opcion;
		if (platosPreparados.isEmpty()) {
			gestorVista.imprimirMensaje("No tienes ningún plato preparado!");
		} else {
			gestorVista.imprimirMensaje("Qué plato quieres entregar al cliente?");
			for (int i = 0; i < platosPreparados.size(); i++) {
				gestorVista.imprimirMensaje((i + 1) + ". " + platosPreparados.get(i).getNombre());
				gestorVista.imprimirMensaje("\tCantidad: " + platosPreparados.get(i).getCantidad());
			}
		}

		gestorVista.imprimirMensaje("\n0. Atrás");

		opcion = gestorVista.pedirNum();
		while (opcion < 0 || opcion > platosPreparados.size()) {
			gestorVista.imprimirError("Selecciona una opción válda: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public int menuServir() {
		int opcion;
		gestorVista.imprimirMensaje("Qué quieres hacer ahora?\n1. Entregar otro plato\n2. Comanda lista\n\n0. Atrás");
		opcion = gestorVista.pedirNum();
		while (opcion < 0 || opcion > 2) {
			gestorVista.imprimirError("Selecciona una opción válda: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public void mensajesPedidoCorrecto(Cliente c, int oro) {
		String[] mensajes = { "El mar estaría orgulloso.", "Por fin algo mejor que pan duro y arrepentimiento.",
				"Ahora sí que vale la pena no haber muerto hoy.", "Esto sí que es comida de verdad capitán!",
				"Con esto puedo hundir barcos hasta mañana.", "Cóbrate lo que sea jefe, esto sabe a victoria.",
				"Por esto merece la pena atracar en puerto.", "Aceptable. Sorprendentemente aceptable." };
		gestorVista.imprimirMensaje("Has atendido satisfactoriamente a " + c.getNombre() + ".");
		gestorVista.imprimirMensaje(c.getNombre() + ": '" + mensajes[ALEATORIO.nextInt(mensajes.length)] + "'");
		gestorVista.imprimirMensaje("Recibes " + oro + " monedas de oro por el buen servicio!");
		gestorVista.imprimirMensaje("");

	}

	public void mensajesPedidoIncorrecto(Cliente c) {
		String[] mensajes = { "He visto motines por menos.", "Mis expectativas eran bajas, pero esto...",
				"Empiezo a entender por qué al cocinero le falta una pierna.", "Retira esto de mi vista.",
				"Última oportunidad antes de que el puerto se quede sin cocinero.", "Comida o castigo?",
				"Esto no es lo que he pedido!", "No vas a ver ni una moneda de plata por mi parte.",
				"El cuchillo ya está fuera.", "Ni las ratas de mi barco comerían esto." };
		gestorVista.imprimirMensaje("Le has entregado lo que no es a " + c.getNombre() + "!");
		gestorVista.imprimirMensaje(c.getNombre() + ": '" + mensajes[ALEATORIO.nextInt(mensajes.length)] + "'");
		gestorVista.imprimirMensaje("");

	}

	public void mensajePacienciaAgotada(Cliente c) {
		String[] mensajes = { "La comida viene nadando o qué?", "He visto naufragios más rápidos que este servicio.",
				"Llevo esperando tanto que ya tengo hambre otra vez.",
				"No vas a ver ni una moneda de plata por mi parte.",
				"Si no llega la comida pronto, alguien va a caminar por el tablón.",
				"Te estás buscando problemas capitán." };
		gestorVista.imprimirMensaje("No has conseguido servir a tiempo a " + c.getNombre() + "!");
		gestorVista.imprimirMensaje(c.getNombre() + ": '" + mensajes[ALEATORIO.nextInt(mensajes.length)] + "'");
		gestorVista.imprimirMensaje("");

	}

	public void mostrarRecetas() {
		gestorVista.imprimirMensaje("================== Libro de Recetas ===================");
		gestorVista.imprimirMensaje(
				"En este libro se muestran los ingredientes de cada plato que servimos en el restaurante.");
		gestorVista.imprimirMensaje(
				"➤ Estofado del Capitán: Especialidad de la casa.\n\t➣ Se prepara con agua, carne, patatas y especias.\n"
						+ "➤ Arroz Marinero: Es como un arroz tres delicias, solo que con una delicia, y es pescado.\n\t➣ Se prepara con arroz y pescado.\n"
						+ "➤ Sopa de Pescado y Algas: Dicen que viene genial para la resaca.\n\t➣ Se prepara con agua, pescado y algas.\n"
						+ "➤ Kraken a la Gallega: En verdad no es carne del legendario Kraken, es pulpo con pimentón. Está increible igualmente.\n\t➣ Se prepara con patatas, pulpo y especias.\n"
						+ "➤ Nigiri Pirata: Receta traída de los mares del Este. Es caro para lo pequeño que es.\n\t➣ Se prepara con arroz, pescado y alga.\n"
						+ "➤ Bocadillo de Carne sin Identificar: Será cordero? Ternera? Cerdo? Perro quizás? Nadie lo sabe pero está rico.\n\t➣ Se prepara con pan y carne.");
		gestorVista.imprimirMensaje("=======================================================");
		gestorVista.imprimirMensaje("");
	}

	public void mensajePlatoCocinado(Plato p) {
		gestorVista.imprimirMensaje("Has cocinado: " + p.getNombre() + "!");
	}
}
