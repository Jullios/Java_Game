package Game;

import java.util.Random;

import jplay.GameImage;
import jplay.GameObject;

import jplay.Keyboard;
import jplay.Scene;
import jplay.URL;
import jplay.Window;

public class Cenario1 extends Cenario{
	
	
	private Random rand = new Random();
	private int x, y;
	public Window janela;
	private Scene cena;
	private Jogador jogador;
	private Keyboard teclado;
	private Enemy1 enemy1[];
	private Enemy2 enemy2[];
	boolean td = false;
	
	private int deadEnemy[] = new int[10];
	
	public Cenario1(Window window){	
		
		janela = window;
		cena = new Scene();
		cena.loadFromFile(URL.scenario("cenario1.scn"));
		jogador = new Jogador(640, 350);
		teclado = janela.getKeyboard();
		enemy1 = new Enemy1[10];
		enemy2 = new Enemy2[10];
		Som.play("mus.mid");
		run();
		
		
	}
	
	private void run(){
		
		for(int i = 0; i < enemy1.length; i++)
		{
			x = rand.nextInt(700);
			y = rand.nextInt(500);
			enemy1[i] = new Enemy1(x, y);
			x = rand.nextInt(800);
			y = rand.nextInt(600);
			enemy2[i] = new Enemy2(x, y);
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
			
			
			
			jogador.energia(janela);
			
			janela.update();
			janela.delay(5);
			mudarCenario(td);
		}
		
	}

	
	private void mudarCenario(boolean td){
		if(tileCollision(03, jogador, cena) == true && td == true)
		{
			new Cenario2(janela);
		}
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
