package programaDibujo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import figuras.Circulo;
import figuras.Punto;
import figuras.Rectangulo;

import seleccionable.*;


public class Vista 
{
	JFrame frameBotones;
	
	JFrame frameLienzo;
	
	Seleccionable figuraSeleccionable;

	Controlador controlador;
	
	Modelo modelo;
	
	Punto punto;
	
	class BotonRectanguloListener implements ActionListener
	
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			figuraSeleccionable = new RectanguloSeleccionable(new Rectangulo(new Punto((float)Math.random()*500,(float)Math.random()*500),200,300));
			
			controlador.botonFiguraApretado();
			
		}
		
	}
	class BotonCirculoListener implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			figuraSeleccionable = new CirculoSeleccionable(new Circulo(new Punto((float)Math.random()*500,(float)Math.random()*500),(float)Math.random()*200));
			
			controlador.botonFiguraApretado();
						
		}
		
	}
	
	class RatonListener implements MouseListener
	{
		
		@Override
		public void mouseClicked(MouseEvent e)
		
		{
			
			punto = new Punto(e.getPoint().x,e.getPoint().y);
			
			controlador.clickLienzo();
			
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) 
		{
			
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent arg0) 
		{
			
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent arg0) 
		{
			
			// TODO Auto-gen<za erated method stub
			
		}
		
		@Override
		public void mouseReleased(MouseEvent arg0) 
		{
			
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class SliderMoverXListener implements ChangeListener
	{
		
		@Override
		public void stateChanged(ChangeEvent e) 
		{
			
			int x = ((JSlider) e.getSource()).getValue();
			
			controlador.moverSeleccionableX(x);
			
			frameLienzo.repaint();
			
			frameBotones.repaint();
			
		}
		
	}
	
	class SliderMoverYListener implements ChangeListener
	{
		
		@Override
		public void stateChanged(ChangeEvent e) 
		{
			
			int y = ((JSlider) e.getSource()).getValue();
			
			controlador.moverSeleccionableY(y);
			
			frameLienzo.repaint();
			
			frameBotones.repaint();
			
		}
		
	}
	
	class SliderRedimensionarListener implements ChangeListener
	{
		
		@Override
		public void stateChanged(ChangeEvent e) 
		{
			
			int factor = ((JSlider) e.getSource()).getValue();
			
			controlador.redimensionar(factor);
			
			frameLienzo.repaint();
			
			frameBotones.repaint();
			
		}
		
	}
	
	class BotonClearListener implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			controlador.clearLista();
			
			frameBotones.repaint();
			
			frameLienzo.repaint();
			
		}
		
	}
	public Punto getPunto()
	{
		return punto;
	}
	
	public void setcontrolador(Controlador controlador)
	{
		this.controlador = controlador;
	}
	
	public void setModelo(Modelo modelo)
	{
		this.modelo = modelo;
	}

	public Seleccionable getSeleccionable()
	{
		
		return figuraSeleccionable;
	
	}
	
	public void modeloCambiado()
	{
		frameLienzo.repaint();
	}
	
	public Vista()
	{
		super();
		
		frameBotones = new JFrame("BOTONES");
		
		frameLienzo = new JFrame("LIENZO");
		
		controlador = new Controlador();
		
	}
	
	public void pintar()
	{
		class PanelFiguras extends JPanel
		{

			private static final long serialVersionUID = 1L;
			
			public PanelFiguras()
			{

				super();
			
			}
			
			public void paintComponent (Graphics g)
			{
				
				super.paintComponent(g);
				
				Circulo circulo;
				
				Rectangulo rectangulo;
				
				Graphics2D g2 = (Graphics2D) g;
				
				for (int i = 0 ; i <controlador.getLista().size() ; i++)
				{
					
					
					if (i == controlador.getSeleccionado())
					{
						
						g.setColor(Color.green);
						
					}
					
					if (controlador.getLista().get(i) instanceof RectanguloSeleccionable)
					{
						
						rectangulo = (Rectangulo)controlador.getLista().get(i);
						
						g2.draw(new Rectangle2D.Float(rectangulo.getIzquierdaArriba().getX(),
								rectangulo.getIzquierdaArriba().getY(), 
								rectangulo.getBase(), rectangulo.getAltura()));
						
					}
					else if (controlador.getLista().get(i) instanceof CirculoSeleccionable)
					{
						
						circulo = (Circulo) controlador.getLista().get(i);
						
						rectangulo = (Rectangulo)circulo.getBoundingBox();
						
						g2.draw(new Ellipse2D.Float(rectangulo.getIzquierdaArriba().getX(),
								rectangulo.getIzquierdaArriba().getY(), 
								rectangulo.getBase(), rectangulo.getAltura()));
						
						if (i == controlador.getSeleccionado())
						{
							
							g2.setColor(Color.BLUE);
							
							g2.draw(new Rectangle2D.Float( rectangulo.getIzquierdaArriba().getX(),
									rectangulo.getIzquierdaArriba().getY(), 
									rectangulo.getBase(), rectangulo.getAltura()));
						}
					}
					
					g2.setColor(Color.black);
					
				}
				
			}
		}
		
		frameBotones.setLocation(810, 0);
		
		frameBotones.setVisible(true);
		
		frameLienzo.setSize(new Dimension(800,700));
		
		frameLienzo.setPreferredSize(new Dimension(800,700));
		
		frameLienzo.setVisible(true);
		
		frameBotones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frameLienzo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		PanelFiguras panelFiguras = new PanelFiguras();
		
		//Voy a crear un boton y añadirlo al panel izquierdo
		JButton botonRectangulo = new JButton("RECTANGULO");
		
		BotonRectanguloListener escuchadorBotonRectangulo = new BotonRectanguloListener();
		
		botonRectangulo.addActionListener(escuchadorBotonRectangulo);
		
		JButton botonCirculo = new JButton("CIRCULO");
		
		BotonCirculoListener escuchadorBotonCirculo = new BotonCirculoListener();
		
		botonCirculo.addActionListener(escuchadorBotonCirculo);
		
		JButton botonClear = new JButton("CLEAR");
		
		BotonClearListener escuchadorBotonListener = new BotonClearListener();
		
		botonClear.addActionListener(escuchadorBotonListener);
		
		JPanel panelBotones = new JPanel();
		
		//Voy a crear sliders
//-----------------------------------------------------------------------------------------------------------------------------------------------------

		//SLIDER MOVER X

		Font fuente =  new Font ("Serif", Font.ITALIC, 15);
		
		JSlider sliderMoverX = new JSlider(JSlider.HORIZONTAL, 0 , 500, 10); //DESDE DONDE HASTA DONDE Y POR UN FACTOR DE CUANDO HACE SU FUNCION
		
		sliderMoverX.setMajorTickSpacing(100);//TRICKS GRANDES
		
		sliderMoverX.setMinorTickSpacing(25);//TRICKS PEQUEÑOS
		
		sliderMoverX.setPaintTicks(true);//DIGO QUE PINTE LOS TRICS
		
		sliderMoverX.setFont(fuente);//CAMBIO LA FUENTE DE LAS ETIQUETAS
		
		sliderMoverX.setPaintLabels(true);//PINTA LAS ETIQUETAS
		
		sliderMoverX.setBorder(BorderFactory.createTitledBorder("MOVER X"));//LEYENDA QUE LE PONEMOS EN EL CUADRADO QUE NOS SALE FUERA DEL SLIDER
		
		sliderMoverX.setToolTipText("Nos permite mover la figura en el eje de las X"); //LETRERO QUE NOS SALE CUANDO PASAMOS EL RATON POR ENCIMA DEL SLIDER
		
		//SLIDER MOVER Y
		
		JSlider sliderMoverY = new JSlider(JSlider.HORIZONTAL, 0 , frameLienzo.getWidth(), 10); //DESDE DONDE HASTA DONDE Y POR UN FACTOR DE CUANDO HACE SU FUNCION
		
		sliderMoverY.setMajorTickSpacing(100);//TRICKS GRANDES
		
		sliderMoverY.setMinorTickSpacing(25);//TRICKS PEQUEÑOS
		
		sliderMoverY.setPaintTicks(true);//DIGO QUE PINTE LOS TRICS
		
		sliderMoverY.setFont(fuente);//CAMBIO LA FUENTE DE LAS ETIQUETAS
		
		sliderMoverY.setPaintLabels(true);//PINTA LAS ETIQUETAS
		
		sliderMoverY.setBorder(BorderFactory.createTitledBorder("MOVER Y"));//LEYENDA QUE LE PONEMOS EN EL CUADRADO QUE NOS SALE FUERA DEL SLIDER
		
		sliderMoverY.setToolTipText("Nos permite mover el eje de las y"); //LETRERO QUE NOS SALE CUANDO PASAMOS EL RATON POR ENCIMA DEL SLIDER

//		frameBotones.repaint();
		
		//SLIDER AUMENTAR DISMINUIR TAMAÑO

		JSlider sliderRedimensionar = new JSlider(JSlider.HORIZONTAL, 0 , 50, 5); //DESDE DONDE HASTA DONDE Y POR UN FACTOR DE CUANDO HACE SU FUNCION

		sliderRedimensionar.setMajorTickSpacing(5);//TRICKS GRANDES
		
		sliderRedimensionar.setMinorTickSpacing(1);//TRICKS PEQUEÑOS
		
		sliderRedimensionar.setPaintTicks(true);//DIGO QUE PINTE LOS TRICS
		
		sliderRedimensionar.setFont(fuente);//CAMBIO LA FUENTE DE LAS ETIQUETAS
		
		sliderRedimensionar.setPaintLabels(true);//PINTA LAS ETIQUETAS

		sliderRedimensionar.setBorder(BorderFactory.createTitledBorder("REDIMENSIONAR"));//LEYENDA QUE LE PONEMOS EN EL CUADRADO QUE NOS SALE FUERA DEL SLIDER
		
		sliderRedimensionar.setToolTipText("Nos permite redimensionar la figura"); //LETRERO QUE NOS SALE CUANDO PASAMOS EL RATON POR ENCIMA DEL SLIDER
		

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

		
		JPanel panel1 = new JPanel();
		
		panel1.add(botonCirculo);
		
		panel1.add(botonRectangulo);
		
		panel1.add(botonClear);
		
		panel1.setLayout(new GridLayout(2,1));
		
		JPanel panel2 = new JPanel();
		
		panel2.setLayout(new GridLayout(0,1));//le pongo el greedLayout a el panel 2 que es donde voy a poner los Sliders
		
		SliderMoverXListener escuchadorMoverXListener = new SliderMoverXListener();
		
		sliderMoverX.addChangeListener(escuchadorMoverXListener);
		
		panel2.add(sliderMoverX);
		
		SliderMoverYListener escuchadorMoverYListener = new SliderMoverYListener();
		
		sliderMoverY.addChangeListener(escuchadorMoverYListener);
		
		panel2.add(sliderMoverY);
		
		SliderRedimensionarListener escuchadorRedimiensionarListener = new SliderRedimensionarListener();
		
		sliderRedimensionar.addChangeListener(escuchadorRedimiensionarListener);
		
		panel2.add(sliderRedimensionar);
		
		panelBotones.setLayout(new GridLayout(0,1));
		
		panelBotones.add(panel1);
		
		panelBotones.add(panel2);
		
		//Añado el boton al contentPane
		
		panelBotones.setBackground(Color.cyan);
		
		frameBotones.getContentPane().add(panelBotones,BorderLayout.EAST);
		
		//Creo un panel de dibujo donde voy a pintar mis figuras
		
		panelFiguras.setBackground(Color.WHITE);
		
		frameBotones.getContentPane().repaint();
		
		RatonListener escuchadorRaton = new RatonListener();
		
		panelFiguras.addMouseListener(escuchadorRaton);
		
		frameLienzo.getContentPane().add(panelFiguras);
		
		frameBotones.pack();
		
		frameLienzo.pack();
		
		frameBotones.repaint();
		
		frameLienzo.repaint();
		
	}
}


