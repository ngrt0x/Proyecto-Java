package app;

import controlador.JuegoControlador;

public class Main {
	public static void main(String[] args) {
		final JuegoControlador JUEGO = new JuegoControlador();

		try {
			JUEGO.iniciarJuego();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
