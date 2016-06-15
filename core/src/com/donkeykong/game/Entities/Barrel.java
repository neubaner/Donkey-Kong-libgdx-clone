package com.donkeykong.game.Entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.donkeykong.game.Assets;

public class Barrel extends Entity
{
	private float velocity = 35f;
	private float gravity = 10f;
	private float maxYVel = 100f;
	
	private TextureRegion graphic = new TextureRegion(Assets.tileset,120,0,16,16);
	
	public Barrel(float x, float y, boolean moveRight)
	{
		super(new Vector2(x,y), new Rectangle(3,0,10,8),"barrel");
		
		if(moveRight)velx = velocity;
		else velx = -velocity;
	}
	
	@Override
	public void update(float dt)
	{
		super.update(dt);
		
		vely -= gravity;
		
		if(vely > maxYVel) vely = maxYVel;
		if(vely < -maxYVel) vely = -maxYVel;
		
		// Vertical collision
		Entity wall = collideWith(pos.x, pos.y+(vely*dt),"solid");
		if(wall != null)
		{
			if( (wall.getHitbox().y + wall.getHitbox().height) < (pos.y + hitbox.y))
			{
				while(collideWith(pos.x,pos.y+Math.signum(vely),"solid") == null)
					pos.y += Math.signum(vely);
				
				vely = 0;
			}
		}
	}

	@Override
	public void draw(Batch batch)
	{
		batch.draw(graphic, pos.x, pos.y, 16, 16);
	}

}
