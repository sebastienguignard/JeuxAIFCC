package com.race.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.race.game.MainGame;
import com.race.game.races.Bitume;
import com.race.game.races.Desert;
import com.race.game.races.Rallye;
import com.race.game.races.Snow;

public class MenuChoixCircuit implements Screen {
	
	private MainGame game;
	private Stage stage;
	private TextureRegion background, title, bitume, desert, rallye, snow;
	private Image backgroundImg, titleImg, bitumeImg, desertImg, rallyeImg, snowImg;

	public MenuChoixCircuit(MainGame g) {
		game = g;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		// Image de fond
		background = new TextureRegion(new Texture(Gdx.files.internal("data/background_race_select.png")));
		backgroundImg = new Image(background);
		backgroundImg.setWidth(Gdx.graphics.getWidth());
		backgroundImg.setHeight(Gdx.graphics.getHeight());
		
		// Titre
		title = new TextureRegion(new Texture(Gdx.files.internal("data/titre.png")));
		titleImg = new Image(title);
		titleImg.setX((float) (Gdx.graphics.getWidth() * 0.20));
		titleImg.setY((float) (Gdx.graphics.getHeight() * 0.75));
		titleImg.setWidth(512);
		titleImg.setHeight(64);
		
		bitume = new TextureRegion(new Texture(Gdx.files.internal("maps/bitume/bitume_select_race.jpg")));
		bitumeImg = new Image(bitume);
		bitumeImg.setX((float) (Gdx.graphics.getWidth() *0.1));
		bitumeImg.setY((float) (Gdx.graphics.getHeight() / 3));
		bitumeImg.setWidth(150);
		bitumeImg.setHeight(150);
		bitumeImg.addListener(new ClickListener() 
		{
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				game.setScreen(new Bitume());
				return true;
			}
		});
		
		desert = new TextureRegion(new Texture(Gdx.files.internal("maps/desert/desert_select_race.jpg")));
		desertImg = new Image(desert);
		desertImg.setX((float) (Gdx.graphics.getWidth() *0.3));
		desertImg.setY((float) (Gdx.graphics.getHeight() / 3));
		desertImg.setWidth(150);
		desertImg.setHeight(150);
		desertImg.addListener(new ClickListener() 
		{
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				game.setScreen(new Desert());
				return true;
			}
		});
		
		rallye = new TextureRegion(new Texture(Gdx.files.internal("maps/rallye/rallye_select_race.jpg")));
		rallyeImg = new Image(rallye);
		rallyeImg.setX((float) (Gdx.graphics.getWidth() * 0.5));
		rallyeImg.setY((float) (Gdx.graphics.getHeight() / 3));
		rallyeImg.setWidth(150);
		rallyeImg.setHeight(150);
		rallyeImg.addListener(new ClickListener() 
		{
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				game.setScreen(new Rallye());
				return true;
			}
		});
		
		snow = new TextureRegion(new Texture(Gdx.files.internal("maps/snow/snow_select_race.jpg")));
		snowImg = new Image(snow);
		snowImg.setX((float) (Gdx.graphics.getWidth() * 0.7));
		snowImg.setY((float) (Gdx.graphics.getHeight() / 3));
		snowImg.setWidth(150);
		snowImg.setHeight(150);
		snowImg.addListener(new ClickListener() 
		{
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				game.setScreen(new Snow());
				return true;
			}
		});
		
		stage.addActor(backgroundImg);
		stage.addActor(titleImg);
		stage.addActor(bitumeImg);
		stage.addActor(desertImg);
		stage.addActor(rallyeImg);
		stage.addActor(snowImg);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1,1,1,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
		game.dispose();
	}

}
