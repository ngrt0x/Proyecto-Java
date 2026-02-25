package controlador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import modeloJugador.Barco;
import modeloJugador.Jugador;
import modeloObjetos.ArmamentoBarco;
import modeloObjetos.Canon;
import modeloObjetos.Consumible;
import modeloObjetos.Item;
import modeloPersonajes.Enemigo;
import modeloPersonajes.ICombatiente;
import modeloPersonajes.Tripulante;
import vista.VistaCombate;

public class GestorCombate implements Minijuego {
	// atributos
	private final Random ALEATORIO = new Random();
	private VistaCombate vistaCombate = new VistaCombate();
	private Jugador jugador;
	private Enemigo[] enemigos;
	private Barco barco;
	private Tripulante[] aliados;
	private ArrayList<Consumible> consumiblesJ = new ArrayList<>();

	// constructor
	public GestorCombate(Jugador jugador) {
		this.jugador = jugador;
		// referencia al barco del jugador
		barco = jugador.getBarco();
		// referencia a los tripulantes del barco
		aliados = jugador.getBarco().getTripulacion();
		// referencia a los consumibles del inventario del jugador
		Map<String, Item> inventarioJ = jugador.getInventario().getItem();
		for (String i : inventarioJ.keySet()) {
			if (inventarioJ.get(i) instanceof Consumible) {
				consumiblesJ.add((Consumible) inventarioJ.get(i));
			}
		}
	}

	// metodos de la interfaz
	@Override
	public void comenzar() {
		int contadorRondas = 1;

		// gestiona la salud de los tripulantes en base al armamento equipado en el
		// barco
		prepararTripulantes();

		// genera un grupo de enemigos aleatorio
		enemigos = generarEncuentro();

		// mostrar los enemigos que te encuentras
		vistaCombate.mostrarEnemigos(enemigos);

		// determinar la iniciativa de cada tripulante dentro de un rango determinado
		// para el rol de dicho tripulante
		for (Tripulante t : aliados) {
			switch (t.getRol()) {
			case 1:
				t.setIniciativa(generarAleatorioEntre(7, 10));
				break;
			case 2:
				t.setIniciativa(generarAleatorioEntre(12, 15));
				break;
			case 3:
				t.setIniciativa(generarAleatorioEntre(15, 18));
				break;
			case 4:
				t.setIniciativa(generarAleatorioEntre(10, 13));
				break;
			}
		}

		// crear array conjunto de combatientes
		List<ICombatiente> combatientes = new ArrayList<>();
		combatientes.addAll(Arrays.asList(aliados));
		combatientes.addAll(Arrays.asList(enemigos));

		// comienza el bucle de combate
		while (aliadosVivos() && enemigosVivos()) {
			// al inicio de cada turno comprueba iniciativa y ordena por si hay algun cambio
			// en la iniciativa de los combatientes
			combatientes
					.sort((a, b) -> b.getIniciativa() - a.getIniciativa() != 0 ? b.getIniciativa() - a.getIniciativa()
							: ALEATORIO.nextInt(3) - 1);

			// comprueba que el barco tiene canones y que el turno es divisible entre 4,
			// entonces los canones atacan
			if (tieneCanones() && contadorRondas % 4 == 0) {
				canonesAtacar(enemigos);
			}
			// turnos infividuales de cada combatiente
			for (int i = 0; i < combatientes.size(); i++) {
				turno(combatientes.get(i));
				if (!aliadosVivos() || !enemigosVivos()) {
					if (!aliadosVivos()) {
						vistaCombate.mensajeDerrota();
					} else if (!enemigosVivos()) {
						int oro = generarGaussOro();
						jugador.sumarOro(oro);
						vistaCombate.mensajeVictoria(oro);
					}
					break;
				}
			}
			contadorRondas++;
		}
	}

	// metodos propios
	// comprueba que hay algun tripulante vivo en el grupo
	private boolean aliadosVivos() {
		int contadorVivos = 0;
		for (int i = 0; i < aliados.length; i++) {
			if (aliados[i].estaVivo()) {
				contadorVivos++;
			}
		}
		if (contadorVivos > 0) {
			return true;
		} else {
			return false;
		}
	}

