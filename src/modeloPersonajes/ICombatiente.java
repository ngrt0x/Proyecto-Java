package modeloPersonajes;

public interface ICombatiente {
	public abstract void recibirDanio(int danio);

	public abstract boolean intentarEsquivar();

	public abstract boolean estaVivo();

	public abstract String getNombre();

	public abstract void setIniciativa(int iniciativa);

	public abstract int getIniciativa();
}
