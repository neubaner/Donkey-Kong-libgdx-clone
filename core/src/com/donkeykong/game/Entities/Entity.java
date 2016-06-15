package com.donkeykong.game.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.donkeykong.game.Worlds.World;

public class Entity
{
	protected Vector2 pos;
	protected Rectangle hitbox;
	protected float velx = 0, vely = 0;
	protected World world;
	public Color hitboxColor = Color.YELLOW;
	private String tag;
	
	
	public Entity(Vector2 position, Rectangle hitbox, String tag)
	{
		this.pos = position;
		this.hitbox = hitbox;
		this.tag = tag;
	}
	
	public Entity()
	{
		this.pos = new Vector2(0,0) ;
		this.hitbox = new Rectangle(0,0,0,0);
		this.tag = "";
	}
	
	public void drawHitbox(ShapeRenderer renderer)
	{	
		renderer.setColor(hitboxColor);
		renderer.rect(hitbox.x + pos.x, hitbox.y + pos.y, hitbox.width, hitbox.height);
	}
	
	public void update(float dt)
	{
		pos.x += velx * dt;
		pos.y += vely * dt;
	}
	
	public boolean collideWith(float x, float y, Entity entity)
	{
		Rectangle oh = new Rectangle(entity.getHitbox());
		oh.x += entity.getX();
		oh.y += entity.getY();
		
		return oh.overlaps(new Rectangle(x + hitbox.x, y + hitbox.y, hitbox.width, hitbox.height));
	}
	
	public Entity collideWith(float x, float y, String tag)
	{	
		for(Entity e : world.getEntitiesByTag(tag))
		{
			if(collideWith(x,y,e))
				return e;
		}
		return null;
	}
	
	public void draw(Batch batch){}
	public void keyUp(int keycode){}
	public void keyDown(int keycode){}
	
	public String 		getTag(){return tag;}
	public Vector2		getPos(){return pos;}
	public float		getX(){return pos.x;}
	public float		getY(){return pos.y;}
	public Rectangle 	getHitbox(){return hitbox;}
	public float 		getVelx(){return velx;}
	public float 		getVely(){return vely;}

	public void			setPos(Vector2 pos){this.pos = pos;}
	public void			setWorld(World world){this.world = world;}
	public void			setX(float x){pos.x = x;}
	public void			setY(float y){pos.y = y;}
	public void			setHitbox(Rectangle hitbox){this.hitbox = hitbox;}
	public void 		setVelx(float velx){this.velx = velx;}
	public void 		setVely(float vely){this.vely = vely;}
}
