import java.util.Scanner;

public class Motor extends Thread{

	private Conexion cx;
	private Ventana v;
	
	public Motor(){
		cx = new Conexion(this);
		v = new Ventana(this);
		this.start();
	}
	
	public void run(){
		while(true){
			try{
				Thread.sleep(15);
			}catch(Exception e){}
			v.obtenerPanel().repaint();
		}
	}
	
	public Conexion obtenerConexion(){
		return cx;
	}
	
	public Ventana obtenerVentana(){
		return v;
	}
	
}
