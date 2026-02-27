package modeloMundo;

import java.util.HashMap;

import controlador.GestorMundo;
import controlador.GestorNPC;
import modeloJugador.Jugador;
import modeloObjetos.ArmamentoBarco;
import modeloObjetos.CanaPescar;
import modeloObjetos.Canon;
import modeloObjetos.Consumible;
import modeloObjetos.Item;
import modeloPersonajes.NPC;

/**
 * Esta clase es el mundo, con sus islas, objetos, npc's de la tienda y tiendas
 * 
 * @author Jesús Manrique y Marcos Villagómez
 * @version 1.0
 */
public class Mundo {
	// atributos
	/** Gestor de los NPC's */
	private GestorNPC gestorNPC = new GestorNPC();
	/** Gestor de mundo sin inicializar */
	private GestorMundo gestorMundo;

	/** El mapa del mundo */
	private Isla[][] mapa = new Isla[150][100]; // mapa para visualizar
	/** La isla en la que actualmente esta el jugador */
	private Isla ubicacionActual; // isla en la que se encuentra el jugador actualmente
	// posiciones para localizar al personaje en el mapa de navegacion
	/** Posicion X del jugador en el mapa */
	private int posicionX;
	/** Posicion Y del jugador en el mapa */
	private int posicionY;

	/** Lista de todas la islas del juego */
	private HashMap<String, Isla> islasDisponibles = new HashMap<String, Isla>(); // islas totales del juego

	// TODOS LOS ITEMS DISPONIBLES POR AHORA
	/** Caña reforzada */
	private CanaPescar canaReforzada = new CanaPescar("Caña reforzada", "cana_reforzada", 150, 15);
	/** Caña flexible */
	private CanaPescar canaFlexible = new CanaPescar("Caña flexible", "cana_flexible", 100, 15);
	/** Cebo bueno */
	private Item ceboBueno = new Item("Cebo de alta calidad", "cebo_bueno", 50);
	/** Cañones oxidados */
	private Canon canonesOxi = new Canon("Cañones oxidados", "canones_oxi", 200, 15, 1);
	/** Cañones reacondicionados */
	private Canon canonesReacond = new Canon("Cañones reacondicionados", "canones_reacond", 300, 25, 2);
	/** Armamento reforzado */
	private ArmamentoBarco armamentoReforzado = new ArmamentoBarco("Armamento Reforzado", "armamento_refor", 150, 20,
			2);
	/** Armamento grado militar */
	private ArmamentoBarco armamentoMili = new ArmamentoBarco("Armamento de Grado Militar", "armamento_mili", 200, 25,
			3);
	/** Poción de salud */
	private Consumible brebajeSalud = new Consumible("Brebaje de Salud", "pot_salud", 75, "curar");
	/** Poción de defensa */
	private Consumible brebajeDefensa = new Consumible("Brebaje de Defensa", "pot_defensa", 75, "defensa");
	/** Poción de iniciativa */
	private Consumible brebajeIniciativa = new Consumible("Brebaje de Iniciativa", "pot_init", 75, "iniciativa");

	// TENDEROS
	/** NPC en la tienda de la isla1 */
	private NPC tenderoIsla1 = new NPC("Alexander el Tendero",
			"Alexander el Tendero: 'Echa un ojo si quieres, pero si no vas a comprar nada no me hagas perder el tiempo.'");
	/** NPC en el astillero de la isla1 */
	private NPC astilleroIsla1 = new NPC("Rodrigo 'El Mañoso'",
			"Rodrigo 'El Mañoso': 'En 'Astilleros El Mañoso S.L.' sólo usamos materiales de la más alta calidad! Realmente no, pero no hay mucha competencia\n"
					+ "por aquí, así que mis clientes no se pueden quejar del trabajo que hago.");
	/** NPC en la tienda de la isla2 */
	private NPC tenderoIsla2 = new NPC("Tania la Tendera",
			"Tania la Tendera: 'No pregones mucho sobre mi tienda forastero. Todo lo que vendo es material alta calidad pero es de contrabando.'");
	/** NPC en el astillero de la isla2 */
	private NPC astilleroIsla2 = new NPC("Marcelo Mano de Hierro",
			"Marcelo Mano de Hierro: 'Mi viejo cuerpo no estará en las mejores condiciones, pero sigo pudiendo reparar un barco a cambio de un buen precio.'");

