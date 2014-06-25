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
import com.race.game.races.Bitume;

public class MenuAccueil implements Screen {
	private MainGame game;// Instance de MyGame pour accéder aux méthodes intéressantes pour changer d'écran 
	private Stage stage;
	private TextureRegion background, boutonJouer, title, boutonQuitter;
	private Image backgroundImg, boutonJouerImg, titleImg, boutonQuitterImg;

	public MenuAccueil(MainGame g) 
	{
		game = g;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		// Image de fond
		background = new TextureRegion(new Texture(Gdx.files.internal("data/background.jpg")));
		backgroundImg = new Image(background);
		backgroundImg.setWidth(Gdx.graphics.getWidth());
		backgroundImg.setHeight(Gdx.graphics.getHeight());
		
		// Buttons
		boutonJouer = new TextureRegion(new Texture(Gdx.files.internal("bouton/jouer.png")));
		title = new TextureRegion(new Texture(Gdx.files.internal("bouton/title.png")));
		boutonQuitter = new TextureRegion(new Texture(Gdx.files.internal("bouton/quitter.png")));
		
		// Display buttons
		titleImg = new Image(title);
		titleImg.setX((float) (Gdx.graphics.getHeight() * 0.08));
		titleImg.setY((float) (Gdx.graphics.getHeight() * 0.53));
		titleImg.setWidth((float) (Gdx.graphics.getHeight() * 1.5));
		titleImg.setHeight((float) (Gdx.graphics.getWidth() * 0.30));
		
		boutonJouerImg = new Image(boutonJouer);
		boutonJouerImg.setX((float) (Gdx.graphics.getWidth() * 0.28));
		boutonJouerImg.setY((float) (Gdx.graphics.getHeight() * -0.08));
		boutonJouerImg.setWidth((float) (Gdx.graphics.getHeight() * 0.75));
		boutonJouerImg.setHeight((float) (Gdx.graphics.getHeight() * 0.55));
		boutonJouerImg.addListener(new ClickListener() 
		{
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				game.setScreen(new Bitume(game));
				return true;
			}
		});
		
		boutonQuitterImg = new Image(boutonQuitter);
		boutonQuitterImg.setX((float) (Gdx.graphics.getWidth() * 0.40));
		boutonQuitterImg.setY((float) (Gdx.graphics.getHeight() * 0.315));
		boutonQuitterImg.setWidth((float) (Gdx.graphics.getWidth() * 0.20));
		boutonQuitterImg.setHeight((float) (Gdx.graphics.getHeight() * 0.20));
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
		stage.addActor(titleImg);
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
