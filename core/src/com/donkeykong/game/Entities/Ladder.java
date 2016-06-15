package com.donkeykong.game.Entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.donkeykong.game.Assets;

public class Ladder extends Entity
{
	private TextureRegion graphic = new TextureRegion(Assets.tileset,0,8,8,8);
	
	public Ladder(float x, float y)
	{
		super(new Vector2(x,y), new Rectangle(0,0,8,8),"ladder");
	}

	@Override
	public void draw(Batch batch)
	{
		batch.draw(graphic, pos.x, pos.y, hitbox.width, hitbox.height);
	}
}
