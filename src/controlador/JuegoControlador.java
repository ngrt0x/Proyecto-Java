package controlador;

import java.util.ArrayList;
import modeloJugador.Jugador;
import modeloMundo.Tienda;
import modeloPersonajes.Tripulante;
import vista.VistaJuego;

public class JuegoControlador {
	// atributos
	private VistaJuego vistaJuego = new VistaJuego();
	private Jugador jugador;
	private Tienda t;
	private GestorTienda tienda;
	private MinijuegoPesca pesca;
	private GestorCombate combate;
	private MinijuegoRestaurante comidas;

	// constructor
	public JuegoControlador() {
		int opcionModo;
		opcionModo = vistaJuego.elegirModo();
		switch (opcionModo) {
		case 1:
			jugador = new Jugador("Placeholder", 1);
			break;
		case 2:
			vistaJuego.mensajeIntroduccion();
			crearJugador();
			break;
		}
		t = new Tienda();
		tienda = new GestorTienda(jugador, t);
		pesca = new MinijuegoPesca(jugador);
		combate = new GestorCombate(jugador);
		comidas = new MinijuegoRestaurante(jugador);
	}

	// metodos
	public void iniciarJuego() {
		int opcion = vistaJuego.menuInicio();
		while (opcion != 0) {
			switch (opcion) {
			case 1 -> pesca.comenzar();
			case 2 -> {
				int opcionInventario = vistaJuego.menuInventarios();
				vistaJuego.mostrarInventario(jugador, opcionInventario);
			}
			case 3 -> tienda.entrarTienda();
			case 4 -> combate.comenzar();
			case 5 -> comidas.comenzar();
			}
			opcion = vistaJuego.menuInicio();
		}
		vistaJuego.mensajeDespedida(jugador);
	}

	public void crearJugador() {
		// variables
		String nombreJugador;
		ArrayList<String> nombresTripulacion = new ArrayList<>();
		ArrayList<String> roles;
		Tripulante t;
		Tripulante[] tripulacion = new Tripulante[4];
		int opcionR;
		int opcionT;
		int contadorArrayTripulantes = 0;
		// pedir el nombre del usuario, y asignar nombres y roles a los miembros de la
		// tripulacion
		nombreJugador = vistaJuego.pedirNombre();
		jugador = new Jugador(nombreJugador, 2);
		vistaJuego.pedirTripulacion(nombresTripulacion);
		roles = new ArrayList<>(vistaJuego.crearRoles());

		// bucle de asignar roles a los tripulantes y crear el array tripulación
		do {
			opcionR = vistaJuego.seleccionarRol(roles);
			opcionT = vistaJuego.seleccionarTripulante(nombresTripulacion);
			String rol = roles.get(opcionR - 1);
			String nombre = nombresTripulacion.get(opcionT - 1);
			if (roles.get(opcionR - 1).equals("Tragaldabas")) {
				t = new Tripulante(nombre, 1);
				tripulacion[contadorArrayTripulantes] = t;
			} else if (rol.equals("Cocinero")) {
				t = new Tripulante(nombre, 2);
				tripulacion[contadorArrayTripulantes] = t;
			} else if (rol.equals("Ladrón")) {
				t = new Tripulante(nombre, 3);
				tripulacion[contadorArrayTripulantes] = t;
			} else if (rol.equals("Pirata retirado")) {
				t = new Tripulante(nombre, 4);
				tripulacion[contadorArrayTripulantes] = t;
			}
			roles.remove(opcionR - 1);
			nombresTripulacion.remove(opcionT - 1);
			contadorArrayTripulantes++;
		} while (!roles.isEmpty() && !nombresTripulacion.isEmpty());

		// asignar las iniciativas a los miembros de la tripulacion segun su rol
		for (Tripulante trip : tripulacion) {
			switch (trip.getRol()) {
			// tanque
			case 1:
				trip.setSaludBase(150);
				trip.setFuerza(12);
				break;
			// equilibrado
			case 2:
				trip.setSaludBase(110);
				trip.setFuerza(18);
				break;
			// rapido pero blandito
			case 3:
				trip.setSaludBase(80);
				trip.setFuerza(20);
				break;
			// bueno en general pero un poquillo lento
			case 4:
				trip.setSaludBase(110);
				trip.setFuerza(22);
				break;
			}
		}

		// pasarle el array con los tripulantes al barco
		jugador.getBarco().setTripulacion(tripulacion);
	}

}
