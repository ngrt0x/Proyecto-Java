package modeloJugador;

import java.util.HashMap;
import java.util.Map;

import modeloObjetos.ArmamentoBarco;

/**
 * Clase inventario para barcos, contiene las mejoras del barco
 * 
 * @author Jesús Manrique y Marcos Villagómez
 * @version 1.0
 */
public class InventarioBarco {
	// atributos
	/**
	 * Lista de las mejoras del barco
	 */
	Map<String, ArmamentoBarco> armamentos;

	/**
	 * Constructor del InventarioBarcos, inicializa la lista de mejoras
	 */
	public InventarioBarco() {
		armamentos = new HashMap<>();
	}

	// getters y setters
	/**
	 * Getter de Armamento
	 * @return Lista de mejoras del barco
	 */
	public Map<String, ArmamentoBarco> getArmamentos() {
		return armamentos;
	}

	// metodos propios
	/**
	 * Metodo para añadir mejoras
	 * @param nuevoItem Mejora a añadir al inventario
	 */
	public void anadirArmamento(ArmamentoBarco nuevoItem) {
		String id = nuevoItem.getId();

		if (armamentos.containsKey(id)) {
			armamentos.get(id).sumarCantidad(1);
		} else {
			armamentos.put(id, nuevoItem);
		}
	}

	/**
	 * Metodo para restar mejoras
	 * @param id Id de la mejora
	 * @param cantidad Cantidad de mejoras a eliminar
	 */
	public void restarArmamento(String id, int cantidad) {
		ArmamentoBarco armamento = armamentos.get(id);

		armamento.restarCantidad(cantidad);
		if (armamento.getCantidad() <= 0) {
			armamentos.remove(id);
		}
	}
}
