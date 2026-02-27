package controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import modeloJugador.Jugador;
import modeloObjetos.Plato;
import modeloPersonajes.Cliente;
import vista.VistaRestaurante;

/**
 * Controlador del minijuego de restaurante.
 * <p>
 * Gestiona todo lo relacionado con éste sistema.
 * </p>
 * 
 * @author Jesús Manrique, Marcos Villagómez.
 * @version 1.0
 */
public class MinijuegoRestaurante implements Minijuego {
	/** Instancia de Random del paquete java.util. */
	private final Random ALEATORIO = new Random();
	/**
	 * Instancia de VistaRestaurante, encargada de imprimir y pedir la información
	 * relacionada al minijuego de restaurante.
	 */
	private VistaRestaurante vistaRestaurante = new VistaRestaurante();
	/**
	 * Instancia del controlador GestorCombate para gestionar los combates que se
	 * dan durante este minijuego.
	 */
	private GestorCombate gestorCombate;
	/**
	 * Referencia al controlador JuegoControlador para poder usar datos como la
	 * isla, o el día actual.
	 */
	private JuegoControlador jc;
	/**
	 * Array de String constante, que contiene los ingredientes disponibles.
	 */
	private final String[] INGREDIENTES = { "Pan", "Carne", "Pescado", "Pulpo", "Agua", "Arroz", "Patatas", "Especias",
			"Alga" };
	/**
	 * ArrayList de Cliente que almacena los clientes que se van generando para
	 * atender.
	 */
	private ArrayList<Cliente> clientes = new ArrayList<>();
	/**
	 * ArrayList de Plato que almacena los platos que prepara el usuario para poder
	 * entregarlos a los clietes.
	 */
	private ArrayList<Plato> platosPreparados = new ArrayList<>();
	/**
	 * ArrayList de Plato que almacena los platos disponibles para preparar.
	 */
	private ArrayList<Plato> platosDisponibles = new ArrayList<>();
	/** Referencia al objeto Jugador. */
	private Jugador j;
	/** Duración en turnos del minijuego de restaurante. */
	private int duracionTurno;
	/** Turno actual */
	private int turnoActual;
	/** Contador de los clientes que llegan al restaurante */
	private int contadorClientes;

	/**
	 * Constructor de la clase MinijuegoRestaurante.
	 * <p>
	 * Hace uso de la función crearPlatosDisponibles() para crear los objetos Plato
	 * correspondientes a los platos disponibles y los mete en el ArrayList
	 * platosDisponibles.
	 * </p>
	 * 
	 * @param j  Recibe el Jugador participando en el minijuego.
	 * @param jc Recibe el JuegoControlador que está gestionando la partida actual.
	 */
	public MinijuegoRestaurante(Jugador j, JuegoControlador jc) {
		this.j = j;
		gestorCombate = new GestorCombate(j);
		this.jc = jc;
		platosDisponibles = crearPlatosDisponibles();
	}

	// getters y setters
	/** Devuelve la duración total del turno de comidas */
	public int getDuracionTurno() {
		return duracionTurno;
	}

	/** Setter de duracionTurno. */
	public void setDuracionTurno(int duracionTurno) {
		this.duracionTurno = duracionTurno;
	}

	/** Devuelve el turno actual */
	public int getTurnoActual() {
		return turnoActual;
	}

	/** Setter de turnoActual */
	public void setTurnoActual(int turnoActual) {
		this.turnoActual = turnoActual;
	}

