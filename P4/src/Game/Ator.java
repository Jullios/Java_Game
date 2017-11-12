package Game;

import java.awt.Point;
import java.util.Vector;

import jplay.GameImage;
import jplay.GameObject;
import jplay.Keyboard;
import jplay.Scene;
import jplay.Sprite;
import jplay.TileInfo;
import jplay.URL;
import jplay.Window;

public class Ator extends Sprite {
	
	public double ataque = 1;
	
	GameImage imag = new GameImage(URL.sprite("yad.png"));
	
	
	Keyboard teclado;

	Controle c = new Controle();

	double velocidade = 1;
	int direcao = 3;
	boolean movendo = false;
	
	public double energia = 1000;
	
	public Ator(String fileName, int numFrames) {
		super(fileName, numFrames);
		
	}
	
	public void caminho(Scene cena){	
		
		Point min = new Point((int)this.x, (int)this.y);  
		Point max = new Point((int)this.x + this.width, (int)this.y + this.height);
		
		Vector<?>tiles = cena.getTilesFromPosition(min,max);
			
		
		for(int i = 0; i < tiles.size(); i++)
		{
			
			TileInfo tile = (TileInfo) tiles.elementAt(i);
			if(c.colisao(this, tile) == true)
			{
				if(colisaoVertical(this, tile))
				{
					if(tile.y + tile.height - 2 < this.y)
					{
						this.y = tile.y + tile.height;
					}
					
					else if(tile.y > this.y + this.height - 2)
					{
						this.y = tile.y - this.height;
					}
				}
				if(colisaoHorizontal(this, tile))
				{
					if(tile.x > this.x + this.width - 2)
					{
						this.x = tile.x - this.width;
					}
					else //if(tile.x + tile.width < this.x)
					{
						this.x = tile.x + tile.width;
					}
				}
			}
			
		}
		 
	}

	public boolean colisaoVertical(GameObject obj, GameObject obj2){
	
		
		if(obj2.x + obj2.width <= obj.x)
			{
				return false;
			}
		
		if(obj.x + obj.width <= obj2.x)
			{
				return false;
			}
		
		return true;
	
	}
	
	public boolean colisaoHorizontal(GameObject obj, GameObject obj2){
		
		if(obj2.y + obj2.height <= obj.y)
			{
				return false;
			}
		
		if(obj.y + obj.height <= obj2.y)
			{
				return false;
			}
		
		return true;
		
	}
	
	public void atacar(Jogador jogador, Window janela){
		
		teclado = janela.getKeyboard();
		
		if(this.collided(jogador)){
			Jogador.energia -= this.ataque;
			
		}
		if(Jogador.energia <= 0)
		{
			while(true){
				imag.draw();
				janela.update();
				if(teclado.keyDown(Keyboard.ENTER_KEY))
				{
					System.exit(0);
				}
			}
			
		}
	}

}
