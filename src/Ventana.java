import java.awt.*;
import javax.swing.*;


public class Ventana extends JFrame{

	private Motor m;
	private Panel p;
	
	public Ventana(Motor m){
		super("Movimiento2");
		this.m = m;
		this.setLayout(null);
		this.setSize(600,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p = new Panel(m);
		this.add(p);
		
		this.addKeyListener(p);
		
		this.setVisible(true);
	}
	
	public Panel obtenerPanel(){
		return p;
	}
	
	
}
