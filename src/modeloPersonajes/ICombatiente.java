package modeloPersonajes;

/**
 * Interfaz para combatientes, tiene todos los metodos necesarios para un combate
 * 
 * @author Jesús Manrique y Marcos Villagómez
 * @version 1.0
 */
public interface ICombatiente {
	/**
	 * Metodo para recibir daño
	 * @param danio Daño recibido
	 */
	public abstract void recibirDanio(int danio);

	/**
	 * Metodo para esquivar
	 * @return Si se ha logrado esquivar
	 */
	public abstract boolean intentarEsquivar();

	/**
	 * Metodo para ver si un combatiente sigue vivo
	 * @return Si esta vivo
	 */
	public abstract boolean estaVivo();

	/**
	 * Getter del nombre del combatiente
	 * @return Nombre del combatiente
	 */
	public abstract String getNombre();

	/**
	 * Setter de iniciativa
	 * @param iniciativa Iniciativa nueva del combatiente
	 */
	public abstract void setIniciativa(int iniciativa);

	/**
	 * Getter de iniciativa
	 * @return Iniciativa del combatiente
	 */
	public abstract int getIniciativa();
}
