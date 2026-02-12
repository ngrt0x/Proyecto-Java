package controlador;

import modeloObjetos.Pez;

public class GestorPesca implements Minijuego {
	// atributos
	private Pez[] peces;
	private int resistenciaLinea;
	private int distanciaPez;
	private FasePesca faseActual;

	enum FasePesca {
		LANZAR, ESPERAR, PICA, LUCHA, RESULTADO
	}

	// metodos de la interfaz
	@Override
	public void comenzar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void finalizar() {
		// TODO Auto-generated method stub

	}

	//metodos propios
	private void tirar() {

	}

	private void aflojar() {

	}

	private void esperar() {

	}

	private void lanzarAnzuelo() {

	}

}
