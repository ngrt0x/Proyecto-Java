package controlador;

import modeloJugador.Jugador;
import modeloPersonajes.NPC;
import vista.VistaNPC;

public class GestorNPC {
	private final VistaNPC vistaNpc = new VistaNPC();

	// NPC'S POR ISLA

	// ========== ISLA 1 ==========

	// Richard Enepece
	String[] frasesRichard = { "'Hola muy buenos dias.'", "'Te veo bien el dia de hoy.'", "'Ten un buen día.'" };
	String opcionesDialogoRichard = "1. Qué me puedes decir de esta isla?\n2. Mola tu camiseta\n3. Qué haces aquí?\n0. Dejar de hablar";
	String[] respuestasRichard = { "'Es una isla muy bonita y muy tranquila. Un sitio ideal para vivir.'",
			"'Gracias! Tengo otras 6 como esta en casa, una para cada día de la semana. Sólo tengo un outfit.'",
			"'No lo sé, vengo a esta plaza todas las tardes y espero a que la gente me hable. Realmente no hago nada más.'" };
	String primeraRichard = "'Es un placer conocerte, soy Richard.'";
	NPC richard = new NPC("Richard Enepece", frasesRichard, opcionesDialogoRichard, respuestasRichard, primeraRichard,
			false);

	// Antonio el Amargao TIENE PISTA
	String opcionesDialogoAntonio = "1. Sabes dónde conseguir provisiones?\n2. Has probado alguna vez la carne de gamusino?\n"
			+ "3. Vaya apodo te has ganado en esta isla eh?\n4. Mirar fijamente\n5. Has escuchado algo sobre algún tesoro?\n0. Dejar de hablar";
	String[] respuestasAntonio = {
			"'Aquí a la vuelta de la esquina hay una tienda de cacharros varios. No suelo pasar por ahí porque el tendero es un desagradable.'",
			"'Carne de gamusino? Qué te crees que esto es jauja? Uno de esos le arrancó una oreja a mi primo de un bocado.'",
			"'La gente me llama amargado por algún motivo. Pero yo soy un cacho de pan, mira que sonrisa.' Dice con muy poco entusiasmo\n"
					+ "mientras fuerza una sonrisa bastante desagradable.",
			"'Qué me miras tú? Tengo sardinas en la cara?'" };
	String[] frasesAntonio = { "'Qué susto me has dado con esas pintas que llevas.'",
			"'Qué se te ha perdido en esta isla de mala muerte?'" };
	String primeraAntonio = "'Me llamo Antonio, soy un tipo de pocas palabras. Si vas a hablar conmigo que sea lo justo y necesario.'";
	NPC antonio = new NPC("Antonio 'El Amargao'", frasesAntonio, opcionesDialogoAntonio, respuestasAntonio,
			primeraAntonio, true);

	// Manuela
	String opcionesDialogoManuela = "1. Toma un cigarrillo\n2. Has escuchado algo de algún tesoro?\n3. Eres de por aquí?\n0. Dejar de hablar";
	String[] respuestasManuela = { "'Como te pasas, mil gracias jefe.'",
			"Que va, aquí pocas veces se ve siquiera gente nueva. Y si alguien sabía de algún tesoro seguro que ya se habrá ido a buscarlo.",
			"Que va, soy de una isla a un par de días de aquí en barco. Estoy haciendo las prácticas de astillería,\n"
					+ "pero como no tenía muy buenas notas en el grado me tocó hacerlas en esta aburrida isla." };
	String[] frasesManuela = { "'Hoy hace un dia estupendo para dar un paseo.'",
			"'Si yo pudiera ser un animal seria un pajarito.'", "'No tendrás algo de tabaco por ahí no, piratilla?'",
			"'Estoy que me fumo encima.'" };
	String primeraManuela = "'Yo a ti te he visto antes en ese barco restaurante. La comida estaba deliciosa pero vaya jaleo teníais montado. Soy Manuela, un placer.'";
	NPC manuela = new NPC("Manuela Jartangas", frasesManuela, opcionesDialogoManuela, respuestasManuela, primeraManuela,
			false);

