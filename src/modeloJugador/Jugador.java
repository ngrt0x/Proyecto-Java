package modeloJugador;

import modeloObjetos.CanaPescar;
import modeloObjetos.Consumible;
import modeloObjetos.Item;

public class Jugador {
	// atributos
	private String nombre;
	private Inventario inventario;
	private int oro;
	private Barco barco;
	private Diario diario;

	// constructor
	public Jugador(String nombre) {
		barco = new Barco();
		this.nombre = nombre;
		oro = 50000000;
		inventario = new Inventario();
		Item canaBase = new CanaPescar("Caña de pescar básica", "cana_base", 5, 10);
		Item brebajeSalud = new Consumible("Brebaje de Salud", "pot_salud", 75, "curar");
		Item brebajeIniciativa = new Consumible("Brebaje de Iniciativa", "pot_init", 75, "iniciativa");
		inventario.anadirItem(canaBase);
		inventario.anadirItem(brebajeIniciativa);
		inventario.anadirItem(brebajeSalud);
	}

	// getters y setters
	public Inventario getInventario() {
		return inventario;
	}

	public int getOro() {
		return oro;
	}

	public void setOro(int oro) {
		this.oro = oro;
	}

	public String getNombre() {
		return nombre;
	}

	public Barco getBarco() {
		return barco;
	}

	// metodos propios
	public void sumarOro(int cantidad) {
		oro = oro += cantidad;
	}

	public void restarOro(int cantidad) {
		oro = oro -= cantidad;
	}
}
