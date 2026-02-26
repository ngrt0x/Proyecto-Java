package modeloJugador;
import java.util.ArrayList;
import java.util.List;

public class Diario {
	//atributos
	private List<String>pistasTesoro;
	
	public Diario() {
		pistasTesoro = new ArrayList<String>();
	}
	
	//getters y setters
	public List<String> getPistasTesoro() {
		return pistasTesoro;
	}

	public void setPistasTesoro(List<String> pistasTesoro) {
		this.pistasTesoro = pistasTesoro;
	}
	
	//metodos propios
	public void agregarPista(String pista){
		pistasTesoro.add(pista);
	}
}
