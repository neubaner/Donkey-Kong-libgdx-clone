package com.donkeykong.game.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.donkeykong.game.Worlds.World;

public class Entity
{
	protected float x, y;
	protected float width, height;
	protected float velx = 0, vely = 0;
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
		renderer.rect(x,y,width,height);
	}
	
	public void update(float dt)
	{
		x += velx * dt;
		y += vely * dt;
	}
	
	public void draw(){}
	public void keyUp(int keycode){}
	public void keyDown(int keycode){}
	
	public float 		getX(){return x;}
	public float 		getY(){return y;}
	public float 		getWidth(){return width;}
	public float 		getHeight(){return height;}
	public String 		getTag(){return tag;}
	public Rectangle 	getRect(){return new Rectangle(x,y,width,height);}
	public float 		getVelx(){return velx;}
	public float 		getVely(){return vely;}

	public void 		setX(float x){this.x = x;}
	public void 		setY(float y){this.y = y;}
	public void 		setWidth(float width){this.width = width;}
	public void 		setHeight(float height){this.height = height;}
	public void 		setVelx(float velx){this.velx = velx;}
	public void 		setVely(float vely){this.vely = vely;}
}
