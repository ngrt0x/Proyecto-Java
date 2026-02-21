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

public class CombateRestaurante implements Minijuego {
	// atributos
	private final Random ALEATORIO = new Random();
	private VistaCombate vistaCombate = new VistaCombate();
	private Jugador jugador;
	private Enemigo[] enemigos;
	private Barco barco;
	private Tripulante[] aliados;
	private ArrayList<Consumible> consumiblesJ = new ArrayList<>();

	// constructor
	public CombateRestaurante(Jugador jugador) {
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
		vistaCombate.mensajeCombateRestaurante();

		// gestiona la salud de los tripulantes en base al armamento equipado en el
		// barco
		prepararTripulantes();

		// genera un grupo de piratas predeterminado
		enemigos = generarEncuentro();

		// mostrar los enemigos que te encuentras
		vistaCombate.estadoEnemigos(enemigos);

		// determinar la iniciativa de cada tripulante dentro de un rango determinado
		// para el rol de dicho tripulante
		aliados[0].setIniciativa(generarAleatorioEntre(12, 15)); // rol medio
		aliados[1].setIniciativa(generarAleatorioEntre(15, 18)); // dps
		aliados[2].setIniciativa(generarAleatorioEntre(7, 10)); // tanque
		aliados[3].setIniciativa(generarAleatorioEntre(10, 13)); // rol medio

		// crear array conjunto de combatientes
		List<ICombatiente> combatientes = new ArrayList<>();
		combatientes.addAll(Arrays.asList(aliados));
		combatientes.addAll(Arrays.asList(enemigos));

		// comienza el bucle de combate
		while (aliadosVivos() && enemigosVivos()) {
			// ordenar por iniciativa a cada turno por si la iniciativa cambia en medio del
			// combate
			combatientes
					.sort((a, b) -> b.getIniciativa() - a.getIniciativa() != 0 ? b.getIniciativa() - a.getIniciativa()
							: ALEATORIO.nextInt(3) - 1);

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

	// comprueba si el barco tiene Armamento lo cual siempre va a ser cierto, a raiz
	// de los Armamentos que haya crea una lista y la ordena en base al tier del
	// armamento. Entonces coge los stats del armamento con mayor tier y aplica esos
	// stats a los tripulantes.
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

	// genera el encuentro predeterminado de piratas enfadados por la comida
	private Enemigo[] generarEncuentro() {
		Enemigo[] encuentro = new Enemigo[4];

		Enemigo capitan = new Enemigo("Capitán Enfadado", 85, 28, generarAleatorioEntre(11, 14));
		encuentro[0] = capitan;
		Enemigo pirataNormal1 = new Enemigo("Pirata 1", 55, 20, generarAleatorioEntre(10, 13));
		encuentro[1] = pirataNormal1;
		Enemigo canonero = new Enemigo("Cañonero", 45, 35, generarAleatorioEntre(14, 17));
		encuentro[2] = canonero;
		Enemigo pirataNormal2 = new Enemigo("Pirata 2", 55, 20, generarAleatorioEntre(10, 13));
		encuentro[3] = pirataNormal2;

		return encuentro;
	}

	// gestiona el aplicar los efectos correspondientes de los consumibles y
	// restarlos del inventario del jugador
	private void gestionarConsumibles(Consumible c, Tripulante t) {
		if (c.getEfecto() == "curar") {
			int curacion = (int) ((double) t.getSaludTope() * 20 / 100);
			t.recuperarSalud(curacion);
			jugador.getInventario().restarItem(c.getId(), 1);
			vistaCombate.mensajeCurar(t, curacion);
		} else if (c.getEfecto() == "defensa") {
			t.setEstado("defendiendo");
			jugador.getInventario().restarItem(c.getId(), 1);
			vistaCombate.mensajeConsumible(t, c);
		} else if (c.getEfecto() == "iniciativa") {
			t.setIniciativa(t.getIniciativa() + 5);
			jugador.getInventario().restarItem(c.getId(), 1);
			vistaCombate.mensajeConsumible(t, c);
		}

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
