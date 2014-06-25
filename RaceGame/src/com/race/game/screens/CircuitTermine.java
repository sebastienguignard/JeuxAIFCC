package com.race.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.race.game.MainGame;
import com.race.game.races.Snow;

public class CircuitTermine implements Screen {
	private MainGame game;// Instance de MyGame pour accéder aux méthodes intéressantes pour changer d'écran 
	private Stage stage;
	private TextureRegion background, boutonSuivant, boutonCircuits, boutonRetour;
	private Image backgroundImg, boutonSuivantImg, boutonCircuitsImg, boutonRetourImg;

	public CircuitTermine(MainGame g) 
	{
		game = g;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		// Image de fond
		background = new TextureRegion(new Texture(Gdx.files.internal("data/finish.png")));
		backgroundImg = new Image(background);
		backgroundImg.setWidth((float) (Gdx.graphics.getWidth()));
		backgroundImg.setHeight((float) (Gdx.graphics.getHeight()));
		
		// Buttons
		boutonCircuits = new TextureRegion(new Texture(Gdx.files.internal("bouton/circuits.png")));
		boutonRetour = new TextureRegion(new Texture(Gdx.files.internal("bouton/retour.png")));
		
		boutonCircuitsImg = new Image(boutonCircuits);
		boutonCircuitsImg.setX((float) (Gdx.graphics.getWidth() * 0.33));
		boutonCircuitsImg.setY((float) (Gdx.graphics.getHeight() * -0.02));
		boutonCircuitsImg.setWidth((float) (Gdx.graphics.getHeight() * 0.6));
		boutonCircuitsImg.setHeight((float) (Gdx.graphics.getHeight() * 0.5));
		boutonCircuitsImg.addListener(new ClickListener() 
		{ 
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				game.setScreen(new MenuChoixCircuit(game));
				return true;
			}
		});
		
		boutonRetourImg = new Image(boutonRetour);
		boutonRetourImg.setX((float) (Gdx.graphics.getWidth() * 0.415));
		boutonRetourImg.setY((float) (Gdx.graphics.getHeight() * 0.33));
		boutonRetourImg.setWidth((float) (Gdx.graphics.getHeight() * 0.3));
		boutonRetourImg.setHeight((float) (Gdx.graphics.getHeight() * 0.2));
		boutonRetourImg.addListener(new ClickListener() 
		{
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				game.setScreen(new MenuAccueil(game));
				return true;
			}
		});
		
		stage.addActor(backgroundImg);;
		stage.addActor(boutonCircuitsImg);
		stage.addActor(boutonRetourImg);
	}
	
	@Override
	public void render(float delta) {
		stage.draw();
	}

	@Override
	public void dispose() {
		stage.dispose();
		game.dispose();
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
}
