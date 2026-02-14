package modeloPersonajes;

public abstract class Persona {
	// atributos
	protected String nombre;
	protected String[] dialogos;
	protected String primeraFrase;

	// getters y setters
	public String getNombre() {
		return nombre;
	}

	public String[] getDialogos() {
		return dialogos;
	}

	public String getPrimeraFrase() {
		return primeraFrase;
	}

}