	@Override
	/**
	 * Método de la interfaz Minijuego. Inicia y gestiona el minijuego del
	 * restaurante.
	 * <p>
	 * Controla el flujo de turnos, la llegada de clientes, la preparación y
	 * servicio de platos, así como la paciencia de los clientes. El turno avanza
	 * según las acciones realizadas por el jugador y finaliza cuando se alcanza la
	 * duración máxima establecida.
	 * </p>
	 */
	public void comenzar() {
		boolean consumirTurno = true;
		// establecer todos los atributos cuando comienza el minijuego
		duracionTurno = Math.min(25, 10 + (2 * jc.getDiaActual()));
		turnoActual = 1;
		contadorClientes = 0;
		clientes.clear();
		platosPreparados.clear();
		vistaRestaurante.mensajeInicio(j);

		while (turnoActual < duracionTurno) {
			// resetear opciones
			int opcion = -1;
			int opcionC = -1; // opcion cliente
			int opcionP = -1; // opcion plato
			int opcionS = -1; // opcion servir

			if (consumirTurno) {
				// si es el priemr turno si o si genera un cliente
				if (turnoActual == 1) {
					Cliente cliente1 = generarCliente();
					clientes.add(cliente1);
					vistaRestaurante.mensajeLlegaCliente();
					vistaRestaurante.hacerPedido(cliente1);
					// a partir del primer turno, si no tienes clientes SI O SI genera uno, para que
					// no te puedas quedar bloqueado en un turno sin clientes y que no avance el
					// juego
				} else if (clientes.isEmpty()) {
					Cliente clienteO = generarCliente();
					clientes.add(clienteO);
					vistaRestaurante.mensajeLlegaCliente();
					vistaRestaurante.hacerPedido(clienteO);
					// el resto de turnos que no se den esas condiciones habra 1/4 de chances de
					// generar un cliente
				} else {
					if (ALEATORIO.nextInt(4) == 0) {
						Cliente clienteR = generarCliente();
						clientes.add(clienteR);
						vistaRestaurante.mensajeLlegaCliente();
						vistaRestaurante.hacerPedido(clienteR);
					}
				}
				vistaRestaurante.mostrarClientes(clientes);
			}
			opcion = vistaRestaurante.menuRestaurante(this, consumirTurno);
			// mirar el libro de recetas no consume turno
			while (opcion == 3) {
				vistaRestaurante.mostrarRecetas();
				opcion = vistaRestaurante.menuRestaurante(this, consumirTurno);
			}

			consumirTurno = true;

			switch (opcion) {
			case 1:
				Plato platoPreparado = prepararPlato();
				addPlatoPreparado(platoPreparado);
				break;
			case 2:
				opcionC = vistaRestaurante.mostrarClientesAServir(clientes);
				if (opcionC == 0) {
					consumirTurno = false;
					break;
				}
				Cliente clienteAServir = clientes.get(opcionC - 1);
				if (!platosPreparados.isEmpty()) {
					opcionP = vistaRestaurante.mostrarPlatosPreparados(platosPreparados);
					if (opcionP == 0) {
						consumirTurno = false;
						break;
					}
					entregarPlato(clienteAServir, opcionP);
				} else {
					consumirTurno = false;
					vistaRestaurante.mensajeNoHayPlatos();
				}

				while (opcionS != 2) {
					opcionS = vistaRestaurante.menuServir();
					if (opcionS == 0) {
						consumirTurno = false;
						break;
					}
					switch (opcionS) {
					case 1:
						if (!platosPreparados.isEmpty()) {
							opcionP = vistaRestaurante.mostrarPlatosPreparados(platosPreparados);
							if (opcionP == 0) {
								consumirTurno = false;
								break;
							}
							entregarPlato(clienteAServir, opcionP);
						} else {
							vistaRestaurante.mensajeNoHayPlatos();
						}
						break;
					case 2:
						terminarComanda(clienteAServir);
						break;
					}
				}

				break;
			}
			if (consumirTurno) {
				Iterator<Cliente> it = clientes.iterator();
				while (it.hasNext()) {
					Cliente c = it.next();
					c.restarPaciencia(1);

					if (c.getPacienciaActual() <= 0) {
						vistaRestaurante.mensajePacienciaAgotada(c);
						it.remove();
						if (ALEATORIO.nextInt(4) == 0) {
							gestorCombate.comenzarCombateRestaurante();
						}
					}
				}
				turnoActual++;
			}

		}
		vistaRestaurante.mensajeFin(j);
	}

