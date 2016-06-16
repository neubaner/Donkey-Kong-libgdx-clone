package com.donkeykong.game.Entities;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.donkeykong.game.Assets;
import com.donkeykong.game.Game;
import com.donkeykong.game.JumpmanState;

public class Jumpman extends Entity
{
	private JumpmanState state;
	private float xVelocity;
	private float jumpForce;
	private float gravity;
	private float maxYVel;
	private boolean facingRight;
	
	//Animation
	private float elapsed = 0;
	private Animation walkAnimation;
	private Animation climbingAnimation;
	private TextureRegion jumpingAnimation = new TextureRegion(Assets.tileset,24,0,16,16);
	private TextureRegion standing = new TextureRegion(Assets.tileset,8,0,16,16);
	
	public Jumpman(float x, float y)
	{
		super(new Vector2(x,y),new Rectangle(4,0,9,11), "Jumpman");
		state = JumpmanState.idle; 
		xVelocity = 35;
		jumpForce = 300;
		gravity = 10;
		maxYVel = 100;
		facingRight = true;
		
		walkAnimation = new Animation(1/15f,new TextureRegion(Assets.tileset,24,0,16,16),
											new TextureRegion(Assets.tileset,8,0,16,16),
											new TextureRegion(Assets.tileset,40,0,16,16));
		
		climbingAnimation = new Animation(1/5f, new TextureRegion(Assets.tileset,56,0,16,16),
												 new TextureRegion(Assets.tileset,72,0,16,16));
	}
	
	@Override
	public void update(float dt)
	{
		super.update(dt);
		elapsed += dt;
		
		if(velx > 0)facingRight = true;
		if(velx < 0)facingRight = false;
		
		vely -= gravity;
		
		if(vely > maxYVel) vely = maxYVel;
		if(vely < -maxYVel) vely = -maxYVel;
		
		int sign = 0;
		
		if(state == JumpmanState.idle)
		{
			if(Gdx.input.isKeyPressed(Keys.RIGHT))
				sign++;
			if(Gdx.input.isKeyPressed(Keys.LEFT))
				sign--;
			if(Gdx.input.isKeyPressed(Keys.A))
				jump();
			velx = xVelocity * sign;
			
			// Climb the ladder
			if(collideWith(pos.x,pos.y,"ladder") != null)
			{
				if(Gdx.input.isKeyJustPressed(Keys.UP))
				{
					state = JumpmanState.climbing;
					vely=0;
					velx=0;
				}			
			}
		}
		
		if(state == JumpmanState.climbing)
		{
			sign = 0;
			// Climb the ladder
			if(collideWith(pos.x,pos.y,"ladder") != null || collideWith(pos.x, pos.y,"solid") != null)
			{
				if(Gdx.input.isKeyPressed(Keys.UP))
					sign += 1;
				if(Gdx.input.isKeyPressed(Keys.DOWN))
					sign -= 1;				
			}
			else
			{
				state = JumpmanState.idle;
			}
			vely = xVelocity * sign;
		}
		
		// Vertical collision
		ArrayList<Entity> wallList = world.getEntitiesByTag("solid");
		for(Entity wall : wallList)
		{
			if(collideWith(pos.x, pos.y+(vely*dt),wall))
			{
				if( (wall.getPos().y + wall.getHitbox().y + wall.getHitbox().height) < (pos.y + hitbox.y))
				{
					while(collideWith(pos.x,pos.y+Math.signum(vely),"solid") == null)
						pos.y += Math.signum(vely);
					
					state = JumpmanState.idle;
					vely = 0;
				}
			}
		}
		
		// Horizontal collision
		for(Entity wall : wallList)
		{
			if(collideWith(pos.x+(velx*dt), pos.y, wall))
				if(pos.x + hitbox.x + (hitbox.width/2) > wall.getX() && collideWith(pos.x, pos.y+1, "solid") == null)
					pos.y++;
		}
		
		// Snap to screen
		if(pos.x + hitbox.x < 0) pos.x = -hitbox.x;
		if(pos.x + hitbox.x + hitbox.width > Game.WIDTH) pos.x = Game.WIDTH - hitbox.width - hitbox.x;
		
	}

	@Override
	public void draw(Batch batch)
	{
		if(state == JumpmanState.idle)
		{
			// If he's moving
			if(velx != 0)
				drawJumpman(batch, walkAnimation.getKeyFrame(elapsed,true));
			else
				drawJumpman(batch, standing);
		}
		if(state == JumpmanState.jumping)
		{
			drawJumpman(batch, jumpingAnimation);
		}
		if(state == JumpmanState.climbing)
		{
			// If he's moving
			if(vely != 0)
				drawJumpman(batch, climbingAnimation.getKeyFrame(elapsed,true));
			else
				drawJumpman(batch, climbingAnimation.getKeyFrames()[0]);
		}
	}
	
	private void drawJumpman(Batch batch, TextureRegion graphic)
	{
		batch.draw(graphic,
				   pos.x,
				   pos.y,
				   8,
				   8,
				   16,
				   16,
				   facingRight ? -1 : 1,
				   1,
				   0);
	}

	public void jump()
	{
		vely = jumpForce;
		state = JumpmanState.jumping;
	}
	
	@Override
	public void keyUp(int keycode)
	{
		if(state == JumpmanState.idle)
		{
			if(keycode == Keys.RIGHT)
				velx = 0;
			if(keycode == Keys.LEFT)
				velx = 0;
		}
	}

	@Override
	public void keyDown(int keycode)
	{
		if(state == JumpmanState.idle)
		{
			if(keycode == Keys.RIGHT)
				velx = xVelocity;
			if(keycode == Keys.LEFT)
				velx = -xVelocity;
			if(keycode == Keys.A)
				jump();
		} 
			
	}

}
