package vista;

import java.util.ArrayList;
import java.util.Random;

import modeloObjetos.Consumible;
import modeloPersonajes.Enemigo;
import modeloPersonajes.ICombatiente;
import modeloPersonajes.Tripulante;

/**
 * Clase Vista combate, contiene todos los mensajes para los combates
 * 
 * @author Jesús Manrique y Marcos Villagómez
 * @version 1.0
 */
public class VistaCombate {
	// atributos
	/**
	 * Instacia de aleatorio
	 */
	private final Random ALEATORIO = new Random();
	/**
	 * Instancia de Gestor vista
	 */
	private GestorVista gestorVista = new GestorVista();

	// metodos propios
	/**
	 * Metodo para que el jugador elija enemigo
	 * @param enemigos Array de enemigos
	 * @param t Tripulante que ataca
	 * @return El index del enemigo que quieres atacar dentro del array
	 */
	public int elegirEnemigo(Enemigo[] enemigos, Tripulante t) {
		int opcion;
		int longitudBarra = 20;

		gestorVista.imprimirMensaje("");
		for (int i = 0; i < enemigos.length; i++) {
			int saludActual = enemigos[i].getSaludActual();
			int saludTope = enemigos[i].getSaludTope();
			int bloquesLlenos = (int) ((double) saludActual / saludTope * longitudBarra);
			int bloquesVacios = longitudBarra - bloquesLlenos;

			gestorVista.imprimirMensaje((i + 1) + ". " + enemigos[i].getNombre());
			for (int j = 0; j < bloquesLlenos; j++) {
				gestorVista.imprimirMensajePegado("█");
			}
			for (int j = 0; j < bloquesVacios; j++) {
				gestorVista.imprimirMensajePegado("░");
			}
			gestorVista.imprimirMensajePegado(" " + saludActual + "/" + saludTope);
			gestorVista.imprimirMensaje("");
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

	/**
	 * Metodo para mostrar enemigos por pantalla
	 * @param enemigos Enemigos que mostrar
	 */
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

	/**
	 * Metodo para mostrar el menu de opciones dentro de combate
	 * @param t Tripulante que esta de turno
	 * @return Opcion elegida por el tripulante
	 */
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

	/**
	 * Metodo para imprimir por pantalla el estado de los aliados
	 * @param aliados Aliados
	 */
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

	/**
	 * Metodo para mostrar el estado de los enemigos
	 * @param enemigos Enemigos
	 */
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

	/**
	 * Metodo para imprimir mensajes por pantalla
	 * @param msg Mensaje a imprimir
	 */
	public void imprimirMensaje(String msg) {
		gestorVista.imprimirMensaje(msg);
	}

	/**
	 * Metodo para imprimir el mensaje de esquive logrado
	 * @param atacante Atacante
	 * @param objetivo Objetivo
	 */
	public void mensajeEsquiva(ICombatiente atacante, ICombatiente objetivo) {
		String[] mensajeEsquiva = { " esquiva el ataque con gracia.", " ha esquivado el ataque!",
				" ha evitado el ataque por los pelos.", " esquiva el ataque como si nada." };
		gestorVista.imprimirMensaje(atacante.getNombre() + " ataca a " + objetivo.getNombre() + " pero "
				+ objetivo.getNombre() + mensajeEsquiva[ALEATORIO.nextInt(3)]);
	}

	/**
	 * Metodo para imprimir el mensaje de cambio a defender
	 * @param t Tripulante que cambia
	 */
	public void mensajeDefensa(Tripulante t) {
		gestorVista.imprimirMensaje(t.getNombre() + " se prepara para bloquear el siguiente ataque.");
	}

	/**
	 * Metodo para imprimir el mensaje de ataque
	 * @param atacante Atacante
	 * @param objetivo Objetivo
	 * @param danio Daño
	 */
	public void mensajeAtaque(ICombatiente atacante, ICombatiente objetivo, int danio) {
		gestorVista.imprimirMensaje(atacante.getNombre() + " ataca a " + objetivo.getNombre() + " que recibe " + danio
				+ " puntos de daño!");
	}

	/**
	 * Metodo para imprimir el mensaje de derrota
	 */
	public void mensajeDerrota() {
		gestorVista.imprimirMensaje("Tu tripulación ha sido derrotada, volviendo a la última isla que visitaste...");
	}

	/**
	 * Metodo para imprimir el mensaje de victoria
	 * @param oro Oro ganado
	 */
	public void mensajeVictoria(int oro) {
		gestorVista.imprimirMensaje(
				"Tu tripulación ha resultado victoriosa! Rebuscáis enntre los enemigos derrotados y encontráis " + oro
						+ " monedas de oro!");
	}

	/**
	 * Metodo para impirmir la derrota de un combatiente
	 * @param c Combatiente (Aliado/Enemigo)
	 */
	public void mensajeMuerte(ICombatiente c) {
		gestorVista.imprimirMensaje(c.getNombre() + " ha sido derrotado!");
	}

	/**
	 * Metodo para imprimir el mensaje de ataque de tu barco
	 */
	public void mensajeCanones() {
		gestorVista.imprimirMensaje("Tu barco dispara sus cañones!");
	}

	/**
	 * Metodo para imprimir el mensaje de ataque del barco a un enemigo
	 * @param c Combatiente
	 * @param danio Daño recibido
	 */
	public void mensajeRecibirCanon(ICombatiente c, int danio) {
		gestorVista.imprimirMensaje(c.getNombre() + " recibe " + danio + " de la descarga de los cañones!");
	}

	/**
	 * Metodo para imprimir el mensaje de ataque de clientes
	 */
	public void mensajeCombateRestaurante() {
		gestorVista.imprimirMensaje(
				"Oh oh capitán, esos clientes no parecen muy contentos. Parece que nos va a tocar pelear!");
		gestorVista.imprimirMensaje("=== Se te abalanzan unos clientes enfadados! ===");
	}

	/**
	 * Metodo para imrpimir el menu de seleccion de consumible
	 * @param consumiblesJ Lista de consumibles
	 * @return El consumible elegido
	 */
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

	/**
	 * Metodo para imprimir el menu de seleccion de aliado
	 * @param aliados Aliados
	 * @return Alidado seleccionado
	 */
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

	/**
	 * Metodo para imprimir el mensaje de curacion
	 * @param t Tripulante
	 * @param curacion Cantidad de curación
	 */
	public void mensajeCurar(Tripulante t, int curacion) {
		gestorVista.imprimirMensaje(
				t.getNombre() + " se ve afectado por el Brebaje de Salud y recupera " + curacion + " puntos de vida!");
	}

	/**
	 * Metodo para imprimir el uso de un consumible
	 * @param t Tripulante
	 * @param c Consumible
	 */
	public void mensajeConsumible(Tripulante t, Consumible c) {
		if (c.getEfecto() == "defensa") {
			gestorVista.imprimirMensaje(
					t.getNombre() + " se ve afectado por el " + c.getNombre() + " y aumenta su defensa!");
		} else if (c.getEfecto() == "iniciativa") {
			gestorVista.imprimirMensaje(
					t.getNombre() + " se ve afectado por el " + c.getNombre() + " y aumenta su iniciativa!");
		}
	}

	/**
	 * Metodo para imprimir el mensaje de no tener consumibles en el inventario
	 */
	public void mensajeSinConsumibles() {
		gestorVista.imprimirMensaje("No tienes ningún objeto consumible!");
	}
}
