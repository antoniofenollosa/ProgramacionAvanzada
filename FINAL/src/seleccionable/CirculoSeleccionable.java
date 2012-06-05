package seleccionable;


import figuras.Circulo;
import figuras.Punto;

public class CirculoSeleccionable extends Circulo implements Seleccionable 
{
	/*
	 * ATRIBUTOS
	 */
	
	private CriterioSeleccion criterioSeleccion = null;

	
	//-------------------------------------------
	
	/*
	 * CONSTRUCTORES
	 */
	
	public CirculoSeleccionable()
	{
		
		super();
		criterioSeleccion = new SeleccionPorFigura();
	
	}
	/**
	 * 
	 * @param circulo
	 */
	public CirculoSeleccionable(Circulo circulo)
	{
	
		super (circulo);
		criterioSeleccion = new SeleccionPorFigura();
	
	}
	
	public CirculoSeleccionable (Circulo circulo, CriterioSeleccion criterioSeleccion)
	{
	
		super(circulo);
		this.criterioSeleccion = criterioSeleccion;
	
	}
	
	//-----------------------------------------------------
	
	/*
	 * METODOS
	 */
	
	public void setCriterioSelecion(CriterioSeleccion criterio)
	{
	
		criterioSeleccion = criterio;
	
	}
	
	/**
	 * Esta clase dependiendo del criterio de seleccion que hemos inyectado seleccionará o bien por figura
	 * o por boundingbox. Esta llamara a la funciona queda seleccionado que hay que esta igualmente definido
	 * en seleccionar por figura y en seleccionar por boundingbox pero con la salvedad que el primero llamara
	 * al propio metodo que tiene la figura para seleccionar un punto que caiga dentro de ella y el otro
	 * primero llamará al boudingbox y despues utilizará este como figura para poder saber si el punto 
	 * ha caido dentro de el bounding box o no.
	 */
	public CriterioSeleccion getCriterioSeleccio()
	{
	
		return criterioSeleccion;
	
	}
	@Override
	public boolean quedaSeleccionado(Punto punto) 
	{
		boolean seleccionado = criterioSeleccion.quedaSeleccionado(this,punto);
		return seleccionado;
	
	}
	
	@Override
	public boolean equals (Object otroObjeto)
	{
		
		boolean salida = false;
		if (otroObjeto instanceof CirculoSeleccionable)
		
		{
		
			CirculoSeleccionable auxiliar = (CirculoSeleccionable) otroObjeto;
			salida = super.equals((Circulo) auxiliar);
		
		}
		
		return salida;
	
	}

	public CriterioSeleccion getCriterioSeleccion()
	{
	
		return criterioSeleccion;
	
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
		super.escalar (factor);
	
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
