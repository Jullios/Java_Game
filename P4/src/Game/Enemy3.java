package Game;

import jplay.URL;

public class Enemy3 extends Ator {
	
//	private double ataque = 5;

	public Enemy3(int x, int y) {
		super(URL.sprite("demonn01.png"), 13);
		this.x = x;
		this.y = y;
		this.setTotalDuration(2000);
		this.velocidade = 0.5;
		this.ataque = 3;

	}
	
public void follow(double x, double y){
		
		if(this.x > x && this.y <= y + 50 && this.y >= y - 50)		//<-
		{
			moveTo(x, y, velocidade);
			if(direcao != 1)
			{
				setSequence(10, 12); // mudar
				direcao = 1;
			}
			movendo = true;
		}
		
		else if(this.x < x && this.y <= y + 50 && this.y >= - 50)		//->
		{
			moveTo(x, y, velocidade);
			if(direcao != 2)
			{
				setSequence(7, 9);
				direcao = 2;
			}
			movendo = true;
		}
		
		else if(this.y < y)		//baixo
		{
			moveTo(x, y, velocidade);
			if(direcao != 4)
			{
				setSequence(1,3);
				direcao = 4;
			}
			movendo = true;
		}
		
		else if(this.y > y)		//cima
		{
			moveTo(x, y, velocidade);
			if(direcao != 5)
			{
				setSequence(4, 6);
				direcao = 5;
			}
			movendo = true;
		}
		
		this.morrer();
		
		if(movendo)
		{
			update();
			movendo = false;
		}
		
	}
	
	public void morrer(){
		if(this.energia <= 0){
			setSequence(12, 13);
			this.velocidade = 0;
			this.ataque = 0;
			this.direcao = 0;
			this.movendo = false;
			
		}
	}
	

}
