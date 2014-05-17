package com.race.game.races;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Desert extends Actor implements Screen 
{
	// Map
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	
	// Elements
	private Image car;

	public Desert() {
		
		// Level
		map = new TmxMapLoader().load("maps/desert/desert.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		
		// Camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 960);
		camera.update();

		// Player 
		car = new Image(new Texture("cars/car1.png"));
		car.setSize((float) Gdx.graphics.getWidth() / 30  , Gdx.graphics.getHeight() / 8 );
		car.setPosition((float) (Gdx.graphics.getWidth() * 0.13) , (float) (Gdx.graphics.getHeight() * 0.8));
		
	}
	
	@Override
	public void render(float delta) {

		// Initialisation
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// set the tile map renderer view based on what the camera sees and render the map
		renderer.setView(camera);
		renderer.render();

		// Render images
		renderer.getSpriteBatch().begin();
		car.draw(renderer.getSpriteBatch(), 1);
		renderer.getSpriteBatch().end();

		final TiledMapTileLayer collision = (TiledMapTileLayer) map.getLayers().get(0);
		final int tileX = (int)(car.getX()/32); // taille de la tuile 32 * 32 
		final int tileY = (int)(car.getY()/32);

		if(collision.getCell(tileX, tileY).getTile().getProperties().containsKey("blocked"))
		{
			car.setX((float) (Gdx.graphics.getWidth() * 0.13));
			car.setY((float) (Gdx.graphics.getHeight() * 0.8));
		}
		else {
			InputListener();
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		InputListener();
	}

	public void InputListener() {
		
		/**** commandes du joueur *****/
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			car.setY(car.getY() + 2);
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			car.setY(car.getY() - 2);
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
		}
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
		map.dispose();
		renderer.dispose();
	}

}
