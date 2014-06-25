package com.race.game.races;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.race.game.MainGame;
import com.race.game.screens.SuivantR;

public class Desert extends Actor implements Screen 
{
	private MainGame game;
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
	private Rectangle carRec;
	private Vector2 carRecPosition;
	private ShapeRenderer sr = new ShapeRenderer();
	private MapObjects CollisionLayer;
	private TiledMapTileLayer mapLayer, SandaLayer, SandbLayer, EndLayer;
	private int tileX, tileY;
	private float posIniX, posIniY;
	private String timeTxt;
	BitmapFont displayTime;
	long sec = 0, min = 0;
	private boolean isCollided;

	public Desert(MainGame g) {

		game = g;
		batch = new SpriteBatch();
		stage = new Stage();
		displayTime = new BitmapFont();
		Gdx.input.setInputProcessor(stage);
		stage.act(Gdx.graphics.getDeltaTime());
		TouchPad();
		stage.addActor(touchpad);

		// Level
		map = new TmxMapLoader().load("maps/desert/Desert.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);

		// Camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		camera.update();

		//Create the car Sprite
		carTxt = new Texture(Gdx.files.internal("cars/desert.png"));
		car = new Sprite(carTxt);
		car.setSize(210, 160);
		posIniX = (float) (Gdx.graphics.getWidth() * 0.85);
		posIniY = (float) (Gdx.graphics.getHeight() * 1.95);
		car.setPosition(posIniX, posIniY);

		mapLayer = (TiledMapTileLayer) map.getLayers().get("Map");
		EndLayer = (TiledMapTileLayer) map.getLayers().get("End Line");
		SandaLayer = (TiledMapTileLayer) map.getLayers().get("Sand_a");	
		SandbLayer = (TiledMapTileLayer) map.getLayers().get("Sand_b");	
		CollisionLayer = map.getLayers().get("Collision").getObjects();
		isCollided = false;	
	}

	@Override
	public void render(float delta) {

		// Initialisation
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// to follow the player
		camera.position.set(car.getX() + 400, car.getY(), 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		sr.setProjectionMatrix(camera.combined);

		// Render the map and set the camera
		renderer.setView(camera);
		renderer.render();


		// Render images  
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		batch.begin();

		// Render the chronometer
		Chronometer();
		displayTime.setScale(5);
		displayTime.draw(batch, timeTxt, car.getX() + 800, car.getY() + 400);

		car.draw(batch);
		batch.end();

		// Collision rectangle movement
		carRecPosition = new Vector2();
		carRecPosition.x = car.getX() + 160;
		carRecPosition.y = car.getY() + 35;
		// Outline of the car (for collision)
		carRec = new Rectangle(carRecPosition.x, carRecPosition.y, 30, 90);
		checkCollision();
	}

	public void moveTheCar(float i, float j) 
	{
		car.setX((float) (car.getX() + touchpad.getKnobPercentX()*i));
		car.setY((float) (car.getY() + touchpad.getKnobPercentY()*j));
	}

	public void checkCollision() 
	{
		tileX = (int)((car.getX() + 85) /32);
		tileY = (int)((car.getY() + 15) /32);

		if(!isCollided) 
		{	
			moveTheCar(13,5);
			for (MapObject Object : CollisionLayer) 
			{
				if (Object instanceof RectangleMapObject)
				{
					Rectangle rec = ((RectangleMapObject) Object).getRectangle();
//													sr.begin(ShapeType.Filled);
//													sr.rect(rec.x, rec.y, rec.width, rec.height);
//													sr.rect(carRec.x, carRec.y, carRec.width, carRec.height);
//													sr.end();


					if (Intersector.overlaps(carRec, rec))
					{
						isCollided = true;
					}
				}
			}
			if (mapLayer.getCell(tileX, tileY).getTile().getProperties().containsKey("slow"))
			{
				moveTheCar(-12,-4);
			}
			if(SandaLayer.getCell(tileX, tileY) != null)
			{
				moveTheCar(-8,-4);
			}
			if(SandbLayer.getCell(tileX, tileY) != null)
			{
				moveTheCar(-5,-2);
			}
			if(EndLayer.getCell(tileX, tileY) != null)
			{
				game.setScreen(new SuivantR(game));
			}
		}
		else if (isCollided)
		{
			car.setX(car.getX() - 12);
			isCollided = false;
		}
	}



	public String Chronometer() {
		long time = System.nanoTime();
		if (time >= 1) {
			sec++;
			if (sec == 6000) {
				sec = 0;
				min++;
			}
		}
		return timeTxt = min + " : " + sec/10;
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
		touchpad.setBounds(15, 15, (float) (Gdx.graphics.getWidth() * 0.25), (float) (Gdx.graphics.getHeight() * 0.40));
	}

	@Override
	public void resize(int width, int height) {
		float aspectRatio = (float) width / (float) height;
		camera = new OrthographicCamera(850 * aspectRatio, 850);
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