	/**
	 * Permite seleccionar ingredientes y preparar un plato.
	 * <p>
	 * Comprueba si la combinación coincide con algún plato disponible y lo
	 * devuelve. En caso contrario, se prepara un bodrio.
	 * </p>
	 * 
	 * @return Plato resultante de la preparación.
	 */
	private Plato prepararPlato() {
		ArrayList<String> preparacion = new ArrayList<>();
		int opcionI;
		int opcionP = -1;
		Plato platoPreparado;
		// comprueba si no has anadido ningun ingrediente aun y te muestra directamente
		// el menu de anadir ingredientes para que no puedas preparar un plato sin
		// ingredientes
		while (opcionP != 2) {
			if (preparacion.isEmpty()) {
				opcionI = vistaRestaurante.mostarIngredientes(INGREDIENTES);
				preparacion.add(INGREDIENTES[opcionI - 1]);
				// si no esta vacio, te muestra el menu de preparacion donde puedes anadir mas
				// ingredienets o preparar el plato ya
			} else {
				while (opcionP != 2) {
					opcionP = vistaRestaurante.menuPreparacion();
					switch (opcionP) {
					case 1:
						opcionI = vistaRestaurante.mostarIngredientes(INGREDIENTES);
						preparacion.add(INGREDIENTES[opcionI - 1]);
						break;
					case 2:
						// por cada platoDisponible compara los tamanos de ingredientes del plato y de
						// ingredientes en preparacion. Si coincide compara los ingredientes en las
						// listas y devuelve o no el plato que coincida
						for (Plato p : platosDisponibles) {
							if (p.getIngredientes().size() != preparacion.size()) {
								continue;
							} else {
								if (p.getIngredientes().containsAll(preparacion)) {
									// crea una coppia del plato en platos dispoonibles para que el plato en platos
									// preparados y el de platos disponibles no sean el mismo plato
									platoPreparado = new Plato(p);
									vistaRestaurante.mensajePlatoCocinado(platoPreparado);
									return platoPreparado;
								}
							}
						}
					}
				}

			}
		}

		// si no coincide con ningun plato preparas un bodrio
		Plato bodrio = new Plato("Bodrio", "bodrio", 0);
		platoPreparado = bodrio;
		vistaRestaurante.mensajePlatoCocinado(platoPreparado);
		return platoPreparado;
	}

	/**
	 * Finaliza la comanda de un cliente.
	 * <p>
	 * Si el pedido es correcto, el jugador recibe el pago. Si no, el cliente se
	 * marcha y puede comenzar un combate.
	 * </p>
	 * 
	 * @param clienteAServir Cliente cuya comanda se desea cerrar.
	 */
	private void terminarComanda(Cliente clienteAServir) {
		if (clienteAServir.getPlatosRecibidos().containsAll(clienteAServir.getPedido())) {
			int pago = 0;
			for (int i = 0; i < clienteAServir.getPedido().size(); i++) {
				pago += clienteAServir.getPedido().get(i).getPrecio();
			}
			j.sumarOro(pago);
			vistaRestaurante.mensajesPedidoCorrecto(clienteAServir, pago);
			quitarCliente(clienteAServir);
		} else {
			vistaRestaurante.mensajesPedidoIncorrecto(clienteAServir);
			quitarCliente(clienteAServir);
			if (ALEATORIO.nextInt(4) == 0) {
				gestorCombate.comenzarCombateRestaurante();
			}
		}
	}

	/**
	 * Entrega un plato preparado a un cliente y lo elimina de la lista de platos
	 * preparados para servir.
	 * 
	 * @param clienteAServir Cliente que recibe el plato.
	 * @param plato          Índice del plato seleccionado + 1.
	 */
	private void entregarPlato(Cliente clienteAServir, int plato) {
		Plato platoAServir = platosPreparados.get(plato - 1);
		clienteAServir.addPlatosRecibidos(platoAServir);
		vistaRestaurante.mensajePlatoEntregado(clienteAServir, platosPreparados.get(plato - 1));
		restarPlatoPreparado(platoAServir);
	}

	/**
	 * Genera un nuevo cliente con un pedido aleatorio.
	 * 
	 * @return Cliente creado.
	 */
	private Cliente generarCliente() {
		ArrayList<Plato> pedido = generarPedido();
		String nombreCliente;
		contadorClientes++;
		nombreCliente = "Cliente " + contadorClientes;
		Cliente c = new Cliente(nombreCliente, pedido);
		return c;
	}

	/**
	 * Genera un pedido aleatorio de entre 1 y 3 platos disponibles, ajustado según
	 * el día actual, para aumentar progresivamente la dificultad del minijuego.
	 * 
	 * @return Lista de platos que componen el pedido.
	 */
	private ArrayList<Plato> generarPedido() {
		ArrayList<Plato> pedido = new ArrayList<>();
		int tamano;
		// determinar el tamano del pedido, entre 1 y 3 platos
		if (jc.getDiaActual() == 1) {
			if (ALEATORIO.nextInt(3) == 0) {
				tamano = 2;
			} else {
				tamano = 1;
			}
		} else {
			tamano = generarAleatorioEntre(1, 3);
		}
		for (int i = 0; i < tamano; i++) {
			pedido.add(platosDisponibles.get(generarAleatorioEntre(0, (platosDisponibles.size() - 1))));
		}
		return pedido;
	}

