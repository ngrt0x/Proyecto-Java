package controlador;

import java.util.ArrayList;

import modeloJugador.Jugador;
import modeloMundo.Isla;
import modeloMundo.Mundo;
import modeloPersonajes.Tripulante;
import vista.VistaJuego;
import vista.VistaNPC;

public class JuegoControlador {
	// atributos
	private VistaJuego vistaJuego = new VistaJuego();
	private VistaNPC vistaNpc = new VistaNPC();
	private Jugador jugador;
	private Mundo mundo;
	private GestorMundo gestorMundo;
	private GestorNPC gestorNPC;
	private GestorTienda gestorTienda;
	private GestorAstillero gestorAstillero;
	private MinijuegoPesca pesca;
	private GestorCombate combate;
	private MinijuegoRestaurante comidas;
	private int opcionModo;

	public static enum FaseDia {
		MANANA, COMIDA, TARDENOCHE, NAVEGACION;
	}

	private int diaActual;
	public static FaseDia faseActual;

	// constructor
	// en el constructor se puede elegir el modo de juego que quieres inicializar
	public JuegoControlador() {
		opcionModo = vistaJuego.elegirModo();
		switch (opcionModo) {
		case 1:
			jugador = new Jugador("Placeholder", 1);
			break;
		case 2:
			vistaJuego.mensajeIntroduccion();
			crearJugador();
			diaActual = 1;
			faseActual = FaseDia.MANANA;
			break;
		}
		pesca = new MinijuegoPesca(jugador);
		combate = new GestorCombate(jugador);
		comidas = new MinijuegoRestaurante(jugador, this);
		mundo = new Mundo(jugador);
		gestorMundo = new GestorMundo(mundo, jugador);
		gestorNPC = new GestorNPC();
	}

	public int getDiaActual() {
		return diaActual;
	}