	// TIENDAS
	/** Tienda de la isla 1 */
	private Tienda tiendaIsla1 = new Tienda(tenderoIsla1, canaFlexible, brebajeSalud, brebajeDefensa,
			brebajeIniciativa, ceboBueno);
	/** Astillero de la isla 1 */
	private Astillero mejorasIsla1 = new Astillero(astilleroIsla1, canonesOxi, armamentoReforzado);
	/** Tienda de la isla 2 */
	private Tienda tiendaIsla2 = new Tienda(tenderoIsla2, canaReforzada, brebajeSalud, brebajeDefensa, brebajeIniciativa,
			ceboBueno);
	/** Astillero de la isla 2 */
	private Astillero mejorasIsla2 = new Astillero(astilleroIsla2, canonesReacond, armamentoMili);

	// constructor
	/**
	 * Constructor de mundo, crea el mundo, incializa el gestor mundo, mete las islas del mapa y reparte las islas en el mapa
	 * @param j Jugador necesario para inicializar el gestor mundo
	 */
	public Mundo(Jugador j) {
		gestorMundo = new GestorMundo(this, j);
		// crea las islas y las mete en el array islasDisponibles
		islasDisponibles.put("Isla Langosta",
				new Isla("Isla Langosta", gestorNPC.getHabitantesIsla1(), tiendaIsla1, mejorasIsla1));
		islasDisponibles.put("Refugio Sombrío",
				new Isla("Refugio Sombrío", gestorNPC.getHabitantesIsla2(), tiendaIsla2, mejorasIsla2));

		// coloca isla langosta que seria la primera isla en el centro del mapa
		gestorMundo.hacerFormaIsla(mapa, 75, 50, islasDisponibles.get("Isla Langosta"));
		// reparte el resto de islas y encuentros enemigos
		gestorMundo.repartirIslas(islasDisponibles.get("Refugio Sombrío"), mapa);
		gestorMundo.repartirEncuentrosEnemigos(mapa);

		// inicializa la ubicacionActual en isla langosta, y prepara las coordenadas
		// para que cuando empiece la navegacion estes al lado de la isla
		ubicacionActual = islasDisponibles.get("Isla Langosta");
		islasDisponibles.get("Isla Langosta").setVisitada();
		posicionX = 75;
		posicionY = 53;
	}

	// getters y setters
	/**
	 * Getter de ubicacion actual
	 * @return La isla en la que esta el jugador
	 */
	public Isla getUbicacionActual() {
		return ubicacionActual;
	}

	/**
	 * Setter de ubicacion actual
	 * @param ubicacionActual La ubicacion nueva en la que se encuentra el jugador
	 */
	public void setUbicacionActual(Isla ubicacionActual) {
		this.ubicacionActual = ubicacionActual;
	}

	/**
	 * Getter de las islas
	 * @return Una lista de todas las islas del juego
	 */
	public HashMap<String, Isla> getIslasDisponibles() {
		return islasDisponibles;
	}

	/**
	 * Getter del mapa
	 * @return Un array bidimensional que es el mapa de las islas
	 */
	public Isla[][] getMapa() {
		return mapa;
	}

	/**
	 * Setter del mapa
	 * @param mapa El mapa nuevo
	 */
	public void setMapa(Isla[][] mapa) {
		this.mapa = mapa;
	}

	/**
	 * Getter de posicionX
	 * @return La posicion X del jugador en el mapa
	 */
	public int getPosicionX() {
		return posicionX;
	}

	/**
	 * Setter de posicionX
	 * @param posicionX La nueva posicionX del jugador en el mapa
	 */
	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	/**
	 * Getter de posicionY
	 * @return La posicion Y del jugador en el mapa
	 */
	public int getPosicionY() {
		return posicionY;
	}

	/**
	 * Setter de posicionY
	 * @param posicionY La nueva posicionY del jugador en el mapa
	 */
	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

}