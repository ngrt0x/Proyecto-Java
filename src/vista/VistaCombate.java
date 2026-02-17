package vista;

import modeloPersonajes.Enemigo;
import modeloPersonajes.Tripulante;

public class VistaCombate {
	// atributos
	private GestorVista gestorVista = new GestorVista();

	// metodos propios
	public void hudCombate() {

	}

	public int mostrarEnemigos(Enemigo[] enemigos, Tripulante t) {
		int opcion;
		int longitudBarra = 20;
		// recorrer el array de enemigos y mostrar sus barras de vida
		for (int i = 0; i < enemigos.length; i++) {
			int saludActual = enemigos[i].getSaludActual();
			int saludTope = enemigos[i].getSaludTope();
			int bloquesLlenos = (int) ((double) saludActual / saludTope * longitudBarra);
			int bloquesVacios = longitudBarra - bloquesLlenos;

			gestorVista.imprimirMensaje((i + 1) + ". " + enemigos[i].getNombre());
			for (int j = 0; j < bloquesLlenos; j++) {
				gestorVista.imprimirMensajePegado("█");
			}
			for (int j = 0; j < bloquesVacios; j++) {
				gestorVista.imprimirMensajePegado("░");
			}
			gestorVista.imprimirMensajePegado(" " + saludActual + "/" + saludTope);
			gestorVista.imprimirMensaje("");
		}
		gestorVista.imprimirMensaje("0. Atrás");
		gestorVista.imprimirMensaje("A qué enemigo quieres que ataque " + t.getNombre() + "?");
		opcion = gestorVista.pedirNum();
		while (opcion < 0 || opcion > enemigos.length) {
			gestorVista.imprimirError("Selecciona una opción válda: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public void mostrarEnemigos(Enemigo[] enemigos) {
		int longitudBarra = 20;

		gestorVista.imprimirMensaje("=== Un grupo de enemigos asaltan tu barco! ===");
		// recorrer el array de enemigos y mostrar sus barras de vida
		for (int i = 0; i < enemigos.length; i++) {
			int saludActual = enemigos[i].getSaludActual();
			int saludTope = enemigos[i].getSaludTope();
			int bloquesLlenos = (int) ((double) saludActual / saludTope * longitudBarra);
			int bloquesVacios = longitudBarra - bloquesLlenos;

			gestorVista.imprimirMensaje(enemigos[i].getNombre());
			for (int j = 0; j < bloquesLlenos; j++) {
				gestorVista.imprimirMensajePegado("█");
			}
			for (int j = 0; j < bloquesVacios; j++) {
				gestorVista.imprimirMensajePegado("░");
			}
			gestorVista.imprimirMensajePegado(" " + saludActual + "/" + saludTope);
			gestorVista.imprimirMensaje("");
		}
	}

	public int menuCombate(Tripulante t) {
		int opcion;

		gestorVista.imprimirMensaje("=== Turno de " + t.getNombre() + " ===");
		gestorVista.imprimirMensaje("1. Atacar\n" + "2. Defender\n" + "3. Usar objeto");

		opcion = gestorVista.pedirNum();
		while (opcion < 1 || opcion > 3) {
			gestorVista.imprimirError("Selecciona una opción válda: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public void imprimirMensaje(String msg) {
		gestorVista.imprimirMensaje(msg);
	}

}
