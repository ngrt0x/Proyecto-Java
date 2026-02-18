package modeloPersonajes;

public class Cliente extends NPC {
	// atributos propios
	private String[] pedidos;
	private String[] posiblesDialogos;

	// constructor
	public Cliente(String nombre) {
		super(nombre);
	}

	// gettes y setters
	public String[] getPedidos() {
		return pedidos;
	}

	public void setPedidos(String[] pedidos) {
		this.pedidos = pedidos;
	}

	public String[] getPosiblesDialogos() {
		return posiblesDialogos;
	}

	public void setPosiblesDialogos(String[] posiblesDialogos) {
		this.posiblesDialogos = posiblesDialogos;
	}

}
