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
		int radio = 19;
		gestorVista.imprimirMensajePegado("\t\t\t");
		for (int y = yPersonaje - radio; y <= yPersonaje + radio; y++) {
			for (int x = xPersonaje - radio; x <= xPersonaje + radio; x++) {
				if (x == xPersonaje && y == yPersonaje) {
					// el personaje se representa con un punto gordo
					gestorVista.imprimirMensajePegado("⬢ ");
				} else if (x < 0 || y < 0 || x >= mapa.length || y >= mapa[0].length) {
					// zona fuera de los limites del mapa
					gestorVista.imprimirMensajePegado("  ");
				} else if (mapa[x][y] == null) {
					// la zona navegable se representa con puntitos
					gestorVista.imprimirMensajePegado(". ");
				} else if (mapa[x][y].getNombre().equals("encuentroEnem")) {
					// los encuentros enemigos son arrobas
					gestorVista.imprimirMensajePegado("@ ");
				} else {
					// y las islas se representan con almohadillas
					gestorVista.imprimirMensajePegado("# ");
				}
			}
			gestorVista.imprimirEspacio();
			gestorVista.imprimirMensajePegado("\t\t\t");
		}
		gestorVista.imprimirEspacio();
	}

	public void mensajeDescubrirIsla(Isla i) {
		gestorVista.imprimirMensaje("Has descubierto " + i.getNombre() + "!");
	}

	public void mensajeLlegarIsla(Isla i) {
		gestorVista.imprimirMensaje("Has llegado a " + i.getNombre() + "!");
	}

	public boolean confirmarEntrarIsla(Isla isla) {
		int opcion;
		if (!isla.isVisitada()) {
			mensajeDescubrirIsla(isla);
		} else {
			mensajeLlegarIsla(isla);
		}
		gestorVista.imprimirMensaje("Quieres echar anclas?");
		gestorVista.imprimirMensaje("1. Si\n" + "2. No");
		opcion = gestorVista.pedirNum();
		while (opcion < 1 || opcion > 2) {
			gestorVista.imprimirError("Introduce una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion == 1;
	}

	public void limpiarPantalla() {
		for (int i = 0; i < 50; i++) {
			gestorVista.imprimirMensaje("");
		}
	}
}
