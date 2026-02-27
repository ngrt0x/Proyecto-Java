package controlador;

import java.util.ArrayList;

import modeloJugador.Jugador;
import modeloMundo.Isla;
import modeloMundo.Mundo;
import modeloPersonajes.Tripulante;
import vista.VistaJuego;
import vista.VistaNPC;

/**
 * Controlador principal del juego. Inicia la partida, gestiona la creación del
 * Jugador y su tripulación, controla el flujo de días y de turnos del día.
 * 
 * @author Jesús Manrique, Marcos Villagómez.
 * @version 1.0
 */
public class JuegoControlador {
	// atributos
	/**
	 * Instancia de VistaJuego, encargada de imprimir y pedir la información
	 * relacionada con el flujo principal de la partida.
	 */
	private VistaJuego vistaJuego = new VistaJuego();
	/**
	 * Instancia de VistaNPC, encargada de imprimir y pedir la información
	 * relacionada a los NPCs.
	 */
	private VistaNPC vistaNpc = new VistaNPC();
	/** Jugador que va a participar en la partida. */
	private Jugador jugador;
	/** Mundo donde se va a desarrollar la partida. */
	private Mundo mundo;
	/**
	 * Instancia de GestorMundo, controlador encargado de gestionar la navegación y
	 * la generación del Mundo.
	 */
	private GestorMundo gestorMundo;
	/**
	 * Instancia de GestorNPC, controlador encargado de la creación e interacción
	 * con los NPCs.
	 */
	private GestorNPC gestorNPC;
	/**
	 * Instancia de GestorTienda, controlador encargado de la compra y venta de
	 * Items.
	 */
	private GestorTienda gestorTienda;
	/**
	 * Instancia de GestorAstillero, controlador encargado de la compra de mejoras
	 * para el Barco.
	 */
	private GestorAstillero gestorAstillero;
	/** Instancia de MinijuegoPesca, controlador del minijuego de pesca. */
	private MinijuegoPesca pesca;
	/** Instancia de GestorCombate, controlador del sistema de combate. */
	private GestorCombate combate;
	/**
	 * Instancia de MinijuegoRestaurante, controlador del minijuego de restaurante.
	 */
	private MinijuegoRestaurante comidas;
	/**
	 * Modo de juego en el que se va a iniciar la partida. 1 es modo debug para
	 * probar los sistemas. 2 es modo normal para la experiencia éstandar del juego.
	 */
	private int opcionModo;

	/**
	 * Representa las distintas fases que puede tener un día dentro del juego.
	 */
	public static enum FaseDia {
		/** Fase de la mañana para la pesca. */
		MANANA,
		/** Fase principal de comida para el minijuego de restaurante. */
		COMIDA,
		/** Fase de la tarde, para bajar a la isla e interactuar con ella. */
		TARDENOCHE,
		/** Fase dedicada a la navegación. */
		NAVEGACION;
	}

	/** Día actual en el progreso del juego. */
	private int diaActual;
	/** Fase del día en la que se encuentra actualmente el juego. */
	public static FaseDia faseActual;

	/**
	 * Constructor principal del controlador del juego.
	 * <p>
	 * Inicializa el modo seleccionado (debug o normal), crea el jugador y prepara
	 * todos los sistemas y gestores necesarios.
	 * </p>
	 */
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

	/**
	 * Devuelve el día actual dentro del progreso del juego.
	 * 
	 * @return Número del día actual.
	 */
	public int getDiaActual() {
		return diaActual;
	}

	/**
	 * Inicia el bucle principal del juego.
	 * <p>
	 * Gestiona el flujo según el modo seleccionado y controla las distintas fases
	 * del día, permitiendo al jugador interactuar con los diferentes sistemas y
	 * minijuegos.
	 * </p>
	 */
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

	/**
	 * Crea el jugador y configura su tripulación.
	 * <p>
	 * Permite asignar nombres y roles a los tripulantes, estableciendo sus
	 * atributos base según el rol seleccionado.
	 * </p>
	 */
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

	/**
	 * Avanza a la siguiente fase del día siguiendo el orden establecido.
	 */
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
