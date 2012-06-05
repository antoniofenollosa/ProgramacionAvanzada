package figuras;

public class Punto 
{
	/**
	 * @argumentos: 
	 * x,y
	 */
	private float x;
	private float y;
	private float tolerancia;
	/**
	 * Contructor por defecto sin argumentos.
	 * 
	 */
	public Punto ()
	{
		super();
		x = 0f;
		y = 0f;
		tolerancia = 0.000001f;
	}
	public Punto (Punto p)
	{
		
		super();
		if (p == null)
		{
			throw new NullPointerException();
		}
		x = p.getX();
		y = p.getY();
		tolerancia = p.getTolerancia();
	
	}
	/**
	 * Contructor dando los parametros siguientes.
	 * @param x
	 * @param y
	 */
	public Punto (float x , float y)
	{
		super();
		this.x = x;
		this.y = y;
		tolerancia = 0.000001f;
	}
	public Punto (float x, float y, float tolerancia)
	{
		super();
		this.x = x;
		this.y = y;
		this.tolerancia = Math.abs(tolerancia);
	}
	public Punto (float tolerancia)
	{
		super();
		new Punto();
		this.tolerancia = Math.abs(tolerancia);
	}
	//Todo esto de la tolerancia es lo que se me ha ocurrido para que funcione el assert equals.
	//El equals esta un poco cambiado pero supongo que cumple su funcion.
	public void setTolerancia(float tolerancia)
	{
		this.tolerancia = Math.abs(tolerancia);
	}
	public float getTolerancia()
	{
		return tolerancia;
	}
	/**
	 * Setter para el punto
	 * @param x
	 * @param y
	 */
	public void setPunto(float x , float y)
	{
		this.x = x;
		this.y = y;
	}
	
	
	public void mover (float distanciaX, float distanciaY)
	{
		x += distanciaX;
		y += distanciaY;
	}
	public void moverAPunto(Punto punto)
	{
		x = punto.x;
		y = punto.y;
	}
	/**
	 * Sobreescritura del metodo toString de la clase object.
	 */
	public String toString ()
	{
		StringBuilder salida = new StringBuilder();
		salida.append("(");
		salida.append(x);
		salida.append(",");
		salida.append(y);
		salida.append(")");
		return salida.toString();
	}
	/**
	 * Distancia entre el punto instancia y un punto dado.
	 * @param otroPunto
	 * @return
	 */
	public float distancia(Punto otroPunto)
	{
		float distanciaX = x-otroPunto.x;
		float distanciaY = y-otroPunto.y;
		return (float) Math.sqrt(distanciaX * distanciaX + distanciaY * distanciaY);
	}
	/**
	 * Getter para el parametro X
	 * @return
	 */
	public float getX()
	{
		return x;
	}
	/**
	 * Getter para el parametro Y
	 * @return
	 */
	public float getY()
	{
		return y;
	}
	/**
	 * Voy a poner el equals
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
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
		Punto other = (Punto) obj;
		if (Math.abs(x - other.x) > tolerancia)
			return false;
		if (Math.abs(y - other.y) > tolerancia)
			return false;
		return true;
	}
}
