package vista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import modeloJugador.Jugador;
import modeloObjetos.Item;

public class VistaJuego {
	// atributos
	private GestorVista gestorVista = new GestorVista();

	public int menuInicio() {
		int opcion;

		gestorVista
				.imprimirMensaje("Qué quieres hacer capitán? Cuál es el plan?\n" + "1. Pescar\n" + "2. Ver inventario\n"
						+ "3. Entrar a la tienda\n" + "4. Combate\n" + "5. Abrir el restaurante\n" + "0. Salir");
		opcion = gestorVista.pedirNum();
		while (opcion > 5 || opcion < 0) {
			gestorVista.imprimirError("Selecciona una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public int menuInventarios() {
		int opcion;
		gestorVista.imprimirMensaje("1. Items\n" + "2. Peces\n" + "0. Atrás");
		opcion = gestorVista.pedirNum();
		while (opcion > 2 || opcion < 0) {
			gestorVista.imprimirError("Selecciona una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public void mostrarInventario(Jugador j, int opcion) {
		gestorVista.imprimirMensaje("===================== INVENTARIO =====================");
		gestorVista.imprimirMensaje("Tu oro: " + j.getOro());
		Map<String, Item> inventario = j.getInventario().getItems();
		if (opcion == 1) {
			inventario = j.getInventario().getItem();
		} else if (opcion == 2) {
			inventario = j.getInventario().getPeces();
		}

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

	public void pedirTripulacion(ArrayList<String> nombres) {
		gestorVista.imprimirMensaje(
				"Te acompañarán en tu aventura 4 tripulantes, los mas fieles de todo el Archipiélago.\n"
						+ "Ahora, cómo es que se llaman?");
		for (int i = 0; i < 4; i++) {
			gestorVista.imprimirMensajePegado("Tripulante " + (i + 1) + ": ");
			nombres.add(gestorVista.pedirString());
		}
	}

	public Map<Integer, String> crearRoles() {
		Map<Integer, String> roles = new HashMap<>();
		roles.put(1,
				"Tragaldabas: Definitivamente no es el más rápido, pero una vida de disfrutar del buen comer le ha bendecido\n"
						+ "con un cuerpo robusto. Se unió a tu tripulación no en busca de fama ni fortuna, sino de buena comida.\n"
						+ "Salud: ★★★★ Fuerza: ★★ Iniciativa: ★");
		roles.put(2,
				"Cocinero: Su habilidad en la cocina se traslada sorprendentemente bien al combate. Blande el sable con la\n"
						+ "misma destreza con la que blande el cuchillo.\n" + "Salud: ★★ Fuerza: ★★★ Iniciativa: ★★");
		roles.put(3,
				"Ladrón: Un ratero que pretendía hacer un sinpa en tu restaurante. La jugada no le salió bien y tras una tunda\n"
						+ "se vio obligado a trabajar en tu barco para saldar su deuda. Sorprendentemente no se le daba mal el trabajo y hasta\n"
						+ "le cogió el gusto. Ahora mismo prefiere trabajar contigo que volver a vivir en la calle.\n"
						+ "Salud: ★ Fuerza: ★★★ Iniciativa: ★★★★");
		roles.put(4,
				"Pirata retirado: Hace años un cazarrecompensas, pero abandonó el oficio despues de perder la pierna en una\n"
						+ "contienda con un grupo enemigo, tal como demuestra su pata de ébano. A día de hoy decide acompañar a este\n"
						+ "peculiar grupo de piratas hosteleros en su búsqueda encubierta del tesoro del Archipiélago.\n"
						+ "Salud: ★★★ Fuerza: ★★★★ Iniciativa: ★★");
		return roles;
	}

	public int seleccionarRol(Map<Integer, String> roles) {
		int opcionR;
		int contador = 1;
		// muestra los roles por pantalla
		gestorVista.imprimirMensaje("\t\t\t\t\t=== Roles ===\n");
		for (int i : roles.keySet()) {
			gestorVista.imprimirMensaje(contador + ". " + roles.get(i));
			gestorVista.imprimirMensaje("");
			contador++;
		}
		// seleccion de rol a asignar
		gestorVista.imprimirMensaje("Qué rol quieres asignar?");
		opcionR = gestorVista.pedirNum();
		while (opcionR > roles.size() || opcionR <= 0) {
			gestorVista.imprimirError("Selecciona una opción válida: ");
			opcionR = gestorVista.pedirNum();
		}
		return opcionR;
	}

	public int seleccionarTripulante(ArrayList<String> nombreTripulacion) {
		int opcionT;
		// muestra los tripulantes por pantalla
		gestorVista.imprimirMensaje("\t\t\t\t=== Tus tripulantes ===\n");
		for (int i = 0; i < nombreTripulacion.size(); i++) {
			gestorVista.imprimirMensaje((i + 1) + ". " + nombreTripulacion.get(i));
		}
		// seleccion del tripulante
		opcionT = gestorVista.pedirNum();
		while (opcionT > nombreTripulacion.size() || opcionT <= 0) {
			gestorVista.imprimirError("Selecciona una opción válida: ");
			opcionT = gestorVista.pedirNum();
		}
		return opcionT;
	}

	public void mensajeIntroduccion() {
		gestorVista.imprimirMensaje("\t\t\t=== Bienvenido a Cañones y Fogones! ===\n"
				+ "En este juego te embarcarás en una aventura culinaria explosiva así que suban a bordo y leven anclas!\n"
				+ "Tu misión como capitán del primer restaurante flotante del Gran Archipiélago será ir de isla en isla\n"
				+ "atendiendo a los clientes más variopintos.\n"
				+ "Pero a puerta cerrada, tras vuestro turno como cocineros y meseros, tú y tu tripulación trataréis de reunir\n"
				+ "información sobre un gran tesoro escondido en alguna parte del Archipiélago.\n"
				+ "La información es oro, y en el mundillo de los piratas hay gente que se toma eso muy en serio, así que\n"
				+ "prepárate, encended los fogones y cargad los cañones!");
		gestorVista.imprimirMensaje("");
	}

	public int elegirModo() {
		int opcion;
		gestorVista.imprimirMensaje("1. Iniciar juego en modo debug\n" + "2. Iniciar juego en modo normal");
		opcion = gestorVista.pedirNum();
		while (opcion > 2 || opcion < 1) {
			gestorVista.imprimirError("Selecciona una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public void mensajeDespedida(Jugador j) {
		gestorVista.imprimirError("Hasta luego " + j.getNombre() + "!");
	}
}