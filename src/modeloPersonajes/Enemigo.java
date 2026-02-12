package modeloPersonajes;

public class Enemigo implements ICombatiente {
	//atributos
	protected String nombre;
	protected int saludActual;
	protected int saludTope;
	
	//metodos de la interfaz
	@Override
	public void atacar(ICombatiente objetivo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recibirDanio(int danio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean intentarEsquivar() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean estaVivo() {
		// TODO Auto-generated method stub
		return false;
	}

}
