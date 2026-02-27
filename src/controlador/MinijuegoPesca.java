package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import modeloJugador.Jugador;
import modeloObjetos.CanaPescar;
import modeloObjetos.Item;
import modeloObjetos.Pez;
import vista.VistaPesca;

/**
 * Controlador del minijuego de pesca. Gestiona todo lo relacionado con éste
 * sistema.
 * 
 * @author Jesús Manrique, Marcos Villagómez.
 * @version 1.0
 */
public class MinijuegoPesca implements Minijuego {
	/**
	 * Instancia de VistaPesca, la vista encargada de imprimir y pedir la
	 * información relacionada con éste minijuego.
	 */
	private VistaPesca vistaPesca = new VistaPesca();
	/** Instancia de Random del paquete java.util. */
	private final Random ALEATORIO = new Random();
	/** Resistencia de la linea. */
	private int resistenciaLinea;
	/** Referencia al objeto Jugador */
	private Jugador j;
	/**
	 * Booleano para comprobar que el usuario tiene el Item cebo bueno en su
	 * inventario.
	 */
	boolean tieneCeboBueno;

	/**
	 * Contructor de la clase MinijuegoPesca.
	 * 
	 * @param jugador Recibe el Jugador que va a participar en la pesca.
	 */
	public MinijuegoPesca(Jugador jugador) {
		j = jugador;
	}

