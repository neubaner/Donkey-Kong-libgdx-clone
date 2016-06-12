package com.donkeykong.game.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.donkeykong.game.Worlds.World;

public class Entity
{
	protected float x, y;
	protected float width, height;
	private String tag;
	protected World world;
	
	public Entity(float x, float y, float width, float height, String tag)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.tag = tag;
	}
	
	public Entity()
	{
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.tag = "";
	}
	
	public void drawHitbox(ShapeRenderer renderer)
	{	
		renderer.setColor(Color.YELLOW);
		renderer.set(ShapeType.Line);
		renderer.rect(x,y,width,height);
	}
	
	public void update(float dt){}
	public void draw(){}
	public void keyUp(int keycode){}
	public void keyDown(int keycode){}
	
	public float 		getX(){return x;}
	public float 		getY(){return y;}
	public float 		getWidth(){return width;}
	public float 		getHeight(){return height;}
	public String 		getTag(){return tag;}
	public Rectangle 	getRect(){return new Rectangle(x,y,width,height);}
	
	public void 		setX(float x){this.x = x;}
	public void 		setY(float y){this.y = y;}
	public void 		setWidth(float width){this.width = width;}
	public void 		setHeight(float height){this.height = height;}
}