	// Josefina
	String opcionesDialogoJosefina = "1. Qué flores tienes en tu jardín?\n2. Conoces al amargado ese?\n3. Abrazar a la abuelita\n0. Dejar de hablar";
	String[] respuestasJosefina = {
			"'Tengo de todo corazón. También tengo un jardín secreto en sótano de mi casa, pero al ayuntamiento no le gusta mucho ese.' La señora te guiña el ojo.",
			"'A Antonio? Claro, tuvimos una apasionada aventura amorosa cuando éramos más jóvenes. Ahora el horno ya no está para bollos, y el tío se ha vuelto un soso.\n"
					+ "Además se ha quedado calvo como una sepia, estaba mucho más guapo cuando tenía el pelo largo.'",
			"La señora te da un largo abrazo. Cuando ya ha pasado suficiente rato para que sea incómodo te percatas de que se ha quedado sopa." };
	String[] frasesJosefina = { "'Algun dia te podrias pasar a ver las flores de mi jardin, las cuido con mucho amor.'",
			"'Si necestias consejos de botánica avísame corazón.'", "'A los piratas les gustan las flores?'" };
	String primeraJosefina = "'Buenas tardes! Siempre es agradable ver caras nuevas por este lugar. Me llamo Josefina corazón.' La anciana te dedica una amplia sonrisa.";
	NPC josefina = new NPC("Josefina", frasesJosefina, opcionesDialogoJosefina, respuestasJosefina, primeraJosefina,
			false);

	NPC[] habitantesIsla1 = { richard, antonio, manuela, josefina }; // HABITANTES DE LA ISLA 1

	// ============================
	// ========== ISLA 2 ==========

	// Gato
	String[] frasesPiti = { "'Mrrrrow'", "'Miau miau miau'", "'Mew'", "Se está lamiendo la entrepierna..." };
	String primeraPiti = "Mrrrow miau miau miau";
	String opcionesDialogoPiti = "1. Acariciar al gato\n2. Tirarle una raspa de pescado\n3. Miau miau miau\n0. Dejar de hablar";
	String[] respuestasPiti = { "Se da la vuelta y te enseña la panza para que le acaricies. Que monada.",
			"'Mrrrrow' Se pone a relamer los huesos del pescado.",
			"'Hay que acabar con el estado ilegítimo y genocida de Israel.'" };
	NPC piti = new NPC("Piti", frasesPiti, opcionesDialogoPiti, respuestasPiti, primeraPiti, false);

	// Encapuchado
	String[] frasesEncapuchado = { "'Qué me andas mirando? Buscas un puñal en el pecho?'", "'Aire.'",
			"'No me suena tu cara y no me gusta eso.'" };
	String primeraEncapuchado = "'No necesitas saber mi nombre. No sé qué haces aquí. Lárgate.'";
	String opcionesDialogoEncapuchado = "1. Qué escondes debajo de esa capucha? Acaso eres extremadamente feo?\n"
			+ "2. Hueles a pescado muerto, date una ducha.\n3. Qué sabes de esta isla?\n0. Dejar de hablar";
	String[] respuestasEncapuchado = { "'Soy más guapo que tú forastero, eso tenlo por seguro.'",
			"'Es el aroma de un verdadero hombre de mar, a las chatis les encanta.'", "'Nada que a ti te importe.'" };
	NPC encapuchado = new NPC("Hombre encapuchado", frasesEncapuchado, opcionesDialogoEncapuchado,
			respuestasEncapuchado, primeraEncapuchado, false);

	// Mujer desmayada TIENE PISTA
	String[] frasesSabrina = { "'...' La mujer sigue tendida en el suelo." };
	String primeraSabrina = "'...' La mujer está tendida en el suelo, inmóvil.";
	String opcionesDialogoSabrina = "1. Acercarte a la mujer\n0. Ignorarla";
	String[] respuestasSabrina = new String[0];
	NPC sabrina = new NPC("Mujer desmayada", frasesSabrina, opcionesDialogoSabrina, respuestasSabrina, primeraSabrina,
			true);

