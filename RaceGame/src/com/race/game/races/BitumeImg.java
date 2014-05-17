package com.race.game.races;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class BitumeImg extends Actor implements Screen 
{
	// Map
	private Stage stage;

	// Elements
	private Image map, car, accelerateur;

	public BitumeImg() {

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		
		// Level
		map = new Image(new Texture("maps/bitume.png"));
		map.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// Player 
		car = new Image(new Texture("cars/car1.png"));
		car.setSize((float) Gdx.graphics.getWidth() / 35  , Gdx.graphics.getHeight() / 15 );
		car.setPosition((float) (Gdx.graphics.getWidth() * 0.080) , (float) (Gdx.graphics.getHeight() * 0.40));

		// Player Interface
		accelerateur = new Image(new Texture("data/accelerer.png"));
		accelerateur.setSize(40, 60);
		accelerateur.setPosition(720, 0);
		accelerateur.addListener(new InputListener() 
		{
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				car.setY(car.getY() + 2);
				return true;
			}
		});

		stage.addActor(map);
		stage.addActor(car);
		stage.addActor(accelerateur);
	}

	@Override
	public void render(float delta) {

		// Initialisation
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Render images
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
}

