package modeloPersonajes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import modeloJugador.Barco;
import modeloObjetos.ArmamentoBarco;
import modeloObjetos.Canon;

/**
 * Clase tripulante, los compañeros de tu aventura
 * 
 * @author Jesús Manrique y Marcos Villagómez
 * @version 1.0
 */
public class Tripulante extends Persona implements ICombatiente {
	/**
	 * Instacia de random
	 */
	private final Random ALEATORIO = new Random();
	// atributos propios
	/**
	 * Salud base
	 */
	private int saludBase;
	/**
	 * Salud actual
	 */
	private int saludActual;
	/**
	 * Salud maxima
	 */
	private int saludTope;
	/**
	 * Fuerza
	 */
	private int fuerza;
	/**
	 * Si esta defendiendo
	 */
	private boolean defendiendo = false;
	/**
	 * Iniciativa
	 */
	private int iniciativa;
	/**
	 * Estado del tripulante
	 */
	private String estado;
	/**
	 * Rol que tiene el tripulante
	 */
	private int rol;

	// constructor
	/**
	 * Constructor de tripulante
	 * 
	 * @param nombre Nombre del tripulante
	 * @param rol    Rol del tripulante
	 */
	public Tripulante(String nombre, int rol) {
		this.nombre = nombre;
		this.rol = rol;
	}

	// getters y setters
	/**
	 * Getter de si esta defendiendo
	 * 
	 * @return Si esta defendiendo
	 */
	public boolean isDefendiendo() {
		return defendiendo;
	}

	/**
	 * Setter de si esta defendiando
	 * 
	 * @param defendiendo Si esta defendiendo
	 */
	public void setDefendiendo(boolean defendiendo) {
		this.defendiendo = defendiendo;
	}

	/**
	 * Getter del rol
	 * 
	 * @return Rol del tripulante
	 */
	public int getRol() {
		return rol;
	}

	/**
	 * Setter del rol
	 * 
	 * @param rol Nuevo rol asignado al tripulante
	 */
	public void setRol(int rol) {
		this.rol = rol;
	}

	/**
	 * Getter de la salud actual
	 * 
	 * @return La salud actual
	 */
	public int getSaludActual() {
		return saludActual;
	}

	/**
	 * Getter de la salud maxima
	 * 
	 * @return La salud maxima
	 */
	public int getSaludTope() {
		return saludTope;
	}

	/**
	 * Setter de la salud maxima
	 * 
	 * @param saludTope Salud maxima nueva
	 */
	public void setSaludTope(int saludTope) {
		this.saludTope = saludTope;
	}

	/**
	 * Setter para salud actual
	 * 
	 * @param saludActual Nueva salud actual
	 */
	public void setSaludActual(int saludActual) {
		this.saludActual = saludActual;
	}

	/**
	 * Getter de la salud base
	 * 
	 * @return La salud base del tripulante
	 */
	public int getSaludBase() {
		return saludBase;
	}

	/**
	 * Setter de salud base
	 * 
	 * @param saludBase Nueva salud base
	 */
	public void setSaludBase(int saludBase) {
		this.saludBase = saludBase;
	}

	/**
	 * Getter de iniciativa
	 * 
	 * @return Iniciativa del tripulante
	 */
	public int getIniciativa() {
		return iniciativa;
	}

	/**
	 * Setter de iniciativa
	 * 
	 * @param iniciativa Nueva iniciativa
	 */
	public void setIniciativa(int iniciativa) {
		this.iniciativa = iniciativa;
	}

	/**
	 * Getter de estado
	 * 
	 * @return Estado del tripulante
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Setter de estado
	 * 
	 * @param estado Nuevo estado del tripulante
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Setter de fuerza
	 * 
	 * @param fuerza Nueva fuerza del tripulante
	 */
	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}

	/**
	 * Metodo para atacar de la clase Tripulante. Compuebla que el barco tenga
	 * armamento, crea una lista con esos armamentos y la ordena en base al tier del
	 * armamento. El daño que se aplica al ataque de los tripulantes es el del
	 * armamento de mayor tier.
	 * 
	 * @param barco Barco en el que estas
	 * @return Daño que hace el barco
	 */
	public int atacar(Barco barco) {
		int danio;
		Map<String, ArmamentoBarco> inventarioB = barco.getInventarioB().getArmamentos();
		List<ArmamentoBarco> armamentos = new ArrayList<>();
		for (String i : inventarioB.keySet()) {
			if (!(inventarioB.get(i) instanceof Canon)) {
				armamentos.add(inventarioB.get(i));
			}
		}
		armamentos.sort((a, b) -> b.getTier() - a.getTier());
		danio = armamentos.get(0).getDanio();
		return danio + fuerza;
	}

	/**
	 * Metodo para cambiar el estado de defendiendo a true
	 */
	public void defender() {
		defendiendo = true;
	}

	/**
	 * Metodo para recuperar Salud
	 * @param saludRecuperada Cantidad de salud recuperada
	 */
	public void recuperarSalud(int saludRecuperada) {
		saludActual = Math.min(saludTope, saludActual + saludRecuperada);
	}

	// metodos de la interfaz
	@Override
	/**
	 * Metodo para recibir daño
	 * @param danio Daño recibido
	 */
	public void recibirDanio(int danio) {
		// si ha consumido una pocion de defensa recibe un 20% menos de danio durante el
		// resto del combate
		if (estado.equals("defendiendo")) {
			danio = (int) ((double) danio * 80 / 100);
		}
		saludActual = Math.max(0, saludActual - danio);
	}

	@Override
	/**
	 * Metodo para intentar esquivar, dependiendo de tu cantidad de iniciativa
	 * @return Si se ha logrado esquivar
	 */
	public boolean intentarEsquivar() {
		int aleatorio = ALEATORIO.nextInt(100);
		if (aleatorio < iniciativa) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	/**
	 * Metodo para saber si sigue vivo
	 * @return Si sigue vivo
	 */
	public boolean estaVivo() {
		return saludActual > 0;
	}

}