	NPC[] habitantesIsla2 = { piti, encapuchado, sabrina };

	// ============================

	// getters
	public NPC[] getHabitantesIsla1() {
		return habitantesIsla1;
	}

	public NPC[] getHabitantesIsla2() {
		return habitantesIsla2;
	}

	// metodos propios
	public void hablarNpc(Jugador j) {
		int opcionH;
		opcionH = vistaNpc.menuHabitantes1(j.getIslaActual());
		while (opcionH != 0) {
			NPC objetivo = j.getIslaActual().getHabitantes()[opcionH - 1];
			if (!objetivo.isConocido()) {
				vistaNpc.primeraFrase(objetivo);
				interactuarNpc(objetivo, j);
				objetivo.setConocido(true);
			} else {
				vistaNpc.fraseRandom(objetivo);
				interactuarNpc(objetivo, j);
			}
			opcionH = vistaNpc.menuHabitantes2(j.getIslaActual());
		}
	}

	public boolean interactuarNpc(NPC objetivo, Jugador j) {
		int opcionDialogo;
		boolean dejarDeHablar = false;
		while (!dejarDeHablar) {
			// comprueba si el npc tiene pista o no
			if (objetivo.tienePista()) {
				opcionDialogo = vistaNpc.hablarNPCConPista(objetivo);
				// si la opcion dialogo es una de mas que las respuestas, esa es la pista, y si
				// la selecciona realiza la logica correspondiente
				if (opcionDialogo == 0) {
					dejarDeHablar = true;
				} else if (opcionDialogo == objetivo.getRespuestas().length + 1) {
					pistaNpc(objetivo, j);
				} else {
					vistaNpc.respuestaNPC(objetivo, opcionDialogo);
				}
			} else {
				opcionDialogo = vistaNpc.hablarNPC(objetivo);
				if (opcionDialogo == 0) {
					dejarDeHablar = true;
				} else {
					vistaNpc.respuestaNPC(objetivo, opcionDialogo);
				}
			}
		}
		return false;
	}

