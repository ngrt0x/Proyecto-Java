package controlador;

import modeloJugador.Barco;
import modeloJugador.Jugador;
import modeloPersonajes.Enemigo;
import modeloPersonajes.ICombatiente;
import modeloPersonajes.Tripulante;
import vista.VistaCombate;

import java.util.ArrayList;
import java.util.Random;

public class GestorCombate implements Minijuego {
	// atributos
	private final Random ALEATORIO = new Random();
	private VistaCombate vistaCombate = new VistaCombate();
	private Jugador jugador;
	private Enemigo[] enemigos;
	private Barco barco;
	private Tripulante[] aliados;

	// constructor
	public GestorCombate(Jugador jugador) {
		this.jugador = jugador;
		barco = jugador.getBarco();
		aliados = jugador.getBarco().getTripulacion();
	}

	// metodos de la interfaz
	@Override
	public void comenzar() {
		int opcion;
		int enemigoSeleccionado;
		// genera un grupo de enemigos aleatorio
		enemigos = generarEncuentro();

		// mostrar los enemigos que te encuentras
		vistaCombate.mostrarEnemigos(enemigos);
		while (aliadosVivos() && enemigosVivos()) {
			// turno de los aliados
			for (int i = 0; i < aliados.length; i++) {
				Tripulante atacante = aliados[i];
				// comprueba que el tripulante del que es el turno esta vivo
				if (!atacante.estaVivo()) {
					continue;
				}
				// muestra el menu de combate con las opciones
				opcion = vistaCombate.menuCombate(atacante);
				while (opcion == 4 || opcion == 5) {
					switch (opcion) {
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
					break;
				}
			}

			// turno de los enemigos
			for (int i = 0; i < enemigos.length; i++) {
				Enemigo atacante = enemigos[i];
				// comprueba que el enemigo del que es el turno esta vivo
				if (!atacante.estaVivo()) {
					continue;
				}
				// el enemigo selecciona un tripulante vivo aleatorio
				Tripulante objetivo = seleccionarAliado(aliados);
				// intenta esquivar, si devuelve true el personaje esquiva, sino recibe el dano
				if (objetivo.intentarEsquivar()) {
					vistaCombate.mensajeEsquiva(atacante, objetivo);
				} else {
					int danio = dispersion(atacante.atacar());
					// si el objetivo se encuentra en estado defensivo recibira la mitad del dano
					if (objetivo.isDefendiendo()) {
						objetivo.recibirDanio(danio / 2);
						vistaCombate.mensajeAtaque(atacante, objetivo, danio);
						if (!objetivo.estaVivo()) {
							vistaCombate.mensajeMuerte(objetivo);
						}
					} else {
						objetivo.recibirDanio(danio);
						vistaCombate.mensajeAtaque(atacante, objetivo, danio);
						if (!objetivo.estaVivo()) {
							vistaCombate.mensajeMuerte(objetivo);
						}
					}
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
			vistaCombate.mensajeDerrota();
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
			int oro = generarGaussOro();
			jugador.sumarOro(oro);
			vistaCombate.mensajeVictoria(oro);
			return false;
		}
	}
	
	/*private void turno(ICombatiente c) {
		if (c instanceof Tripulante) {
			int opcion;
			int enemigoSeleccionado;
			Tripulante atacante = (Tripulante) c;
			// comprueba que el tripulante del que es el turno esta vivo
			if (!atacante.estaVivo()) {
				return;
			}
			// muestra el menu de combate con las opciones
			opcion = vistaCombate.menuCombate(atacante);
			while (opcion == 4 || opcion == 5) {
				switch (opcion) {
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
				break;
			}
		} else if(c instanceof Enemigo) {
			Enemigo atacante = (Enemigo) c;
			// comprueba que el enemigo del que es el turno esta vivo
			if (!atacante.estaVivo()) {
				return;
			}
			// el enemigo selecciona un tripulante vivo aleatorio
			Tripulante objetivo = seleccionarAliado(aliados);
			// intenta esquivar, si devuelve true el personaje esquiva, sino recibe el dano
			if (objetivo.intentarEsquivar()) {
				vistaCombate.mensajeEsquiva(atacante, objetivo);
			} else {
				int danio = dispersion(atacante.atacar());
				// si el objetivo se encuentra en estado defensivo recibira la mitad del dano
				if (objetivo.isDefendiendo()) {
					objetivo.recibirDanio(danio / 2);
					vistaCombate.mensajeAtaque(atacante, objetivo, danio);
					if (!objetivo.estaVivo()) {
						vistaCombate.mensajeMuerte(objetivo);
					}
				} else {
					objetivo.recibirDanio(danio);
					vistaCombate.mensajeAtaque(atacante, objetivo, danio);
					if (!objetivo.estaVivo()) {
						vistaCombate.mensajeMuerte(objetivo);
					}
				}
			}
		}
	}*/

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
				Enemigo cangrejoAcorazado = new Enemigo("Cangrejo Acorazado", 250, 50);
				encuentro[0] = cangrejoAcorazado;
			} else {
				Enemigo leviatan = new Enemigo("Leviatán", 235, 60);
				encuentro[0] = leviatan;
			}
		} else if (random >= 10 && random <= 35) {
			encuentro = new Enemigo[generarAleatorioEntre(2, 4)];
			for (int i = 0; i < encuentro.length; i++) {
				random = generarAleatorioEntre(1, 3);
				switch (random) {
				case 1:
					Enemigo sirena = new Enemigo("Sirena", 75, 25);
					encuentro[i] = sirena;
					break;
				case 2:
					Enemigo hombrePez = new Enemigo("Hombre Pez", 75, 20);
					encuentro[i] = hombrePez;
					break;
				case 3:
					Enemigo calamarGigante = new Enemigo("Calamar Gigante", 125, 40);
					encuentro[i] = calamarGigante;

				}
			}
		} else if (random > 35 && random <= 60) {
			encuentro = new Enemigo[generarAleatorioEntre(2, 4)];
			for (int i = 0; i < encuentro.length; i++) {
				random = generarAleatorioEntre(1, 3);
				switch (random) {
				case 1:
					Enemigo pirataEspectral = new Enemigo("Pirata Espectral", 75, 35);
					encuentro[i] = pirataEspectral;
					break;
				case 2:
					Enemigo capitanEspectral = new Enemigo("Capitán Espectral", 125, 40);
					encuentro[i] = capitanEspectral;
					break;
				case 3:
					Enemigo loroEspectral = new Enemigo("Loro Espectral", 30, 15);
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
					Enemigo pirataNormal = new Enemigo("Pirata", 50, 20);
					encuentro[i] = pirataNormal;
					break;
				case 2:
					Enemigo canonero = new Enemigo("Cañonero", 40, 35);
					encuentro[i] = canonero;
					break;
				case 3:
					Enemigo capitan = new Enemigo("Capitán", 75, 30);
					encuentro[i] = capitan;
					break;
				}
			}
		}
		return encuentro;
	}

	// aplicar dispersion de daño
	private int dispersion(int danio) {
		int variacion = ALEATORIO.nextInt(11) - 5;
		return danio + variacion;
	}

	// determinar la iniciativa de cada combatiente al principio del combate
	private void determinarIniciativa(ICombatiente c) {
		int random = generarAleatorioEntre(1, 20);
		c.setIniciativa(random);
	}

	// genera un numero aleatorio entre num min y num max
	private int generarAleatorioEntre(int min, int max) {
		return ALEATORIO.nextInt(max - min + 1) + min;
	}

}
