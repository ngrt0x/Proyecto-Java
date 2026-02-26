package modeloJugador;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase diario contenedor de las pistas del tesoro
 * 
 * @author Jesús Manrique y Marcos Villagómez
 * @version 1.0
 */
public class Diario {
	//atributos
	/**
	 * Lista de pistas
	 */
	private List<String>pistasTesoro;
	
	/**
	 * Constructor del barco, inicializa la lista de pistas
	 */
	public Diario() {
		pistasTesoro = new ArrayList<String>();
	}
	
	//getters y setters
	/**
	 * Getter de pistas
	 * @return La lista de pistas del tesoro
	 */
	public List<String> getPistasTesoro() {
		return pistasTesoro;
	}

	/**
	 * Setter de pistas
	 * @param pistasTesoro Las lista de pistas nueva
	 */
	public void setPistasTesoro(List<String> pistasTesoro) {
		this.pistasTesoro = pistasTesoro;
	}
	
	//metodos propios
	/**
	 * Agrega una pista nueva a la lista de pistas
	 * @param pista La pista nueva
	 */
	public void agregarPista(String pista){
		pistasTesoro.add(pista);
	}
}
