package vista;
import java.util.InputMismatchException;
import java.util.Scanner;
public class GestorVista {
	//atributos
	private static final Scanner TECLADO=new Scanner(System.in);
	
	//metodos propios
	public void imprimir (String msg) {
		System.out.println(msg);
	}
	
	public int leerInt(String prompt) {
		int opcion=-1;
		boolean hayError=true;
		System.out.println(prompt);
		while (hayError) {
			try {
				opcion=TECLADO.nextInt();
				TECLADO.nextLine();
				hayError=false;
			} catch (InputMismatchException e) {
				TECLADO.nextLine();
				System.out.println("Introduce un número válido:");
				hayError=true;
			}
		}
		return opcion;
	}
	
	public String pedirNombre() {
		String nombre;
		System.out.println("Cuál es tu nombre, capitán?");
		nombre=TECLADO.nextLine();
		return nombre;
	}
}