	// metodos
	public void iniciarJuego() {
		switch (opcionModo) {
		// case 1 inicia el juego en modo debug, para probar los sistemas del juego
		// directamente
		case 1:
			jugador.setIslaActual(mundo.getUbicacionActual());
			gestorTienda = new GestorTienda(jugador, mundo.getUbicacionActual().getTiendaLocal());
			gestorAstillero = new GestorAstillero(jugador, mundo.getUbicacionActual().getAstilleroLocal());
			int opcion = vistaJuego.menuDebug();
			while (opcion != 0) {
				switch (opcion) {
				case 1 -> pesca.comenzar();
				case 2 -> {
					int opcionInventario = vistaJuego.menuInventarios();
					vistaJuego.mostrarInventario(jugador, opcionInventario);
				}
				case 3 -> gestorTienda.entrarTienda();
				case 4 -> combate.comenzar();
				case 5 -> comidas.comenzar();
				case 6 -> gestorAstillero.entrarTienda();
				case 7 -> {
					Isla nuevaIsla = gestorMundo.navegar();
					mundo.setUbicacionActual(nuevaIsla);
					jugador.setIslaActual(nuevaIsla);
				}
				case 8 -> gestorNPC.hablarNpc(jugador);
				}
				opcion = vistaJuego.menuDebug();
			}
			break;
		// case 2 inicia el juego en modo normal, la experiencia estandar
		case 2:
			int accion;
			boolean salir = false;
			while (!salir) {
				jugador.setIslaActual(mundo.getUbicacionActual());
				gestorTienda = new GestorTienda(jugador, mundo.getUbicacionActual().getTiendaLocal());
				gestorAstillero = new GestorAstillero(jugador, mundo.getUbicacionActual().getAstilleroLocal());
				switch (faseActual) {
				case MANANA:
					accion = vistaJuego.menuManana1(diaActual, jugador);
					while (accion != 3) {
						switch (accion) {
						// comenzar minijuego pesca
						case 1 -> pesca.comenzar();
						// hablar con los tripulantes
						case 2 -> {
							int opcionT = vistaJuego.hablarTripulante(jugador.getBarco().getTripulacion());
							while (opcionT != 0) {
								switch (jugador.getBarco().getTripulacion()[opcionT - 1].getRol()) {
								case 1:
									vistaNpc.dialogosTragaldabas(jugador);
									break;
								case 2:
									vistaNpc.dialogosCocinero(jugador);
									break;
								case 3:
									vistaNpc.dialogosLadron(jugador);
									break;
								case 4:
									vistaNpc.dialogosPirata(jugador);
									break;
								}
								opcionT = vistaJuego.hablarTripulante(jugador.getBarco().getTripulacion());
							}
						}
						// mostrar el inventario
						case 4 -> {
							int opcionInventario = vistaJuego.menuInventarios();
							vistaJuego.mostrarInventario(jugador, opcionInventario);
						}
						// leer el diario
						case 5 -> vistaJuego.mostrarDiario(jugador.getDiario());
						}
						accion = vistaJuego.menuManana2(jugador);
					}
					// si el usuario selecciona atracar el barco avanza la fase del dia
					vistaJuego.mensajeAtracar(jugador);
					avanzarFase();
					break;
				case COMIDA:
					accion = vistaJuego.menuComida1(jugador);
					while (accion != 1) {
						switch (accion) {
						// hablar con los tripulantes
						case 2 -> {
							int opcionT = vistaJuego.hablarTripulante(jugador.getBarco().getTripulacion());
							while (opcionT != 0) {
								switch (jugador.getBarco().getTripulacion()[opcionT - 1].getRol()) {
								case 1 -> vistaNpc.dialogosTragaldabas(jugador);
								case 2 -> vistaNpc.dialogosCocinero(jugador);
								case 3 -> vistaNpc.dialogosLadron(jugador);
								case 4 -> vistaNpc.dialogosPirata(jugador);
								}
								opcionT = vistaJuego.hablarTripulante(jugador.getBarco().getTripulacion());
							}
						}
						// mostrar el inventario
						case 3 -> {
							int opcionInventario = vistaJuego.menuInventarios();
							vistaJuego.mostrarInventario(jugador, opcionInventario);
						}
						// leer Diario
						case 4 -> vistaJuego.mostrarDiario(jugador.getDiario());
						}
						accion = vistaJuego.menuComida2(jugador);

					}
					// si el usuario selecciona abrir restaurante inicia el minijuego de restaurante
					// y despues de eso avanza el dia
					comidas.comenzar();
					vistaJuego.mensajeTerminarComidas(jugador);
					avanzarFase();
					break;
				case TARDENOCHE:
					accion = vistaJuego.menuTardenoche1(jugador);
					while (accion != 5) {
						switch (accion) {
						// baja a la isla
						case 1:
							accion = vistaJuego.menuIsla1(jugador);
							while (accion != 4) {
								switch (accion) {
								// entrar a la tienda
								case 1:
									vistaJuego.mensajeEntrarTienda(jugador.getIslaActual());
									gestorTienda.entrarTienda();
									break;
								// entrar al astillero
								case 2:
									vistaJuego.mensajeEntrarAstillero(jugador.getIslaActual());
									gestorAstillero.entrarTienda();
									break;
								// hablar con los NPCs
								case 3:
									gestorNPC.hablarNpc(jugador);
									break;
								}
								accion = vistaJuego.menuIsla2(jugador);
							}
							vistaJuego.mensajeVolverBarco();
							break;
						// hablar con los tripulantes
						case 2:
							int opcionT = vistaJuego.hablarTripulante(jugador.getBarco().getTripulacion());
							while (opcionT != 0) {
								switch (jugador.getBarco().getTripulacion()[opcionT - 1].getRol()) {
								case 1:
									vistaNpc.dialogosTragaldabas(jugador);
									break;
								case 2:
									vistaNpc.dialogosCocinero(jugador);
									break;
								case 3:
									vistaNpc.dialogosLadron(jugador);
									break;
								case 4:
									vistaNpc.dialogosPirata(jugador);
									break;
								}
								opcionT = vistaJuego.hablarTripulante(jugador.getBarco().getTripulacion());
							}
							break;
						// mostrar el inventario
						case 3:
							int opcionInventario = vistaJuego.menuInventarios();
							vistaJuego.mostrarInventario(jugador, opcionInventario);
							break;
						case 4:
							vistaJuego.mostrarDiario(jugador.getDiario());
							break;
						}
						accion = vistaJuego.menuTardenoche2(jugador);
					}
					vistaJuego.mensajeAvanzarTardenoche(jugador);
					avanzarFase();
					break;
				case NAVEGACION:
					Isla nuevaIsla = gestorMundo.navegar();
					mundo.setUbicacionActual(nuevaIsla);
					diaActual++;
					avanzarFase();
					break;
				}
			}
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

	private void avanzarFase() {
		switch (faseActual) {
		case MANANA:
			faseActual = FaseDia.COMIDA;
			break;
		case COMIDA:
			faseActual = FaseDia.TARDENOCHE;
			break;
		case TARDENOCHE:
			faseActual = FaseDia.NAVEGACION;
			break;
		case NAVEGACION:
			faseActual = FaseDia.MANANA;
			break;
		}
	}

}
