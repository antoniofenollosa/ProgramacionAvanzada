package seleccionable;

import figuras.Figura;
import figuras.Punto;

public interface CriterioSeleccion 
{
	
	public boolean quedaSeleccionado(Figura figura, Punto punto);
	public boolean equals (Object objeto);
	public String toString();
	
}
