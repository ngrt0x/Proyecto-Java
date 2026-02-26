package modeloPersonajes;

import java.util.ArrayList;

import modeloObjetos.Plato;

/**
 * Clase cliente se encarga de almacenar la informacion de los clientes del restaurante
 * 
 * @author Jesús Manrique y Marcos Villagómez
 * @version 1.0
 */
public class Cliente extends NPC {
	// atributos propios
	/**
	 * Lista de platos pedidos por el cliente
	 */
	private ArrayList<Plato> pedido = new ArrayList<>();
	/**
	 * Lista de platos recibidos por el jugador
	 */
	private ArrayList<Plato> platosRecibidos = new ArrayList<>();
	/**
	 * Paciencia actual del cliente
	 */
	private int pacienciaActual;
	/**
	 * Paciencia tope del cliente
	 */
	private int pacienciaTope;

	// constructor
	/**
	 * Constructor de cliente
	 * @param nombre Nombre del cliente
	 * @param pedido Platos pedidos
	 */
	public Cliente(String nombre, ArrayList<Plato> pedido) {
		super(nombre);
		this.pedido = pedido;
		pacienciaTope = 6;
		pacienciaActual = pacienciaTope;
	}

	// gettes y setters
	/**
	 * Getter de pedido
	 * @return Lista de platos pedidos
	 */
	public ArrayList<Plato> getPedido() {
		return pedido;
	}

	/**
	 * Getter de platos recibidos
	 * @return Lista de platos recibidos
	 */
	public ArrayList<Plato> getPlatosRecibidos() {
		return platosRecibidos;
	}

	/**
	 * Getter paciencia actual
	 * @return Paciencia actual
	 */
	public int getPacienciaActual() {
		return pacienciaActual;
	}

	/**
	 * Getter paciencia tope
	 * @return Paciencia tope
	 */
	public int getPacienciaTope() {
		return pacienciaTope;
	}

	// metodos propios
	/**
	 * Metodo para restar paciencia de la paciencia actual
	 * @param cantidad Cantidad de paciencia a restar
	 */
	public void restarPaciencia(int cantidad) {
		pacienciaActual = Math.max(pacienciaActual - cantidad, 0);
	}

	/**
	 * Metodo para darle los platos que ha pedido al cliente
	 * @param p Plato dado al cliente
	 */
	public void addPlatosRecibidos(Plato p) {
		platosRecibidos.add(p);
	}
}
