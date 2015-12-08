import java.awt.event.KeyEvent;
import java.io.*;
import java.net.*;


public class Servidor implements Runnable{
	
	private ServerSocket ss;
	private Socket s;
	private ObjectOutput oos;
	private ObjectInput ois;
	private Motor m;
	private Panel p;
	private int puerto;
	
	public Servidor(Motor m,int puerto){
		this.m = m;
		this.p = m.obtenerVentana().obtenerPanel();
		this.puerto = puerto;
	}

	public void run() {
		try{
			ss = new ServerSocket(puerto);
			s = ss.accept();
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			
			System.out.println("Conexion Exitosa");
			this.readLine();
		}catch(Exception e){
			this.closeServidor();
			e.printStackTrace();
		}		
	}
	
	public void writeLine(int pj){		
		try{
			oos.writeObject((Object)pj);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void readLine(){
		while(true){
			try{
				Object aux = ois.readObject();
				if(aux!=null){
					p.pj2.direccion = (int)aux;
					Thread t = new Thread(p.pj2);
					t.start();
				}
				Thread.sleep(15);
			}catch(Exception e){}	
			p.repaint();
		}
	}
	
	public void closeServidor(){
		try{
			oos.close();
			ois.close();
			s.close();
			ss.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
