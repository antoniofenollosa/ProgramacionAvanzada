package programaDibujo;

import java.util.List;

import figuras.Punto;

import seleccionable.SeleccionPorBoundingBox;
import seleccionable.SeleccionPorFigura;
import seleccionable.Seleccionable;

public class Controlador 
{
	Modelo modelo;
	
	Vista vista;
	
	Seleccionable seleccionable;
	
	public Controlador()
	{
		
		super();

		modelo = new Modelo();

	}
	
	public void setVista(Vista vista)
	{
		this.vista = vista;
	}
	
	public void setModelo(Modelo modelo)
	{
		this.modelo = modelo;
	}
	
	//Es avisado cuando se apreta alguna figura
	public void botonFiguraApretado ()
	{
		
		seleccionable = vista.getSeleccionable();
		
		modelo.addSeleccionable(seleccionable);
		
	}
	
	public void clickLienzo()
	{
		
		Punto punto = vista.getPunto();
		
		seleccionado(punto);
	
	}
	
	public void moverSeleccionableX (int x)
	{
		int numero = modelo.getObjetoSeleccionado();

		
		if (modelo.getObjetoSeleccionado() > -1)
		{
			
			Seleccionable seleccionable = modelo.getLista().get(numero);
			
			seleccionable.moverAPunto(new Punto(x , seleccionable.getCentro().getY()));
			
		}
		
	}
	
	public void moverSeleccionableY (int y)
	{
		int numero = modelo.getObjetoSeleccionado();

		
		if (numero > -1)
		{
			Seleccionable seleccionable = modelo.getLista().get(numero);
			
			seleccionable.moverAPunto(new Punto(seleccionable.getCentro().getX(),y));
		}
		
	}
	
	public void redimensionar(int factor)
	{
		int numero = modelo.getObjetoSeleccionado();
		
		
		if (numero > -1)
		{
			
			Seleccionable seleccionable = modelo.getLista().get(numero);
			
			seleccionable.escalar(factor);
		}
	}
	
	
	public void quitaElementoLista(Seleccionable seleccionable)
	{
		
		modelo.getLista().remove(seleccionable);
	
	}
	
	public void seleccionado(Punto punto)
	{
		List<Seleccionable> lista = modelo.getLista();
		
		for (int i = 0 ; i < lista.size(); i++)
		{
			if (lista.get(i).quedaSeleccionado(punto))
			{
				lista.get(i).setCriterioSelecion(new SeleccionPorBoundingBox());
				
				if (modelo.getObjetoSeleccionado() != i && modelo.getObjetoSeleccionado() != -1)
				{
					
					lista.get(modelo.getObjetoSeleccionado()).setCriterioSelecion(new SeleccionPorFigura());
				
				}
				
				modelo.setObjetoSeleccionado(i);
				
				break;
			
			}
			
			//Si por alguna casualidad entra ahí en alguna ocasion antes del
			//ultimo elemento de la fila esto se lo saltará.
			
			if (i == lista.size() - 1 && modelo.getObjetoSeleccionado() != -1)
			{
				//el modelo.getObjetoSeleccionado() es el de antes de apretar ya que se supone que no
				//ha entrado en ninguna ocasión en el if.
				lista.get(modelo.getObjetoSeleccionado()).setCriterioSelecion(new SeleccionPorFigura());

				modelo.setObjetoSeleccionado(-1);
				
			}	
		}
	}
	
	public void clearLista()
	{
		
		modelo.getLista().clear();
		
		modelo.setObjetoSeleccionado(-1);
	
	}
	public List<Seleccionable> getLista()
	{
		return modelo.getLista();
	}
	public int getSeleccionado()
	{
		return modelo.getObjetoSeleccionado();
	}
}