	// comprueba que hay algun enemigo vivo en el grupo
	private boolean enemigosVivos() {
		int contadorVivos = 0;
		for (int i = 0; i < enemigos.length; i++) {
			if (enemigos[i].estaVivo()) {
				contadorVivos++;
			}
		}
		if (contadorVivos > 0) {
			return true;
		} else {
			return false;
		}
	}

	private void turno(ICombatiente c) {
		if (c instanceof Tripulante) {
			int opcion;
			int enemigoSeleccionado;
			Tripulante atacante = (Tripulante) c;

			// al inicio del turno del personaje desactivar la posicion defensiva SIEMPRE
			atacante.setDefendiendo(false);

			// comprueba que el tripulante del que es el turno esta vivo
			if (!atacante.estaVivo()) {
				return;
			}

			// muestra el menu de combate con las opciones
			opcion = vistaCombate.menuCombate(atacante);
			while (opcion == 4 || opcion == 5 || (opcion == 3 && consumiblesJ.isEmpty())) {
				switch (opcion) {
				case 3:
					vistaCombate.mensajeSinConsumibles();
					break;
				case 4:
					vistaCombate.estadoAliados(aliados);
					break;
				case 5:
					vistaCombate.estadoEnemigos(enemigos);
					break;
				}
				opcion = vistaCombate.menuCombate(atacante);
			}
			switch (opcion) {
			// atacar, te muestra los enemigos y te da a elegir uno
			case 1:
				enemigoSeleccionado = vistaCombate.elegirEnemigo(enemigos, atacante);
				Enemigo objetivo = enemigos[enemigoSeleccionado - 1];
				// intenta esquivar, si devuelve true el personaje esquiva, sino recibe el dano
				if (objetivo.intentarEsquivar()) {
					vistaCombate.mensajeEsquiva(atacante, objetivo);
				} else {
					int danio = dispersion(atacante.atacar(barco));
					objetivo.recibirDanio(danio);
					vistaCombate.mensajeAtaque(atacante, objetivo, danio);
					if (!objetivo.estaVivo()) {
						vistaCombate.mensajeMuerte(objetivo);
					}
				}
				break;
			// defender, el tripulante del que sea turno defiende
			case 2:
				atacante.defender();
				vistaCombate.mensajeDefensa(atacante);
				break;
			// usar objeto, muestra menu de objetos a usar
			case 3:
				Consumible itemAConsumir = vistaCombate.elegirConsumible(consumiblesJ);
				Tripulante tripulanteAfect = vistaCombate.seleccionAliado(aliados);
				gestionarConsumibles(itemAConsumir, tripulanteAfect);
				break;
			}
		} else if (c instanceof Enemigo) {
			Enemigo atacante = (Enemigo) c;
			Tripulante objetivo;

			// comprueba que el enemigo del que es el turno esta vivo
			if (!atacante.estaVivo()) {
				return;
			}

			// el enemigo tiene 1/3 chances de elegir al aliado mas debil para atacarlo,
			// sino simplemente selecciona a alguien aleatorio
			if (ALEATORIO.nextInt(3) == 0) {
				objetivo = seleccionarDebil(aliados);
			} else {
				objetivo = seleccionarAliado(aliados);
			}

			// intenta esquivar, si devuelve true el personaje esquiva, sino recibe el dano
			if (objetivo.intentarEsquivar()) {
				vistaCombate.mensajeEsquiva(atacante, objetivo);
			} else {
				int danio = dispersion(atacante.atacar());
				// si el objetivo esta defendiendo recibira un 20% menos de danio, INDEPENDIENTE
				// DEL ESTADO DE LOS CONSUMIBLES
				if (objetivo.isDefendiendo()) {
					danio = (int) ((double) danio * 80 / 100);
				}

				objetivo.recibirDanio(danio);
				vistaCombate.mensajeAtaque(atacante, objetivo, danio);
				if (!objetivo.estaVivo()) {
					vistaCombate.mensajeMuerte(objetivo);
				}
			}
		}
	}

	// metodo para comprobar que el barco tiene canones
	private boolean tieneCanones() {
		return barco.getInventarioB().getArmamentos().values().stream().anyMatch(a -> a instanceof Canon);
	}

