package vista;

import java.util.ArrayList;
import java.util.Random;

import modeloObjetos.Consumible;
import modeloPersonajes.Enemigo;
import modeloPersonajes.ICombatiente;
import modeloPersonajes.Tripulante;

public class VistaCombate {
	// atributos
	private final Random ALEATORIO = new Random();
	private GestorVista gestorVista = new GestorVista();

	// metodos propios
	public int elegirEnemigo(Enemigo[] enemigos, Tripulante t) {
		int opcion;
		gestorVista.imprimirMensaje("");
		for (int i = 0; i < enemigos.length; i++) {
			gestorVista.imprimirMensaje((i + 1) + ". " + enemigos[i].getNombre());
		}
		gestorVista.imprimirMensaje("A qué enemigo quieres que ataque " + t.getNombre() + "?");
		opcion = gestorVista.pedirNum();
		while (opcion <= 0 || opcion > enemigos.length || !enemigos[opcion - 1].estaVivo()) {
			if (opcion <= 0 || opcion > enemigos.length) {
				gestorVista.imprimirError("Selecciona una opción válda: ");
			} else if (!enemigos[opcion - 1].estaVivo()) {
				gestorVista.imprimirError("No puedes atacar a un enemigo que ya ha sido derrotado!");
			}
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public void mostrarEnemigos(Enemigo[] enemigos) {
		int longitudBarra = 20;
		gestorVista.imprimirMensaje("");
		gestorVista.imprimirMensaje("=== Un grupo de enemigos asaltan tu barco! ===");
		// recorrer el array de enemigos y mostrar sus barras de vida
		for (int i = 0; i < enemigos.length; i++) {
			int saludActual = enemigos[i].getSaludActual();
			int saludTope = enemigos[i].getSaludTope();
			int bloquesLlenos = (int) ((double) saludActual / saludTope * longitudBarra);
			int bloquesVacios = longitudBarra - bloquesLlenos;

			gestorVista.imprimirMensaje(enemigos[i].getNombre());
			for (int j = 0; j < bloquesLlenos; j++) {
				gestorVista.imprimirMensajePegado("█");
			}
			for (int j = 0; j < bloquesVacios; j++) {
				gestorVista.imprimirMensajePegado("░");
			}
			gestorVista.imprimirMensajePegado(" " + saludActual + "/" + saludTope);
			gestorVista.imprimirMensaje("");
		}
	}

	public int menuCombate(Tripulante t) {
		int opcion;
		gestorVista.imprimirMensaje("");
		gestorVista.imprimirMensaje("=== Turno de " + t.getNombre() + " ===");
		gestorVista.imprimirMensaje("1. Atacar\n" + "2. Defender\n" + "3. Usar objeto\n" + "4. Ver estado aliados\n"
				+ "5. Ver estado enemigos");

		opcion = gestorVista.pedirNum();
		while (opcion < 1 || opcion > 5) {
			gestorVista.imprimirError("Selecciona una opción válda: ");
			opcion = gestorVista.pedirNum();
		}
		return opcion;
	}

	public void estadoAliados(Tripulante[] aliados) {
		int longitudBarra = 20;
		gestorVista.imprimirMensaje("");
		for (int i = 0; i < aliados.length; i++) {
			int saludActual = aliados[i].getSaludActual();
			int saludTope = aliados[i].getSaludTope();
			int bloquesLlenos = (int) ((double) saludActual / saludTope * longitudBarra);
			int bloquesVacios = longitudBarra - bloquesLlenos;

			gestorVista.imprimirMensaje(aliados[i].getNombre());
			for (int j = 0; j < bloquesLlenos; j++) {
				gestorVista.imprimirMensajePegado("█");
			}
			for (int j = 0; j < bloquesVacios; j++) {
				gestorVista.imprimirMensajePegado("░");
			}
			gestorVista.imprimirMensajePegado(" " + saludActual + "/" + saludTope);
			gestorVista.imprimirMensaje("");
		}
	}

	public void estadoEnemigos(Enemigo[] enemigos) {
		int longitudBarra = 20;
		gestorVista.imprimirMensaje("");
		for (int i = 0; i < enemigos.length; i++) {
			int saludActual = enemigos[i].getSaludActual();
			int saludTope = enemigos[i].getSaludTope();
			int bloquesLlenos = (int) ((double) saludActual / saludTope * longitudBarra);
			int bloquesVacios = longitudBarra - bloquesLlenos;

			gestorVista.imprimirMensaje(enemigos[i].getNombre());
			for (int j = 0; j < bloquesLlenos; j++) {
				gestorVista.imprimirMensajePegado("█");
			}
			for (int j = 0; j < bloquesVacios; j++) {
				gestorVista.imprimirMensajePegado("░");
			}
			gestorVista.imprimirMensajePegado(" " + saludActual + "/" + saludTope);
			gestorVista.imprimirMensaje("");
		}
	}

	public void imprimirMensaje(String msg) {
		gestorVista.imprimirMensaje(msg);
	}

	public void mensajeEsquiva(ICombatiente atacante, ICombatiente objetivo) {
		String[] mensajeEsquiva = { " esquiva el ataque con gracia.", " ha esquivado el ataque!",
				" ha evadido el ataque por los pelos.", " esquiva el ataque como si nada." };
		gestorVista.imprimirMensaje(atacante.getNombre() + " ataca a " + objetivo.getNombre() + " pero "
				+ objetivo.getNombre() + mensajeEsquiva[ALEATORIO.nextInt(3)]);
	}

	public void mensajeDefensa(Tripulante t) {
		gestorVista.imprimirMensaje(t.getNombre() + " se prepara para bloquear el siguiente ataque.");
	}

	public void mensajeAtaque(ICombatiente atacante, ICombatiente objetivo, int danio) {
		gestorVista.imprimirMensaje(atacante.getNombre() + " ataca a " + objetivo.getNombre() + " que recibe " + danio
				+ " puntos de daño!");
	}

	public void mensajeDerrota() {
		gestorVista.imprimirMensaje("Tu tripulación ha sido derrotada, volviendo a la última isla que visitaste...");
	}

	public void mensajeVictoria(int oro) {
		gestorVista.imprimirMensaje(
				"Tu tripulación ha resultado victoriosa! Rebuscáis enntre los enemigos derrotados y encontráis " + oro
						+ " monedas de oro!");
	}

	public void mensajeMuerte(ICombatiente c) {
		gestorVista.imprimirMensaje(c.getNombre() + " ha sido derrotado!");
	}

	public void mensajeCanones() {
		gestorVista.imprimirMensaje("Tu barco dispara sus cañones!");
	}

	public void mensajeRecibirCanon(ICombatiente c, int danio) {
		gestorVista.imprimirMensaje(c.getNombre() + " recibe " + danio + " de la descarga de los cañones!");
	}

	public void mensajeCombateRestaurante() {
		gestorVista.imprimirMensaje(
				"Oh oh capitán, esos clientes no parecen muy contentos. Parece que nos va a tocar pelear!");
		gestorVista.imprimirMensaje("=== Se te abalanzan unos clientes enfadados! ===");
	}

	public Consumible elegirConsumible(ArrayList<Consumible> consumiblesJ) {
		int opcion;
		Consumible itemAConsumir;
		for (int i = 0; i < consumiblesJ.size(); i++) {
			gestorVista.imprimirMensaje(
					(i + 1) + ". " + consumiblesJ.get(i).getNombre() + ": " + consumiblesJ.get(i).getCantidad());
		}

		opcion = gestorVista.pedirNum();
		while (opcion < 0 || opcion > consumiblesJ.size()) {
			gestorVista.imprimirError("Selecciona una opción válda: ");
			opcion = gestorVista.pedirNum();
		}

		itemAConsumir = consumiblesJ.get(opcion - 1);
		return itemAConsumir;
	}

	public Tripulante seleccionAliado(Tripulante[] aliados) {
		int opcion;
		int longitudBarra = 20;
		Tripulante tripulanteAfect;
		gestorVista.imprimirMensaje("");
		for (int i = 0; i < aliados.length; i++) {
			int saludActual = aliados[i].getSaludActual();
			int saludTope = aliados[i].getSaludTope();
			int bloquesLlenos = (int) ((double) saludActual / saludTope * longitudBarra);
			int bloquesVacios = longitudBarra - bloquesLlenos;

			gestorVista.imprimirMensaje((i + 1) + ". " + aliados[i].getNombre());
			for (int j = 0; j < bloquesLlenos; j++) {
				gestorVista.imprimirMensajePegado("█");
			}
			for (int j = 0; j < bloquesVacios; j++) {
				gestorVista.imprimirMensajePegado("░");
			}
			gestorVista.imprimirMensajePegado(" " + saludActual + "/" + saludTope);
			gestorVista.imprimirMensaje("");
		}

		gestorVista.imprimirMensaje("Sobre qué tripulante quieres usar el brebaje?");
		opcion = gestorVista.pedirNum();
		while (opcion < 1 || opcion > aliados.length) {
			gestorVista.imprimirError("Selecciona una opción válda: ");
			opcion = gestorVista.pedirNum();
		}

		tripulanteAfect = aliados[opcion - 1];
		return tripulanteAfect;
	}

	public void mensajeCurar(Tripulante t, int curacion) {
		gestorVista.imprimirMensaje(
				t.getNombre() + " se ve afectado por el Brebaje de Salud y recupera " + curacion + " puntos de vida!");
	}

	public void mensajeConsumible(Tripulante t, Consumible c) {
		if (c.getEfecto() == "defensa") {
			gestorVista.imprimirMensaje(
					t.getNombre() + " se ve afectado por el " + c.getNombre() + " y aumenta su defensa!");
		} else if (c.getEfecto() == "iniciativa") {
			gestorVista.imprimirMensaje(
					t.getNombre() + " se ve afectado por el " + c.getNombre() + " y aumenta su iniciativa!");
		}
	}
	
	public void mensajeSinConsumibles () {
		gestorVista.imprimirMensaje("No tienes ningún objeto consumible!");
	}
}
