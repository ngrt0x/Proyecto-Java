package controlador;

import java.util.ArrayList;
import java.util.Random;

import modeloJugador.Jugador;
import modeloObjetos.Plato;
import modeloPersonajes.Cliente;
import vista.VistaRestaurante;

public class MinijuegoRestaurante implements Minijuego {
	// atributos propios
	private final Random ALEATORIO = new Random();
	private VistaRestaurante vistaRestaurante = new VistaRestaurante();
	private final String[] INGREDIENTES = { "Pan", "Carne", "Pescado", "Pulpo", "Agua", "Arroz", "Patatas", "Especias",
			"Alga" };
	private ArrayList<Cliente> clientes = new ArrayList<>();
	private ArrayList<Plato> platosPreparados = new ArrayList<>();
	private Jugador j;
	private int duracionTurno = 25;
	private int turnoActual;
	private int contadorClientes;

	// constructor
	public MinijuegoRestaurante(Jugador j) {
		this.j = j;
	}

	// getters y setters
	public int getDuracionTurno() {
		return duracionTurno;
	}

	public void setDuracionTurno(int duracionTurno) {
		this.duracionTurno = duracionTurno;
	}

	public int getTurnoActual() {
		return turnoActual;
	}

	public void setTurnoActual(int turnoActual) {
		this.turnoActual = turnoActual;
	}

	// metodos de la interfaz
	@Override
	public void comenzar() {
		int opcion;
		// resetear todos los atributos cuando comienza el minijuego
		turnoActual = 1;
		contadorClientes = 0;
		clientes.clear();
		platosPreparados.clear();
		vistaRestaurante.mensajeInicio(j);
		while (turnoActual < duracionTurno) {
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
			opcion = vistaRestaurante.menuRestaurante(this);
			switch (opcion) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			}

			// resta paciencia de todos los clientes a cada turno que pasa
			for (Cliente c : clientes) {
				c.restarPaciencia(1);
			}
			turnoActual++;
		}

	}

	// metodos propios
	public void mostrarPreparaciones() {

	}

	private void prepararPlato() {

	}

	private void servirCliente() {

	}

	private Cliente generarCliente() {
		Plato[] pedido = generarPedido();
		String nombreCliente;
		contadorClientes++;
		nombreCliente = "Cliente " + contadorClientes;
		Cliente c = new Cliente(nombreCliente, pedido);
		return c;
	}

	private Plato[] generarPedido() {
		Plato[] pedido;
		// platos disponibles
		Plato estofadoCapitan = new Plato("Estofado del Capitán", "estofado_cap", 5, "Agua", "Carne", "Patatas",
				"Especias");
		Plato arrozMarinero = new Plato("Arroz Marinero", "arroz_mar", 3, "Arroz", "Pescado");
		Plato sopaPescado = new Plato("Sopa de Pescado y Algas", "sopa_pescado", 3, "Agua", "Pescado", "Alga");
		Plato krakenGallega = new Plato("Kraken a la Gallega", "kraken_gallega", 5, "Patatas", "Pulpo");
		Plato nigiri = new Plato("Nigiri Pirata", "nigiri", 4, "Arroz", "Pescado", "Alga");
		Plato bocadillo = new Plato("Bocadillo de Carne Sin Identificar", "bocadillo", 3, "Pan", "Carne");
		Plato[] platosDisponibles = { estofadoCapitan, arrozMarinero, sopaPescado, krakenGallega, nigiri, bocadillo };

		// determinar el tamano del pedido, entre 1 y 3 platos
		int tamano = generarAleatorioEntre(1, 3);
		pedido = new Plato[tamano];
		for (int i = 0; i < pedido.length; i++) {
			pedido[i] = platosDisponibles[generarAleatorioEntre(0, (platosDisponibles.length - 1))];
		}
		return pedido;
	}

	private int generarAleatorioEntre(int min, int max) {
		return ALEATORIO.nextInt(max - min + 1) + min;
	}

}
