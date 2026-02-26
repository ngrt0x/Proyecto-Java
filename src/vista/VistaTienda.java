package vista;

import java.util.HashMap;
import java.util.Map;

import controlador.GestorAstillero;
import controlador.GestorTienda;
import modeloJugador.Jugador;
import modeloMundo.Astillero;
import modeloMundo.Isla;
import modeloMundo.Tienda;
import modeloObjetos.Item;

public class VistaTienda {
	// atributos
	private GestorVista gestorVista = new GestorVista();

	// metodos propios
	public int hablarTendero1(Tienda t, Isla islaActual) {
		int opcion;
		gestorVista.imprimirEspacio();
		switch (islaActual.getNombre()) {
		case "Isla Langosta":
			gestorVista.imprimirMensaje(t.getTendero().getNombre()
					+ ": 'Buenas tardes.' El tendero te observa con cara de pocos amigos.\n"
					+ "'Eres nuevo por aquí verdad? Más te vale no andar armando jaleo. Qué te puedo ofrecer?'\n"
					+ "1. Comprar\n" + "2. Vender\n" + "0. Salir");
			break;
		case "Refugio Sombrío":
			gestorVista.imprimirMensaje(t.getTendero().getNombre()
					+ ": 'Pasa pasa, cuida no te des en la cabeza con los trastos. Qué te puedo ofrecer pequeño lobo de mar?'\n"
					+ "La señora te hace un gesto con la mano, dirigiendo tu mirada a un escaparate lleno de artículos.\n"
					+ "1. Comprar\n" + "2. Vender\n" + "0. Salir");
			break;
		}
		opcion = gestorVista.pedirNum();
		while (opcion < 0 || opcion > 2) {
			gestorVista.imprimirError("Introduce una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public int hablarTendero2(Tienda t) {
		int opcion;
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje(t.getTendero().getNombre() + ": 'Qué te puedo ofrecer?'\n" + "1. Comprar\n"
				+ "2. Vender\n" + "0. Salir");
		opcion = gestorVista.pedirNum();
		while (opcion < 0 || opcion > 2) {
			gestorVista.imprimirError("Introduce una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public int hablarAstillero1(Astillero ast, Isla islaActual) {
		int opcion;
		gestorVista.imprimirEspacio();
		switch (islaActual.getNombre()) {
		case "Isla Langosta":
			gestorVista.imprimirMensaje(ast.getTendero().getNombre()
					+ ": 'Buenas tardes capitán! Precioso navío el que trae consigo, pero creo que le vendrían bien unos retoques.'\n"
					+ "1. Comprar mejoras de barco\n" + "0. Salir");
			break;
		case "Refugio Sombrío":
			gestorVista.imprimirMensaje(
					"El trabajador del astillero, de aspecto lúgubre, te saluda sin mucha energía cuando entras por la puerta.\n"
							+ "No puedes evitar fijarte en la prótesis de metal que tiene por mano.\n"
							+ ast.getTendero().getNombre()
							+ ": 'Cuando me preguntan sobre mi mano suelo decir que la perdí en una fiera batalla contra un tiburón blanco.\n"
							+ "La verdad es que la perdí en un accidente laboral porque mi astillero no cumplía con los requisitos de la norma ISO 45001.\n"
							+ "1. Comprar mejoras de barco\n" + "0. Salir");
			break;
		}
		opcion = gestorVista.pedirNum();
		while (opcion < 0 || opcion > 1) {
			gestorVista.imprimirError("Introduce una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public int hablarAstillero2(Astillero ast) {
		int opcion;
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje(ast.getTendero().getNombre() + ": 'Qué te puedo ofrecer?'\n"
				+ "1. Comprar mejoras de barco\n" + "0. Salir");
		opcion = gestorVista.pedirNum();
		while (opcion < 0 || opcion > 1) {
			gestorVista.imprimirError("Introduce una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public int mostrarStock(GestorTienda gt, Tienda t) {
		int opcion;
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje(t.getTendero().getPrimeraFrase());
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje("======================== STOCK ========================");
		Map<String, Item> stock = t.getStock().getItems();
		int contador = 1;
		for (String i : stock.keySet()) {
			gestorVista.imprimirMensaje(contador + ". " + stock.get(i).getNombre() + ":");
			gestorVista.imprimirMensaje("\tPrecio: " + stock.get(i).getPrecio() + "g");
			gestorVista.imprimirMensaje("\tCantidad: " + stock.get(i).getCantidad());
			contador++;
		}
		gestorVista.imprimirMensaje("=======================================================");
		gestorVista.imprimirMensaje("Tu oro: " + gt.getJugador().getOro() + "g");
		gestorVista.imprimirMensaje("0. Atrás");
		opcion = gestorVista.pedirNum();
		while (opcion < 0 || opcion > stock.size()) {
			gestorVista.imprimirError("Introduce una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public int mostrarMejoras(GestorAstillero ga, Astillero ast) {
		int opcion;
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje(ast.getTendero().getPrimeraFrase());
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje("======================= MEJORAS ========================");
		Map<String, Item> stock = ast.getStock().getItems();
		int contador = 1;
		for (String i : stock.keySet()) {
			gestorVista.imprimirMensaje(contador + ". " + stock.get(i).getNombre() + ":");
			gestorVista.imprimirMensaje("\tPrecio: " + stock.get(i).getPrecio() + "g");
			gestorVista.imprimirMensaje("\tCantidad: " + stock.get(i).getCantidad());
			contador++;
		}
		gestorVista.imprimirMensaje("=======================================================");
		gestorVista.imprimirMensaje("Tu oro: " + ga.getJugador().getOro() + "g");
		gestorVista.imprimirMensaje("0. Atrás");
		opcion = gestorVista.pedirNum();
		while (opcion < 0 || opcion > stock.size()) {
			gestorVista.imprimirError("Introduce una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public void mostrarInventario(Jugador j, int opcion) {
		int contador = 1;
		if (opcion != 0) {
			gestorVista.imprimirEspacio();
			gestorVista.imprimirMensaje("===================== INVENTARIO =====================");
			gestorVista.imprimirMensaje("Tu oro: " + j.getOro());
			Map<String, Item> inventario;

			if (opcion == 1) {
				inventario = j.getInventario().getItem();
				if (inventario.isEmpty()) {
					gestorVista.imprimirEspacio();
					gestorVista.imprimirMensaje("No tienes ningún objeto en el inventario!");
				} else {
					gestorVista.imprimirEspacio();
					for (String i : inventario.keySet()) {
						gestorVista.imprimirMensaje(contador + ". " + inventario.get(i).getNombre() + ":");
						gestorVista.imprimirMensaje("\tPrecio: " + inventario.get(i).getPrecio() + "g");
						gestorVista.imprimirMensaje("\tCantidad: " + inventario.get(i).getCantidad());
						contador++;
					}
				}
			} else if (opcion == 2) {
				inventario = j.getInventario().getPeces();
				if (inventario.isEmpty()) {
					gestorVista.imprimirEspacio();
					gestorVista.imprimirMensaje("No tienes ningún pez en el inventario!");
				} else {
					gestorVista.imprimirEspacio();
					for (String i : inventario.keySet()) {
						gestorVista.imprimirMensaje(contador + ". " + inventario.get(i).getNombre() + ":");
						gestorVista.imprimirMensaje("\tPrecio: " + inventario.get(i).getPrecio() + "g");
						gestorVista.imprimirMensaje("\tCantidad: " + inventario.get(i).getCantidad());
					}
				}
			}
		}
	}

	public int ventanaVenta(GestorTienda t, int opcionInventario) {
		Jugador j = t.getJugador();
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje("Que quiere vender capitan?");
		int opcionVenta = gestorVista.pedirNum(); // PEDIR EL NUMERO DEL ITEM EN LA LISTA DEL INVENTARIO

		HashMap<String, Item> inventario = (opcionInventario == 1) ? j.getInventario().getItem() // SI OPCION INVENTARIO
																									// ES 1
				: j.getInventario().getPeces(); // SI OPCION INVENTARIO ES 2

		while (opcionVenta < 1 || opcionVenta > inventario.size()) {
			gestorVista.imprimirError("Elija un valor valido");
			opcionVenta = gestorVista.pedirNum();
		}

		return opcionVenta; // return DEL NUMERO DE ITEM
	}

	public int menuConfirmacionCompra(Item i) {
		int opcion;
		gestorVista.imprimirEspacio();
		gestorVista
				.imprimirMensaje("Esto es lo que quieres comprar?\n" + i.getNombre() + " por " + i.getPrecio() + "g?");
		gestorVista.imprimirMensaje("1. Si\n2. No");
		opcion = gestorVista.pedirNum();
		while (opcion < 1 || opcion > 2) {
			gestorVista.imprimirError("Introduce una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public int menuConfirmacionVenta(Item i) {
		int opcion;
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje("Seguro que quieres vender " + i.getNombre() + " por " + i.getPrecio() + "g?");
		gestorVista.imprimirMensaje("1. Si\n2. No");
		opcion = gestorVista.pedirNum();
		while (opcion < 1 || opcion > 2) {
			gestorVista.imprimirError("Introduce una opción válida: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public void mensajeCompra(GestorTienda t) {
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje(t.getTienda().getTendero().getNombre() + ": 'Gracias por su compra.'");
	}

	public void mensajeMejora(GestorAstillero ast) {
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje(ast.getAstillero().getTendero().getNombre()
				+ ": 'Gracias! Instalaremos su nuevo equipamiento en un santiamén capitán.'");
	}

	public void mensajeVenta(GestorTienda t, Item itemVendido) {
		gestorVista.imprimirEspacio();
		gestorVista.imprimirMensaje(
				"Has vendido x1 " + itemVendido.getNombre() + ", por " + itemVendido.getPrecio() + "g.");
	}

	public void imprimirMensaje(String msg) {
		gestorVista.imprimirMensaje(msg);
	}
}
