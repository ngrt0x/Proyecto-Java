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

public class Mundo {
	// atributos
	private GestorNPC gestorNPC = new GestorNPC();
	private GestorMundo gestorMundo;

	private Isla[][] mapa = new Isla[150][100]; // mapa para visualizar
	private Isla ubicacionActual; // isla en la que se encuentra el jugador actualmente
	// posiciones para localizar al personaje en el mapa de navegacion
	private int posicionX;
	private int posicionY;

	private HashMap<String, Isla> islasDisponibles = new HashMap<String, Isla>(); // islas totales del juego

	// TODOS LOS ITEMS DISPONIBLES POR AHORA
	private CanaPescar canaReforzada = new CanaPescar("Caña reforzada", "cana_reforzada", 150, 15);
	private CanaPescar canaFlexible = new CanaPescar("Caña flexible", "cana_flexible", 100, 17);
	private Item ceboBueno = new Item("Cebo de alta calidad", "cebo_bueno", 50);
	private Canon canonesOxi = new Canon("Cañones oxidados", "canones_oxi", 200, 15, 1);
	private Canon canonesReacond = new Canon("Cañones reacondicionados", "canones_reacond", 300, 25, 2);
	private ArmamentoBarco armamentoReforzado = new ArmamentoBarco("Armamento Reforzado", "armamento_refor", 150, 20,
			2);
	private ArmamentoBarco armamentoMili = new ArmamentoBarco("Armamento de Grado Militar", "armamento_mili", 200, 25,
			3);
	private Consumible brebajeSalud = new Consumible("Brebaje de Salud", "pot_salud", 75, "curar");
	private Consumible brebajeDefensa = new Consumible("Brebaje de Defensa", "pot_defensa", 75, "defensa");
	private Consumible brebajeIniciativa = new Consumible("Brebaje de Iniciativa", "pot_init", 75, "iniciativa");

	// TENDEROS
	private NPC tenderoIsla1 = new NPC("Alexander el Tendero",
			"Alexander el Tendero: 'Echa un ojo si quieres, pero si no vas a comprar nada no me hagas perder el tiempo.'");
	private NPC astilleroIsla1 = new NPC("Rodrigo 'El Mañoso'",
			"Rodrigo 'El Mañoso': 'En 'Astilleros El Mañoso S.L.' sólo usamos materiales de la más alta calidad! Realmente no, pero no hay mucha competencia\n"
					+ "por aquí, así que mis clientes no se pueden quejar del trabajo que hago.");
	private NPC tenderoIsla2 = new NPC("Tania la Tendera",
			"Tania la Tendera: 'No pregones mucho sobre mi tienda forastero. Todo lo que vendo es material alta calidad pero es de contrabando.'");
	private NPC astilleroIsla2 = new NPC("Marcelo Mano de Hierro",
			"Marcelo Mano de Hierro: 'Mi viejo cuerpo no estará en las mejores condiciones, pero sigo pudiendo reparar un barco a cambio de un buen precio.'");

	// TIENDAS
	private Tienda tiendaIsla1 = new Tienda(tenderoIsla1, canaReforzada, brebajeSalud, brebajeDefensa,
			brebajeIniciativa, ceboBueno);
	private Astillero mejorasIsla1 = new Astillero(astilleroIsla1, canonesOxi, armamentoReforzado);
	private Tienda tiendaIsla2 = new Tienda(tenderoIsla2, canaFlexible, brebajeSalud, brebajeDefensa, brebajeIniciativa,
			ceboBueno);
	private Astillero mejorasIsla2 = new Astillero(astilleroIsla2, canonesReacond, armamentoMili);

	// constructor
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
	public Isla getUbicacionActual() {
		return ubicacionActual;
	}

	public void setUbicacionActual(Isla ubicacionActual) {
		this.ubicacionActual = ubicacionActual;
	}

	public HashMap<String, Isla> getIslasDisponibles() {
		return islasDisponibles;
	}

	public Isla[][] getMapa() {
		return mapa;
	}

	public void setMapa(Isla[][] mapa) {
		this.mapa = mapa;
	}

	public int getPosicionX() {
		return posicionX;
	}

	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}

	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

}