package vista;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase Gestor Vista, es la clase que gestiona todas las vistas
 * 
 * @author Jesús Manrique y Marcos Villagómez
 * @version 1.0
 */
public class GestorVista {
	// atributos
	/**
	 * Scanner teclado
	 */
	private final Scanner TECLADO = new Scanner(System.in);

	// metodos
	/**
	 * Metodo para perdirle números al jugador
	 * @return El número obtenido por el jugador
	 */
	public int pedirNum() {
		int num = -1;
		boolean hayError = true;
		while (hayError) {
			try {
				imprimirMensajePegado("> ");
				num = TECLADO.nextInt();
				TECLADO.nextLine();
				hayError = false;
			} catch (InputMismatchException e) {
				System.err.println("Introduce un numero valido:");
				TECLADO.nextLine();
				hayError = true;
			}
		}
		return num;
	}

	/**
	 * Metodo para perdirle string al jugador
	 * @return El string pasado por el jugador
	 */
	public String pedirString() {
		String texto;
		imprimirMensajePegado("> ");
		texto = TECLADO.nextLine();
		return texto;
	}

	/**
	 * Metodo para imprimir mensajes por pantalla
	 * @param msg Mensaje a imprimir
	 */
	public void imprimirMensaje(String msg) {
		System.out.println(msg);
	}

	/**
	 * Metodo para imprimir una linea en blanco
	 */
	public void imprimirEspacio() {
		System.out.println();
	}

	/**
	 * Metodo para imprimir mensajes sin el salto de linea
	 * @param msg Mensaje a imprimir
	 */
	public void imprimirMensajePegado(String msg) {
		System.out.print(msg);
	}

	/**
	 * Metodo para imrpimir errores
	 * @param msg Error a imprimir
	 */
	public void imprimirError(String msg) {
		System.err.println(msg);
	}

}
