package modeloPersonajes;

/**
 * Clase NPC, contiene todos los dialogos del npc, y tambien si tiene pista su pista correspondiente
 * 
 * @author Jesús Manrique y Marcos Villagómez
 * @version 1.0
 */
public class NPC extends Persona {
	// atributos propios
	/**
	 * Si ya has hablado con un NPC
	 */
	protected boolean conocido;
	/**
	 * Si tiene pista
	 */
	protected boolean tienePista;

	// constructor para los NPCs que pueden tener pistas, que por lo genera son los
	// NPCs que tienen mas interacciones
	/**
	 * Constructor para NPC
	 * @param nombre Nombre del npc
	 * @param dialogos Dialogos del npc
	 * @param opcionesDialogo Opciones del dialogo con el npc
	 * @param respuestas Respuestas del npc
	 * @param primeraFrase Primera frase del npc
	 * @param tienePista Si tiene pista ese npc
	 */
	public NPC(String nombre, String[] dialogos, String opcionesDialogo, String[] respuestas, String primeraFrase,
			boolean tienePista) {
		this.nombre = nombre;
		this.dialogos = dialogos;
		this.opcionesDialogo = opcionesDialogo;
		this.respuestas = respuestas;
		this.primeraFrase = primeraFrase;
		this.tienePista = tienePista;
		this.conocido = false;
	}

	// constructor para los tenderos, clientes u otros NPCs sin pistas ni tantas
	// interacciones
	/**
	 * Constructor para npc sin dialogos
	 * @param nombre Nombre del npc
	 * @param primeraFrase Primera frase
	 */
	public NPC(String nombre, String primeraFrase) {
		this.nombre = nombre;
		this.primeraFrase = primeraFrase;
	}

	// NPCS random que solo necesitan nombre
	/**
	 * Constructor para NPC de relleno
	 * @param nombre Nombre del npc
	 */
	public NPC(String nombre) {
		this.nombre = nombre;
	}

	// getters y setters
	/**
	 * Getter de si es conocido
	 * @return Si es conocido o no
	 */
	public boolean isConocido() {
		return conocido;
	}

	/**
	 * Setter de si es conocido
	 * @param conocido Cambia conocido para que el npc se conozca o se desconozca
	 */
	public void setConocido(boolean conocido) {
		this.conocido = conocido;
	}

	/**
	 * Getter de si tiene pista
	 * @return Si tiene pista o no
	 */
	public boolean tienePista() {
		return tienePista;
	}

	/**
	 * Getter de Opciones de dialogo
	 * @return Las opciones de dialogo
	 */
	public String getOpcionesDialogo() {
		return opcionesDialogo;
	}

}
