
package figuras;

public class Circulo extends Figura
{
	/**
	 * Realmente tengo el atributo radio y el centro que esta en la clase
	 * padre. 
	 */
	private float radio;
	
	private int factor;

	/**
	 * Constructor por defecto.
	 */
	public Circulo ()
	{
		super(new Punto(1f,1f));
		
		radio = 1f;
		
		factor = 0;
		
	}
	
	public Circulo (Circulo otro)
	{
		
		super (new Punto(otro.getCentro()));
		
		radio = otro.radio;
	
	}
	
	/**
	 * Constructor con parametros.
	 * @param centro
	 * @param radio
	 * El radio ha de ser positivo. En el caso de que se de uno negativo
	 * se convertirá en positivo.
	 */
	public Circulo (Punto centro, float radio)
	{
		super(centro);
		radio = Math.abs(radio);
		this.radio = radio;
	}

	/**
	  * Mover a un punto en concreto el circulo
	  * @param punto
	  */
	@Override
	public void moverAPunto (Punto punto)
	{
		
		setCentro(punto);
	
	}
	
	public float getRadio()
	{
		return radio;
	}
	
	/**
	 * Mover a punto. Lo que le paso son distancias
	 * @param: mueveX
	 * @param: mueveY
	 */
	@Override
	public void moverDistancia (float mueveX, float mueveY)
	{
		float x = super.getCentro().getX() + mueveX;
		float y = super.getCentro().getY() + mueveY;
		super.setCentro(new Punto(x,y));
	}
	
	/**
	 * Escala pero siempre partiendo del tamaño inicial
	 * @param factor
	 */
	@Override
	public void escalar (int factor)
	{
		if (factor < 0 ) factor = 0;
		
		radio += factor - this.factor;
		
		this.factor = factor;
		
	}
	/**
	 * Nos dice si el punto cae dentro o fuera
	 * @param: punto
	 * @return: true o false
	 * 
	 * En esta funcion se podria utilizar tambien el Strategy?
	 */
	@Override
	public boolean contienePunto (Punto punto)
	{
		
		boolean puntoContenido = false;
		
		Punto centro = getCentro();
		
		if (centro.distancia(punto) <= radio)
		{
			puntoContenido = true;
		}
		return puntoContenido;
	}

	/**
	 * sobreescritura del toString
	 */
	@Override
	public String toString()
	{
		StringBuilder salida = new StringBuilder();
		salida.append("[ ");
		salida.append(getCentro());
		salida.append(" , ");
		salida.append(radio);
		salida.append(" ]");
		return salida.toString();
	}
	
	@Override
	public int hashCode() 
	{
	
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Float.floatToIntBits(radio);
		return result;
	
	}

	@Override
	public boolean equals(Object obj) 
	{
	
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Circulo other = (Circulo) obj;
		if (Float.floatToIntBits(radio) != Float.floatToIntBits(other.radio))
		{
		
			if (super.equals((Figura)obj))
			{
				
				return false;
			
			}
		}
		
		return true;
	}

	/*
	 * Parece que no cuadran en exceso estos dos metodos con el de un circulo
	 * pero realmente un circulo tiene estas dos cosas sobre todo cuando lo que
	 * queramos es poner el cuadrado circundante (bounding box).
	 */
	/**
	 * @return altura
	 */
	@Override
	public float alturaBoundingBox() 
	{
		return radio * 2;
	}
	
	/**
	 * @return longitud
	 */
	@Override
	public float baseBoundingBox() 
	{
		return radio * 2;
	}
	
	/**
	 * @return boundingBox.
	 */
	public Figura getBoundingBox()
	{
		
		Punto punto = new Punto (getCentro().getX() - baseBoundingBox()/2 ,getCentro().getY() - alturaBoundingBox() / 2);
		Figura boundingBox = new Rectangulo(punto, baseBoundingBox(), alturaBoundingBox());
		return boundingBox;
	
	}
}
