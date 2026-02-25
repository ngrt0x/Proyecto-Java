package modeloJugador;
import java.util.ArrayList;
import java.util.List;

import vista.GestorVista;
public class Diario {
	//atributos
	private static final GestorVista gestorVista = new GestorVista();
	private List<String>pistasTesoro;
	
	public Diario() {
		pistasTesoro = new ArrayList<String>();
	}
	
	//metodos propios
	public void agregarPista(String pista){
		pistasTesoro.add(pista);
	}
	
	public void leerDiario() {
		if(pistasTesoro.isEmpty()) {
			gestorVista.imprimirMensaje("No hay nada apuntado en tu diario...");
		}else {
			gestorVista.imprimirMensaje(pistasTesoro.toString());
		}
	}
}
