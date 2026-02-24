package controlador;

import java.util.Random;

import modeloPersonajes.NPC;
import vista.GestorVista;

public class GestorNPC {
	private static final Random RANDOM = new Random();
	private GestorVista gestorVista = new GestorVista();
	
	// NPC'S POR ISLA
	
	//========== ISLA 1 ==========
	
	// Richard
	String[] dialogosRichard = {
			"Hola muy buenos dias",
			"Te veo bien el dia de hoy",
			"No se lo digas a los demas pero soy un therian"
	};
	String primeraRichard = "Es un placer conocerte, soy Richard";
	NPC richard = new NPC("Richard", dialogosRichard, primeraRichard);
	
	// Antonio
	String[] dialogosAntonio = {
			"No estoy de humor hoy",
			"Mi mujer dice que soy muy prepotente",
			"Creo que a los demas les caigo mal",
			"Que me miras, tengo monos en la cara?"
	};
	String primeraAntonio = "Me llamo Antonio, soy un tipo de pocas palabras, si vas a hablar conmigo que sea lo minimo y necesario";
	NPC antonio = new NPC("Antonio", dialogosAntonio, primeraAntonio);
	
	// Manuela
	String[] dialogosManuela = {
			"Hoy hace un dia estupendo para dar un paseo",
			"Si yo pudiera ser un animal seria un pajarito",
			"Hoy la comida me ha salido realmente bien"
	};
	String primeraManuela = "Soy la esposa de Antonio, me llamo Manuela, porfavor perdona a mi marido si es un poco grosero, en el fondo es una buena persona";
	NPC manuela = new NPC("Manuela", dialogosManuela, primeraManuela);
	
	// Josefina
	String[] dialogosJosefina = {
			"Algun dia te podrias pasar a ver las flores de mi jardin, las cuido con mucho amor",
			"Si necestias consejos de botánica yo soy tu persona",
			"Dicen que hay muchos piratas en estos mares, ten siempre cuidado cuando vayas a navegar"
	};
	String primeraJosefina = "Hey es agradable ver caras nuevas por este lugar, mi nombre es Josefina es un placer conocerte, si necesitas algo siempre puedes contar conmigo";
	NPC josefina = new NPC("Josefina", dialogosJosefina, primeraJosefina);
	
	NPC[] habitantesIsla1 = {richard, antonio, manuela, josefina}; // HABITANTES DE LA ISLA 1
	
	//============================
	
	// getters
	
	public NPC[] getHabitantesIsla1() {
		return habitantesIsla1;
	}
	
	// metodos propios
	
	public void hablarNPC(NPC objetivo) {
		if(!objetivo.isConocido()) {
			gestorVista.imprimirMensaje(objetivo.getPrimeraFrase()); // si es la primera vez que hablas con el NPC te dira su primera frase
		}else {
			int random = RANDOM.nextInt(objetivo.getDialogos().length + 1); // si no es la primera vez te dira una de sus diversas frases
			gestorVista.imprimirMensaje(objetivo.getDialogos()[random]);
		}
	}
}
