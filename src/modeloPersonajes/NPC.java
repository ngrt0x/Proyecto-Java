package modeloPersonajes;

public class NPC extends Persona {
	// atributos propios
	protected boolean conocido;
	protected boolean tienePista;
	protected String opcionesDialogo;
	protected String pista;

	// constructor para los NPCs que pueden tener pistas, que por lo genera son los
	// NPCs que tienen mas interacciones
	public NPC(String nombre, String[] dialogos, String opcionesDialogo, String primeraFrase, boolean tienePista, String pista) {
		this.nombre = nombre;
		this.dialogos = dialogos;
		this.opcionesDialogo = opcionesDialogo;
		this.primeraFrase = primeraFrase;
		this.tienePista = tienePista;
		this.pista = pista;
		this.conocido = false;
	}

	// constructor para los NPCs normales habitantes de la isla sin pistas y con
	// dialogos normales
	public NPC(String nombre, String[] dialogos, String primeraFrase) {
		this.nombre = nombre;
		this.dialogos = dialogos;
		this.primeraFrase = primeraFrase;
	}

	// constructor para los tenderos, clientes u otros NPCs sin pistas ni tantas
	// interacciones
	public NPC(String nombre, String primeraFrase) {
		this.nombre = nombre;
		this.primeraFrase = primeraFrase;
	}

	// NPCS random que solo necesitan nombre
	public NPC(String nombre) {
		this.nombre = nombre;
	}

	// getters y setters
	public boolean isConocido() {
		return conocido;
	}

	public void setConocido(boolean conocido) {
		this.conocido = conocido;
	}
	
	public boolean tienePista() {
		return tienePista;
	}
	
	public String getPista() {
		return pista;
	}
	
	public String getOpciones() {
		return opcionesDialogo;
	}

}
