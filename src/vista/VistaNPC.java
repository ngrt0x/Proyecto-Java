package vista;

import controlador.JuegoControlador;
import modeloJugador.Jugador;
import modeloPersonajes.NPC;
import modeloPersonajes.Tripulante;

public class VistaNPC {
	// atributos
	private GestorVista gestorVista = new GestorVista();

	// metodos
	public void primeraFrase(NPC npc) {
		gestorVista.imprimirMensaje(npc.getPrimeraFrase());
	}

	public void menuDialogo(NPC npc) {

	}

	public void hablar(NPC npc) {

	}

	public void entregarPista(NPC npc, Jugador j) {

	}

	public void dialogosTragaldabas(int dia, Jugador j) {
		// ver que tripulante es el correspondiente a este rol
		Tripulante t;
		int indexTrip = -1;
		for (int i = 0; i < j.getBarco().getTripulacion().length; i++) {
			if (j.getBarco().getTripulacion()[i].getRol() == 1) {
				indexTrip = i;
			}
		}
		t = j.getBarco().getTripulacion()[indexTrip];

		// dialogos dependiendo del dia y de la fase del dia
		if (dia == 1) {
			switch (JuegoControlador.faseActual) {
			case MANANA:
				gestorVista.imprimirMensaje(t.getNombre()
						+ ": 'Buen día jefe, qué tal va la mañana? Yo he intentado aprovechar para pescar algo pero no se me da muy bien,\n"
						+ "la verdad que la paciencia no es lo mío. Por cierto, cuándo planeas atracar en la isla? Andamos algo justos de pasta y estoy\n"
						+ "deseando darme un festín en cuanto baje de este barco. Ya ni me acuerdo cuántos días llevamos deambulando de isla en isla buscando\n"
						+ "ese maldito tesoro tuyo. Espero que sea algún manjar que nadie nunca haya probado antes o algo así.'");
				break;
			case COMIDA:
				gestorVista.imprimirMensaje(t.getNombre()
						+ ": 'Madre mía, ya estoy escuchando a los clientes arañar las puertas del restaurante, son como perros rabiosos.\n"
						+ "Más nos vale dar un buen servicio o se nos van a echar encima a nosotros.'");
				break;
			case TARDENOCHE:
				gestorVista.imprimirMensaje(t.getNombre()
						+ ": 'Creo que me iré a dar un paseo por la isla antes de embarcarnos hacia nuestro siguiente\n"
						+ "destino. Debería reponer en aperitivos para el viaje, que luego andamos todos muertos de hambre a mitad de camino. A ti qué te\n"
						+ "apetecerá jefe?'");
				break;
			case NAVEGACION:
				break;
			}
		}
	}

	public void dialogosCocinero(int dia, Jugador j) {
		// ver que tripulante es el correspondiente a este rol
		Tripulante t;
		int indexTrip = -1;
		for (int i = 0; i < j.getBarco().getTripulacion().length; i++) {
			if (j.getBarco().getTripulacion()[i].getRol() == 2) {
				indexTrip = i;
			}
		}
		t = j.getBarco().getTripulacion()[indexTrip];

		// dialogos dependiendo del dia y de la fase del dia
		if (dia == 1) {
			switch (JuegoControlador.faseActual) {
			case MANANA:
				gestorVista.imprimirMensaje(t.getNombre()
						+ ": 'Buenos días capitán! Listo para un nuevo día? Lo creas o no, tengo de visitar esta zona. Dicen que los peces de\n"
						+ "por aquí son extremadamente tiernos y sabrosos. Seguro que podríamos duplicar las ventas si nos hinchamos a pescar\n"
						+ "peces por aquí para servirlos luego en el restaurante! Que emoción.'");
				break;
			case COMIDA:
				gestorVista.imprimirMensaje(t.getNombre()
						+ ": 'Tengo los cuchillos listos, y bien afilados. No vaya a ser que algún listillo pretenda probar de mi cocina e irse\n"
						+ "sin pagar. Abran las puertas, estoy listo para lo que sea!'");
				break;
			case TARDENOCHE:
				gestorVista.imprimirMensaje(t.getNombre()
						+ ": 'Me iré a dar una vuelta por las tiendas de esta isla, a ver si encuentro algo interesante. Tú también deberías dar\n"
						+ "un paseo por los alrededores a ver si descubres algo de ese tesoro que andas buscando.'");
				break;
			case NAVEGACION:
				break;
			}
		}
	}

