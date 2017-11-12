package Game;

import jplay.GameObject;
import jplay.TileInfo;

public class Controle {
	
	public boolean colisao(GameObject obj, TileInfo tile){

		if((tile.id >= 5 )&& obj.collided(tile))
		{
			return true;
		}
		return false;
	}

}
