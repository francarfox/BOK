import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Panel extends JPanel implements KeyListener{

	private BufferedImage image,image2;
	public Personaje pj,pj2;
	private Motor m;
	
	
	public Panel(Motor m){
		this.pj = new Personaje(200,200,0,m);
		this.pj2 = new Personaje(100,100,0,m);
		this.m = m;
		this.setSize(600,600);
		this.cargarImage();
	}
	
	private void cargarImage() {
		try{
			image = ImageIO.read(getClass().getClassLoader().getResource("personaje.png"));
			image2 = ImageIO.read(getClass().getClassLoader().getResource("personaje2.png"));
		}catch(Exception e){}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//dibujo
		if(pj!=null && pj2!=null){
			g.drawImage(image, pj.x, pj.y, pj.x+33, pj.y+48, pj.cuadro*33, pj.direccion*48, pj.cuadro*33+33, pj.direccion*48+48, null);
			g.drawImage(image2, pj2.x, pj2.y, pj2.x+33, pj2.y+48, pj2.cuadro*33, pj2.direccion*48, pj2.cuadro*33+33, pj2.direccion*48+48, null);
		}
		//loop
		try{
			Thread.sleep(15);
		}catch(Exception e){}
		this.repaint();
	}

	
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_DOWN:
		{	if(pj.y+4<600-2*48)
				this.desplazamiento(0);
			break;
		}
		case KeyEvent.VK_LEFT:
		{	if(pj.x-4>0)
				this.desplazamiento(1);
			break;
		}
		case KeyEvent.VK_RIGHT:
		{	if(pj.x+4<600-2*33)
				this.desplazamiento(2);
			break;
		}
		case KeyEvent.VK_UP:
		{	if(pj.y-4>0)
				this.desplazamiento(3);
			break;
		}/*
		case KeyEvent.VK_S:
		{	this.desplazamiento(0);
			break;
		}
		case KeyEvent.VK_A:
		{	this.desplazamiento(1);
			break;
		}
		case KeyEvent.VK_D:
		{	this.desplazamiento(2);
			break;
		}
		case KeyEvent.VK_W:
		{	this.desplazamiento(3);
			break;
		}*/
		}
	}
	
	private void desplazamiento(int n){
		if(m.obtenerConexion().esServidor()){
			pj.direccion = n;
			Thread t = new Thread(pj);
			t.start();
			m.obtenerConexion().sv.writeLine(pj.direccion);
		}else{
			pj2.direccion = n;
			Thread t = new Thread(pj2);
			t.start();
			m.obtenerConexion().ct.writeLine(pj2.direccion);
		}
	}
		
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub	
	}

	
}
