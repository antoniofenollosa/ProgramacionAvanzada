package figuras;

public class Rectangulo extends Figura
{

	private Punto izquierdaArriba;
	private Punto derechaArriba;
	private Punto izquierdaAbajo;
	private Punto derechaAbajo;
	private int factor;
	private final  float hipotenusaInicial;
	
	public Rectangulo()
	{
		super();
		izquierdaArriba = new Punto(0f,0f);
		derechaArriba = new Punto(1f,0f);
		izquierdaAbajo = new Punto(0f,1f);
		derechaAbajo = new Punto(1f,1f);
		factor = 0;
		hipotenusaInicial = hipotenusa();

	}
	public Rectangulo (Punto izquierdaArriba, float longitud , float altura)
	{
		super();
		longitud = Math.abs(longitud);
		altura = Math.abs(altura);
		this.izquierdaArriba = izquierdaArriba;
		this.derechaArriba = new Punto(izquierdaArriba.getX() + longitud, izquierdaArriba.getY());
		this.izquierdaAbajo = new Punto(izquierdaArriba.getX(), izquierdaArriba.getY() + altura);
		this.derechaAbajo = new Punto(izquierdaArriba.getX() + longitud, izquierdaArriba.getY() + altura);
		setCentro(setCentroRectangulo());
		factor = 0;
		hipotenusaInicial = hipotenusa();
	}
	public Rectangulo (Rectangulo unRectangulo)
	{
		izquierdaArriba = new Punto (unRectangulo.izquierdaArriba);
		derechaArriba = new Punto (unRectangulo.derechaArriba);
		izquierdaAbajo = new Punto (unRectangulo.izquierdaAbajo);
		derechaAbajo = new Punto (unRectangulo.derechaAbajo);
		setCentro(setCentroRectangulo());
		factor = 0;
		hipotenusaInicial = hipotenusa();
	}
	public float hipotenusa()
	{
		return izquierdaArriba.distancia(getCentro());
	}
	public Punto setCentroRectangulo()
	{
		Punto punto = new Punto((izquierdaArriba.getX() + base() / 2),
				(izquierdaArriba.getY() + altura() / 2));
	
		return punto;
	}
	
	public float base()
	{
		return izquierdaArriba.distancia(derechaArriba);
	}
	
	public float altura()
	{
		return izquierdaAbajo.distancia(izquierdaArriba);
	}
	/**
	 * Tenemos que tener en cuenta que este metodo no va a funcionar cuando
	 * lo que queremos hacer es girar la figura. solo funciona con figuras
	 * rectas.
	 */
	public Punto getIzquierdaArriba()
	{
		return izquierdaArriba;
	}
	public Punto getIzquierdaAbajo()
	{
		return izquierdaAbajo;
	}
	public Punto getDerechaArriba()
	{
		return derechaArriba;
	}
	public Punto getDerechaAbajo()
	{
		return derechaAbajo;
	}
	/**
	 * le paso las coordenadas de un punto en concreto para que se ponga donde quiero
	 * 
	 * @param punto
	 */
	@Override
	public void moverAPunto (Punto punto)
	{
		super.setCentro(punto);
		float base = baseBoundingBox();
		float altura = alturaBoundingBox();
		izquierdaArriba.moverAPunto(new Punto(punto.getX() - (base/2), punto.getY() - (altura/2)));
		derechaArriba = new Punto(izquierdaArriba.getX() + base
				, izquierdaArriba.getY());
		izquierdaAbajo = new Punto(izquierdaArriba.getX()
				,izquierdaArriba.getY() + altura);
		derechaAbajo = new Punto(izquierdaArriba.getX() + base
				, izquierdaArriba.getY() + altura);
	}
	
