package controlador;

import modeloMundo.Isla;
import modeloMundo.Mundo;
import vista.VistaNavegacion;
import java.util.Random;

import modeloJugador.Jugador;

/**
 * Controlador del sistema de navegación y generación del mapa.
 * 
 * @author Jesús Manrique, Marcos Villagómez
 */
public class GestorMundo {
	/** Instancia de Random de la clase java.util. */
	private final Random ALEATORIO = new Random();
	/**
	 * Instancia de GestorCombate, el controlador correspondiente al sistema de
	 * combate.
	 */
	private GestorCombate combate;
	/**
	 * Instancia de VistaNavegacion, la vista encargada de mostrar la información de
	 * la navegación.
	 */
	private VistaNavegacion vistaNavegacion = new VistaNavegacion();
	/** Referencia al objeto Mundo a gestionar. */
	private Mundo mundo;

	/**
	 * Constructor de la clase GestorMundo.
	 * 
	 * @param mundo Recibe el objeto Mundo sobre el que realizar las funciones.
	 * @param j     Recibe el Jugador del usuario.
	 */
	public GestorMundo(Mundo mundo, Jugador j) {
		this.mundo = mundo;
		combate = new GestorCombate(j);
	}

	/**
	 * Este método inicia el sistema de navegación. Tiene unas variables locales
	 * distancia y dirección que se le pedirán al usuario. También una variable
	 * booleana echarAncla que determinará si el usuairo sigue navegando o termina
	 * de navegar.
	 * 
	 * @return Devuelve el objeto Isla al que ha navegado el usuario.
	 */
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
			vistaNavegacion.limpiarPantalla();
		}
		return isla;
	}

	/**
	 * Método para pausar la ejecución del hilo mediante un Thread.sleep().
	 * 
	 * @param ms Recibe un Integer correspondiente a los milisegundos que va a
	 *           dormir la ejecución del hilo.
	 */
	public void dormir(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Éste método crea referencias del objeto Isla en la matriz de Isla que hay en
	 * la clase Mundo. No crea una una sola referencia en la coordenada que se le de
	 * sino que crea referencias con una forma "circular" en el mapa para darles más
	 * cuerpo a las Islas de cara a mostrarlas en el mapa durante la navegación.
	 * 
	 * @param mapa Recibe una matriz de objetos Isla.
	 * @param x    Integer correspondiente a la coordenada X donde se va a colocar
	 *             el centro de la Isla.
	 * @param y    Integer correspondiente a la coordenada Y donde se va a colocar
	 *             el centro de la Isla.
	 * @param isla Recibe el objeto Isla a referenciar dentro del mapa.
	 */
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

	/**
	 * Reparte una cantidad de Islas correspondiente a la variable local numIslas
	 * por el mapa de forma aleatoria. Comprueba que la Isla que va a colocar no se
	 * sale de los límites del mapa. Luego le pasa la funcion sePuedeColocar() para
	 * comprobar que no haya ninguna otra Isla donde se va a colocar ésta, y que
	 * haya cierto margen entre las Islas.
	 * 
	 * @param isla Recibe la Isla que va a repartir por el mapa.
	 * @param mapa Recibe la matriz de Isla, representante del mapa.
	 */
	public void repartirIslas(Isla isla, Isla[][] mapa) {
		int numIslas = 10;
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
			if (sePuedeColocar(mapa, 20, x, y)) {
				hacerFormaIsla(mapa, x, y, mundo.getIslasDisponibles().get("Refugio Sombrío"));
			} else {
				i--;
			}
		}
	}

	/**
	 * Comprueba que no haya ninguna otra Isla donde se va a colocar ésta, y que
	 * haya cierto margen entre las Islas.
	 * 
	 * @param mapa   Matriz de Isla
	 * @param margen Integer correspondiente al margen que va a haber entre Isla e
	 *               Isla
	 * @param x      Integer correspondiente a la coordenada X donde se va a colocar
	 *               el centro de la Isla.
	 * @param y      Integer correspondiente a la coordenada Y donde se va a colocar
	 *               el centro de la Isla.
	 * @return Devuelve un booleano. True si la Isla se puede colocar en esas
	 *         coordenadas, false si no.
	 */
	private boolean sePuedeColocar(Isla[][] mapa, int margen, int x, int y) {
		if (mapa[x][y] != null)
			return false;

		for (int i = x - margen; i <= x + margen; i++) {
			for (int j = y - margen; j <= y + margen; j++) {

				if (i >= 0 && i < mapa.length && j >= 0 && j < mapa[0].length) {
					if (mapa[i][j] != null) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Método para repartir los encuentros con enemigos durante la navegación de
	 * forma aleatoria. Muy parecido al método para repartir islas solo que que los
	 * encuentros con enemigos solo ocupan una casilla y no se preocupan por margen
	 * entre otros objetos. La cantidad de enemigos en el mapa la determina la
	 * variable local numEncuentros.
	 * 
	 * @param mapa Matriz de Isla
	 */
	public void repartirEncuentrosEnemigos(Isla[][] mapa) {
		Isla encuentroEnem = new Isla("encuentroEnem");
		int numEncuentros = 150;
		for (int i = 0; i < numEncuentros; i++) {
			int x = ALEATORIO.nextInt(mapa.length);
			int y = ALEATORIO.nextInt(mapa.length);

			if (x < 0 || y < 0 || x >= mapa.length || y >= mapa[0].length) {
				i--;
				continue;
			}

			if (mapa[x][y] == null) {
				mapa[x][y] = encuentroEnem;
			} else {
				i--;
			}
		}
	}

	/**
	 * Desplaza la posición actual del jugador en el mapa 1 posición, en una
	 * dirección.
	 * 
	 * @param direccion Integer representante de la dirección que el jugador se va a
	 *                  mover.
	 */
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
