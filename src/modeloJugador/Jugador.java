package modeloJugador;

import modeloMundo.Isla;
import modeloObjetos.CanaPescar;
import modeloObjetos.Consumible;
import modeloObjetos.Item;

public class Jugador {
	// atributos
	private String nombre;
	private Inventario inventario = new Inventario();
	private int oro;
	private Barco barco;
	private Diario diario;
	private Isla islaActual;

	// constructor
	public Jugador(String nombre, int opcionModo) {
		barco = new Barco(opcionModo);
		this.nombre = nombre;
		diario = new Diario();
		
		Item canaBase = new CanaPescar("Caña de pescar básica", "cana_base", 5, 10);
		Item brebajeSalud = new Consumible("Brebaje de Salud", "pot_salud", 75, "curar");
		Item brebajeIniciativa = new Consumible("Brebaje de Iniciativa", "pot_init", 75, "iniciativa");
		switch (opcionModo) {
		case 1:
			oro = 50000000;
			inventario.anadirItem(canaBase);
			inventario.anadirItem(brebajeIniciativa);
			inventario.anadirItem(brebajeSalud);
			break;
		case 2:
			oro = 10;
			inventario.anadirItem(canaBase);
			break;
		}
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
	
	public Diario getDiario() {
		return diario;
	}

	public Isla getIslaActual() {
		return islaActual;
	}

	public void setIslaActual(Isla islaActual) {
		this.islaActual = islaActual;
	}

	// metodos propios
	public void sumarOro(int cantidad) {
		oro = oro += cantidad;
	}

	public void restarOro(int cantidad) {
		oro = oro -= cantidad;
	}
}
