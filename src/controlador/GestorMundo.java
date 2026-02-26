package controlador;

import modeloMundo.Isla;
import modeloMundo.Mundo;
import vista.VistaNavegacion;
import java.util.Random;

import modeloJugador.Jugador;

public class GestorMundo {
	// atributos
	private final Random ALEATORIO = new Random();
	private GestorCombate combate;
	private VistaNavegacion vistaNavegacion = new VistaNavegacion();

	private Mundo mundo;

	// Constructor
	public GestorMundo(Mundo mundo, Jugador j) {
		this.mundo = mundo;
		combate = new GestorCombate(j);
	}

	// metodos propios
	public Isla navegar() {
		int distancia;
		int direccion;
		boolean echarAncla = false;
		Isla isla = new Isla("isla");
		while (!echarAncla) {
			vistaNavegacion.imprimirMapa(mundo.getPosicionX(), mundo.getPosicionY(), mundo.getMapa());
			direccion = vistaNavegacion.seleccionarDireccion();
			distancia = vistaNavegacion.seleccionarDistancia();
			for (int i = 0; i < distancia; i++) {

				moverUnaUnidad(direccion);
				isla = mundo.getMapa()[mundo.getPosicionX()][mundo.getPosicionY()];
				vistaNavegacion.limpiarPantalla();
				vistaNavegacion.imprimirMapa(mundo.getPosicionX(), mundo.getPosicionY(), mundo.getMapa());

				if (isla == null) {
					dormir(150);
					continue;
				}

				if (isla.getNombre().equals("encuentroEnem")) {
					combate.comenzar();
					mundo.getMapa()[mundo.getPosicionX()][mundo.getPosicionY()] = null;
					break;
				}

				if (!isla.isVisitada()) {
					isla.setVisitada();
				}
				echarAncla = vistaNavegacion.confirmarEntrarIsla(isla);
				break;
			}
		}
		return isla;
	}

	public void dormir(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void hacerFormaIsla(Isla[][] mapa, int x, int y, Isla isla) {
		mapa[x][y] = isla;
		mapa[x + 1][y] = isla;
		mapa[x - 1][y] = isla;
		mapa[x][y + 1] = isla;
		mapa[x][y - 1] = isla;
		mapa[x + 1][y + 1] = isla;
		mapa[x - 1][y - 1] = isla;
		mapa[x - 1][y + 1] = isla;
		mapa[x + 1][y - 1] = isla;
		mapa[x + 2][y] = isla;
		mapa[x - 2][y] = isla;
		mapa[x][y + 2] = isla;
		mapa[x][y - 2] = isla;
	}

	public void repartirIslas(Isla isla, Isla[][] mapa) {
		int numIslas =10;
		for (int i = 0; i < numIslas; i++) {
			int x = ALEATORIO.nextInt(mapa.length);
			int y = ALEATORIO.nextInt(mapa.length);

			// comprobar posicionamiento
			// primero que la isla quepa entera en el mapa, osea que si x o y resulta estar
			// pegado con algun borde del mapa como la isla no se puede generar entera
			// porque ocupa un espacio de 5x5, la isla no se genera
			if (x < 2 || y < 2 || x >= mapa.length - 2 || y >= mapa[0].length - 2) {
				i--;
				continue;
			}
			// si no existe nada en el espacio donde se generara la isla, la genera en ese
			// espacio, sino, no se genera
			if (mapa[x + 2][y] == null && mapa[x - 2][y] == null && mapa[x][y + 2] == null && mapa[x][y - 2] == null) {
				hacerFormaIsla(mapa, x, y, mundo.getIslasDisponibles().get("Refugio Sombrío"));
			} else {
				i--;
			}
		}
	}

	public void repartirEncuentrosEnemigos(Isla[][] mapa) {
		Isla encuentroEnem = new Isla("encuentroEnem");
		int numEncuentros = 150;
		for (int i = 0; i < numEncuentros; i++) {
			int x = ALEATORIO.nextInt(mapa.length);
			int y = ALEATORIO.nextInt(mapa.length);

			// comprobar posicionamiento
			// primero que la isla quepa entera en el mapa, osea que si x o y resulta estar
			// pegado con algun borde del mapa como la isla no se puede generar entera
			// porque ocupa un espacio de 5x5, la isla no se genera
			if (x < 0 || y < 0 || x >= mapa.length || y >= mapa[0].length) {
				i--;
				continue;
			}
			// si no existe nada en el espacio donde se generara la isla, la genera en ese
			// espacio, sino, no se genera
			if (mapa[x][y] == null) {
				mapa[x][y] = encuentroEnem;
			} else {
				i--;
			}
		}
	}

	private void moverUnaUnidad(int direccion) {
		int x = mundo.getPosicionX();
		int y = mundo.getPosicionY();

		switch (direccion) {
		case 1:
			y--;
			break; // norte
		case 2:
			y++;
			break; // sur
		case 3:
			x++;
			break; // este
		case 4:
			x--;
			break; // oeste
		}

		if (x >= 0 && y >= 0 && x < mundo.getMapa().length && y < mundo.getMapa()[0].length) {
			mundo.setPosicionX(x);
			mundo.setPosicionY(y);
		}
	}

}
