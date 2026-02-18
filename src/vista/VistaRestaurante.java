package vista;

import modeloPersonajes.Cliente;

public class VistaRestaurante {
	// atributos
	private GestorVista gestorVista = new GestorVista();

	// metodos propios
	public void hacerPedido(Cliente c) {
		if (c.getPedidos().length == 1) {
			gestorVista.imprimirMensaje("Buenas tardes! Ponme por favor " + c.getPedidos()[0]);
		}
	}
}
