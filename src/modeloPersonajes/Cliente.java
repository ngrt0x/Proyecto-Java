package modeloPersonajes;

import java.util.ArrayList;

import modeloObjetos.Plato;

public class Cliente extends NPC {
	// atributos propios
	private ArrayList<Plato> pedido = new ArrayList<>();
	private ArrayList<Plato> platosRecibidos = new ArrayList<>();
	private int pacienciaActual;
	private int pacienciaTope;

	// constructor
	public Cliente(String nombre, ArrayList<Plato> pedido) {
		super(nombre);
		this.pedido = pedido;
		pacienciaTope = 6;
		pacienciaActual = pacienciaTope;
	}

	// gettes y setters
	public ArrayList<Plato> getPedido() {
		return pedido;
	}

	public ArrayList<Plato> getPlatosRecibidos() {
		return platosRecibidos;
	}

	public int getPacienciaActual() {
		return pacienciaActual;
	}

	public int getPacienciaTope() {
		return pacienciaTope;
	}

	// metodos propios
	public void restarPaciencia(int cantidad) {
		pacienciaActual = Math.max(pacienciaActual - cantidad, 0);
	}

	public void addPlatosRecibidos(Plato p) {
		platosRecibidos.add(p);
	}
}
