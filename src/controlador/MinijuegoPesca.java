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

public class MinijuegoPesca implements Minijuego {
	// atributos
	private VistaPesca vistaPesca = new VistaPesca();
	private final Random ALEATORIO = new Random();
	private int resistenciaLinea;
	private Jugador j;

	// constructor
	public MinijuegoPesca(Jugador jugador) {
		j = jugador;
	}

	// metodos
	@Override
	public void comenzar() {
		// muestra el menu inicial
		int opcion;
		opcion = vistaPesca.menuInicial();

		// comprueba las cañas de pescar que tenga el jugador
		resistenciaLinea = comprobarCana();
		while (opcion != 0) {
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

	private Pez generarPezRandom() {
		// peces disponibles, para añadir mas peces simplemente crea un pez nuevo y
		// añádelo a la lista de peces
		Pez tiburon = new Pez("Tiburón", "tiburon", 100, 10, 40);
		Pez pezGlobo = new Pez("Pez globo", "pez_globo", 15, 25, 15);
		Pez ballena = new Pez("Ballena", "ballena", 200, 5, 75);
		Pez gallo = new Pez("Gallo", "gallo", 15, 40, 15);
		Pez bota = new Pez("Bota vieja", "bota_vieja", 0, 15, 20);
		Pez dorada = new Pez("Dorada", "dorada", 25, 40, 15);
		Pez calamar = new Pez("Calamar", "calamar", 20, 30, 20);
		Pez boqueron = new Pez("Boquerón", "boqueron", 5, 45, 10);
		Pez atun = new Pez("Atún", "atun", 85, 10, 35);
		Pez jurel = new Pez("Jurel", "jurel", 5, 45, 10);
		int totalRareza = 0;
		int random;
		int rarezaAcumulada = 0;
		Pez[] listaPeces = { tiburon, pezGlobo, gallo, bota, dorada, calamar, boqueron, atun, jurel, ballena };
		for (Pez p : listaPeces) {
			totalRareza += p.getRareza();
		}
		random = generarAleatorioEntre(0, totalRareza);
		for (Pez p : listaPeces) {
			rarezaAcumulada += p.getRareza();
			if (random < rarezaAcumulada) {
				return p;
			}
		}
		return null;
	}

	private void lanzarAnzuelo() {
		vistaPesca.imprimirMensaje("Lanzas el anzuelo lo más fuerte que puedes, a ver si pica algo...");
	}

	private boolean esperar() throws InterruptedException {
		int turnosAEsperar = generarAleatorioEntre(2, 6);
		// chequea si el jugador ha comprado cebo bueno, si es verdad reduce los turnos
		// de espera para que pique algo
		if (j.getInventario().getItems().containsKey("cebo_bueno")) {
			turnosAEsperar = generarAleatorioEntre(1, 4);
		}
		for (int i = 0; i < turnosAEsperar; i++) {
			Thread.sleep(3000);
			vistaPesca.imprimirMensaje(vistaPesca.getMensajeEspera()[generarAleatorioEntre(0, 4)]);
		}
		Thread.sleep(3000);
		vistaPesca.imprimirMensaje(vistaPesca.getMensajePicada()[generarAleatorioEntre(0, 2)]);
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
				lineaActual = Math.max(0, lineaActual - 3);
				energiaPActual = Math.max(0, energiaPActual - 3);
				distanciaActual = Math.max(0, distanciaActual - 4);
				if (generarAleatorioEntre(0, 60) < pez.getFuerza() && energiaPActual > 0) {
					vistaPesca.imprimirMensaje("El pez se revuelve violentamente!");
					lineaActual = Math.max(0, lineaActual - 2);
				}
				break;
			case 2:
				vistaPesca.mensajesLucha(opcion);
				lineaActual = Math.min(LINEA_TOPE, lineaActual + 4);
				distanciaActual = Math.min(DISTANCIA_TOPE, distanciaActual + 3);
				if (generarAleatorioEntre(1, 4) == 4) {
					vistaPesca.imprimirMensaje("El pez se cansa un poco huyendo.");
					energiaPActual = Math.max(0, energiaPActual - 1);
				}
				break;
			case 3:
				vistaPesca.mensajesLucha(opcion);
				lineaActual = Math.max(0, lineaActual - 1);
				energiaPActual = Math.max(0, energiaPActual - 1);
				if (generarAleatorioEntre(0, 100) > 60) {
					distanciaActual = Math.min(DISTANCIA_TOPE, distanciaActual + 1);
				}
				break;
			}
			if (lineaActual == 0 || distanciaActual == DISTANCIA_TOPE) {
				finalizado = true;
				vistaPesca.resultadoEscape(lineaActual, distanciaActual, DISTANCIA_TOPE);
			}
			if (energiaPActual == 0 && distanciaActual == 0 && lineaActual != 0) {
				finalizado = true;
				vistaPesca.resultadoPesca(pez.getNombre());
				pez.setCantidad(1);
				j.getInventario().anadirItem(pez);
			}
		}
	}

	private int generarAleatorioEntre(int min, int max) {
		return ALEATORIO.nextInt(max - min + 1) + min;
	}

}
