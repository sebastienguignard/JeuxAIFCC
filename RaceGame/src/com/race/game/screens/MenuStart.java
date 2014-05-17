package com.race.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.race.game.MainGame;

public class MenuStart implements Screen {

    MainGame game;// Instance de MyGame pour accéder aux méthodes intéressantes pour changer d'écran 
    
    public MenuStart(MainGame g) {
        game = g;
    }
	@Override
	public void render(float delta) {
		  Gdx.gl.glClearColor(1, 0.2f, 0.4f, 1); // Notre jolie couleur rose :p
	        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	        game.setScreen(new MenuStart(game));
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
