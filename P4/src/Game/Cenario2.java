package Game;

import java.util.Random;

import jplay.Keyboard;
import jplay.Scene;
import jplay.URL;
import jplay.Window;

public class Cenario2 extends Cenario{
	
	private Random rand = new Random();
	private int x, y;
	public Window janela;
	private Scene cena;
	private Jogador jogador;
	private Keyboard teclado;
	private Enemy1 enemy1[];
	private Enemy2 enemy2[];
	private Enemy4 enemy4[];
	boolean td = false;
	boolean td1 = false;
	
	public Cenario2(Window window){
		
		janela = window;
		cena = new Scene();
		cena.loadFromFile(URL.scenario("Cenario2.scn"));
		jogador = new Jogador(750, 550);
		teclado = janela.getKeyboard();
		enemy1 = new Enemy1[10];
		enemy2 = new Enemy2[10];
		enemy4 = new Enemy4[5];
		Som.play("mus.mid");
		run();
	}
	
private void run(){
	
		for(int i = 0; i < enemy1.length; i++)
		{
			x = rand.nextInt(700);
			y = rand.nextInt(500);
			enemy1[i] = new Enemy1(x, y);
			x = rand.nextInt(700);
			y = rand.nextInt(500);
			enemy2[i] = new Enemy2(x, y);
		}
		
		for(int i = 0; i < enemy4.length; i++)
		{
			x = rand.nextInt(700);
			y = rand.nextInt(500);
			enemy4[i] = new Enemy4(x, y);
		}
	
		
		
		while(true){
			
			
			jogador.controle(janela, teclado);
			jogador.caminho(cena);
			
			
			cena.moveScene(jogador);
			jogador.x += cena.getXOffset();
			jogador.y += cena.getYOffset();
			
			jogador.draw();	
			
			for(int i = 0; i < enemy1.length; i++)
			{
				enemy1[i].caminho(cena);
				enemy1[i].follow(jogador.x, jogador.y);
				enemy1[i].x += cena.getXOffset();
				enemy1[i].y += cena.getYOffset();
				enemy1[i].draw();
				jogador.atirar(janela, cena, teclado, enemy1[i]);
				enemy1[i].atacar(jogador, janela);
				
				enemy2[i].caminho(cena);
				enemy2[i].follow(jogador.x, jogador.y);
				enemy2[i].x += cena.getXOffset();
				enemy2[i].y += cena.getYOffset();
				enemy2[i].draw();
				jogador.atirar(janela, cena, teclado, enemy2[i]);
				enemy2[i].atacar(jogador, janela);
				td = todosMortos(enemy1, enemy2);
			}
			
			for(int i= 0; i < enemy4.length; i++)
			{
				enemy4[i].caminho(cena);
				enemy4[i].follow(jogador.x, jogador.y);
				enemy4[i].x += cena.getXOffset();
				enemy4[i].y += cena.getYOffset();
				enemy4[i].draw();
				jogador.atirar(janela, cena, teclado, enemy4[i]);
				enemy4[i].atacar(jogador, janela);
				td1 = todosMortos2(enemy4);
			}
						
			
			jogador.energia(janela);
			
			janela.update();
			janela.delay(5);
			
			mudarCenario(td, td1);
		}
	}

	private void mudarCenario(boolean td, boolean td1){
		if(tileCollision(3, jogador, cena) == true && td == true && td1 == true)
		{
			new Cenario3(janela);	//03
		}
	}
	
	private boolean todosMortos2(Enemy4 enemy4[])
			{
				for(int i = 0; i < enemy4.length; i++)if(enemy4[i].energia > 0)return false;
				return true;
			}
	
	private boolean todosMortos(Enemy1 enemy1[], Enemy2 enemy2[]){
		for(int i = 0; i < enemy1.length; i++)
		{
			if(enemy1[i].energia > 0)return false;
		}
		for(int i = 0; i < enemy2.length; i++)
		{
			if(enemy2[i].energia > 0)return false;
		}
		return true;
	}
	

}