	/**
	 * Crea e inicializa la lista de platos disponibles en el restaurante junto con
	 * sus ingredientes y precios.
	 * 
	 * @return Lista de platos base disponibles.
	 */
	private ArrayList<Plato> crearPlatosDisponibles() {
		Plato estofadoCapitan = new Plato("Estofado del Capitán", "estofado_cap", 10);
		estofadoCapitan.addIngredientes(INGREDIENTES[4]);
		estofadoCapitan.addIngredientes(INGREDIENTES[1]);
		estofadoCapitan.addIngredientes(INGREDIENTES[6]);
		estofadoCapitan.addIngredientes(INGREDIENTES[7]);

		Plato arrozMarinero = new Plato("Arroz Marinero", "arroz_mar", 6);
		arrozMarinero.addIngredientes(INGREDIENTES[5]);
		arrozMarinero.addIngredientes(INGREDIENTES[2]);

		Plato sopaPescado = new Plato("Sopa de Pescado y Algas", "sopa_pescado", 6);
		sopaPescado.addIngredientes(INGREDIENTES[4]);
		sopaPescado.addIngredientes(INGREDIENTES[2]);
		sopaPescado.addIngredientes(INGREDIENTES[8]);

		Plato krakenGallega = new Plato("Kraken a la Gallega", "kraken_gallega", 10);
		krakenGallega.addIngredientes(INGREDIENTES[6]);
		krakenGallega.addIngredientes(INGREDIENTES[3]);
		krakenGallega.addIngredientes(INGREDIENTES[7]);

		Plato nigiri = new Plato("Nigiri Pirata", "nigiri", 8);
		nigiri.addIngredientes(INGREDIENTES[5]);
		nigiri.addIngredientes(INGREDIENTES[2]);
		nigiri.addIngredientes(INGREDIENTES[8]);

		Plato bocadillo = new Plato("Bocadillo de Carne Sin Identificar", "bocadillo", 6);
		bocadillo.addIngredientes(INGREDIENTES[0]);
		bocadillo.addIngredientes(INGREDIENTES[1]);

		ArrayList<Plato> platosDisponibles = new ArrayList<>();
		platosDisponibles.add(estofadoCapitan);
		platosDisponibles.add(arrozMarinero);
		platosDisponibles.add(sopaPescado);
		platosDisponibles.add(krakenGallega);
		platosDisponibles.add(nigiri);
		platosDisponibles.add(bocadillo);

		return platosDisponibles;
	}

	/**
	 * Añade un plato a la lista de preparados. Si ya existe, incrementa su cantidad
	 * en lugar de duplicarlo.
	 * 
	 * @param plato Plato a añadir.
	 */
	private void addPlatoPreparado(Plato plato) {
		for (int i = 0; i < platosPreparados.size(); i++) {
			Plato p = platosPreparados.get(i);
			if (p.equals(plato)) {
				p.sumarCantidad(1);
				return;
			}
		}
		platosPreparados.add(plato);
	}

	/**
	 * Reduce la cantidad de un plato preparado. Si su cantidad llega a cero, se
	 * elimina de la lista.
	 * 
	 * @param plato Plato que se quiere restar.
	 */
	private void restarPlatoPreparado(Plato plato) {
		Iterator<Plato> it = platosPreparados.iterator();
		while (it.hasNext()) {
			Plato p = it.next();
			if (p.equals(plato)) {
				p.restarCantidad(1);
				if (p.getCantidad() <= 0) {
					it.remove();
				}
				return;
			}
		}
	}

	/**
	 * Elimina un cliente de la lista de clientes activos. Ya sea porque se le haya
	 * acabado la paciencia, se le haya entregado un plato incorrecto, o su comanda
	 * se ha cerrado correctamente.
	 * 
	 * @param cliente Cliente a eliminar de la lista de clientes activos.
	 */
	private void quitarCliente(Cliente cliente) {
		Iterator<Cliente> it = clientes.iterator();
		while (it.hasNext()) {
			Cliente c = it.next();
			if (c.equals(cliente)) {
				it.remove();
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
