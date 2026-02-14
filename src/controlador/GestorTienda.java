package controlador;

import java.util.Map;

import modeloJugador.Inventario;
import modeloObjetos.Item;
import modeloJugador.Jugador;
import modeloPersonajes.NPC;
import vista.VistaTienda;

public class GestorTienda {
	// atributos
	private VistaTienda vistaTienda = new VistaTienda();
	private Inventario stock;
	private NPC tendero = new NPC("Alexander el Tendero", "Buenos días capitán, que le puedo ofrecer hoy?");
	private Jugador j;
	// Items disponibles
	private Item canaReforzada = new Item("Caña reforzada", "cana_reforzada", 150, 1);
	private Item canaFlexible = new Item("Caña flexible", "cana_flexible", 100, 1);
	private Item canaMaestra = new Item("Caña maestra", "cana_maestra", 300, 1);
	private Item ceboBueno = new Item("Cebo de alta calidad", "cebo_bueno", 50, 5);

	// constructor
	public GestorTienda(Jugador jugador) {
		j = jugador;
		stock = new Inventario();
		stock.anadirItem(canaFlexible);
		stock.anadirItem(canaReforzada);
		stock.anadirItem(canaMaestra);
		stock.anadirItem(ceboBueno);
	}

	// getters y setters
	public Inventario getStock() {
		return stock;
	}

	public NPC getTendero() {
		return tendero;
	}

	public Jugador getJugador() {
		return j;
	}

	// metodos propios
	public void entrarTienda() {
		int opcion = vistaTienda.mostrarStock(this);
		while (opcion != 0) {
			comprarItem(opcion);
			opcion = vistaTienda.mostrarStock(this);
		}
	}

	private void comprarItem(int opcion) {
		int confirmacion;
		int contador = 1;
		Map<String, Item> itemsALaVenta = stock.getItems();
		for (String i : itemsALaVenta.keySet()) {
			if (opcion == contador) {
				if (j.getOro() - itemsALaVenta.get(i).getPrecio() <= 0) {
					vistaTienda.imprimirMensaje("No tienes suficiente dinero para comprar eso!");
				} else {
					confirmacion = vistaTienda.menuConfirmacion(itemsALaVenta.get(i));
					switch (confirmacion) {
					case 1:
						j.getInventario().anadirItem(itemsALaVenta.get(i));
						vistaTienda.mensajeCompra(this);
						break;
					}
				}
			}
			contador++;
		}
	}

	// hay que crear un menu en vistaTienda que te de la opcion de hablar con el
	// tendero, comprar o vender items, imprimir ese menu desde entrarTienda(), de
	// ahi con la opcion que introduzca el usuario empezar con la logica de mostrar
	// stock para comprar, mostrar el inventario del jugador para vender, y hacer lo
	// correspondiente. Hablar con el tendero por lo pronto nos lo podemos ahorrar.
	private void venderItem() {

	}
}