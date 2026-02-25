package vista;

import modeloMundo.Isla;

public class VistaNavegacion {
	// atributos
	private GestorVista gestorVista = new GestorVista();

	// metodos propios
	public int seleccionarDistancia() {
		int distancia;
		gestorVista.imprimirMensajePegado("Introduce la distancia que quieres que se desplace tu navío: ");
		distancia = gestorVista.pedirNum();
		while (distancia > 100 || distancia < 1) {
			gestorVista.imprimirError("Introduce una distancia entre 1 y 100 unidades: ");
			distancia = gestorVista.pedirNum();
		}
		return distancia;
	}

	public int seleccionarDireccion() {
		int direccion;
		gestorVista.imprimirMensaje("En qué dirección quieres zarpar capitán?");
		gestorVista.imprimirMensaje("1. Norte\n" + "2. Sur\n" + "3. Este\n" + "4. Oeste");
		direccion = gestorVista.pedirNum();
		while (direccion > 4 || direccion < 1) {
			gestorVista.imprimirError("Introduce una opción válida: ");
			direccion = gestorVista.pedirNum();
		}
		return direccion;
	}

	public void imprimirMapa(int xPersonaje, int yPersonaje, Isla[][] mapa) {
		int radio = 25;

		for (int y = yPersonaje - radio; y <= yPersonaje + radio; y++) {
			for (int x = xPersonaje - radio; x <= xPersonaje + radio; x++) {
				if (x == xPersonaje && y == yPersonaje) {
					System.out.print("⬢ ");
				} else if (x < 0 || y < 0 || x >= mapa.length || y >= mapa[0].length) {
					System.out.print(". ");
				} else if (mapa[x][y] == null) {
					System.out.print("~ ");
				} else {
					System.out.print("# ");
				}
			}
			System.out.println();
		}
	}

	public void mensajeDescubrirIsla(Isla i) {
		gestorVista.imprimirMensaje("Has descubierto " + i.getNombre() + "!");
	}

	public void mensajeLlegarIsla(Isla i) {
		gestorVista.imprimirMensaje("Has llegado a " + i.getNombre() + "!");
	}

	public void limpiarPantalla() {
		for (int i = 0; i < 50; i++) {
			gestorVista.imprimirMensaje("");
		}
	}
}
