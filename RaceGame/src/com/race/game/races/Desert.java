package com.race.game.races;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Desert extends Actor implements Screen 
{
	private Stage stage;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Touchpad touchpad;
	private TouchpadStyle touchpadStyle;
	private Skin touchpadSkin;
	private Drawable touchBackground;
	private Drawable touchKnob;
	private Texture carTxt;
	private Sprite car;
	private float carSpeedX, carSpeedY;

	public Desert() {

		batch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		TouchPad();

		//Create block sprite
		carTxt = new Texture(Gdx.files.internal("cars/car4.png"));
		car = new Sprite(carTxt);
		car.setSize(135, 90);
		car.setPosition((float) (Gdx.graphics.getWidth() * 0.47) , (float) (Gdx.graphics.getHeight()));
		carSpeedX = 15;
		carSpeedY = 9;
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.addActor(touchpad);

		// Level
		map = new TmxMapLoader().load("maps/rallye/rallye.tmx");
		map.getLayers();
		renderer = new OrthogonalTiledMapRenderer(map);

		// Camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		camera.update();

	}

	@Override
	public void render(float delta) {

		// Initialisation
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Move the car with TouchPad
		car.setX(car.getX() + touchpad.getKnobPercentX()*carSpeedX);
		car.setY(car.getY() + touchpad.getKnobPercentY()*carSpeedY);
		
		// to follow the player
		camera.position.set(car.getX() + 300, car.getY(), 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		// Render the map and set the camera
		renderer.setView(camera);
		renderer.render();

		// Render images  
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		batch.begin();
		car.draw(batch);
		batch.end();   

//		final TiledMapTileLayer collision = (TiledMapTileLayer) map.getLayers().get(0);
//		final int tileX = (int)(car.getX()/32); // taille de la tuile 32 * 32 
//		final int tileY = (int)(car.getY()/32);

//		if(collision.getCell(tileX, tileY).getTile().getProperties().containsKey("limit"))
//		{
//			car.setX((float) (Gdx.graphics.getWidth() * 0.50));
//			car.setY((float) (Gdx.graphics.getHeight() * 0.35));
//		}
	}

	public void TouchPad() {

		//Create a touchpad skin	
		touchpadSkin = new Skin();
		//Set background image
		touchpadSkin.add("touchBackground", new Texture("data/touchBackground.png"));
		//Set knob image
		touchpadSkin.add("touchKnob", new Texture("data/touchKnob.png"));
		//Create TouchPad Style
		touchpadStyle = new TouchpadStyle();
		//Create Drawable's from TouchPad skin
		touchBackground = touchpadSkin.getDrawable("touchBackground");
		touchKnob = touchpadSkin.getDrawable("touchKnob");
		//Apply the Drawables to the TouchPad Style
		touchpadStyle.background = touchBackground;
		touchpadStyle.knob = touchKnob;
		//Create new TouchPad with the created style
		touchpad = new Touchpad(10, touchpadStyle);
		//setBounds(x,y,width,height)
		touchpad.setBounds(15, 15, 150, 150);	
	}
	@Override
	public void resize(int width, int height) {
		float aspectRatio = (float) width / (float) height;
		camera = new OrthographicCamera(550f * aspectRatio, 550f);
	}

	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();
		touchpadSkin.dispose();
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
