package com.donkeykong.game.Entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.donkeykong.game.Assets;

public class Kong extends Entity
{
	private Jumpman jumpman;
	private float rate;
	private float rateMax = 0.1f;
	private float rateMin = 5;
	private float acc = 99999;
	
	//Animation
	private float elapsed = 0;
	private TextureRegion standing = new TextureRegion(Assets.tileset, 48, 16, 48, 32);
	private Animation throwingAnimation = new Animation(1/3f, new TextureRegion(Assets.tileset, 0, 16, 48, 32),
															  new TextureRegion(Assets.tileset, 48, 16, 48, 32),
															  new TextureRegion(Assets.tileset, 96, 16, 48, 32));
	//Barrel graphic
	private TextureRegion barrels = new TextureRegion(Assets.tileset, 144, 16, 24, 32);
	
	public Kong(float x, float y, Jumpman jumpman)
	{
		super(new Vector2(x,y), new Rectangle(0,0,0,0), "");
		this.jumpman = jumpman;
	}
	
	@Override
	public void update(float dt)
	{
		super.update(dt);
		acc += dt;
		rate = (jumpman.getY()/255)*(rateMax-rateMin)+rateMin;
		
		if(acc > rate)
		{
			elapsed+=dt;
			if(throwingAnimation.isAnimationFinished(elapsed))
			{
				world.addEntity(new Barrel(pos.x+35,pos.y+5,true));	
				acc = 0;
				elapsed = 0;
			}
		}
		
	}

	@Override
	public void draw(Batch batch)
	{
		batch.draw(barrels,pos.x-15,pos.y);
		if(elapsed > 0)
			batch.draw(throwingAnimation.getKeyFrame(elapsed,false),pos.x, pos.y);
		else
			batch.draw(standing,pos.x,pos.y);
	}

}
