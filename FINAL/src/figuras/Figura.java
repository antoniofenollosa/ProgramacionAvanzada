package figuras;

public abstract class Figura
{
	private Punto centro;
	
	public Figura()
	{
		super();
		centro = new Punto(0.5f,0.5f);
	}
	
	public Figura(Punto centro)
	{
		super();
		this.centro = centro;
	}
	
	public Punto getCentro()
	{
		return centro;
	}
	public void setCentro(Punto centro)
	{
		this.centro = centro;
	}
	
	public abstract float alturaBoundingBox();
	
	public abstract float baseBoundingBox ();
	
	public abstract void moverAPunto (Punto punto);
	
	public abstract void moverDistancia (float mueveX, float mueveY);
	
	public abstract void escalar (int factor);
	
	public abstract boolean contienePunto (Punto punto);
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((centro == null) ? 0 : centro.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Figura other = (Figura) obj;
		if (centro == null) {
			if (other.centro != null)
				return false;
		} else if (!centro.equals(other.centro))
			return false;
		return true;
	}
	
	public abstract String toString();
	
	public abstract Figura getBoundingBox();
	
}
