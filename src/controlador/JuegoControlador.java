package controlador;

import modeloJugador.Jugador;
import vista.VistaJuego;

public class JuegoControlador {
	// atributos
	private VistaJuego vistaJuego = new VistaJuego();
	private Jugador jugador = new Jugador(vistaJuego.pedirNombre());
	private final GestorTienda TIENDA = new GestorTienda(jugador);
	private final MinijuegoPesca PESCA = new MinijuegoPesca(jugador);
	private final GestorCombate COMBATE = new GestorCombate(jugador.getBarco());

	// metodos
	public void iniciarJuego() {
		int opcion;
		opcion = vistaJuego.menuInicio();
		while (opcion != 0) {
			switch (opcion) {
			case 1:
				PESCA.comenzar();
				break;
			case 2:
				vistaJuego.mostrarInventario(jugador);
				break;
			case 3:
				TIENDA.entrarTienda();
				break;
			case 4:
				COMBATE.comenzar();
			}
			opcion = vistaJuego.menuInicio();
		}
		vistaJuego.mensajeDespedida(jugador);
	}

}
