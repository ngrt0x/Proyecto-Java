package controlador;

import modeloPersonajes.NPC;

public class GestorNPC {
	// NPC'S POR ISLA
	
	// ISLA 1
	NPC[] habitantes1 = new NPC[4];
	
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
			""
	};
	String primeraJosefina = "Hey es agradable ver caras nuevas por este lugar, mi nombre es Josefina es un placer conocerte, si necesitas algo siempre puedes contar conmigo";
}
