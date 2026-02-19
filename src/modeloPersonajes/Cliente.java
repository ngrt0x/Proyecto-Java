package modeloPersonajes;

import modeloObjetos.Plato;

public class Cliente extends NPC {
	// atributos propios
	private Plato[] pedido;
	private int pacienciaActual;
	private int pacienciaTope;

	// constructor
	public Cliente(String nombre, Plato[] pedido) {
		super(nombre);
		this.pedido = pedido;
		pacienciaTope = 6;
		pacienciaActual = pacienciaTope;
	}

	// gettes y setters
	public Plato[] getPedido() {
		return pedido;
	}

	public void setPedido(Plato[] pedido) {
		this.pedido = pedido;
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
}
