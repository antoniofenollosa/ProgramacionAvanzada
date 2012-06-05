package seleccionable;

import figuras.Punto;

public interface Seleccionable 
{
	/**
	 * Le  paso un punto y el me devuelve true o false dependiendo si
	 * cae dentro de la figura o no.
	 * @param punto
	 * @return
	 */
	public boolean quedaSeleccionado(Punto punto);
	public void moverAPunto (Punto punto);
	public Punto getCentro();
	public void escalar(int factor);
	public boolean contienePunto(Punto punto);
	public void setCriterioSelecion(CriterioSeleccion criterio);
	//public boolean equals(Object otroObjeto);
	//public Figura primitiva();
	//public CriterioSeleccion getCriterioSeleccion();
}