	// hacer una interaccion un poco mas currada para algunas pistas
	private void pistaNpc(NPC objetivo, Jugador j) {
		int opcion;
		switch (objetivo.getNombre()) {
		case ("Antonio 'El Amargao'"):
			vistaNpc.imprimirMensaje(objetivo.getNombre()
					+ ": 'Puede que si, puede que no. Unas moneditas me pueden refrescar la memoria. No creo que la gente de ésta isla sepa nada sobre tesoros...\n"
					+ "Excepto yo. O igual no. Eso depende de tu bolsillo.'");
			opcion = vistaNpc.pedirMoneditasAntonio();
			switch (opcion) {
			case 1:
				// comprueba que tengas suficiente oro en el inventario
				if (j.getOro() < 10) {
					vistaNpc.imprimirMensaje("No tienes suficientes monedas para darle!");
				} else {
					j.restarOro(10);
					vistaNpc.imprimirMensaje(
							"Le das las monedas a Antonio. El tipo sonríe, ésta vez enseñando toda, o lo que queda, de su dentadura.");
					vistaNpc.imprimirMensaje(objetivo.getNombre()
							+ ": 'Pues ahora que lo mencionas, mi primo al que le arrancó la oreja un gamusino se fue hace ya unos años detrás del\n"
							+ "supuesto 'Fuego del Paraiso' o algo así. Yo creo que era una historieta que algún pirata pasajero le contaría, pero yo qué sé, cada uno que haga lo que quiera\n"
							+ "con su vida. Lo último que me dijo es que iba a zarpar en viaje hacia las islas del norte. Igual se lo ha comido una ballena de camino o algo.'");

					// tras la interaccion se guardan las pistas en el diario
					j.getDiario().agregarPista(
							"En Isla Langosa he escuchado algo sobre un tesoro llamado 'Fuego del Paraiso'.");
					j.getDiario().agregarPista("Parece que las islas del norte pueden esconder algo interesante.");
				}
				break;
			}
			break;
		case ("Mujer desmayada"):
			vistaNpc.imprimirMensaje(
					"Al acercarte a la mujer puedes ver claramente que está inconsciente. Tiene una herida notable en la cabeza.");
			opcion = vistaNpc.actuarMujerDesmayada();
			switch (opcion) {
			case 1:
				if (j.getInventario().getItem().containsKey("pot_salud")) {
					vistaNpc.imprimirMensaje(
							"Acercas el frasco a la boca a la muchacha, y viertes el contenido con cuidado no vaya a ser que se atragante.");
					j.getInventario().restarItem("pot_salud", 1);
					objetivo.setNombre("Sabrina la Bucanera");
					vistaNpc.imprimirMensaje(objetivo.getNombre()
							+ ": '*cof**cof*... Sigo viva?? Qué ha pasado? Has sido tú quien me ha ayudado o estás con esos piratuchos del tres al cuarto?!\n"
							+ "Cómo pueden tener tan poca clase para atracar a una chica entre 3? En fin, vergüenza me daría ser ellos.'\n"
							+ "'Imagino que has sido tú quien me ha ayudado verdad? No sé cómo agradecértelo, pero supongo que una presentación sería lo primero.\n"
							+ "Me llamo Sabrina, y soy una loba de mar como no hay otra en el Gran Archipiélago. Esta es una isla de ladrones y foragidos, pero me he visto\n"
							+ "obligada a parar aquí para hacer unas reparaciones en el astillero. Imagino que esos piratas que me han atacado antes llevarían siguiéndome\n"
							+ "la pista un buen trecho y han aprovechado el momento que me he quedado sola. Ahora que lo pienso...'\n"
							+ "La chica rebusca en todos sus bolsillos que no son pocos.\n"
							+ "'...esos desgraciados se han llevado mi llave! Mi padre era un famoso pirata. Me abandonó cuando yo era aún muy pequeña, y lo único\n"
							+ "que tengo que demuestre su existencia es esa llave que le dejó a mi madre. Se supone que era la llave de uno de sus cofres personales.\n"
							+ "Según él, el contenido de ese cofre haría palidecer hasta a los más grandes hombres.'");
					vistaNpc.imprimirMensaje("");
					vistaNpc.imprimirMensaje(objetivo.getNombre() + ": 'Bueno " + j.getNombre()
							+ " muchísimas gracias por salvarme la vida, te deseo suerte en todo lo que te propongas.\n"
							+ "Yo me pondré en marcha trás esos ladronzuelos en cuanto mi barco esté listo. Con mis habilidades de navegante les tendré a tiro en\n"
							+ "menos de 2 días. Un placer conocerte!'");

					j.getDiario()
							.agregarPista("Hay unos ladrones sueltos en posesión de una llave que parece importante.");
					j.getDiario().agregarPista(
							"Esa tal Sabrina que me he encontrado en Refugio Sombrío parece saber muchas cosas sobre los tesoros del Gran Archipiélago.");

					frasesSabrina = new String[1];
					frasesSabrina[0] = "'Muchas gracias por lo de antes capitán! Tienes mis bendiciones en todos tus viajes!'";
					opcionesDialogoSabrina = "1. Saludar con la mano\n0. Dejar de hablar";
					respuestasSabrina = new String[1];
					respuestasSabrina[0] = "La chica te saluda de vuelta alegremente.";
					objetivo.setDialogos(frasesSabrina);
					objetivo.setDialogos(frasesSabrina);
					objetivo.setOpcionesDialogo(opcionesDialogoSabrina);
					objetivo.setRespuestas(respuestasSabrina);
				}
				break;
			}
			break;
		}
	}
}
