package com.donkeykong.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.donkeykong.game.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Game.WIDTH * Game.SCALE;
		config.height = Game.HEIGHT * Game.SCALE;
		new LwjglApplication(new Game(), config);
	}
}
