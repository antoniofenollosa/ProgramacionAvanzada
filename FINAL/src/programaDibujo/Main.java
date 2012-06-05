package programaDibujo;

public class Main 
{
	public Main ()
	{
		super();
	}
	public static void main (String [] args)
	{
		Main empieza = new Main();
		empieza.ya();
	}
	
	public void ya()
	{
		Vista vista = new Vista();
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador();
		vista.setModelo(modelo);
		vista.setcontrolador(controlador);
		controlador.setModelo(modelo);
		controlador.setVista(vista);
		modelo.setVista(vista);
		vista.pintar();
		System.out.println("fin");
		
	}
}
