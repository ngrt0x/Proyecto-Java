package modeloPersonajes;

public abstract class Persona {
	// atributos
	protected String nombre;
	protected String[] dialogos;
	protected String[] respuestas;
	protected String opcionesDialogo;
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

	public void setPrimeraFrase(String primeraFrase) {
		this.primeraFrase = primeraFrase;
	}

	public String[] getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(String[] respuestas) {
		this.respuestas = respuestas;
	}

	public String getOpcionesDialogo() {
		return opcionesDialogo;
	}

	public void setOpcionesDialogo(String opcionesDialogo) {
		this.opcionesDialogo = opcionesDialogo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDialogos(String[] dialogos) {
		this.dialogos = dialogos;
	}
	
	

}
