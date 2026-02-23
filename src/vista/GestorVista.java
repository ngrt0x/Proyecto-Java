package vista;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GestorVista {
	// atributos
	private final Scanner TECLADO = new Scanner(System.in);

	// metodos
	public int pedirNum() {
		int num = -1;
		boolean hayError = true;
		while (hayError) {
			try {
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

	public String pedirString() {
		String texto;
		texto = TECLADO.nextLine();
		return texto;
	}

	public void imprimirMensaje(String msg) {
		System.out.println(msg);
	}

	public void imprimirMensajePegado(String msg) {
		System.out.print(msg);
	}

	public void imprimirError(String msg) {
		System.err.println(msg);
	}

	public void limpiarBuffer() {
		TECLADO.nextLine();
	}

}
