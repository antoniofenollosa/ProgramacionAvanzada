package seleccionable;

/*
 *Clases importadas. 
 */

import figuras.*;

public class RectanguloSeleccionable extends Rectangulo implements Seleccionable
{
	/*
	 * Atributo local
	 */
	
	CriterioSeleccion criterioSeleccion;
	
	/*
	 * Consturctores varios
	 */
	
	public RectanguloSeleccionable()
	{
		super();
		this.criterioSeleccion = new SeleccionPorFigura();
	}
	
	public RectanguloSeleccionable (Rectangulo rectangulo)
	{
		super(rectangulo);
		this.criterioSeleccion = new SeleccionPorFigura();

	}
	public RectanguloSeleccionable (Rectangulo rectangulo, CriterioSeleccion criterio)
	{
		super(rectangulo);
		this.criterioSeleccion = criterio;

	}
	
	/**
	 * Para poder cambiar de criterio de selección una vez creado el 
	 * objeto (En tiempo de ejecución)
	 */
	
	public void setCriterioSelecion(CriterioSeleccion criterio)
	{
		criterioSeleccion = criterio;
	}
	public CriterioSeleccion getCriterioSeleccion()
	{
		return criterioSeleccion;
	}

	/**
	 * Esta clase dependiendo del criterio de seleccion que hemos inyectado seleccionará o bien por figura
	 * o por boundingbox. Esta llamara a la funciona queda seleccionado que hay que esta igualmente definido
	 * en seleccionar por figura y en seleccionar por boundingbox pero con la salvedad que el primero llamara
	 * al propio metodo que tiene la figura para seleccionar un punto que caiga dentro de ella y el otro
	 * primero llamará al boudingbox y despues utilizará este como figura para poder saber si el punto 
	 * ha caido dentro de el bounding box o no.
	 */
	@Override
	public boolean quedaSeleccionado(Punto punto) {
		boolean seleccionado = criterioSeleccion.quedaSeleccionado(this,punto);
		return seleccionado;
	}
	
	public boolean equals (Object objeto)
	{
		boolean salida = false;
		if (objeto instanceof RectanguloSeleccionable)
		{
			RectanguloSeleccionable auxiliar = (RectanguloSeleccionable) objeto;
			salida = super.equals((Rectangulo) auxiliar) && criterioSeleccion.equals(auxiliar.getCriterioSeleccion());
		}
		return salida;
	}
	
	@Override
	public void moverDistancia(float distanciaX, float distanciaY)
	{

		super.moverDistancia(distanciaX, distanciaY);

	}
	@Override
	public void moverAPunto (Punto punto)
	{
	
		super.moverAPunto(punto);

	}
	@Override
	public void escalar (int factor)
	{
		StringBuilder evento = new StringBuilder();
		evento.append(super.toString());
		evento.append(" - ");
		super.escalar (factor);
		evento.append(super.toString());
	}
	@Override
	public String toString()
	{
		StringBuilder salida = new StringBuilder();
		salida.append("\n");
		salida.append(super.toString());
		salida.append("\n");
		salida.append(criterioSeleccion);
		salida.append("\n");
		return salida.toString();		
		
	}
}
