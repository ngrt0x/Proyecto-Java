package modeloJugador;

import java.util.HashMap;
import java.util.Map;

import modeloObjetos.ArmamentoBarco;

public class InventarioBarco {
	// atributos
	Map<String, ArmamentoBarco> armamentos;

	public InventarioBarco() {
		armamentos = new HashMap<>();
	}

	// getters y setters
	public Map<String, ArmamentoBarco> getArmamentos() {
		return armamentos;
	}

	// metodos propios
	public void anadirArmamento(ArmamentoBarco nuevoItem) {
		String id = nuevoItem.getId();

		if (armamentos.containsKey(id)) {
			armamentos.get(id).sumarCantidad(1);
		} else {
			armamentos.put(id, nuevoItem);
		}
	}

	public void restarArmamento(String id, int cantidad) {
		ArmamentoBarco armamento = armamentos.get(id);

		armamento.restarCantidad(cantidad);
		if (armamento.getCantidad() <= 0) {
			armamentos.remove(id);
		}
	}
}