	public void dialogosLadron(int dia, Jugador j) {
		// ver que tripulante es el correspondiente a este rol
		Tripulante t;
		Tripulante coci;
		int indexTrip = -1;
		int indexCoci = -1;
		for (int i = 0; i < j.getBarco().getTripulacion().length; i++) {
			if (j.getBarco().getTripulacion()[i].getRol() == 3) {
				indexTrip = i;
			}
		}
		for (int i = 0; i < j.getBarco().getTripulacion().length; i++) {
			if (j.getBarco().getTripulacion()[i].getRol() == 2) {
				indexCoci = i;
			}
		}
		t = j.getBarco().getTripulacion()[indexTrip];
		coci = j.getBarco().getTripulacion()[indexCoci];

		// dialogos dependiendo del dia y de la fase del dia
		if (dia == 1) {
			switch (JuegoControlador.faseActual) {
			case MANANA:
				gestorVista.imprimirMensaje(t.getNombre()
						+ ": 'Buenos días caraculo. Tienes tan mal aspecto como siempre. Es broma capi, venga, vamos a aprovechar el día. Qué plan tienes hoy?\n"
						+ "Imagino que después de las comidas te bajarás a la isla a investigar un poco verdad? Ya sé que llevas tiempo con tu fachada de restaurante\n"
						+ "ambulante para poder buscar información de sobre ese tesoro, pero no te desanimes. Si me entero de algo te informaré.'");
				break;
			case COMIDA:
				gestorVista.imprimirMensaje(t.getNombre()
						+ ": 'Lo que más pereza me da de todo esto es saber que luego hay que limpiar toda la sala. Diría que prefiero volver a vivir en las calles\n"
						+ "antes que tener que trabajar en este dichoso restaurante, pero no es verdad. Al menos después de recoger"
						+ coci.getNombre() + "prepara comida para todos.'");
				break;
			case TARDENOCHE:
				gestorVista.imprimirMensaje(t.getNombre()
						+ ": 'Voy a ver si encuentro a algún tonto despistado al que poder sacarle unas perras o algo de información sobre ese tesoro. Pásatelo bien capi,\n"
						+ "yo sé que lo voy a hacer.'");
				break;
			case NAVEGACION:
				break;
			}
		}
	}

	public void dialogosPirata(int dia, Jugador j) {
		// ver que tripulante es el correspondiente a este rol
		Tripulante t;
		Tripulante ladr;
		int indexTrip = -1;
		int indexLadr = -1;
		for (int i = 0; i < j.getBarco().getTripulacion().length; i++) {
			if (j.getBarco().getTripulacion()[i].getRol() == 4) {
				indexTrip = i;
			}
		}
		for (int i = 0; i < j.getBarco().getTripulacion().length; i++) {
			if (j.getBarco().getTripulacion()[i].getRol() == 3) {
				indexLadr = i;
			}
		}
		t = j.getBarco().getTripulacion()[indexTrip];
		ladr = j.getBarco().getTripulacion()[indexLadr];

		// dialogos dependiendo del dia y de la fase del dia
		if (dia == 1) {
			switch (JuegoControlador.faseActual) {
			case MANANA:
				gestorVista.imprimirMensaje(t.getNombre() + ": 'Buenos días " + j.getNombre()
						+ ", cómo has pasado el viaje? Después de tantos años deambulando por el Gran Archipiélago imagino que\n"
						+ "ya no te marearás en el barco. Yo también estoy curado de espanto. Pero esta mañana nada más despertarme he visto a "
						+ ladr.getNombre() + "\n" + "échando la pota por la borda, hacía años que no me reía tanto.");
				break;
			case COMIDA:
				gestorVista.imprimirMensaje(t.getNombre()
						+ ": 'Con esta vieja pierna de madera mía no estoy muy ágil para andar atendiendo mesas, pero el estofado me sale de muerte (figuradamente).'");
				break;
			case TARDENOCHE:
				gestorVista.imprimirMensaje(t.getNombre()
						+ ": 'Me quedaré aquí en el barco vigilando por si acaso. Al final del día estoy tan acostumbrado a estar a flote que se me\n"
						+ "hace raro bajar a tierra firme. Aún así, tengo muy buenas vistas de la isla subido a la cofa.'");
				break;
			case NAVEGACION:
				break;
			}
		}
	}

}
