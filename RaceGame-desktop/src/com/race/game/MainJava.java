package com.race.game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class MainJava {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "RaceGame";
		cfg.useGL20 = true;
		cfg.width = 790;
		cfg.height = 480;
		
		new LwjglApplication(new MainGame(), cfg);
	}
}
