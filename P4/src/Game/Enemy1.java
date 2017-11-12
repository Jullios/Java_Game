package Game;

import jplay.URL;

public class Enemy1 extends Ator {
	
//	private double ataque = 1;

	public Enemy1(int x, int y) { //parametros são a posição
		super(URL.sprite("spider01.png"), 26);
		this.x = x;
		this.y = y;
		this.setTotalDuration(2000);
		this.velocidade = 0.3;
		this.ataque = 1;
	}
	
	public void follow(double x, double y){
		
		if(this.x > x && this.y <= y + 50 && this.y >= y - 50)
		{
			moveTo(x, y, velocidade);
			if(direcao != 1)
			{
				setSequence(7, 12); // mudar
				direcao = 1;
			}
			movendo = true;
		}
		
		else if(this.x < x && this.y <= y + 50 && this.y >= - 50)
		{
			moveTo(x, y, velocidade);
			if(direcao != 2)
			{
				setSequence(19, 24);
				direcao = 2;
			}
			movendo = true;
		}
		
		else if(this.y < y)
		{
			moveTo(x, y, velocidade);
			if(direcao != 4)
			{
				setSequence(13, 18);
				direcao = 4;
			}
			movendo = true;
		}
		
		else if(this.y > y)
		{
			moveTo(x, y, velocidade);
			if(direcao != 5)
			{
				setSequence(1, 6);
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
		if(energia <= 0){
			setSequence(25, 26);
			velocidade = 0;
			ataque = 0;
			direcao = 0;
			movendo = false;
			
		}
	}
	
	
	
		

}
