package com.donkeykong.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.donkeykong.game.Entities.Entity;
import com.donkeykong.game.Worlds.World;

public class Game extends ApplicationAdapter
{
	public static final int WIDTH = 224;
	public static final int HEIGHT = 256;
	public static final int SCALE = 3;
	
	SpriteBatch batch;
	World world;
	OrthographicCamera camera;
	
	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		world = new World(camera);
		
		camera.set
		world.addEntity(new Entity(50,50,50,50,"watever"));
	}

	@Override
	public void render ()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		world.draw(batch);
		world.drawHitbox();
		
	}
}
