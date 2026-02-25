package controlador;

import java.util.Random;

import modeloJugador.Jugador;
import modeloPersonajes.NPC;
import vista.GestorVista;
import vista.VistaJuego;

public class GestorNPC {
	private static final Random RANDOM = new Random();
	private static final GestorVista gestorVista = new GestorVista();
	private static final VistaJuego vistaJuego = new VistaJuego();

	// NPC'S POR ISLA

	// ========== ISLA 1 ==========

	// Richard
	String[] dialogosRichard = { "Hola muy buenos dias", "Te veo bien el dia de hoy",
			"No se lo digas a los demas pero soy un therian" };
	String primeraRichard = "Es un placer conocerte, soy Richard";
	NPC richard = new NPC("Richard", dialogosRichard, primeraRichard);

	// Antonio // CONTIENE PISTA
	String opcionesDialogoAntonio = "1. Hablemos un poco\n2. He hablado con tu mujer\n3. Que opinas de los demas habitantes\n4. Mirar fijamente\n5. Preguntar acerca de los recientes robos de piratas";
	String[] dialogosAntonio = { "No estoy de humor hoy", "Mi mujer dice que soy muy prepotente, puede que tenga razon",
			"Creo que a los demas les caigo mal", "Que me miras, tengo monos en la cara?" };

	String primeraAntonio = "Me llamo Antonio, soy un tipo de pocas palabras, si vas a hablar conmigo que sea lo minimo y necesario";

	String pistaAntonio = "Esto es una pista";

	NPC antonio = new NPC("Antonio", dialogosAntonio, opcionesDialogoAntonio, primeraAntonio, true, pistaAntonio);

	// Manuela
	String[] dialogosManuela = { "Hoy hace un dia estupendo para dar un paseo",
			"Si yo pudiera ser un animal seria un pajarito", "Hoy la comida me ha salido realmente bien" };
	String primeraManuela = "Soy la esposa de Antonio, me llamo Manuela, porfavor perdona a mi marido si es un poco grosero, en el fondo es una buena persona";
	NPC manuela = new NPC("Manuela", dialogosManuela, primeraManuela);

	// Josefina
	String[] dialogosJosefina = { "Algun dia te podrias pasar a ver las flores de mi jardin, las cuido con mucho amor",
			"Si necestias consejos de botánica yo soy tu persona",
			"Dicen que hay muchos piratas en estos mares, ten siempre cuidado cuando vayas a navegar" };
	String primeraJosefina = "Hey es agradable ver caras nuevas por este lugar, mi nombre es Josefina es un placer conocerte, si necesitas algo siempre puedes contar conmigo";
	NPC josefina = new NPC("Josefina", dialogosJosefina, primeraJosefina);

	NPC[] habitantesIsla1 = { richard, antonio, manuela, josefina }; // HABITANTES DE LA ISLA 1

	// ============================
	// ========== ISLA 2 ==========

	// Recesvinto
	String[] dialogosRecesvinto = { "La gente se burla de mi por tener un nombre muy raro",
			"Me gusta cuando me acarician detras de las orejas", "Se me da muy bien la carpinteria" };
	String primeraRecesvinto = "No les hagas caso a los demas, no soy tan rara como aparento";
	NPC recesvinto = new NPC("Recesvinto", dialogosRecesvinto, primeraRecesvinto);

	// Eduardo
	String[] dialogosEduardo = { "Me gusta mucho el futbol", "Mi abuela tiene un gato muy bonito",
			"Ven conmigo a jugar algun dia" };
	String primeraEduardo = "Hola soy Eduardo tengo 9 años";
	NPC eduardo = new NPC("Eduardo", dialogosEduardo, primeraEduardo);

	// Moises // TIENE PISTA
	String opcionesDialogoMoises = "1. Saludar\n2. Preguntar sobre el tiempo\n3. Hablar sobre Eduardo\n4. Oye he oido que tu separaste los mares ";
	String[] dialogoMoises = { "Hey te veo bien", "Aqui siempre suele haber buen tiempo ni mucho frio ni mucho calor sabes", "Eduardo es un buen niño, seguro que se hace famoso y da a conocer nuestra isla"};

	String primeraMoises = "Me llaman Moises, es un placer verte visitando esta humilde isla";

	String pistaMoises = "Habla conmigo en otro momendo (En desarrollo, opcion no disponible)";

	NPC moises = new NPC("Moises", dialogoMoises, opcionesDialogoMoises, primeraMoises, true, pistaMoises);

	// Patricia
	String[] dialogosPatricia = { "Tengo un nieto muy guapo llamado Eduardo",
			"Seguro que a mi gata le encantarias", "Algun dia te traere galletas para que las pruebes" };
	String primeraPatricia = "Hola corazon, es tu primera vez por aqui?";
	NPC patricia = new NPC("Patricia", dialogosPatricia, primeraPatricia);
	
	NPC[] habitantesIsla2 = {recesvinto,eduardo,moises,patricia};
	
	// ============================

	// getters

	public NPC[] getHabitantesIsla1() {
		return habitantesIsla1;
	}
	
	public NPC[] getHabitantesIsla2() {
		return habitantesIsla2;
	}

	// metodos propios

	public void hablarNPC(Jugador j) {
		NPC objetivo = vistaJuego.menuHabitantes(j.getIslaActual());

		boolean salir = false;
		while (!salir) {
			try {
				if (!objetivo.isConocido()) {
					gestorVista.imprimirMensaje(objetivo.getPrimeraFrase()); // si es la primera vez que hablas con el
																				// NPC
																				// te
																				// dira su primera frase
					objetivo.setConocido(true);
				} else {
					if (objetivo.tienePista()) { // NPC especial con dialogos "interactivos" con varias opciones
						gestorVista.imprimirMensaje(objetivo.getOpciones());
						int opcionDialogo = gestorVista.pedirNum();
						while (opcionDialogo < 1 || opcionDialogo > objetivo.getDialogos().length + 1) {
							gestorVista.imprimirError("Elige una opcion valida");
						}

						if (opcionDialogo == objetivo.getDialogos().length + 1) { // si la opcion del dialogo esta 1 por
																					// fuera
																					// del
																					// array es el dialogo clave
							gestorVista.imprimirMensaje(objetivo.getPista()); // te pone la pista y la guardamos en el
																				// diario
							j.getDiario().agregarPista(objetivo.getPista());
						} else {
							gestorVista.imprimirMensaje(objetivo.getDialogos()[opcionDialogo - 1]); // si es una opcion
																									// normal dara
							// el
							// dialogo correspondiente
						}
					} else {// NPC normales de las islas con dialogos random
						int random = RANDOM.nextInt(objetivo.getDialogos().length); // si no es la primera vez te dira
																					// una
																					// de sus diversas frases
						gestorVista.imprimirMensaje(objetivo.getDialogos()[random]);
					}

				}
				objetivo = vistaJuego.menuHabitantes(j.getIslaActual());
			} catch (NullPointerException e) { // si el objetivo es null significa que el jugador ha elegido la opcion
												// "Atrás" por lo que simplemente capturamos la exception y continuamos
				// TODO: handle exception
				salir = true;
			}
		}
	}
}