	// metodo atacar de los canones
	private void canonesAtacar(Enemigo[] enemigos) {
		int danioCanones;
		Map<String, ArmamentoBarco> equipamientoBarco = barco.getInventarioB().getArmamentos();
		List<Canon> canones = new ArrayList<>();
		for (String i : equipamientoBarco.keySet()) {
			if (equipamientoBarco.get(i) instanceof Canon) {
				canones.add((Canon) equipamientoBarco.get(i));
			}
		}
		canones.sort((a, b) -> b.getTier() - a.getTier());
		danioCanones = canones.get(0).getDanio();
		vistaCombate.mensajeCanones();
		for (Enemigo e : enemigos) {
			if (e.estaVivo()) {
				danioCanones = dispersion(danioCanones);
				e.recibirDanio(danioCanones);
				vistaCombate.mensajeRecibirCanon(e, danioCanones);
			}

		}
	}

	// comprueba si el barco tiene Armamento lo cual siempre va a ser cierto, a raiz
	// de los Armamentos que haya crea una lista y la ordena en base al tier del
	// armamento. Entonces coge los stats del armamento con mayor tier y aplica esos
	// stats a los tripulantes. Tambien limpia el estado
	private void prepararTripulantes() {
		if (!barco.getInventarioB().getArmamentos().values().stream().anyMatch(a -> a instanceof Canon)) {
			Map<String, ArmamentoBarco> equipamientoBarco = barco.getInventarioB().getArmamentos();
			List<ArmamentoBarco> armamentos = new ArrayList<>();
			for (String i : equipamientoBarco.keySet()) {
				if (!(equipamientoBarco.get(i) instanceof Canon)) {
					armamentos.add(equipamientoBarco.get(i));
				}
			}
			armamentos.sort((a, b) -> b.getTier() - a.getTier());
			for (Tripulante t : aliados) {
				t.setEstado("");
				t.setSaludTope(t.getSaludBase());
				t.setSaludTope(t.getSaludTope() + (armamentos.get(0).getTier() * 10));
				t.setSaludActual(t.getSaludTope());
			}
		}
	}

	// el enemigo selecciona un tripulante aleatorio al que atacar de entre los
	// tripulantes vivos
	private Tripulante seleccionarAliado(Tripulante[] aliados) {
		ArrayList<Tripulante> aliadosVivos = new ArrayList<Tripulante>();
		for (int i = 0; i < aliados.length; i++) {
			if (aliados[i].estaVivo()) {
				aliadosVivos.add(aliados[i]);
			}
		}
		if (aliadosVivos.isEmpty()) {
			return null;
		}
		int aliadoVivoAleatorio = ALEATORIO.nextInt(aliadosVivos.size());
		return aliadosVivos.get(aliadoVivoAleatorio);
	}

	// el enemigo selecciona al tripulante mas debil de entre los tripulantes vivos
	private Tripulante seleccionarDebil(Tripulante[] aliados) {
		// STREAM PARA FILTRAR LOS ALIADOS VIVOS Y DE AHI SACAR EL MAS BAJO DE VIDA
		// OLEEEEEEE
		Tripulante aliadoDebil = Arrays.stream(aliados).filter(Tripulante::estaVivo)
				.min((a, b) -> a.getSaludActual() - b.getSaludActual()).orElse(null);
		return aliadoDebil;
	}

	// generar una cantidad de oro con una dispersion gaussiana
	private int generarGaussOro() {
		int oro = (int) Math.round(ALEATORIO.nextGaussian() * 3 + 15);
		return Math.max(1, Math.min(oro, 25));
	}

