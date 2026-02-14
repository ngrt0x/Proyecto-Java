package vista;

import modeloJugador.Jugador;

public class VistaPesca {
	// atributos
	private GestorVista gestorVista = new GestorVista();
	private String[] mensajeEspera = { "Nada...", "Me parece ver algo de movimiento...",
			"Los peces deben de estar durmiendo...", "Paciencia...", "No pica nada..." };
	private String[] mensajePicada = { "Ha picado algo!", "Este es grande, ha picado!", "Ahí va, tengo algo!" };

	// getters y setters
	public String[] getMensajeEspera() {
		return mensajeEspera;
	}

	public String[] getMensajePicada() {
		return mensajePicada;
	}

	// metodos propios
	public int menuInicial() {
		int opcion;
		gestorVista.imprimirMensaje("=======================================================");
		gestorVista.imprimirMensaje("Qué quieres hacer?");
		gestorVista.imprimirMensaje("1. Lanzar anzuelo");
		gestorVista.imprimirMensaje("2. Mostrar reglas");
		gestorVista.imprimirMensaje("0. Dejar de pescar");
		gestorVista.imprimirMensaje("=======================================================");

		opcion = gestorVista.pedirNum();
		while (opcion < 0 || opcion > 2) {
			gestorVista.imprimirError("Introduce una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public void mostrarReglas(Jugador j) {
		gestorVista.imprimirMensaje("=== INSTRUCCIONES ===\n" + "Vale " + j.getNombre()
				+ ", parece que eres nuevo en esto de la pesca. "
				+ "Es muy fácil, lo primero de todo es lanzar el anzuelo al agua a ser posible, y a partir de ahí a esperar, hay que ser paciente.\n"
				+ "Pero no te descuides pues tendrás que TIRAR con fuerza en el momento que pique algo!\n\n"
				+ "En cuanto tengas algo enganchado comenzará la fiera lucha que es la pesca. "
				+ "Podrás tirar, mantener tu posición, o dar línea al pez, depende de la situación.\n"
				+ "Tu misión es agotar la energía del pez y sacarlo del agua mientras balanceas estas acciones para que no se rompa la línea de tu caña o el pez se escape.\n\n"
				+ "Cada acción repercutirá en esta lucha de forma diferente:\n"
				+ "- Tirar causará que reduzca la energía del pez y estrechará distancias entre vosotros, pero aumentará la tensión en la línea.\n"
				+ "- Dar línea al pez reducirá bastante la tensión de línea pero no afectará a la energía del pez, y éste se alejará de ti.\n"
				+ "- Mantener la posición agotará un poco la energía del pez, aumentará un poco la tensión en la línea, y mantendrá la distancia entre vosotros.\n\n"
				+ "Aprovecha cuando el pez esté agotado para reducir todo lo posible la distancia entre vosotros y sacarlo así del agua.\n"
				+ "Con esto dicho, mucho ánimo con la pesca!");
	}

	public int menuLucha() {
		int opcion;

		gestorVista.imprimirMensaje("Qué quieres hacer?");
		gestorVista.imprimirMensaje("1. Tirar con fuerza");
		gestorVista.imprimirMensaje("2. Aflojar un poco");
		gestorVista.imprimirMensaje("3. Mantener la posición");
		gestorVista.imprimirMensaje("=======================================================");
		opcion = gestorVista.pedirNum();
		while (opcion < 1 || opcion > 3) {
			gestorVista.imprimirError("Introduce una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public void mensajesLucha(int opcion) {
		switch (opcion) {
		case 1:
			gestorVista.imprimirMensaje("Tiras con fuerza pero el pez se resiste!");
			break;
		case 2:
			gestorVista.imprimirMensaje("Le das algo de linea al pez, no dejes que se escape!");
			break;
		case 3:
			gestorVista.imprimirMensaje("Te mantienes firme, esta es una batalla de desgaste.");
			break;
		}
	}

	public void resultadoPesca(String nombre) {
		gestorVista.imprimirMensaje("Tras una ardua batalla consigues sacar al agotado pez del agua, es tuyo!");
		gestorVista.imprimirMensaje("Has pescado: " + nombre + "!");
	}

	public void resultadoEscape(int lineaActual, int distanciaActual, int distanciaTope) {
		if (lineaActual == 0) {
			gestorVista.imprimirMensaje("Se ha roto la linea de tu caña y el pez se ha escapado!");
		} else if (distanciaActual >= distanciaTope) {
			gestorVista.imprimirMensaje("El pez se ha alejado demasiado. Lo has perdido de vista y se ha escapado...");
		}
	}

	public void hudLucha(int lineaTope, int lineaActual, int pezTope, int pezActual, int distanciaTope,
			int distanciaActual) {
		gestorVista.imprimirMensaje("=======================================================");
		gestorVista.imprimirMensajePegado("Resistencia de linea: \t");
		for (int i = 0; i < lineaActual; i++) {
			gestorVista.imprimirMensajePegado("█");
		}
		for (int i = 0; i < lineaTope - lineaActual; i++) {
			gestorVista.imprimirMensajePegado("░");
		}
		gestorVista.imprimirMensaje("");
		gestorVista.imprimirMensajePegado("Energía del pez: \t");
		for (int i = 0; i < pezActual; i++) {
			gestorVista.imprimirMensajePegado("█");
		}
		for (int i = 0; i < pezTope - pezActual; i++) {
			gestorVista.imprimirMensajePegado("░");
		}
		gestorVista.imprimirMensaje("");
		gestorVista.imprimirMensajePegado("Distancia del pez: \t");
		for (int i = 0; i < distanciaActual; i++) {
			gestorVista.imprimirMensajePegado("█");
		}
		for (int i = 0; i < distanciaTope - distanciaActual; i++) {
			gestorVista.imprimirMensajePegado("░");
		}
		gestorVista.imprimirMensaje("");
	}

	public String tirarCana() {
		gestorVista.imprimirMensaje("Escribe 'TIRAR' para comenzar la lucha!!");
		return gestorVista.pedirString();
	}

	public void imprimirMensaje(String msg) {
		gestorVista.imprimirMensaje(msg);
	}
}