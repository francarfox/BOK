import java.awt.event.KeyEvent;
import java.io.*;
import java.net.Socket;


public class Cliente implements Runnable{

	private Socket s;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Motor m;
	private Panel p;
	private int puerto;
	
	public Cliente(Motor m,int puerto){
		this.m = m;
		this.p = m.obtenerVentana().obtenerPanel();
		this.puerto = puerto;
	}

	public void run() {
		try{
			s = new Socket("localhost",puerto);
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			
			System.out.println("Conexion Exitosa");
			this.readLine();
		}catch(Exception e){
			this.closeCliente();
			e.printStackTrace();
		}
	}
	
	public void writeLine(int pj){		
		try{
			oos.writeObject(pj);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void readLine(){
		while(true){
			try{
				Object aux = ois.readObject();
				if(aux!=null){
					p.pj.direccion = (int)aux;
					Thread t = new Thread(p.pj);
					t.start();
				}
				Thread.sleep(15);
			}catch(Exception e){}	
			p.repaint();
		}
	}
	
	public void closeCliente(){
		try{
			oos.close();
			ois.close();
			s.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
