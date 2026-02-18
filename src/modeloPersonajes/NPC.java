package modeloPersonajes;

public class NPC extends Persona {
	// atributos propios
	protected boolean tienePista;
	protected String pista;

	// constructor para los NPCs que pueden tener pistas, que por lo genera son los
	// NPCs que tienen mas interacciones
	public NPC(String nombre, String[] dialogos, String primeraFrase, boolean tienePista, String pista) {
		this.nombre = nombre;
		this.dialogos = dialogos;
		this.primeraFrase = primeraFrase;
		this.tienePista = tienePista;
		this.pista = pista;
	}

	// constructor para los tenderos, clientes u otros NPCs sin pistas ni tantas
	// interacciones
	public NPC(String nombre) {
		this.nombre = nombre;
	}
}
