package vista;

import modeloJugador.Jugador;

/**
 * Clase Vsita Pesca, tiene todos los mensajes relacionados a la pesca
 * 
 * @author Jesús Manrique y Marcos Villagómez
 * @version 1.0
 */
public class VistaPesca {
	// atributos
	/**
	 * Instancia de GestorVista
	 */
	private GestorVista gestorVista = new GestorVista();
	/**
	 * Mensajes de espera
	 */
	private String[] mensajeEspera = { "Nada...", "Me parece ver algo de movimiento...",
			"Los peces deben de estar durmiendo...", "Paciencia...", "No pica nada...",
			"Quizá debería haber traído mejor cebo...", "Un pequeño tirón... no, falsa alarma.",
			"Vamos... sé que hay algo ahí abajo...", "El agua está demasiado tranquila..." };
	/**
	 * Mensaje de picada
	 */
	private String[] mensajePicada = { "Ha picado algo!", "Este es grande, ha picado!", "Ahí va, tengo algo!",
			"Algo ha mordido el anzuelo!", "Esto promete!", "Rápido, antes de que se escape!" };

	// getters y setters
	/**
	 * Getter de mensaje de espera
	 * @return Mensaje de espera
	 */
	public String[] getMensajeEspera() {
		return mensajeEspera;
	}

	/**
	 * Getter de mensaje de picada
	 * @return Mensaje de picada
	 */
	public String[] getMensajePicada() {
		return mensajePicada;
	}

	// metodos propios
	/**
	 * Metodo para imprimir el menu de pesca
	 * @return Opcion
	 */
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

	/**
	 * Metodo para imprimir las reglas
	 * @param j Jugador
	 */
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

	/**
	 * Metodo para imprimir el menu de lucha con el pez
	 * @return Opcion
	 */
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

	/**
	 * Metodo para imprimir el mensaje del menuLucha
	 * @param opcion Opcion Elegida
	 */
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

	/**
	 * Metodo para imprimir el resultado de la pesca
	 * @param nombre Nombre del pez
	 */
	public void resultadoPesca(String nombre) {
		gestorVista.imprimirMensaje("Tras una ardua batalla consigues sacar al agotado pez del agua, es tuyo!");
		gestorVista.imprimirMensaje("Has pescado: " + nombre + "!");
	}

	/**
	 * Metodo para imprimir el resultado de escape y a cuanto te has quedado
	 * @param lineaActual Linea Actual
	 * @param distanciaActual Distancia Actual
	 * @param distanciaTope Distancia tope
	 */
	public void resultadoEscape(int lineaActual, int distanciaActual, int distanciaTope) {
		if (lineaActual == 0) {
			gestorVista.imprimirMensaje("Se ha roto la linea de tu caña y el pez se ha escapado!");
		} else if (distanciaActual >= distanciaTope) {
			gestorVista.imprimirMensaje("El pez se ha alejado demasiado. Lo has perdido de vista y se ha escapado...");
		}
	}

	/**
	 * Metodo para imprimir el HUD de lucha con el pez
	 * @param lineaTope Linea tope
	 * @param lineaActual Linea actual
	 * @param pezTope Pez tope
	 * @param pezActual Pez actual
	 * @param distanciaTope Distancia tope
	 * @param distanciaActual Distancia actual
	 */
	public void hudLucha(int lineaTope, int lineaActual, int pezTope, int pezActual, int distanciaTope,
			int distanciaActual) {
		int longitudBarra = 20;
		int bloquesLlenos;
		int bloquesVacios;
		gestorVista.imprimirMensaje("=======================================================");
		// resistencia de linea
		gestorVista.imprimirMensajePegado("Resistencia de linea: \t");
		bloquesLlenos = (int) ((double) lineaActual / lineaTope * longitudBarra);
		bloquesVacios = longitudBarra - bloquesLlenos;
		for (int j = 0; j < bloquesLlenos; j++) {
			gestorVista.imprimirMensajePegado("█");
		}
		for (int j = 0; j < bloquesVacios; j++) {
			gestorVista.imprimirMensajePegado("░");
		}
		gestorVista.imprimirMensaje("");

		// energia del pez
		gestorVista.imprimirMensajePegado("Energía del pez: \t");
		bloquesLlenos = (int) ((double) pezActual / pezTope * longitudBarra);
		bloquesVacios = longitudBarra - bloquesLlenos;
		for (int j = 0; j < bloquesLlenos; j++) {
			gestorVista.imprimirMensajePegado("█");
		}
		for (int j = 0; j < bloquesVacios; j++) {
			gestorVista.imprimirMensajePegado("░");
		}
		gestorVista.imprimirMensaje("");

		// distancia del pez
		gestorVista.imprimirMensajePegado("Distancia del pez: \t");
		bloquesLlenos = (int) ((double) distanciaActual / distanciaTope * longitudBarra);
		bloquesVacios = longitudBarra - bloquesLlenos;
		for (int j = 0; j < bloquesLlenos; j++) {
			gestorVista.imprimirMensajePegado("█");
		}
		for (int j = 0; j < bloquesVacios; j++) {
			gestorVista.imprimirMensajePegado("░");
		}
		gestorVista.imprimirMensaje("");
	}

	/**
	 * Metodo para imprimir el mensaje de empezar lucha
	 * @return String del jugador
	 */
	public String tirarCana() {
		gestorVista.imprimirMensaje("Escribe 'TIRAR' para comenzar la lucha!!");
		return gestorVista.pedirString();
	}

	/**
	 * Metodo para imprimir el mensaje de no tienes una caña
	 */
	public void mensajeNoCana() {
		gestorVista.imprimirMensaje("No tienes una caña de pescar en tu inventario! Cómo vas a pescar así?");
	}

	/**
	 * Metodo para imprimir un mensaje por pantalla
	 * @param msg Mensaje a imprimir
	 */
	public void imprimirMensaje(String msg) {
		gestorVista.imprimirMensaje(msg);
	}
}