package programaDibujo;

import java.util.ArrayList;

import java.util.List;
import seleccionable.Seleccionable;

public class Modelo 
{
	private List<Seleccionable> lista;
	private int objetoSeleccionado;
	
	Vista vista;
	
	public Modelo()
	{
		lista = new ArrayList<Seleccionable>();
		objetoSeleccionado = -1;
	}
	
	public void setVista(Vista vista)
	{
		this.vista = vista;
	}
	
	public void addSeleccionable(Seleccionable seleccionable)
	{
		
		lista.add(seleccionable);
		
		vista.modeloCambiado();
		
	}
	
	public List<Seleccionable> getLista()
	{
	
		return lista;
	
	}
	
	public int getObjetoSeleccionado()
	{
		
		return objetoSeleccionado;
	
	}
	public void setObjetoSeleccionado(int numero)
	{
		objetoSeleccionado = numero;
		
		vista.modeloCambiado();
	}
	
	
}
