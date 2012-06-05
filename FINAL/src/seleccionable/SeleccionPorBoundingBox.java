package seleccionable;

import figuras.Figura;
import figuras.Punto;

public class SeleccionPorBoundingBox implements CriterioSeleccion
{

	@Override
	public boolean quedaSeleccionado(Figura figura, Punto punto) 
	{
		
		Figura boundingBox = figura.getBoundingBox();

		return boundingBox.contienePunto(punto);
		
	}
	@Override
	public boolean equals (Object objeto)
	{
	
		if (objeto instanceof SeleccionPorBoundingBox)
		{
			return true;
		}
		return false;
	
	}
	@Override
	public String toString()
	{
		
		StringBuilder salida = new StringBuilder();
		salida.append("Seleccion por BoundingBox\n");
		return salida.toString();
	
	}
	
}