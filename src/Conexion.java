import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Conexion extends JFrame implements ActionListener{

	private Motor m;
	private boolean s;
	private JTextField texto_n,texto_p;
	private JButton servidor,cliente;
	private JLabel nombre,puerto,mensaje;
	public Servidor sv;
	public Cliente ct;
	
	public Conexion(Motor m){
		super("Conexion");
		this.m = m;
		this.setLayout(new GridBagLayout());
		this.definirVentana();
		
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setSize(200,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}
	
	private void definirVentana(){
		texto_n = new JTextField(8);
		texto_p = new JTextField(4);
		servidor = new JButton("Servidor");
		cliente = new JButton("Cliente");
		nombre = new JLabel(); 
		puerto = new JLabel();
		mensaje = new JLabel();
		nombre.setText("Nombre ");
		puerto.setText("Puerto ");
		this.add(nombre);
		this.add(texto_n);
		this.add(puerto);
		this.add(texto_p);
		this.add(servidor);
		this.add(cliente);
		this.add(mensaje);
		servidor.addActionListener(this);
		cliente.addActionListener(this);	
		
		definirLayout();
	}
	
	private void definirLayout(){
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;		
		gbc.gridy = 0;		
		gbc.gridwidth = 1;	
		gbc.gridheight = 1;	
		gbc.weightx = 0.0;	
		gbc.weighty = 0.0;	
		gbc.fill = GridBagConstraints.NONE;
		add(nombre,gbc);	
		gbc.gridx = 2;		
		gbc.gridy = 0;		
		gbc.gridwidth = 2;	
		gbc.gridheight = 1;	
		gbc.weightx = 0.0;	
		gbc.weighty = 0.0;	
		gbc.fill = GridBagConstraints.NONE;
		add(texto_n,gbc);		
		gbc.gridx = 0;		
		gbc.gridy = 1;		
		gbc.gridwidth = 1;	
		gbc.gridheight = 1;	
		gbc.weightx = 0.0;	
		gbc.weighty = 0.0;	
		gbc.fill = GridBagConstraints.NONE;
		add(puerto,gbc);		
		gbc.gridx = 2;		
		gbc.gridy = 1;		
		gbc.gridwidth = 1;	
		gbc.gridheight = 1;	
		gbc.weightx = 0.0;	
		gbc.weighty = 0.0;	
		gbc.fill = GridBagConstraints.NONE;
		add(texto_p,gbc);		
		gbc.gridx = 0;		
		gbc.gridy = 3;		
		gbc.gridwidth = 2;	
		gbc.gridheight = 1;	
		gbc.weightx = 0.0;	
		gbc.weighty = 0.0;	
		gbc.fill = GridBagConstraints.NONE;
		add(servidor,gbc);		
		gbc.gridx = 2;		
		gbc.gridy = 3;		
		gbc.gridwidth = 2;	
		gbc.gridheight = 1;	
		gbc.weightx = 0.0;	
		gbc.weighty = 0.0;	
		gbc.fill = GridBagConstraints.NONE;
		add(cliente,gbc);
		gbc.gridx = 0;		
		gbc.gridy = 4;		
		gbc.gridwidth = 4;	
		gbc.gridheight = 1;	
		gbc.weightx = 0.0;	
		gbc.weighty = 0.0;	
		gbc.fill = GridBagConstraints.NONE;
		add(mensaje,gbc);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==servidor){
			if(texto_p!=null) {
				this.s = true;
				Integer num = Integer.parseInt(texto_p.getText().substring(0,texto_p.getText().length()));
				sv = new Servidor(m,num);
				Thread th = new Thread(sv);
				th.start();
				System.out.println("servidor");
			}
			this.dispose();
		}else if(e.getSource()==cliente){
			if(texto_p!=null) {
				this.s = false;
				Integer num = Integer.parseInt(texto_p.getText().substring(0,texto_p.getText().length()));
				ct = new Cliente(m,num);
				Thread th = new Thread(ct);
				th.start();
				System.out.println("cliente");
			}
			this.dispose();
		}
		
	}
	
	public boolean esServidor(){
		return s;
	}
	
}