	@Override
	public void moverDistancia(float mueveX, float mueveY)
	{
		izquierdaArriba.mover(mueveX, mueveY);
		derechaArriba.mover(mueveX,  mueveY);
		izquierdaAbajo.mover(mueveX,mueveY);
		derechaAbajo.mover(mueveX, mueveY);
		super.setCentro(setCentroRectangulo());
	}
	@Override
	public void escalar(int factor) 
	{
	
		//Voy a hacerlo por trigonometria para que aumente
		//Desde el centro hacia fuera.
		factor = Math.abs(factor);
		float hipotenusa = hipotenusa();
		float anchura = base();
		float altura = altura();
		float senoAngulo = (altura/2) / hipotenusa;
		float cosenoAngulo = (anchura/2) / hipotenusa;
		
		hipotenusa += hipotenusaInicial * (factor - this.factor);
		
		float centroX = super.getCentro().getX();
		float centroY = super.getCentro().getY();
		
		
		derechaArriba.moverAPunto(new Punto (centroX + hipotenusa * cosenoAngulo,
				centroY - hipotenusa * senoAngulo));
		izquierdaArriba.moverAPunto(new Punto(centroX - hipotenusa *cosenoAngulo ,
				centroY - hipotenusa * senoAngulo));
		izquierdaAbajo.moverAPunto(new Punto (centroX - hipotenusa * cosenoAngulo,
				centroY + hipotenusa * senoAngulo));
		derechaAbajo.moverAPunto(new Punto(centroX + hipotenusa * cosenoAngulo,
				centroY + hipotenusa * senoAngulo));
		
		this.factor = factor;
	
	}

	public float area()
	{
		return base() * altura();
	}
	@Override
	public boolean contienePunto( Punto punto ) 
	{
		
		if (punto.getX() >= izquierdaArriba.getX() &&
				punto.getX() <= derechaArriba.getX() &&
				punto.getY() >= izquierdaArriba.getY() &&
				punto.getY() <= izquierdaAbajo.getY())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((derechaAbajo == null) ? 0 : derechaAbajo.hashCode());
		result = prime * result
				+ ((derechaArriba == null) ? 0 : derechaArriba.hashCode());
		result = prime * result
				+ ((izquierdaAbajo == null) ? 0 : izquierdaAbajo.hashCode());
		result = prime * result
				+ ((izquierdaArriba == null) ? 0 : izquierdaArriba.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rectangulo other = (Rectangulo) obj;
		if (derechaAbajo == null) 
		{
			if (other.derechaAbajo != null)
				return false;
		} else if (!derechaAbajo.equals(other.derechaAbajo))
			return false;
		if (derechaArriba == null) 
		{
			if (other.derechaArriba != null)
				return false;
		} else if (!derechaArriba.equals(other.derechaArriba))
			return false;
		if (izquierdaAbajo == null)
		{
			if (other.izquierdaAbajo != null)
				return false;
		} else if (!izquierdaAbajo.equals(other.izquierdaAbajo))
			return false;
		if (izquierdaArriba == null)
		{
			if (other.izquierdaArriba != null)
				return false;
		} else if (!izquierdaArriba.equals(other.izquierdaArriba))
			return false;
		return true;
	}

	@Override
	public String toString ()
	{
		StringBuilder salida = new StringBuilder();
		salida.append("[");
		salida.append(izquierdaArriba);
		salida.append(" , ");
		salida.append(derechaArriba);
		salida.append("\n");
		salida.append(izquierdaAbajo);
		salida.append(" , ");
		salida.append(derechaAbajo);
		salida.append("]");
		return  salida.toString();
	}
	
	@Override
	public Figura getBoundingBox()
	{
	
		return new Rectangulo(this);
	
	}
	
	@Override
	public float alturaBoundingBox()
	{
		
		return izquierdaAbajo.getY() - izquierdaArriba.getY();
		
	}
	@Override
	public float baseBoundingBox()
	{

		return derechaArriba.getX() - izquierdaArriba.getX();
	
	}
	public float getBase()
	{
		
		return izquierdaArriba.distancia(derechaArriba);
	
	}
	public float getAltura()
	{
		
		return izquierdaArriba.distancia(izquierdaAbajo);
	
	}
}
