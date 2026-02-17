package controlador;

import modeloJugador.Barco;
import modeloPersonajes.Enemigo;
import modeloPersonajes.Tripulante;
import vista.VistaCombate;

import java.util.Random;

public class GestorCombate implements Minijuego {
	// atributos
	private final Random ALEATORIO = new Random();
	private VistaCombate vistaCombate = new VistaCombate();
	private Enemigo[] enemigos;
	private Tripulante[] aliados;
	private Barco barco;
	// enemigos disponibles
	// minijefes
	private Enemigo cangrejoAcorazado = new Enemigo("Cangrejo Acorazado", 250, 50);
	private Enemigo leviatan = new Enemigo("Leviatán", 235, 60);
	// seres del mar
	private Enemigo sirena = new Enemigo("Sirena", 75, 25);
	private Enemigo hombrePez = new Enemigo("Hombre Pez", 75, 20);
	private Enemigo calamarGigante = new Enemigo("Calamar Gigante", 125, 40);
	// fantasmas
	private Enemigo pirataEspectral = new Enemigo("Pirata Espectral", 75, 35);
	private Enemigo capitanEspectral = new Enemigo("Capitán Espectral", 125, 40);
	private Enemigo loroEspectral = new Enemigo("Loro Espectral", 30, 15);
	// piratas normales
	private Enemigo pirataNormal = new Enemigo("Pirata", 50, 20);
	private Enemigo canonero = new Enemigo("Cañonero", 40, 35);
	private Enemigo capitan = new Enemigo("Capitán", 75, 30);

	// constructor
	public GestorCombate(Barco barco) {
		this.barco = barco;
		aliados = barco.getTripulacion();
	}

	// metodos de la interfaz
	@Override
	public void comenzar() {
		int contadorRondas = 1;
		int opcion;
		int enemigoSeleccionado;
		enemigos = generarEncuentro();

		// mostrar los enemigos que te encuentras
		vistaCombate.mostrarEnemigos(enemigos);
		while (aliadosVivos() && enemigosVivos()) {
			// turno de los aliados
			for (int i = 0; i < aliados.length; i++) {
				Tripulante atacante = aliados[i];
				if (!aliados[i].estaVivo()) {
					continue;
				}
				opcion = vistaCombate.menuCombate(atacante);
				switch (opcion) {
				case 1:
					// ARREGLAR, HAY QUE VER COMO APLICAR LA DISPERSION AL DAÑO
					enemigoSeleccionado = vistaCombate.mostrarEnemigos(enemigos, atacante);
					Enemigo objetivo = enemigos[enemigoSeleccionado - 1];
					atacante.atacar(objetivo, barco);
				}
			}
		}
	}

	// metodos propios
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

	private int elegirEnemigo(Tripulante t) {
		int opcion;
		opcion = vistaCombate.mostrarEnemigos(enemigos, t);
		return opcion;
	}

	private Tripulante seleccionarAliado() {

	}

	// generar una cantidad de oro con una dispersion gaussiana
	private int generarGaussOro() {
		int oro = (int) Math.round(ALEATORIO.nextGaussian() * 3 + 15);
		return Math.max(1, Math.min(oro, 25));
	}

	// generar el array de enemigos contra el que se va a enfrentar el usuario
	private Enemigo[] generarEncuentro() {
		int random;
		Enemigo[] seresMar = { sirena, hombrePez, calamarGigante };
		Enemigo[] fantasmas = { pirataEspectral, capitanEspectral, loroEspectral };
		Enemigo[] piratas = { pirataNormal, canonero, capitan };
		Enemigo[] encuentro;
		random = generarAleatorioEntre(0, 100);
		if (random >= 0 && random < 10) {
			encuentro = new Enemigo[1];
			if (ALEATORIO.nextBoolean()) {
				encuentro[0] = cangrejoAcorazado;
			} else {
				encuentro[0] = leviatan;
			}
		} else if (random >= 10 && random <= 35) {
			encuentro = new Enemigo[generarAleatorioEntre(2, 4)];
			for (int i = 0; i < encuentro.length; i++) {
				encuentro[i] = seresMar[generarAleatorioEntre(0, (seresMar.length - 1))];
			}
		} else if (random > 35 && random <= 60) {
			encuentro = new Enemigo[generarAleatorioEntre(2, 4)];
			for (int i = 0; i < encuentro.length; i++) {
				encuentro[i] = fantasmas[generarAleatorioEntre(0, (fantasmas.length - 1))];
			}
		} else {
			encuentro = new Enemigo[generarAleatorioEntre(2, 4)];
			for (int i = 0; i < encuentro.length; i++) {
				encuentro[i] = piratas[generarAleatorioEntre(0, (piratas.length - 1))];
			}
		}
		return encuentro;
	}

	// aplicar dispersion de daño
	private int dispersion(int danio) {
		int variacion = ALEATORIO.nextInt(11) - 5;
		return danio + variacion;
	}

	private int generarAleatorioEntre(int min, int max) {
		return ALEATORIO.nextInt(max - min + 1) + min;
	}

}
