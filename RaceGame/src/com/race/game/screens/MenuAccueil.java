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

public class MenuAccueil implements Screen {
	private MainGame game;// Instance de MyGame pour accéder aux méthodes intéressantes pour changer d'écran 
	private Stage stage;
	private TextureRegion background, boutonJouer, boutonTemps, boutonQuitter;
	private Image backgroundImg, boutonJouerImg, boutonTempsImg, boutonQuitterImg;

	public MenuAccueil(MainGame g) 
	{
		game = g;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		// Image de fond
		background = new TextureRegion(new Texture(Gdx.files.internal("data/background_accueil.png")));
		backgroundImg = new Image(background);
		backgroundImg.setWidth(Gdx.graphics.getWidth());
		backgroundImg.setHeight(Gdx.graphics.getHeight());
		
		// Boutons
		boutonJouer = new TextureRegion(new Texture(Gdx.files.internal("bouton/jouer.png")));
		boutonTemps = new TextureRegion(new Texture(Gdx.files.internal("bouton/temps.png")));
		boutonQuitter = new TextureRegion(new Texture(Gdx.files.internal("bouton/quitter.png")));

		boutonJouerImg = new Image(boutonJouer);
		boutonJouerImg.setX((float) (Gdx.graphics.getWidth() * 0.730));
		boutonJouerImg.setY((float) (Gdx.graphics.getHeight() * 0.75));
		boutonJouerImg.setWidth(Gdx.graphics.getWidth() / 5);
		boutonJouerImg.setHeight(Gdx.graphics.getHeight() / 7);
		boutonJouerImg.addListener(new ClickListener() 
		{
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				game.setScreen(new MenuChoixCircuit(game));
				return true;
			}
		});

		boutonTempsImg = new Image(boutonTemps);
		boutonTempsImg.setX((float) (Gdx.graphics.getWidth() * 0.730));
		boutonTempsImg.setY((float) (Gdx.graphics.getHeight() * 0.575));
		boutonTempsImg.setWidth(Gdx.graphics.getWidth() / 5);
		boutonTempsImg.setHeight(Gdx.graphics.getHeight() / 7);
		boutonTempsImg.addListener(new ClickListener() 
		{
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				System.exit(0);
				return true;
			}
		});

		boutonQuitterImg = new Image(boutonQuitter);
		boutonQuitterImg.setX((float) (Gdx.graphics.getWidth() * 0.730));
		boutonQuitterImg.setY((float) (Gdx.graphics.getHeight() * 0.40));
		boutonQuitterImg.setWidth(Gdx.graphics.getWidth() / 5);
		boutonQuitterImg.setHeight(Gdx.graphics.getHeight() / 7);
		boutonQuitterImg.addListener(new ClickListener() 
		{
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				System.exit(0);
				return true;
			}
		});

		stage.addActor(backgroundImg);
		stage.addActor(boutonJouerImg);
		stage.addActor(boutonTempsImg);
		stage.addActor(boutonQuitterImg);
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
