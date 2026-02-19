package controlador;

import modeloJugador.Jugador;
import vista.VistaJuego;

public class JuegoControlador {
	// atributos
	private VistaJuego vistaJuego = new VistaJuego();
	private Jugador jugador = new Jugador(vistaJuego.pedirNombre());
	private final GestorTienda TIENDA = new GestorTienda(jugador);
	private final MinijuegoPesca PESCA = new MinijuegoPesca(jugador);
	private final GestorCombate COMBATE = new GestorCombate(jugador);
	private final MinijuegoRestaurante COMIDAS = new MinijuegoRestaurante(jugador);

	// metodos
	public void iniciarJuego() {
		int opcion = vistaJuego.menuInicio();
		while (opcion != 0) {
			switch (opcion) {
			case 1 -> PESCA.comenzar();
			case 2 -> {
				int opcionInventario = vistaJuego.menuInventarios();
				vistaJuego.mostrarInventario(jugador, opcionInventario);
			}
			case 3 -> TIENDA.entrarTienda();
			case 4 -> COMBATE.comenzar();
			case 5 -> COMIDAS.comenzar();
			}
			opcion = vistaJuego.menuInicio();
		}
		vistaJuego.mensajeDespedida(jugador);
	}

}
