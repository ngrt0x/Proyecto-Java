package modeloPersonajes;

public interface ICombatiente {
	public abstract void atacar(ICombatiente objetivo);

	public abstract void recibirDanio(int danio);

	public abstract boolean intentarEsquivar();
	
	public abstract boolean estaVivo();
}
