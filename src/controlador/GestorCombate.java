package controlador;

import modeloJugador.Barco;
import modeloPersonajes.Enemigo;
import modeloPersonajes.Tripulante;
import java.util.Random;

public class GestorCombate implements Minijuego{
	//atributos
	private final Random ALEATORIO=new Random();
	private int contadorTurnos;
	private Tripulante[]combatientes;
	private Enemigo[]enemigos;
	private Barco barco;
	
	//metodos de la interfaz
	@Override
	public void comenzar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finalizar() {
		// TODO Auto-generated method stub
		
	}
	
	//metodos propios
	private boolean aliadosVivos() {
		
	}
	
	private boolean enemigosVivos() {
		
	}
	
	private void elegirEnemigo() {
		
	}
	
	private Tripulante seleccionarAliado() {
		
	}
	
	private int generarGaussOro() {
		int oro = (int) Math.round(ALEATORIO.nextGaussian() * 3 + 15);
		return Math.max(1, Math.min(oro, 25));
	}
	
	private Enemigo[] generarEnemigos() {
		
	}

}