	@Override
	/**
	 * Método de la interfaz Minijuego. Comienza el minijuego.
	 */
	public void comenzar() {
		// muestra el menu inicial
		int opcion;
		opcion = vistaPesca.menuInicial();

		// comprueba las cañas de pescar que tenga el jugador
		resistenciaLinea = comprobarCana();
		while (opcion != 0) {
			tieneCeboBueno = false;
			switch (opcion) {
			case 1:
				if (resistenciaLinea == 0) {
					vistaPesca.mensajeNoCana();
				} else {
					lanzarAnzuelo();
					try {
						if (esperar()) {
							lucha(generarPezRandom());
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;
			case 2:
				vistaPesca.mostrarReglas(j);
				break;
			}

			opcion = vistaPesca.menuInicial();
		}
	}

	/**
	 * Comprueba que el usuario tenga un Item de la clase CanaPescar.
	 * <p>
	 * En el caso de que tenga varias, las guarda en un ArrayList, lo ordena en base
	 * a la línea de la caña y devuelve la linea de la caña con más línea.
	 * </p>
	 * 
	 * @return La linea del objeto CanaPescar con más línea en el inventario del
	 *         Jugador.
	 */
	private int comprobarCana() {
		Map<String, Item> inventario = j.getInventario().getItems();
		List<CanaPescar> canas = new ArrayList<>();
		for (String i : inventario.keySet()) {
			if (inventario.get(i) instanceof CanaPescar) {
				canas.add((CanaPescar) inventario.get(i));
			}
		}
		canas.sort((a, b) -> b.getLinea() - a.getLinea());
		return canas.get(0).getLinea();
	}

	/**
	 * Genera un Pez aleatorio de entre los peces disponibles, instanciados en este
	 * mismo método.
	 * <p>
	 * Cuanto más bajo sea el número correspondiente a la rareza del pez, saldrá con
	 * menos frecuencia.
	 * </p>
	 * 
	 * @return Devuelve un Pez.
	 */
	private Pez generarPezRandom() {
		// peces disponibles, para añadir mas peces simplemente crea un pez nuevo y
		// añádelo a la lista de peces
		Pez tiburon = new Pez("Tiburón", "tiburon", 200, 10, 70);
		Pez pezGlobo = new Pez("Pez globo", "pez_globo", 16, 25, 13);
		Pez ballena = new Pez("Ballena", "ballena", 500, 5, 90);
		Pez gallo = new Pez("Gallo", "gallo", 18, 40, 20);
		Pez bota = new Pez("Bota vieja", "bota_vieja", 0, 15, 25);
		Pez dorada = new Pez("Dorada", "dorada", 22, 40, 20);
		Pez calamar = new Pez("Calamar", "calamar", 17, 30, 25);
		Pez boqueron = new Pez("Boquerón", "boqueron", 5, 45, 13);
		Pez atun = new Pez("Atún", "atun", 150, 10, 60);
		Pez jurel = new Pez("Jurel", "jurel", 8, 45, 13);
		Pez pezEspada = new Pez("Pez espada", "pez_espada", 175, 10, 65);
		int totalRareza = 0;
		int random;
		int rarezaAcumulada = 0;
		Pez[] listaPeces = { tiburon, pezGlobo, gallo, bota, dorada, calamar, boqueron, atun, jurel, ballena,
				pezEspada };
		for (Pez p : listaPeces) {
			totalRareza += p.getRareza();
		}
		random = generarAleatorioEntre(0, totalRareza - 1);
		for (Pez p : listaPeces) {
			rarezaAcumulada += p.getRareza();
			if (random < rarezaAcumulada) {
				return p;
			}
		}
		return null;
	}

	/**
	 * Conecta con la VistaPesca para imprimir un mensaje para indicar que el
	 * usuario lanza el anzuelo.
	 */
	private void lanzarAnzuelo() {
		vistaPesca.imprimirMensaje("Lanzas el anzuelo lo más fuerte que puedes, a ver si pica algo...");
	}

	/**
	 * Genera un número aleatorio entre 2 y 6 si el usuario no tiene cebo bueno en
	 * su inventario, o entre 1 y 3 si tiene. Este número corresponderá a los turnos
	 * que tiene que se hará esperar al usuario hasta que "pique" un pez.
	 * <p>
	 * A cada turno se conectará con VistaPesca para imprimir un mensaje de espera y
	 * de dormirá al hilo durante 3000 milisegundos. Cuando acaben esos turnos
	 * VistaPesca pedirá al usuario que introduzca TIRAR por teclado.
	 * </p>
	 * 
	 * @return Devuelve true si el usuario consigue escribir TIRAR bien, false si
	 *         no.
	 * @throws InterruptedException
	 */
	private boolean esperar() throws InterruptedException {
		int turnosAEsperar = generarAleatorioEntre(2, 6);
		// chequea si el jugador ha comprado cebo bueno, si es verdad reduce los turnos
		// de espera para que pique algo
		if (j.getInventario().getItems().containsKey("cebo_bueno")) {
			turnosAEsperar = generarAleatorioEntre(1, 3);
			tieneCeboBueno = true;
		}
		for (int i = 0; i < turnosAEsperar; i++) {
			Thread.sleep(3000);
			vistaPesca.imprimirMensaje(
					vistaPesca.getMensajeEspera()[ALEATORIO.nextInt(vistaPesca.getMensajeEspera().length)]);
		}
		Thread.sleep(3000);
		vistaPesca.imprimirMensaje(
				vistaPesca.getMensajePicada()[ALEATORIO.nextInt(vistaPesca.getMensajePicada().length)]);
		String texto = vistaPesca.tirarCana();
		// devuelve true si el usuario consigue tirar bien del pez, sino el pez de va y
		// no comienza la pesca
		if (!texto.toLowerCase().equals("tirar")) {
			vistaPesca.imprimirMensaje("Vaya, parece que se ha ido... A la próxima tendré que estar más atento.");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Método principal del minijuego. Gestiona el minijuego de lucha entre el
	 * jugador y un pez una vez que ha mordido el anzuelo.
	 * <p>
	 * Durante la lucha se controlan 3 variables principales: La resistencia de
	 * linea, la energia del pez, y la distancia entre el pez y el jugador.
	 * </p>
	 * <p>
	 * El minijuego termina cuando la resistencia de linea llega a 0 o la distancia
	 * del pez supera la distancia tope, enconces el pez se escapa y el jugador
	 * pierde la lucha.
	 * </p>
	 * <p>
	 * También puede terminar si la energía del pez y la distancia llegan a 0,
	 * entonces el jugador gana la lucha y se añade el Pez a su Inventario.
	 * </p>
	 * 
	 * @param pez Recibe el Pez contra el que se va a luchar. Su fuerza determina la
	 *            dificultad de la lucha.
	 */
	private void lucha(Pez pez) {
		// atributos
		final int LINEA_TOPE = resistenciaLinea;
		int lineaActual = resistenciaLinea;
		final int ENERGIA_TOPE = pez.getFuerza();
		int energiaPActual = pez.getFuerza();
		final int DISTANCIA_TOPE = 20;
		int distanciaActual = 15;
		boolean finalizado = false;
		int opcion;

		// bucle
		while (!finalizado) {
			vistaPesca.hudLucha(LINEA_TOPE, lineaActual, ENERGIA_TOPE, energiaPActual, DISTANCIA_TOPE, distanciaActual);
			if (energiaPActual == 0) {
				vistaPesca.imprimirMensaje("El pez está agotado, es tu momento!");
			}
			opcion = vistaPesca.menuLucha();
			switch (opcion) {
			case 1:
				vistaPesca.mensajesLucha(opcion);
				energiaPActual = Math.max(0, energiaPActual - 3);
				distanciaActual = Math.max(0, distanciaActual - 5);
				if (ALEATORIO.nextInt(100) < Math.min(80, pez.getFuerza()) && energiaPActual > 0) {
					vistaPesca.imprimirMensaje("El pez se revuelve violentamente!");
					lineaActual = Math.max(0, lineaActual - (3 + (int) ((double) pez.getFuerza() / 20)));
					distanciaActual = Math.min(DISTANCIA_TOPE, distanciaActual + 3);
				} else {
					lineaActual = Math.max(0, lineaActual - 3);
				}
				break;
			case 2:
				vistaPesca.mensajesLucha(opcion);
				lineaActual = Math.min(LINEA_TOPE, lineaActual + 3);
				distanciaActual = Math.min(DISTANCIA_TOPE, distanciaActual + 3);
				if (energiaPActual > 0) {
					energiaPActual = Math.min(ENERGIA_TOPE, energiaPActual + 1);
				}
				break;
			case 3:
				vistaPesca.mensajesLucha(opcion);
				if (ALEATORIO.nextBoolean()) {
					lineaActual = Math.max(0, lineaActual - 1);
				}
				energiaPActual = Math.max(0, energiaPActual - 1);
				break;
			}
			if (lineaActual == 0 || distanciaActual == DISTANCIA_TOPE) {
				finalizado = true;
				vistaPesca.resultadoEscape(lineaActual, distanciaActual, DISTANCIA_TOPE);
				j.getInventario().restarItem("cebo_bueno", 1);
			}
			if (energiaPActual == 0 && distanciaActual == 0 && lineaActual != 0) {
				finalizado = true;
				vistaPesca.resultadoPesca(pez.getNombre());
				pez.setCantidad(1);
				j.getInventario().anadirItem(pez);
			}
		}
	}

	/**
	 * Genera un número aleatorio entre un número mímino y un número máximo que
	 * recibe.
	 * 
	 * @param min Integer correspondiente al número mínimo que quieres que se
	 *            genere.
	 * @param max Integer correspondiente al número máximo que quieres que se
	 *            genere.
	 * @return Devuelve un Integer aleatorio entre el número mínimo y el número
	 *         máximo que le has pasado.
	 */
	private int generarAleatorioEntre(int min, int max) {
		return ALEATORIO.nextInt(max - min + 1) + min;
	}

}
