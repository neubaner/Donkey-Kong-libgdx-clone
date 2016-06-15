package com.donkeykong.game.Worlds;

import java.util.ArrayList;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.donkeykong.game.Entities.Entity;

public class World implements InputProcessor	
{
	protected ArrayList<Entity> entities = new ArrayList<Entity>();
	protected OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	public boolean shouldDrawHitbox = true;
	
	public World(OrthographicCamera camera)
	{
		this.camera = camera;
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);
	}
	
	public void update(float dt)
	{
		for(Entity e : entities)
		{
			if(e != null)
				e.update(dt);
		}
	}
	
	public void draw(Batch batch)
	{
		batch.begin();
		for(Entity e : entities)
		{
			if(e != null)
				e.draw(batch);
		}
		batch.end();
	}
	
	public void drawHitbox()
	{
		if(shouldDrawHitbox)
		{
			shapeRenderer.setProjectionMatrix(camera.combined);
			shapeRenderer.begin(ShapeType.Line);
			for(Entity e : entities)
			{
				if(e != null)
					e.drawHitbox(shapeRenderer);
			}
			shapeRenderer.end();
		}
	}
	
	public Entity addEntity(Entity e)
	{
		e.setWorld(this);
		entities.add(e);
		return e;
	}
	
	public Entity removeEntity(Entity e)
	{
		entities.remove(e);
		return e;
	}
	
	public ArrayList<Entity> getEntitiesByTag(String tag)
	{
		ArrayList<Entity> list = new ArrayList<Entity>();
		
		for(Entity e : entities)
		{
			if(e.getTag() == tag)
				list.add(e);
		}
		
		return list;
	}

	@Override
	public boolean keyDown(int keycode)
	{
		for(Entity e : entities)
		{
			if(e != null)
				e.keyDown(keycode);
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		for(Entity e : entities)
		{
			if(e != null)
				e.keyUp(keycode);
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character){return false;}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button){return false;}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button){return false;}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer){return false;}

	@Override
	public boolean mouseMoved(int screenX, int screenY){return false;}

	@Override
	public boolean scrolled(int amount){return false;}
}
