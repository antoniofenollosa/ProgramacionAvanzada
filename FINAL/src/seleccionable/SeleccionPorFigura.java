package seleccionable;

import figuras.Figura;
import figuras.Punto;

public class SeleccionPorFigura implements CriterioSeleccion 
{

	@Override
	public boolean quedaSeleccionado(Figura figura, Punto punto) 
	{
		
		return figura.contienePunto(punto); 
	
	}
	@Override
	public boolean equals (Object objeto)
	{
		
		if (objeto instanceof SeleccionPorFigura)
		{
			return true;
		}
		return false;
	}
	@Override
	public String toString()
	{
		StringBuilder salida = new StringBuilder();
		salida.append("\nSeleccion por Figura\n");
		return salida.toString();
	}
}
