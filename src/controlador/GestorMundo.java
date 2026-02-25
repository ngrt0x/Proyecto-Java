package controlador;

import modeloMundo.Isla;
import modeloMundo.Mundo;
import vista.GestorVista;
import vista.VistaNavegacion;
import java.util.Random;

public class GestorMundo {
	// atributos
	private final Random ALEATORIO = new Random();
	private GestorVista gestorVista = new GestorVista();
	private VistaNavegacion vistaNavegacion = new VistaNavegacion();

	private Mundo mundo;

	// Constructor
	public GestorMundo(Mundo mundo) {
		this.mundo = mundo;
	}

	// metodos propios
	public void cambiarIsla() {
		gestorVista.imprimirMensaje("A que isla te gustaria viajar?");
		String opcionIsla = gestorVista.pedirString(); // cogemos el nombre de la isla a la que quiere viajar el jugador

		while (!mundo.getIslasDisponibles().containsKey(opcionIsla)) { // si la isla que ha elegido el jugador no existe
																		// le volvemos a pedir el string
			gestorVista.imprimirError("Elige una de las islas disponibles (Asegurate de escribir bien el nombre)");
			opcionIsla = gestorVista.pedirString();
		}

		if (opcionIsla.equals(mundo.getUbicacionActual().getNombre())) { // si la isla elegida es la misma en la que
																			// esta le salta error
			gestorVista.imprimirError("No puedes viajar a la misma isla en la que estas, vuelve a elegir");
		}
	}

	public void navegar() throws InterruptedException {
		int distancia;
		int direccion;
		direccion = vistaNavegacion.seleccionarDireccion();
		distancia = vistaNavegacion.seleccionarDistancia();
		for (int i = 0; i < distancia; i++) {
			moverUnaUnidad(direccion);
			Isla isla = mundo.getMapa()[mundo.getPosicionX()][mundo.getPosicionY()];
			vistaNavegacion.limpiarPantalla();
			vistaNavegacion.imprimirMapa(mundo.getPosicionX(), mundo.getPosicionY(), mundo.getMapa());
			if (isla != null) {
				if (!isla.isVisitada()) {
					isla.setVisitada();
					vistaNavegacion.mensajeDescubrirIsla(isla);
				} else {
					vistaNavegacion.mensajeLlegarIsla(isla);
				}
				break;
			}

			Thread.sleep(150);
		}
		direccion = vistaNavegacion.seleccionarDireccion();
		distancia = vistaNavegacion.seleccionarDistancia();
	}

	private void hacerFormaIsla(Isla[][] mapa, int x, int y, Isla isla) {
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
		int numIslas = 50;
		for (int i = 0; i < numIslas; i++) {
			int x = ALEATORIO.nextInt(mapa.length);
			int y = ALEATORIO.nextInt(mapa.length);

			// comprobar posicionamiento
			if (x < 2 || y < 2 || x >= mapa.length - 2 || y >= mapa[0].length - 2) {
				i--;
				continue;
			}
			if (mapa[x + 2][y] == null && mapa[x - 2][y] == null && mapa[x][y + 2] == null && mapa[x][y - 2] == null) {
				hacerFormaIsla(mapa, x, y, mundo.getIslasDisponibles().get("Refugio Sombrío"));
			} else {
				i--;
			}
		}
	}

	private void moverUnaUnidad(int direccion) {
		int nx = mundo.getPosicionX();
		int ny = mundo.getPosicionY();

		switch (direccion) {
		case 1:
			ny--;
			break; // norte
		case 2:
			ny++;
			break; // sur
		case 3:
			nx++;
			break; // este
		case 4:
			nx--;
			break; // oeste
		}

		if (nx >= 0 && ny >= 0 && nx < mundo.getMapa().length && ny < mundo.getMapa()[0].length) {
			mundo.setPosicionX(nx);
			mundo.setPosicionY(ny);
		}
	}

}
