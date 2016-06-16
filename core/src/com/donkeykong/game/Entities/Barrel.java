package com.donkeykong.game.Entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.donkeykong.game.Assets;
import com.donkeykong.game.Game;

public class Barrel extends Entity
{
	private float velocity = 50f;
	private float gravity = 10f;
	private float maxYVel = 100f;
	
	//Animation
	private float elapsed;
	private Animation walkAnimation;
	
	public Barrel(float x, float y, boolean moveRight)
	{
		super(new Vector2(x,y), new Rectangle(3,0,10,8),"barrel");
		
		if(moveRight)velx = velocity;
		else velx = -velocity;
		
		elapsed = 0;
		walkAnimation = new Animation(1/5f, new TextureRegion(Assets.tileset,136,0,16,16),
											new TextureRegion(Assets.tileset,152,0,16,16),
											new TextureRegion(Assets.tileset,168,0,16,16),
											new TextureRegion(Assets.tileset,184,0,16,16));
	}
	
	@Override
	public void update(float dt)
	{
		super.update(dt);
		elapsed += dt;
		
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
		
		// Change velocity's direction
		if(pos.y > 9)
		{
			if(pos.x + hitbox.x < 0) velx = -velx;
			if(pos.x + hitbox.x + hitbox.width > Game.WIDTH) velx = -velx;
		}
	}

	@Override
	public void draw(Batch batch)
	{
		batch.draw(walkAnimation.getKeyFrame(elapsed, true),pos.x, pos.y);
	}

}
