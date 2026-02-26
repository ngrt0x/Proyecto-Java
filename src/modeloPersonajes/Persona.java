package modeloPersonajes;

/**
 * Clase para persona, persona contiene dialogos respuestas opciones de dialogo y primera frase.
 * 
 * @author Jesús Manrique y Marcos Villagómez
 * @version 1.0
 */
public abstract class Persona {
	// atributos
	/**
	 * Nombre de la persona
	 */
	protected String nombre;
	/**
	 * Dialogos de la persona
	 */
	protected String[] dialogos;
	/**
	 * Respuesta de la persona
	 */
	protected String[] respuestas;
	/**
	 * Opciones de dialogo que puedes tener con esa persona
	 */
	protected String opcionesDialogo;
	/**
	 * Primera frase de esa persona
	 */
	protected String primeraFrase;

	// getters y setters
	/**
	 * Getter del nombre
	 * @return Nombre de la persona
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Getter de los dialogos
	 * @return Array de los dialogos de la persona
	 */
	public String[] getDialogos() {
		return dialogos;
	}

	/**
	 * Getter de la primera frase
	 * @return La primera frase de la persona
	 */
	public String getPrimeraFrase() {
		return primeraFrase;
	}

	/**
	 * Setter de la primera frase
	 * @param primeraFrase Nueva primera frase
	 */
	public void setPrimeraFrase(String primeraFrase) {
		this.primeraFrase = primeraFrase;
	}

	/**
	 * Getter de las respuestas
	 * @return Array de las respuestas
	 */
	public String[] getRespuestas() {
		return respuestas;
	}

	/**
	 * Setter de las respuestas
	 * @param respuestas Respuestas nuevas
	 */
	public void setRespuestas(String[] respuestas) {
		this.respuestas = respuestas;
	}

	/**
	 * Getter de opciones dialogo
	 * @return Opciones del dialogo
	 */
	public String getOpcionesDialogo() {
		return opcionesDialogo;
	}

	/**
	 * Setter de opciones dialogo
	 * @param opcionesDialogo Nuevas opciones de dialogo
	 */
	public void setOpcionesDialogo(String opcionesDialogo) {
		this.opcionesDialogo = opcionesDialogo;
	}

	/**
	 * Setter del nombre
	 * @param nombre Nuevo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Setter de dialogos
	 * @param dialogos Nuevos dialogos
	 */
	public void setDialogos(String[] dialogos) {
		this.dialogos = dialogos;
	}
	
	

}
