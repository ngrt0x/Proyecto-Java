package modeloMundo;

import java.util.HashMap;

import controlador.GestorMundo;
import controlador.GestorNPC;
import modeloObjetos.ArmamentoBarco;
import modeloObjetos.CanaPescar;
import modeloObjetos.Canon;
import modeloObjetos.Consumible;
import modeloObjetos.Item;
import modeloPersonajes.NPC;

public class Mundo {
	// atributos
	private GestorNPC gestorNPC = new GestorNPC();
	private GestorMundo gestorMundo = new GestorMundo(this);

	private Isla[][] mapa = new Isla[250][250]; // mapa para visualizar
	private Isla ubicacionActual; // isla en la que se encuentra el jugador actualmente
	// posiciones para localizar al personaje en el mapa de navegacion
	private int posicionX;
	private int posicionY;

	private HashMap<String, Isla> islasDisponibles = new HashMap<String, Isla>(); // islas totales del juego

	// TODOS LOS ITEMS DISPONIBLES POR AHORA
	private Item canaReforzada = new CanaPescar("Caña reforzada", "cana_reforzada", 150, 15);
	private Item canaFlexible = new CanaPescar("Caña flexible", "cana_flexible", 100, 17);
	private Item canaMaestra = new CanaPescar("Caña maestra", "cana_maestra", 300, 20);
	private Item ceboBueno = new Item("Cebo de alta calidad", "cebo_bueno", 50);
	private Item canonesOxi = new Canon("Cañones oxidados", "canones_oxi", 200, 15, 1);
	private Item canonesReacond = new Canon("Cañones reacondicionados", "canones_reacond", 300, 25, 2);
	private Item armamentoReforzado = new ArmamentoBarco("Armamento Reforzado", "armamento_refor", 150, 20, 2);
	private Item armamentoMili = new ArmamentoBarco("Armamento de Grado Militar", "armamento_mili", 200, 25, 3);
	private Item brebajeSalud = new Consumible("Brebaje de Salud", "pot_salud", 75, "curar");
	private Item brebajeDefensa = new Consumible("Brebaje de Defensa", "pot_defensa", 75, "defensa");
	private Item brebajeIniciativa = new Consumible("Brebaje de Iniciativa", "pot_init", 75, "iniciativa");

	// TENDEROS
	private NPC tenderoIsla1 = new NPC("Alexander el Tendero",
			"Alexander el Tendero: 'Echa un ojo si quieres, pero si no vas a comprar nada no me hagas perder el tiempo.'");
	private NPC astilleroIsla1 = new NPC("Rodrigo 'El Mañoso'",
			"Rodrigo 'El Mañoso': 'En 'Astilleros El Mañoso S.L.' sólo usamos materiales de la más alta calidad! Realmente no, pero no hay mucha competencia\n"
					+ "por aquí, así que mis clientes no se pueden quejar del trabajo que hago.");

	// TIENDAS
	private Tienda tiendaIsla1 = new Tienda(tenderoIsla1, canaReforzada, ceboBueno, brebajeSalud, brebajeDefensa,
			brebajeIniciativa);
	private Astillero mejorasIsla1 = new Astillero(astilleroIsla1, canonesOxi, armamentoReforzado);
	private Tienda tiendaIsla2 = new Tienda(tenderoIsla1, canaFlexible, ceboBueno, brebajeSalud, brebajeDefensa,
			brebajeIniciativa);
	private Astillero mejorasIsla2 = new Astillero(astilleroIsla1, canonesReacond, armamentoMili);

	// constructor
	public Mundo() {
		islasDisponibles.put("Isla Langosta",
				new Isla("Isla Langosta", gestorNPC.getHabitantesIsla1(), tiendaIsla1, mejorasIsla1));
		islasDisponibles.put("Refugio Sombrío",
				new Isla("Refugio Sombrío", gestorNPC.getHabitantesIsla1(), tiendaIsla2, mejorasIsla2));

		gestorMundo.repartirIslas(islasDisponibles.get("Refugio Sombrío"), mapa);

		ubicacionActual = islasDisponibles.get("Isla Langosta");
		islasDisponibles.get("Isla Langosta").setVisitada();
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