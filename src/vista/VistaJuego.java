package vista;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import modeloJugador.Diario;
import modeloJugador.Jugador;
import modeloMundo.Isla;
import modeloObjetos.Item;
import modeloPersonajes.Tripulante;

/**
 * Clase Vsita Juego es el que contiene los metodos que usa el jugador para navegar por el juego
 * 
 * @author Jesús Manrique y Marcos Villagómez
 * @version 1.0
 */
public class VistaJuego {
	// atributos
	/**
	 * Instacia de GestorVista
	 */
	private GestorVista gestorVista = new GestorVista();
	/**
	 * Instacia de Random
	 */
	private final Random ALEATORIO = new Random();

	/**
	 * Metodo para imprimir el menu debug
	 * @return La opcion elegida del menu
	 */
	public int menuDebug() {
		int opcion;

		gestorVista.imprimirMensaje("Qué quieres hacer capitán? Cuál es el plan?\n" + "1. Pescar\n"
				+ "2. Ver inventario\n" + "3. Entrar a la tienda\n" + "4. Combate\n" + "5. Abrir el restaurante\n"
				+ "6. Entrar astillero\n" + "7. Navegar\n" + "8. Hablar con habitantes\n" + "0. Salir");
		opcion = gestorVista.pedirNum();
		while (opcion > 8 || opcion < 0) {
			gestorVista.imprimirError("Selecciona una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	/**
	 * Metodo para imprimir el menu de inventario
	 * @return La opcion elegido del menu
	 */
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

	/**
	 * Metodo para mostrar el Inventario del jugador
	 * @param j Jugador
	 * @param opcion Opcion de inventario
	 */
	public void mostrarInventario(Jugador j, int opcion) {
		if (opcion != 0) {
			gestorVista.imprimirEspacio();
			gestorVista.imprimirMensaje("===================== INVENTARIO =====================");
			gestorVista.imprimirMensaje("Tu oro: " + j.getOro());
			Map<String, Item> inventario;

			if (opcion == 1) {
				inventario = j.getInventario().getItem();
				if (inventario.isEmpty()) {
					gestorVista.imprimirMensaje("No tienes ningún objeto en el inventario!");
				} else {
					for (String i : inventario.keySet()) {
						gestorVista.imprimirMensaje("- " + inventario.get(i).getNombre() + ":");
						gestorVista.imprimirMensaje("\tPrecio: " + inventario.get(i).getPrecio() + "g");
						gestorVista.imprimirMensaje("\tCantidad: " + inventario.get(i).getCantidad());
					}
				}
			} else if (opcion == 2) {
				inventario = j.getInventario().getPeces();
				if (inventario.isEmpty()) {
					gestorVista.imprimirMensaje("No tienes ningún pez en el inventario!");
				} else {
					for (String i : inventario.keySet()) {
						gestorVista.imprimirMensaje("- " + inventario.get(i).getNombre() + ":");
						gestorVista.imprimirMensaje("\tPrecio: " + inventario.get(i).getPrecio() + "g");
						gestorVista.imprimirMensaje("\tCantidad: " + inventario.get(i).getCantidad());
					}
				}
			}
		}
	}

	/**
	 * Metodo para pedirle el nombre al jugador
	 * @return El nombre que tendra el jugador
	 */
	public String pedirNombre() {
		String texto;
		gestorVista.imprimirMensaje("Cómo te llamas capitán?");
		texto = gestorVista.pedirString();
		while (texto.equals("")) {
			gestorVista.imprimirError("Introduce un nombre válido: ");
			texto = gestorVista.pedirString();
		}
		return texto;
	}

	/**
	 * Metodo para pedirle al jugador que elija los nombres de sus compañeros
	 * @param nombres Lista de los nombres de los compañeros
	 */
	public void pedirTripulacion(ArrayList<String> nombres) {
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje(
				"Te acompañarán en tu aventura 4 tripulantes, los mas fieles de todo el Archipiélago.\n"
						+ "Ahora, cómo es que se llaman?");
		for (int i = 0; i < 4; i++) {
			String nombre;
			gestorVista.imprimirMensajePegado("Tripulante " + (i + 1) + ": ");
			nombre = gestorVista.pedirString();
			while (nombre.equals("")) {
				gestorVista.imprimirError("Introduce un nombre válido: ");
				nombre = gestorVista.pedirString();
			}
			nombres.add(nombre);
		}
	}

	/**
	 * Metodo para añadir los roles a la lista de roles disponibles
	 * @return ArrayList de los roles
	 */
	public ArrayList<String> crearRoles() {
		ArrayList<String> roles = new ArrayList<>();
		roles.add("Tragaldabas");
		roles.add("Cocinero");
		roles.add("Ladrón");
		roles.add("Pirata retirado");
		return roles;
	}

	/**
	 * Metodo para que el jugador elija los roles de sus compañeros
	 * @param roles Roles disponibles
	 * @return Rol elegido
	 */
	public int seleccionarRol(ArrayList<String> roles) {
		int opcionR;
		// muestra los roles por pantalla
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje("\t\t\t\t=== Roles ===");
		for (int i = 0; i < roles.size(); i++) {
			gestorVista.imprimirMensaje((i + 1) + ". " + roles.get(i) + ": ");
			if (roles.get(i).equals("Tragaldabas")) {
				gestorVista.imprimirMensaje(
						"Definitivamente no es el más rápido, pero una vida de disfrutar del buen comer le ha bendecido\n"
								+ "con un cuerpo robusto. Se unió a tu tripulación no en busca de fama ni fortuna, sino de buena comida.\n"
								+ "Salud: ★★★★ Fuerza: ★★ Iniciativa: ★");
			} else if (roles.get(i).equals("Ladrón")) {
				gestorVista.imprimirMensaje(
						"Un ratero que pretendía hacer un sinpa en tu restaurante. La jugada no le salió bien y tras una tunda\n"
								+ "se vio obligado a trabajar en tu barco para saldar su deuda. Sorprendentemente no se le daba mal el trabajo y hasta\n"
								+ "le cogió el gusto. Ahora mismo prefiere trabajar contigo que volver a vivir en la calle.\n"
								+ "Salud: ★ Fuerza: ★★★ Iniciativa: ★★★★");
			} else if (roles.get(i).equals("Cocinero")) {
				gestorVista.imprimirMensaje(
						"Su habilidad en la cocina se traslada sorprendentemente bien al combate. Blande el sable con la\n"
								+ "misma destreza con la que blande el cuchillo.\n"
								+ "Salud: ★★ Fuerza: ★★★ Iniciativa: ★★");
			} else if (roles.get(i).equals("Pirata retirado")) {
				gestorVista.imprimirMensaje(
						"Hace años un cazarrecompensas, pero abandonó el oficio despues de perder la pierna en una\n"
								+ "contienda con un grupo enemigo, tal como demuestra su pata de ébano. A día de hoy decide acompañar a este\n"
								+ "peculiar grupo de piratas hosteleros en su búsqueda encubierta del tesoro del Archipiélago.\n"
								+ "Salud: ★★★ Fuerza: ★★★★ Iniciativa: ★★");
			}
			gestorVista.imprimirMensaje("");
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

	/**
	 * Metodo para seleccionar un tripulante
	 * @param nombreTripulacion Los tripulantes
	 * @return El tripulante seleccionado
	 */
	public int seleccionarTripulante(ArrayList<String> nombreTripulacion) {
		int opcionT;
		// muestra los tripulantes por pantalla
		gestorVista.imprimirMensaje("\t\t\t\t=== Tus tripulantes ===");
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

	/**
	 * Metodo para imprimir el mensaje de bienvenida al juego
	 */
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

	/**
	 * Metodo para imprimir y elegir el modo de juego
	 * @return Modo de juego seleccionado
	 */
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

	/**
	 * Metodo para imprimir el mensaje de despedida del juego
	 * @param j Jugador
	 */
	public void mensajeDespedida(Jugador j) {
		gestorVista.imprimirError("Hasta luego " + j.getNombre() + "!");
	}

	/**
	 * Metodo para imprimir el menu para el momento del dia MANANA
	 * @param dia El dia que es dentro del juego
	 * @param j Jugador
	 * @return Opcion elegida
	 */
	public int menuManana1(int dia, Jugador j) {
		int opcion;
		Tripulante[] tripulacion = j.getBarco().getTripulacion();
		Tripulante hablante = tripulacion[generarAleatorioEntre(0, 3)];
		// mostrar menu y dar breve descripcion de la situacion
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje("\t\t\t\t=== DÍA " + dia + " ===");
		gestorVista.imprimirMensaje(
				"Amanece un nuevo día en el Gran Archipiélago... Os encontráis en tu barco, anclados a una distancia prudente de "
						+ j.getIslaActual().getNombre()
						+ ".\nTú y tu tripulación os reunís en cubierta como de costumbre.");
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje(hablante.getNombre() + ": 'Buen día " + j.getNombre() + "! Qué plan tenemos hoy?'");
		gestorVista.imprimirMensaje("1. Pescar\n" + "2. Hablar con la tripulación\n" + "3. Atracar en el puerto de "
				+ j.getIslaActual().getNombre() + "\n" + "4. Mostrar inventario" + "\n" + "5. Leer Diario");
		// seleccionar accion
		opcion = gestorVista.pedirNum();
		while (opcion > 5 || opcion < 1) {
			gestorVista.imprimirError("Selecciona una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	/**
	 * Metodo para imprimir el menu para el momento del dia MANANA pero sin dia
	 * @param j Jugador
	 * @return Opcion elegida
	 */
	public int menuManana2(Jugador j) {
		int opcion;
		Tripulante[] tripulacion = j.getBarco().getTripulacion();
		Tripulante hablante = tripulacion[generarAleatorioEntre(0, 3)];
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje(hablante.getNombre() + ": 'Qué quieres hacer capitán?'");
		gestorVista.imprimirMensaje("1. Pescar\n" + "2. Hablar con la tripulación\n" + "3. Atracar en el puerto de "
				+ j.getIslaActual().getNombre() + "\n" + "4. Mostrar inventario" + "\n" + "5. Leer Diario");
		// seleccionar accion
		opcion = gestorVista.pedirNum();
		while (opcion > 5 || opcion < 1) {
			gestorVista.imprimirError("Selecciona una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	/**
	 * Metodo para imprimir el mensaje de barco atracado
	 * @param j Jugador
	 */
	public void mensajeAtracar(Jugador j) {
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje(
				"Diriges el barco hacia el puerto de " + j.getIslaActual().getNombre() + " y lo atracas con soltura.");
	}

	/**
	 * Metodo para imprimir el menu del momento del dia COMIDA
	 * @param j Jugador 
	 * @return Opicon elegida
	 */
	public int menuComida1(Jugador j) {
		int opcion;
		Tripulante[] tripulacion = j.getBarco().getTripulacion();
		Tripulante hablante = tripulacion[generarAleatorioEntre(0, 3)];
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje("Os encontráis atracados en el puerto de " + j.getIslaActual().getNombre()
				+ ". Ahora sólo queda abrir el restaurante\n"
				+ "y que empiece la fiesta, que ya se acerca la hora de comer.");
		gestorVista.imprimirEspacio();
		gestorVista
				.imprimirMensaje(hablante.getNombre() + ": 'Tienes que revisar algo antes de abrir el restaurante?'");
		gestorVista.imprimirMensaje("1. Abrir restaurante\n" + "2. Hablar con la tripulación\n"
				+ "3. Mostrar inventario\n" + "4. Leer Diario");
		opcion = gestorVista.pedirNum();
		while (opcion > 4 || opcion < 1) {
			gestorVista.imprimirError("Selecciona una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	/**
	 * Metodo para imprimir el menu del momento del dia COMIDA pero sin la introduccion
	 * @param j Jugador
	 * @return Opcion elegida
	 */
	public int menuComida2(Jugador j) {
		int opcion;
		Tripulante[] tripulacion = j.getBarco().getTripulacion();
		Tripulante hablante = tripulacion[generarAleatorioEntre(0, 3)];
		gestorVista.imprimirEspacio();
		gestorVista
				.imprimirMensaje(hablante.getNombre() + ": 'Tienes que revisar algo antes de abrir el restaurante?'");
		gestorVista.imprimirMensaje("1. Abrir restaurante\n" + "2. Hablar con la tripulación\n"
				+ "3. Mostrar inventario\n" + "4. Leer Diario");
		opcion = gestorVista.pedirNum();
		while (opcion > 4 || opcion < 1) {
			gestorVista.imprimirError("Selecciona una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	/**
	 * Metodo para imprimir el mensaje para cuando terminas el turno de comidas
	 * @param j Jugador
	 */
	public void mensajeTerminarComidas(Jugador j) {
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje(j.getNombre()
				+ ": 'Buen trabajo chicos, hemos sobrevivido otro día más al turno de comidas! Algún día los clientes nos acabarán comiendo a nosotros,\n"
				+ "son peores que un tiburón blanco.'");
	}

	/**
	 * Metodo para imprimir el menu del momento del dia TARDENOCHE
	 * @param j Jugador
	 * @return Opcion elegida
	 */
	public int menuTardenoche1(Jugador j) {
		int opcion;
		Tripulante[] tripulacion = j.getBarco().getTripulacion();
		Tripulante hablante = tripulacion[generarAleatorioEntre(0, 3)];
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje(hablante.getNombre()
				+ ": 'Ahora que ya hemos hecho caja tenemos el resto de la tarde libre, qué querrás que hagamos capitán?'");
		gestorVista.imprimirMensaje("1. Bajar a la isla\n" + "2. Hablar con la tripulación\n"
				+ "3. Mostrar inventario\n" + "4. Leer Diario\n" + "5. Continuar con el día");
		opcion = gestorVista.pedirNum();
		while (opcion > 5 || opcion < 1) {
			gestorVista.imprimirError("Selecciona una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	/**
	 * Metodo para imprimir el menu del momento del dia TARDENOCHE pero sin introduccion
	 * @param j Jugador
	 * @return Opcion elegida
	 */
	public int menuTardenoche2(Jugador j) {
		int opcion;
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje("Qué quieres hacer?");
		gestorVista.imprimirMensaje("1. Bajar a la isla\n" + "2. Hablar con la tripulación\n"
				+ "3. Mostrar inventario\n" + "4. Leer Diario\n" + "5. Continuar con el día");
		opcion = gestorVista.pedirNum();
		while (opcion > 5 || opcion < 1) {
			gestorVista.imprimirError("Selecciona una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	/**
	 * Metodo para imprimir el menu de bajar a isla
	 * @param j Jugador
	 * @return Opcion elegida
	 */
	public int menuIsla1(Jugador j) {
		int opcion;
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje(
				"Bajas del barco y tu cuerpo agradece algo de tierra firme. Observas los alrededores y distingues alguna tienda.\n"
						+ "También puedes ver algunas personas rondando por las calles.");
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje("Qué quieres hacer?");
		gestorVista.imprimirMensaje("1. Entrar a la tienda\n" + "2. Entrar en el astillero\n"
				+ "3. Hablar con los locales\n" + "4. Volver al barco");
		opcion = gestorVista.pedirNum();
		while (opcion > 4 || opcion < 1) {
			gestorVista.imprimirError("Selecciona una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	/**
	 * Metodo para imprimir el menu de baja a isla pero sin introduccion
	 * @param j Jugador
	 * @return Opcion elegida
	 */
	public int menuIsla2(Jugador j) {
		int opcion;
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje(
				"Observas los alrededores y distingues alguna tienda. También puedes ver algunas personas rondando por las calles.");
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje("Qué quieres hacer?");
		gestorVista.imprimirMensaje("1. Entrar a la tienda\n" + "2. Entrar en el astillero\n"
				+ "3. Hablar con los locales\n" + "4. Volver al barco");
		opcion = gestorVista.pedirNum();
		while (opcion > 4 || opcion < 1) {
			gestorVista.imprimirError("Selecciona una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	/**
	 * Metodo para imprimir el mensaje de entrar a tienda
	 * @param islaActual Isla Actual
	 */
	public void mensajeEntrarTienda(Isla islaActual) {
		gestorVista.imprimirEspacio();
		switch (islaActual.getNombre()) {
		case "Isla Langosta":
			gestorVista.imprimirMensaje(
					"Te acercas a un edificio con un letrero cochambroso, hecho con un par de tablones de madera. En el letreto\n"
							+ "puedes discernir que pone TIENDA.");
			break;
		case "Refugio Sombrío":
			break;
		}
	}

	/**
	 * Metodo para imprimir el mensaje de entrar al astillero
	 * @param islaActual Isla Actual
	 */
	public void mensajeEntrarAstillero(Isla islaActual) {
		gestorVista.imprimirEspacio();
		switch (islaActual.getNombre()) {
		case "Isla Langosta":
			gestorVista.imprimirMensaje(
					"Rondando la zona del puerto ubicas una especie de 'nave' si se puede llamar de alguna forma. Asumes que es el astillero local\n"
							+ "por el dique seco que se encuentra dentro de ésta estructura, pero las instalaciones dejan que desear. Igualmente, a tu barco no le vendrían\n"
							+ "mal un par de capas de pintura.");
			break;
		case "Refugio Sombrío":
			break;
		}
	}

	/**
	 * Metodo para imprimir el mensaje de volver al barco
	 */
	public void mensajeVolverBarco() {
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje("Te diriges de vuelta al barco...");
	}

	/**
	 * Metodo para imprimir el mensaje de avanzar a TARDENOCHE
	 * @param j Jugador
	 */
	public void mensajeAvanzarTardenoche(Jugador j) {
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje("Ya hace un rato que ha atardecido sobre el paisaje de "
				+ j.getIslaActual().getNombre() + ". Tras un día de trabajo duro\n"
				+ "te metes en tu camarote, con intención de planear tu siguiente movimiento y descansar de cara al siguiente día...");
	}

	/**
	 * Metodo para imprimir el menu de hablar con tripulantes
	 * @param tripulantes Tripulantes
	 * @return Tripulante con el que se quiere hablar
	 */
	public int hablarTripulante(Tripulante[] tripulantes) {
		int opcionT;
		// muestra los tripulantes por pantalla
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje("Con quién quieres hablar?");
		for (int i = 0; i < tripulantes.length; i++) {
			gestorVista.imprimirMensaje((i + 1) + ". " + tripulantes[i].getNombre());
		}
		gestorVista.imprimirMensaje("0. Atrás");
		// seleccion del tripulante
		opcionT = gestorVista.pedirNum();
		while (opcionT > tripulantes.length || opcionT < 0) {
			gestorVista.imprimirError("Selecciona una opción válida: ");
			opcionT = gestorVista.pedirNum();
		}
		return opcionT;
	}

	/**
	 * Metodo para mostrar el diario del jugador
	 * @param diario Diario del jugador
	 */
	public void mostrarDiario(Diario diario) {
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje("\t\t\t\t=== DIARIO ===");
		if (diario.getPistasTesoro().isEmpty()) {
			gestorVista.imprimirMensaje("No hay nada apuntado en tu diario aun...");
		} else {
			for (String i : diario.getPistasTesoro()) {
				gestorVista.imprimirMensajePegado("➤ ");
				gestorVista.imprimirMensaje(i);
			}
		}

	}

	/**
	 * Metodo para generar un numero aleatorio
	 * @param min Numero Minimo
	 * @param max Numero Maximo
	 * @return Numero random
	 */
	private int generarAleatorioEntre(int min, int max) {
		return ALEATORIO.nextInt(max - min + 1) + min;
	}
}