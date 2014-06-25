package com.race.game;

import com.badlogic.gdx.Game;
import com.race.game.races.Desert;
import com.race.game.screens.MenuAccueil;

public class MainGame extends Game {
	MenuAccueil MenuAccueil;
	
	@Override
	public void create() 
	{	
		MenuAccueil = new MenuAccueil(this);
        setScreen(MenuAccueil); 
	}
	
	public void render(){
		super.render();
	}
	
	public void dispose(){
		super.dispose();
	}
}