	// generar el array de enemigos contra el que se va a enfrentar el usuario
	// existen varias pools de enmigos las cuales pueden salir por chances, despues
	// de eso se genera un array de tamano aleatorio entre 2 y 4 y lo rellena con
	// enemigos de la pool que haya salido. Devuelve ese array.
	private Enemigo[] generarEncuentro() {
		int random;
		Enemigo[] encuentro;
		random = generarAleatorioEntre(0, 100);
		if (random >= 0 && random < 10) {
			encuentro = new Enemigo[1];
			if (ALEATORIO.nextBoolean()) {
				Enemigo cangrejoAcorazado = new Enemigo("Cangrejo Acorazado", 260, 45, generarAleatorioEntre(6, 9));
				encuentro[0] = cangrejoAcorazado;
			} else {
				Enemigo leviatan = new Enemigo("Leviatán", 230, 55, generarAleatorioEntre(8, 11));
				encuentro[0] = leviatan;
			}
		} else if (random >= 10 && random <= 35) {
			encuentro = new Enemigo[generarAleatorioEntre(2, 4)];
			for (int i = 0; i < encuentro.length; i++) {
				random = generarAleatorioEntre(1, 3);
				switch (random) {
				case 1:
					Enemigo sirena = new Enemigo("Sirena", 70, 22, generarAleatorioEntre(14, 17));
					encuentro[i] = sirena;
					break;
				case 2:
					Enemigo hombrePez = new Enemigo("Hombre Pez", 85, 18, generarAleatorioEntre(10, 13));
					encuentro[i] = hombrePez;
					break;
				case 3:
					Enemigo calamarGigante = new Enemigo("Calamar Gigante", 120, 35, generarAleatorioEntre(6, 9));
					encuentro[i] = calamarGigante;

				}
			}
		} else if (random > 35 && random <= 60) {
			encuentro = new Enemigo[generarAleatorioEntre(2, 4)];
			for (int i = 0; i < encuentro.length; i++) {
				random = generarAleatorioEntre(1, 3);
				switch (random) {
				case 1:
					Enemigo pirataEspectral = new Enemigo("Pirata Espectral", 80, 32, generarAleatorioEntre(13, 16));
					encuentro[i] = pirataEspectral;
					break;
				case 2:
					Enemigo capitanEspectral = new Enemigo("Capitán Espectral", 130, 38, generarAleatorioEntre(10, 13));
					encuentro[i] = capitanEspectral;
					break;
				case 3:
					Enemigo loroEspectral = new Enemigo("Loro Espectral", 35, 15, generarAleatorioEntre(18, 20));
					encuentro[i] = loroEspectral;
					break;
				}
			}
		} else {
			encuentro = new Enemigo[generarAleatorioEntre(2, 4)];
			for (int i = 0; i < encuentro.length; i++) {
				random = generarAleatorioEntre(1, 3);
				switch (random) {
				case 1:
					Enemigo pirataNormal = new Enemigo("Pirata", 55, 20, generarAleatorioEntre(10, 13));
					encuentro[i] = pirataNormal;
					break;
				case 2:
					Enemigo canonero = new Enemigo("Cañonero", 45, 35, generarAleatorioEntre(14, 17));
					encuentro[i] = canonero;
					break;
				case 3:
					Enemigo capitan = new Enemigo("Capitán", 85, 28, generarAleatorioEntre(11, 14));
					encuentro[i] = capitan;
					break;
				}
			}
		}
		return encuentro;
	}

	// gestiona el aplicar los efectos correspondientes de los consumibles y
	// restarlos del inventario del jugador
	private void gestionarConsumibles(Consumible c, Tripulante t) {
		if (c.getEfecto() == "curar") {
			int curacion = (int) ((double) t.getSaludTope() * 20 / 100);
			t.recuperarSalud(curacion);
			vistaCombate.mensajeCurar(t, curacion);
		} else if (c.getEfecto() == "defensa") {
			t.setEstado("defendiendo");
			vistaCombate.mensajeConsumible(t, c);
		} else if (c.getEfecto() == "iniciativa") {
			t.setIniciativa(t.getIniciativa() + 5);
			vistaCombate.mensajeConsumible(t, c);
		}

		jugador.getInventario().restarItem(c.getId(), 1); // restamos el item al usarlo
		
		if (c.getCantidad() <= 0) {
			consumiblesJ.remove(c);
		}
	}

	// aplicar dispersion de daño
	private int dispersion(int danio) {
		int variacion = (int) (danio * (ALEATORIO.nextDouble() * 0.3 - 0.15));
		return danio + variacion;
	}

	// genera un numero aleatorio entre num min y num max
	private int generarAleatorioEntre(int min, int max) {
		return ALEATORIO.nextInt(max - min + 1) + min;
	}

}
