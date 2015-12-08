
public class Personaje extends Thread implements Runnable{

	public int cuadro;
	public int x,y;
	public int direccion;
	private Motor m;
	
	public Personaje(int x,int y,int direccion,Motor m){
		this.cuadro = 0;
		this.x = x;
		this.y = y;
		this.m = m;
		//this.start();
	}
	
	public void run(){
		for(int i=0;i<4;i++){
			try{
				Thread.sleep(70);
			}catch(Exception e){}
			cuadro++;
			//manejo cuadro
			if(cuadro>=4){
				cuadro = 0;
			}
			//desplazamiento
			switch(direccion){
			case 0:
				y++;
				break;
			case 1:
				x--;
				break;
			case 2:
				x++;
				break;
			case 3:
				y--;
				break;
			}

		}
		if(cuadro!=0)
			cuadro = 0;
	}
	
	
}